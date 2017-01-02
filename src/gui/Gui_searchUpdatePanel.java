package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import thread.SearchUpdate;
import control.*;

/* This program is licensed under the terms of the GPL V3 or newer*/
/* Written by Johanes Putzke*/
/* eMail: die_eule@gmx.net*/  

/**
 * The GUI where the user can look for informations about new
 * releases for StreamRipStar
 */
public class Gui_searchUpdatePanel extends JPanel {
	private static final long serialVersionUID = 4165135034469872266L;
	private ResourceBundle trans = ResourceBundle.getBundle("translations.StreamRipStar");
	private Control_Stream controlStream;
	private SearchUpdate searchUpdate;
	private Gui_searchUpdateWin updateWin;
	private ImageIcon loadingIcon = new ImageIcon((URL)getClass().getResource("/Icons/update_loading.png"));
	private ImageIcon okIcon = new ImageIcon((URL)getClass().getResource("/Icons/update_ok.png"));
	private ImageIcon availableIcon = new ImageIcon((URL)getClass().getResource("/Icons/update_available.png"));
	private ImageIcon failIcon = new ImageIcon((URL)getClass().getResource("/Icons/update_fail.png"));
	private JPanel panel = new JPanel();
	private JLabel iconLabel = new JLabel(loadingIcon);
	private JLabel infoLabel = new JLabel("Searching for new Version. Please wait...");
	private boolean quiteSearch = false;

	
	/**
	 * Create the GUI as an dialog 
	 * 
	 * @param controlStream The controlstream, where this dialog can find the webbrowsser
	 * @param mainWin The parent for this Dialog
	 */
	public Gui_searchUpdatePanel(Control_Stream controlStream, boolean quiteSearch) {
		super();
		this.controlStream = controlStream;
		this.quiteSearch = quiteSearch;
		
		init();
	}
	
	public Gui_searchUpdatePanel(Control_Stream controlStream, Gui_searchUpdateWin updateWin, boolean quiteSearch) {
		super();
		this.controlStream = controlStream;
		this.quiteSearch = quiteSearch;
		this.updateWin = updateWin;
		
		init();
	}
	
	private void init()
	{

		searchUpdate = new SearchUpdate(this,this.quiteSearch);

		panel.setLayout(new GridBagLayout());
		
		//set Constrains defaults
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets( 5, 5, 5, 5);
		c.gridwidth = 3;
		
		c.gridy = 0;
		c.gridx = 0;
		panel.add(iconLabel,c);
		c.gridy = 1;
		c.insets = new Insets( 10, 5, 15, 5);
		panel.add(infoLabel,c);

		add(panel);
		

		searchUpdate.start();
	}
	
	/**
	 * Set the Dialog with the information, the the user knows, that this
	 * version of StreamRipStar is the newst
	 */
	public void setAllOk() {
		iconLabel.setIcon(okIcon);
		infoLabel.setText(trans.getString("searchUpdate.infoLabel_alreadyNewest"));
	}
	
	/**
	 * Show the user, that it has a newer version, that the server knows
	 */
	public void setNewVersion() {
		iconLabel.setIcon(okIcon);
		infoLabel.setText(trans.getString("searchUpdate.infoLabel_newer"));
	}
	
	/**
	 * Show all information about the new release.  
	 * @param revision The new revision (integer value)
	 * @param version The name of the version (e.g. StreamRipStar 0.6 Alpha 1)
	 * @param url The url to the download of this version
	 */
	public void setNewVersionAvailable(String revision, String version, String url) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets( 5, 5, 5, 5);
		c.gridwidth = 3;
		
		iconLabel.setIcon(availableIcon);
		infoLabel.setText(trans.getString("searchUpdate.infoLabel_foundNewVersion"));
		JTextField downloadTF = new JTextField(url,20);
		JTextField versionTF = new JTextField(version);
		JTextField revisionTF = new JTextField(revision);
		
		downloadTF.setEditable(false);
		versionTF.setEditable(false);
		revisionTF.setEditable(false);
		
		downloadTF.addMouseListener(new WebsiteListener(url));
		iconLabel.addMouseListener(new WebsiteListener(url));
		
		c.gridwidth = 1;
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(new JLabel(trans.getString("searchUpdate.version")),c);
		c.gridx = 1;
		panel.add(versionTF,c);	
		c.gridx = 0;
		c.gridy = 3;
		panel.add(new JLabel(trans.getString("searchUpdate.revision")),c);
		c.gridx = 1;
		panel.add(revisionTF,c);
		c.gridx = 0;
		c.gridy = 4;
		panel.add(new JLabel(trans.getString("searchUpdate.download")),c);
		c.gridx = 1;
		panel.add(downloadTF,c);
		
		//if the window should only be visible at this point, try to make it visible
		if(quiteSearch && updateWin != null)
		{
			updateWin.setVisible(true);
		}
	}
	
	/**
	 * If something did wrong (e.g. no connection to the internet),
	 * show it to the user
	 */
	public void setFailedToFetchInformation() {
		iconLabel.setIcon(failIcon);
		infoLabel.setText(trans.getString("searchUpdate.infoLabel_failedToLoad"));
	}

	
	/**
	 * Is executed when the user clicks on the download
	 * 
	 * @author Johannes Putzke
	 */
	class WebsiteListener extends MouseAdapter {
		
		String url;
		
		/**
		 * Need the url where the download is as a parameter
		 * @param url The url to the website you like to open
		 */
		public WebsiteListener(String url) {
			this.url = url;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			controlStream.startWebBrowser(url);
		}
	}
}
