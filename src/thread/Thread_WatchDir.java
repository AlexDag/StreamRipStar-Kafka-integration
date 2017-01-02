package thread;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import misc.Stream;

public class Thread_WatchDir extends Thread{
	private final Stream stream;
    private  WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private final String incompleteName = "incomplete";
    private boolean trace = false;
    private Path pathCreatedIncomplete=null;
    

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }
    private void registerAll(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_MODIFY ,ENTRY_CREATE/*,ENTRY_DELETE*/);
        keys.put(key, dir);
    }
    public Thread_WatchDir( Stream stream_, Path dir) throws IOException {
    	this.stream = stream_;
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        
        registerAll(dir);
    }

    void processEvents() {
         for(;;){ 
        	if( watcher==null)
        		break;;
        	
        	Path child=null;
            WatchKey key=null;
            try {
                key = watcher.take();
             }catch (ClosedWatchServiceException ex){
              	 return;
             }
             catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                 child = dir.resolve(name);
                
                 
                 if ( (kind == ENTRY_CREATE) && child.toString().contains(stream.name) ) {
                     try {
                         if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                             registerAll(child);
                         }
                     } catch (IOException x) {
                     }
                 }
                File file = new File(child.toUri());
                if( child.toString().contains(stream.name) && file.isFile() ){ 
                       if( isCompleted(kind,child) || isIncompletedFinished(kind,child) ){
                       	 Thread_Kafka kafka = new Thread_Kafka(child,stream.name);
                       	 kafka.start();
                       }
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                break;	
            }
         }  
    }

    private boolean isIncompletedFinished(WatchEvent.Kind kind,Path path){
    	if(path.getParent().toString().contains(incompleteName)){
    		if(kind==ENTRY_CREATE){
    			if(pathCreatedIncomplete==null){
    				pathCreatedIncomplete = path;
    				return false;
    			}else
    			{     // return path created for kafka
    				if(!pathCreatedIncomplete.equals(path)){
    					String swap = pathCreatedIncomplete.toString();
    					pathCreatedIncomplete = Paths.get(path.toString());
    					path = Paths.get(swap);
    					return true;
    				}else
    					return false;
    			}
    		}// end entry_create
    		else{/* MODIFY */
    			if(pathCreatedIncomplete==null){
    				pathCreatedIncomplete = path;
    				return false;
    			}
    		}
    		return false;
    	}else
    		return false;
    	
    }
    private boolean isCompleted(WatchEvent.Kind kind,Path path){
    	if(kind ==ENTRY_MODIFY && !path.getParent().toString().contains(incompleteName)){
    		return true;
    	}
    	else
    		return	false;
    }
    
	@Override
	public void run() {
	   while(watcher!=null) {	
		processEvents();
	   }
	  System.out.println(stream.name + " monitor closed  ");
	}

	public void closeWatcherDir(){
		if(watcher!=null)
		try {
			watcher.close();
			watcher = null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
//	private boolean isRippFile(String filename) {
//	
//			if (filename.equalsIgnoreCase(filename)) {
//				return false;
//			}
//				else
//		return true;
//	}
}