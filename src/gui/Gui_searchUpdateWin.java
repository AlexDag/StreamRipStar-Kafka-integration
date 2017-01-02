package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.*;

/* This program is licensed under the terms of the GPL V3 or newer*/
/* Written by Johanes Putzke*/
/* eMail: die_eule@gmx.net*/  

/**
 * The GUI where the user can look for informations about new
 * releases for StreamRipStar
 */
public class Gui_searchUpdateWin extends JDialog
{
	private static final long serialVersionUID = 4165135034469872266L;
	private ResourceBundle trans = ResourceBundle.getBundle("translations.StreamRipStar");
	private JPanel panel = new JPanel();
	private Gui_searchUpdatePanel updatePanel;
	private JButton okButton = new JButton("Beenden");
	
	/**
	 * Create the GUI as an dialog 
	 * 
	 * @param controlStream The controlstream, where this dialog can find the webbrowsser
	 * @param mainWin The parent for this Dialog
	 */
	public Gui_searchUpdateWin(Control_Stream controlStream, JFrame mainWin, boolean quiteSearch) {
		super(mainWin);

		this.setTitle("Check for Updates");
		updatePanel = new Gui_searchUpdatePanel(controlStream, this, quiteSearch);
		panel.setLayout(new GridBagLayout());
		
		//set Constrains defaults
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets( 5, 5, 5, 5);
		c.gridwidth = 3;
		
		c.gridy = 0;
		c.gridx = 0;
		panel.add(updatePanel,c);
		c.gridy = 1;
		panel.add(okButton,c);
		
		okButton.addActionListener(new AbortListener());
		add(panel);
		
		setLanguage();
		
		//set size of window
		//pack together
		this.setSize(600,400);
		//get new dimension of the window
        Dimension frameDim = getSize();
    	
        //get resolution
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        
        //calculates the app. values
        int x = (screenDim.width - frameDim.width)/2;
        int y = (screenDim.height - frameDim.height)/2;
        
        //set location
        setLocation(x, y);
        setLanguage();
        
        //only show if the user wants it
        if(!quiteSearch)
        	setVisible(true);
	}
	
	/**
	 * Update the components with the language specific contents
	 */
	public void setLanguage() {
		try {
			//title of window
			setTitle(trans.getString("searchUpdate.title"));
			okButton.setText(trans.getString("searchUpdate.okButton"));
			
		} catch ( MissingResourceException e ) { 
			SRSOutput.getInstance().logE("Error in translation in Gui_searchUpdate: ");
			e.printStackTrace();
	    }		
	}
	
	/**
	 * Stop this dialog and show the mainwindow
	 * 
	 * @author Johannes Putzke
	 */
	class AbortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
}
