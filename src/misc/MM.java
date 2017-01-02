package misc;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MM {

	String s = "init";
	MM (String str){
		
		System.out.println("1:"+s);
		s = get(str);
		
	}
	
	
	String get(String s){
		return s.substring(0, 4);
	}
	
	private Path get(Path path ){
		return Paths.get("C:\\main\\get");
	}
	
	
	public static void main(String[] args) {
		Path path = Paths.get("from main");
		
		System.out.println(""+path.toString());
		
		//Paths.get(" from get "+path.toString());
		
		MM m = new MM(path.toString());
		
		path = m.get(path);
		
		System.out.println(""+path.toString());
		System.out.println("2:"+m.s);
	}

}
