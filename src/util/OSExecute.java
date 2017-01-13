package util;
import java.util.*;
import java.io.*;
/**
 * @author Jagreet Das Gupta
 */
import java.util.Scanner;
public class OSExecute {
	public static Map.Entry<String,String> getStreams (String command) throws IOException{
		Process process = new ProcessBuilder(command.split(" ")).start();
		BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
		String s;
		while(true){
            s = results.readLine();
            if(s==null) break;
			s1.append(s);
		}
		while(true){
            s = errors.readLine();
            if(s==null) break;
			s2.append(s);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put(s1.toString(), s2.toString());
		Map.Entry<String, String> ent = null;
		for(Map.Entry<String, String> en : map.entrySet())
			ent = en;
		return ent;
		
	}
   public static void execute(String command){
	   try{
	   Map.Entry<String,String> t = getStreams(command);
	   System.out.println(t.getKey());
	   String s = (String)t.getValue();
	   if(s!=null){
		   System.err.println(s);
		   //throw new IOException("Error Occurred While Executing Command");
	   }
	   }
	   catch(IOException e){/*throw new RuntimeException(e);*/e.printStackTrace();}
   }
   public static void main(String[] args)throws IOException{
	   System.out.println("Welcome to OSExecute Software\nDeveloper : Jagreet Das Gupta");
	   String s = "";
	   while(s.trim() != "exit"){
	   System.out.print(">");
	   Scanner in = new Scanner(System.in);
	   s = in.nextLine();
	   execute(s);
	   }
   }
}
