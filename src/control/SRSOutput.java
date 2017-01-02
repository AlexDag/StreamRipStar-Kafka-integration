package control;

/* This program is licensed under the terms of the GPLV3 or newer*/
/* Written by Johannes Putzke*/
/* eMail: die_eule@gmx.net*/ 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import misc.StreamRipStar;

/**
 * @author Johannes Putzke
 * With this class you get all Methods to log our outputs
 */
public class SRSOutput 
{
	
	public static enum LOGLEVEL{Nothing,Error,Normal,Debug};
	private int logLevel = 1;			// 1 means loglevel = normal
	private SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd '-' HH:mm:ss ");
	private static SRSOutput instance = null;
	private BufferedWriter writer = null;
	private String path = "";
	private Vector<String> tmpMessages = new Vector<String>(0,1);
	private boolean hasInitDone = false;
	
	/**
	 * This Constructor initialize the outputstream writer an
	 */
	private SRSOutput()
	{
		
	}
	
	/**
	 * initialize the log file and make it writable
	 */
	private void init() 
	{
		try 
		{
			Boolean append = true;  	// append messages to the log file -> default yes
			path = new Control_GetPath().getStreamRipStarPath() + "/output.log";
			
			//delete here the log file first, if it has a large size
			File logFile = new File(path);
			
			//if its larger then ~0.5MB, overwrite it
			if(logFile.length() > 500000)
			{
				append = false;
			}
			
			
			writer = new BufferedWriter(new FileWriter(path,append));
			log("StreamRipStar in version "+StreamRipStar.releaseVersion+
					" and revision "+ StreamRipStar.releaseRevision + " has been started");
			
			//print out the old messages
			log("\n\n=== now the old messages from start ====\n\n");
			for(int i=0; i < tmpMessages.capacity(); i++)
			{
				log(tmpMessages.get(i));
			}
			log("\n\n=== old messages done! ====\n\n");
			
			//now delete all old messages for the next time
			tmpMessages.removeAllElements();
			tmpMessages.setSize(0);
			tmpMessages.trimToSize();
			
			//say we have init done successful 
			hasInitDone = true;
		}
		catch (IOException e) 
		{
			System.err.println("Error in SRSOutput: Can't open the file for StreamRipStar");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error in SRSOutput: Unkown error");
			e.printStackTrace();
		}
	}
	
	/**
	 * initalizie the log file and make it writeable
	 */
	private void deInit() 
	{
		try 
		{
			writer.close();
			hasInitDone= false;
		}
		catch (IOException e) 
		{
			System.err.println("Error in SRSOutput: Can't close writer: "+path);
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the object from this class. If no one exist, one will created from this method.
	 * @return The log-object
	 */
	public static SRSOutput getInstance()
	{
		if(instance == null)
		{
			instance = new SRSOutput();
		}
		
		if(!instance.hasInitDone)
		{
			instance.init();
		}
		
		return instance;
	}
	
	
	/**
	 * Set the loglevel for this session. All logs with higher priority will
	 * be logged, too. If a wrong value is given, the loglevel normal is used
	 * 
	 * @param loglevel the maximum loglevel
	 */
	public void setLoglevel(LOGLEVEL loglevel)
	{
		if(loglevel == LOGLEVEL.Nothing)
		{
			logLevel = -1;
		}
		
		else if(loglevel == LOGLEVEL.Error)
		{
			logLevel = 0;
		}
		
		else if(loglevel == LOGLEVEL.Normal)
		{
			logLevel = 1;
		}
		
		else if(loglevel == LOGLEVEL.Debug)
		{
			logLevel = 2;
		}
		
		else
		{
			logLevel = 1;
		}
	}
	
	/**
	 * get the loglevel for this session. All logs with higher priority will
	 * be logged, too. If a wrong value is given, the loglevel normal is used
	 * 
	 * @return the current loglevel
	 */
	public LOGLEVEL getLoglevel()
	{
		if(logLevel == -1)
		{
			return LOGLEVEL.Nothing;
		}
		
		else if(logLevel == 0)
		{
			return LOGLEVEL.Error;
		}
		
		else if(logLevel == 1)
		{
			return LOGLEVEL.Normal;
		}
		
		else if(logLevel == 2)
		{
			return LOGLEVEL.Debug;
		}
		
		else
		{
			return LOGLEVEL.Normal;
		}
	}
	
	
	/**
	 * Normal log to output file and to the console. The message to the log file
	 * gets the current date+time and the message-format (here normal output)
	 * 
	 * @param logMessage The message you want to log
	 * @return true, if everything worked. Else false
	 */
	public synchronized boolean log(String logMessage)
	{
		if(logLevel >= 1) {
			try {
				//first print to console
				System.out.println(logMessage);
				
				//then to the file
				writer.write(formatter.format(new Date())
										+":\n"+logMessage+"\n\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	

	/**
	 * Error log to output file and to the console. The message to the log file
	 * gets the current date+time and the message format (here Error output)
	 * 
	 * @param logMessage The error message you want to log
	 * @return true, if everything worked. Else false
	 */
	public synchronized boolean logE(String logMessage)
	{
		if(logLevel >= 0) {
			try {
				//first print to console
				System.err.println("Error: "+logMessage);
				
				//then to the file
				writer.write("Error: "+formatter.format(new Date())
										+":\n"+logMessage+"\n\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Debug log to output file and to the console. The message to the log file
	 * gets the current date+time and the message format (here Debug output)
	 * 
	 * @param logMessage The error message you want to log
	 * @return true, if everything worked. Else false
	 */
	public synchronized boolean logD(String logMessage)
	{
		if(logLevel >= 2) {
			try {
				//first print to console
				System.out.println("Debug: "+logMessage);
				
				//then to the file
				writer.write("Debug: "+formatter.format(new Date())
										+":\n"+logMessage+"\n\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
			return true;
	}
	
	/**
	 * Deletes the log file and all messages 
	 */
	public synchronized void deleteLogFile()
	{
		deInit();
		try {
			File outPutFile = new File(path);
			outPutFile.delete();
		} catch (NullPointerException e) {
			logTemp("SRSOutput: NullpointerException while deleting the old log file");
		}
		
		init();
		
		//noch die Fehler abfangen
	}
	
	/**
	 * prints a message to stderr and save it in a temporary vector until a log-object
	 * is created. After this, the messages are automatically printed out
	 */
	public static synchronized void logTemp(String logMessage) 
	{
		//test if we have a instance with we can work with
		if(instance == null)
		{
			instance = new SRSOutput();
		}
		//first print to console
		System.err.println("Error: "+logMessage);
		instance.tmpMessages.add(logMessage);
	}
	
}
