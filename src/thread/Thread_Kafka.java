package thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import misc.Constant;

public class Thread_Kafka extends Thread {

	final private Path rippPath;
	final private String rippfile;
	final private String topicName;
	final int Chunk_Size = 1024 * 900;

	public Thread_Kafka(Path path,String name) {
		this.rippPath = path;
		rippfile = getAscii( path.getFileName().toString());
		topicName= getAscii(name);
	}

	@Override
	public void run() {

		File ifile = new File(rippPath.toString());
		FileInputStream fis = null;
		long fileSize = (long) ifile.length();
		int  read = 0;
		int readLength = Chunk_Size;
		byte[] byteChunk;
		try {
			
			fis =    new FileInputStream(ifile);
			while (fileSize > 0) {
				if (fileSize <= Chunk_Size) {
					readLength = (int) fileSize;
				}
				byteChunk = new byte[readLength];
				read = fis.read(byteChunk, 0, readLength);
				fileSize -= read;
				kafkaProducer(topicName, rippfile , byteChunk);
				byteChunk = null;
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(String.format(" rippfile:%s from topic:%s was sent",rippfile,topicName));
			fis = null;
		}
	}


	private void kafkaProducer(String topic, String key, byte[] message) {
		Properties props = new Properties();
		String broker = System.getProperty("kafka_streaming");
		props.put("metadata.broker.list", broker);
		props.put("serializer.class", "kafka.serializer.DefaultEncoder");
		props.put("request.required.acks", "1");
		props.put("producer.type	", "async");
		props.put("key.serializer.class","kafka.serializer.StringEncoder");
		
		if(Constant.trace)
			System.out.println("send to broker:"+broker);
		ProducerConfig config = new ProducerConfig(props);
		Producer<String, byte[]> producer = new Producer<String, byte[]>(config);
				
		if(Constant.trace){
			System.out.println("topic sent: "+topic);
		}
		KeyedMessage<String, byte[]> data = new KeyedMessage<String, byte[]>(
				topic, key, message);
		
		if(Constant.trace){
			System.out.println(String.format(" topic sent :%s key:%s length msg:%d ", topic,key,message.length));
		}
		producer.send(data);
		producer.close();
	}
	
	private String getAscii(String input){
		StringBuilder build = new StringBuilder();
	    char repl = '_';
		for (int i = 0; i < input.length(); i++) {
	        int c = input.charAt(i);
	        if (c > 0x7F) {
	            c=repl;
	        }else{
	        	if(c<48){
	        		c=repl;
	        	}else
	        		if(c>90 && c<97){
	        			c=repl;
	        		}else
	        			if(c>122){
	        				c=repl;
	        			}
	        }
	        build.append((char)c);
	    }
	    return build.toString();
	}
}
