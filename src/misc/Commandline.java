package misc;
/* This program is licensed under the terms of the GPLV3 or newer*/
/* Written by Johanes Putzke*/
/* eMail: die_eule@gmx.net*/  

/**
 * Contains all information from command line arguments. If also extract all 
 * information from the args and give some useful default values.
 * @author Johannes Putzke
 */
public class Commandline
{
	boolean tmpEnableSystray = false;
	boolean tmpStartInSystray = false;
	boolean printHelpMessage = false;
	
	public Commandline(String[] args)
	{
		for(int i=0; i < args.length; i++)
		{
			if(args[i].equals("--help") || args[i].equals("-h"))
			{
				printHelpMessage = true;
				printHelpMessage();
			} 
			
			else if(args[i].toLowerCase().equals("-startinsystray"))
			{
				tmpStartInSystray = true;
				tmpEnableSystray = true;
			}
			
			else if(args[i].toLowerCase().equals("-enablesystray"))
			{
				tmpEnableSystray = true;
			}
		}
	}
	
	public boolean isTmpEnableSystray() {
		return tmpEnableSystray;
	}

	public void setTmpEnableSystray(boolean tmpEnableSystray) {
		this.tmpEnableSystray = tmpEnableSystray;
	}

	public boolean isTmpStartInSystray() {
		return tmpStartInSystray;
	}

	public void setTmpStartInSystray(boolean tmpStartInSystray) {
		this.tmpStartInSystray = tmpStartInSystray;
	}
	
	/**
	 * If the user wants to see the command line args help, then this
	 * variable is true.
	 * @return true if -h or --help was used, else false
	 */
	public boolean requestHelpMessage()
	{
		return printHelpMessage;
	}
	
	public void printHelpMessage()
	{
		System.out.println("" +
"StreamRipStar command-line help message. You have the following options: \n" +
"All options are temoraray and does not change the regular settings\n" +
"\n"+
"    -h; --help                Print this help message\n" +
"    -enableSysTray            Enables the system tray\n"+
"    -startInSysTray           Start StreamRipStar in system tray (hidden, enables the system tray)\n" +
"\n" +
"Unkown or wrong command line arguments will be ignored.");
	}
}
