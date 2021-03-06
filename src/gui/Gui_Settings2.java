package gui;
/* This program is licensed under the terms of the GPL V3 or newer*/
/* Written by Johannes Putzke*/
/* eMail: die_eule@gmx.net*/ 

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import control.Control_GetPath;
import control.Control_TestStartFirst;
import control.SRSOutput;

public class Gui_Settings2 extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JList<Object> list;
	
	private ImageIcon commonPrefIcon = new ImageIcon((URL)getClass().getResource("/Icons/preferences/LookAndFeel1.png"));
	private ImageIcon pathPrefIcon = new ImageIcon((URL)getClass().getResource("/Icons/preferences/AudioAndPrograms1.png"));
	private ImageIcon audioPlayerPrefIcon = new ImageIcon((URL)getClass().getResource("/Icons/preferences/Language1.png"));
	private ImageIcon udpatePrefIcon = new ImageIcon((URL)getClass().getResource("/Icons/preferences/update.png"));
	
	private OnlyOneActivePanel mainPanel = new OnlyOneActivePanel();
	private JScrollPane mainSP = new JScrollPane(mainPanel);
	
	//pathAudioPanel = pathPanel + audioPanel
	private JPanel pathAudioPanel = new JPanel();
	private JPanel pathPanel = new JPanel();
	private JPanel internalaudioPanel = new JPanel();
	
	//lookAndFeelPanel = look and feel
	private JPanel lookAndFeelPanel = new JPanel();
	private JPanel sysTrayIconPanel = new JPanel();
	private JPanel otherLookAndFeelPanel = new JPanel(); 
	private JPanel actionPanel = new JPanel();
	private JPanel programStartPanel = new JPanel();
	
	//langLogPanel = language + log panel
	private JPanel langLogPanel = new JPanel();
	private JPanel languagePanel = new JPanel();
	private JPanel logPanel = new JPanel();
	
	//update Panel
	private JPanel updatePanel = new JPanel();
	private JPanel autoUpdatePanel = new JPanel();
//	private JPanel searchUpdatePanel = new JPanel();
	private JPanel searchUpdatePanel;
	
	private JPanel buttonPanel = new JPanel();
	private JPanel commonPanel = new JPanel();

	
	private JTextField ripperPathField = new JTextField("",30) ;
	private JTextField shoutcastPlayer = new JTextField("",30) ;
	private JTextField generellPathField = new JTextField("",30) ;
	private JTextField fileBrowserField = new JTextField("",30) ;
	private JTextField webBrowserField = new JTextField("",30) ;
	
	private ImageIcon findIcon = new ImageIcon((URL)getClass().getResource("/Icons/open_small.png"));
	private ImageIcon saveAndExitIcon = new ImageIcon((URL)getClass().getResource("/Icons/ok_small.png"));
	private ImageIcon saveIcon = new ImageIcon((URL)getClass().getResource("/Icons/save_small.png"));
	private ImageIcon abortIcon = new ImageIcon((URL)getClass().getResource("/Icons/abort_small.png"));
	
	private JLabel ripLabel = new JLabel("Path to streamripper: ");
	private JLabel mediaPlayer = new JLabel("Path to mp3 player: ");
	private JLabel generellPathLabel = new JLabel("Generell Save : ");
	private JLabel fileBrowserLabel = new JLabel("Path to filemanager");
	private JLabel webBrowserLabel = new JLabel("Path to webbrowser");
	private JLabel reqRestart =  new JLabel("Chances require programmrestart");
	private JLabel explainActionLabel = new JLabel("What to to, when doubleclicking on a Field: ");
	private JLabel statusLabel= new JLabel("Status :");
	private JLabel nameLabel= new JLabel("Name :");
	private JLabel currentTrackLabel = new JLabel("Current Track: ");
	private JLabel windowClosing = new JLabel("Action when clicking on closing window");
	private JLabel lnfLabel = new JLabel("All installed Look and Feels");
	private JLabel logLabel = new JLabel("How much do you want to log?");
	
	private JTextArea translationTA = new JTextArea();
	private JScrollPane translationSP = new JScrollPane(translationTA);
	
	private JButton abortButton = new JButton("Abort", abortIcon);
	private JButton saveAndExitButton = new JButton ("OK", saveAndExitIcon);
	private JButton saveButton = new JButton("Save", saveIcon);
	private JButton browseRipper = new JButton(findIcon);
	private JButton browseMP3Player = new JButton(findIcon);
	private JButton browseGenerellPath = new JButton(findIcon);
	private JButton browseFileBrowserPath = new JButton(findIcon);
	private JButton browseWebBrowserPath = new JButton(findIcon);

	private JFileChooser dirChooser;

	private String[] languages = {"English","German", "System", "French"};
	private String[] actions = {"none","Open Browser","edit Stream","start/stop", "play Stream"};
	private String[] windowActions = {"do nothing", "Exit StreamRipStar", "Send in Systemtray"};
	private String[] logLevel = {"Nothing", "Error", "Normal", "Everything"};
	private String[] lookAndFeelList;
	
	private String lookAndFeelBox_className = null;
	
	private JComboBox<String> langMenu = new JComboBox<String>(languages);
	private JComboBox<String> statusBox = new JComboBox<String> (actions);
	private JComboBox<String> nameBox = new JComboBox<String> (actions);
	private JComboBox<String> currentTrackBox = new JComboBox<String> (actions);
	private JComboBox<String> windowActionBox = new JComboBox<String> (windowActions);
	private JComboBox<String> LookAndFeelBox = new JComboBox<String> ();
	private JComboBox<String> logLevelBox = new JComboBox<String> (logLevel);
	
	private boolean activeTrayIconTMP = false;
	private JCheckBox activeTrayIcon = new JCheckBox("Show Systemtray (requires restart)");
	private JCheckBox showTextCheckBox = new JCheckBox("Show Text under Icons",true);
	private JCheckBox useInternalAudioPlayerCB = new JCheckBox("Use internal check box (Requires gstreamer installed");
	private JCheckBox useAnotherLnfBox = new JCheckBox("Use another Look and Feel");
	private JCheckBox startInSystemTrayCB = new JCheckBox("Start in system tray (minimized)");
	private JCheckBox checkForUpdatesOnStartCB = new JCheckBox("Automatically check for updates on start",true);
	private JCheckBox hideUpateWindowCB = new JCheckBox("Hide update window until a new update is available",true);
	
	private TitledBorder sysTrayTabTitle = BorderFactory.createTitledBorder("System Tray Icon");
	private TitledBorder lookAndFeelTabTitle = BorderFactory.createTitledBorder("Look And Feel");
	private TitledBorder actionsTabTitle = BorderFactory.createTitledBorder("Actions On Columns");
	private TitledBorder languageTabTitle = BorderFactory.createTitledBorder("Language");
	private TitledBorder logTabTitle = BorderFactory.createTitledBorder("Logging");
	private TitledBorder internalAudioTitle = BorderFactory.createTitledBorder("Audio");
	private TitledBorder pathTitle = BorderFactory.createTitledBorder("Path To Programs");
	private TitledBorder programStartTitle = BorderFactory.createTitledBorder("Path To Programs");
	private TitledBorder autoSearchUpdateTitle = BorderFactory.createTitledBorder("Auto update configuration");
	private TitledBorder searchNowUpdatesTitle = BorderFactory.createTitledBorder("Search for updates");
	
	private UIManager.LookAndFeelInfo[] lookAndFeelInfos;

	private ResourceBundle trans = ResourceBundle.getBundle("translations.StreamRipStar");
	private Gui_StreamRipStar mainGui = null;

	public Gui_Settings2(Gui_StreamRipStar mainGui)
	{
		super(mainGui, "Preferences");
		this.mainGui = mainGui;
		this.setModalityType(ModalityType.APPLICATION_MODAL);
	
		Object elements[][] = {
				{"Look And Feel",commonPrefIcon},
				{"Audio and Programs",pathPrefIcon},
				{"Language and Log",audioPlayerPrefIcon},
				{"Updates",udpatePrefIcon}};
		
		list = new JList<Object>(elements);
		list.setCellRenderer(new IconCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	    list.setVisibleRowCount(-1);
	    list.addMouseListener(new ClickOnListListener());
	   
		//create rest of components at runtime
		lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
		lookAndFeelList = new String[lookAndFeelInfos.length];
		
		for(int i=0; i < lookAndFeelInfos.length; i++)
		{
			lookAndFeelList[i] = lookAndFeelInfos[i].getName();
		}
		
		LookAndFeelBox = new JComboBox<String>(lookAndFeelList);

		translationTA.setEditable(false);
		
		searchUpdatePanel = new Gui_searchUpdatePanel(mainGui.getControlStream(), false);
		
		//pack the basic layout
		setLayout(new BorderLayout());
		add(new JScrollPane(list), BorderLayout.WEST);
		add(mainSP, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//Set Layouts for JPanels
		pathAudioPanel.setLayout(new GridBagLayout());
		pathPanel.setLayout(new GridBagLayout());
		internalaudioPanel.setLayout(new GridBagLayout());
		lookAndFeelPanel.setLayout(new GridBagLayout());
		sysTrayIconPanel.setLayout(new GridBagLayout());
		otherLookAndFeelPanel.setLayout(new GridBagLayout());
		actionPanel.setLayout(new GridBagLayout());
		programStartPanel.setLayout(new GridBagLayout());
		langLogPanel.setLayout(new GridBagLayout());
		languagePanel.setLayout(new GridBagLayout());
		logPanel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());
		commonPanel.setLayout(new GridBagLayout());
		updatePanel.setLayout(new GridBagLayout());
		autoUpdatePanel.setLayout(new GridBagLayout());
		searchUpdatePanel.setLayout(new GridBagLayout());
		
		//set borders
		sysTrayIconPanel.setBorder(sysTrayTabTitle);
		otherLookAndFeelPanel.setBorder(lookAndFeelTabTitle);
		actionPanel.setBorder(actionsTabTitle);
		programStartPanel.setBorder(programStartTitle);
		languagePanel.setBorder(languageTabTitle);
		logPanel.setBorder(logTabTitle);
		internalaudioPanel.setBorder(internalAudioTitle);
		pathPanel.setBorder(pathTitle);
		autoUpdatePanel.setBorder(autoSearchUpdateTitle);
		searchUpdatePanel.setBorder(searchNowUpdatesTitle);
		
		
		//now pack them together
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets( 1, 1, 1, 1);
		c.anchor = GridBagConstraints.PAGE_START;
		

		
//TAB 1: lookAndFeelPanel
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0;
		c.weightx = 1;
		lookAndFeelPanel.add(sysTrayIconPanel,c);
		c.gridy = 1;
		lookAndFeelPanel.add(otherLookAndFeelPanel,c);
		c.gridy = 2;
		lookAndFeelPanel.add(actionPanel,c);
		c.gridy = 3;
		lookAndFeelPanel.add(programStartPanel,c);
		c.gridy = 4;
		c.weighty = 1;
		lookAndFeelPanel.add(new JLabel(""),c);
		
		//TAB 1 - Panel 1: sysTrayIconPanel 
			c.gridy = 0;
			c.gridx = 0;
			c.weighty = 0;
			sysTrayIconPanel.add(activeTrayIcon,c);
			c.gridy = 1;
			sysTrayIconPanel.add(windowClosing,c);
			c.gridy = 2;
			c.gridx = 1;
			c.gridwidth=2;
			sysTrayIconPanel.add(windowActionBox,c);
			
		//TAB 1 - Panel 2: otherLookAndFeelPanel
			c.gridy = 0;
			c.gridx = 0;
			c.weightx = 1;
			c.weighty = 0;
			c.gridwidth=2;
			otherLookAndFeelPanel.add(useAnotherLnfBox,c);
			c.weightx = 0;
			c.gridy = 1;
			c.gridwidth=1;
			otherLookAndFeelPanel.add(lnfLabel,c);
			c.gridx = 1;
			otherLookAndFeelPanel.add(LookAndFeelBox,c);
			c.gridy = 2;
			c.gridx = 0;
			otherLookAndFeelPanel.add(showTextCheckBox,c);
			
		//TAB 1 - Panel 3: actionPanel
			//1. Line: explain what you are doing
			c.insets = new Insets( 5, 5, 10, 5);
			c.weightx = 0.0;
			c.gridy = 0;
			c.gridx = 0;
			c.gridwidth = 7;
			actionPanel.add(explainActionLabel,c);
			c.gridx = 0;
			c.weightx = 1;
			actionPanel.add(new JLabel(""),c);
			//2. Line: click on status
			c.insets = new Insets( 2, 30, 2, 5);
			c.weightx = 0;
			c.gridwidth = 1;
			c.gridy = 1;
			c.gridx = 0;
			actionPanel.add(statusLabel,c);
			c.gridx = 1;
			actionPanel.add(statusBox,c);
			//3. Line: click on Name
			c.gridy = 2;
			c.gridx = 0;
			actionPanel.add(nameLabel,c);
			c.gridx = 1;
			actionPanel.add(nameBox,c);
			//4. Line: click on current Track
			c.gridy = 3;
			c.gridx = 0;
			actionPanel.add(currentTrackLabel,c);
			c.gridx = 1;
			actionPanel.add(currentTrackBox,c);
			
		//TAB 1 - Panel 4: program start Panel
			c.insets = new Insets( 5, 5, 10, 5);
			c.weightx = 1.0;
			c.gridy = 0;
			c.gridx = 0;
			programStartPanel.add(startInSystemTrayCB);
			c.gridx = 1;
			c.weightx = 1;
			programStartPanel.add(new JLabel(""),c);
			
//TAB 2: Path and Audio	
		c.insets = new Insets( 1, 1, 1, 1);
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0;
		pathAudioPanel.add(internalaudioPanel,c);
		
		c.weightx = 0;
		c.gridy = 1;
		pathAudioPanel.add(pathPanel,c);
		c.gridy = 2;
		c.weighty = 1;
		pathAudioPanel.add(new JLabel(""),c);

		//TAB 2 - Panel 1: Internal Audio
			c.gridy = 0;
			c.gridx = 0;
			c.weighty = 0;
			c.gridwidth = 3;
			c.insets = new Insets( 1, 1, 4, 1);
			internalaudioPanel.add(useInternalAudioPlayerCB,c);
			c.insets = new Insets( 1, 1, 1, 1);
			c.gridwidth = 1;
			c.gridy = 1;
			c.gridx = 0;
			internalaudioPanel.add(mediaPlayer,c);
			c.gridx = 1;
			c.weightx = 1.0;
			internalaudioPanel.add(shoutcastPlayer,c);
			c.gridx = 2;
			c.weightx = 0.0;
			internalaudioPanel.add(browseMP3Player,c);
			
		//TAB 2 - Panel 2: Paths
			//1. line: Path to streamripper
			c.gridy = 0;
			c.gridx = 0;
			c.weighty = 0;
			pathPanel.add(ripLabel,c);
			c.gridx = 1;
			c.weightx = 1.0;
			pathPanel.add(ripperPathField,c);
			c.gridx = 2;
			c.weightx = 0.0;
			pathPanel.add(browseRipper,c);
			//3. line: path to general save path for the stream
			c.gridy = 1;
			c.gridx = 0;
			pathPanel.add(generellPathLabel,c);
			c.gridx = 1;
			c.weightx = 1.0;
			pathPanel.add(generellPathField,c);
			c.gridx = 2;
			c.weightx = 0.0;
			pathPanel.add(browseGenerellPath,c);
			//4. line: path ot webbrowser
			c.gridy = 2;
			c.gridx = 0;
			pathPanel.add(webBrowserLabel,c);
			c.gridx = 1;
			c.weightx = 1.0;
			pathPanel.add(webBrowserField,c);
			c.gridx = 2;
			c.weightx = 0.0;
			pathPanel.add(browseWebBrowserPath,c);
			//5. line: path ot fielbrowser
			c.gridy = 3;
			c.gridx = 0;
			pathPanel.add(fileBrowserLabel,c);
			c.gridx = 1;
			c.weightx = 1.0;		
			pathPanel.add(fileBrowserField,c);
			c.gridx = 2;
			c.weightx = 0.0;
			pathPanel.add(browseFileBrowserPath,c);
		
		
		
//TAB 3: Language and Logging
		c.gridy = 0;
		c.weighty = 0;
		langLogPanel.add(languagePanel,c);
		c.gridy = 1;
		c.weightx = 1.0;
		langLogPanel.add(logPanel,c);
		c.gridy = 2;
		c.weighty = 1;
		langLogPanel.add(new JLabel(""),c);
		
		//TAB 1 - Panel 1: languagePanel
			//1. line on lang: langchange
			c.weightx = 0.0;
			c.gridy = 0;
			c.gridx = 0;
			languagePanel.add(langMenu, c);
			c.weightx = 1.0;
			c.gridx = 1;
			languagePanel.add(new JLabel(""), c);
			//2. line on lang: langchange
			c.weightx = 0.0;
			c.gridy = 1;
			c.gridx = 0;
			c.gridwidth = 2;
			languagePanel.add(reqRestart,c);
			//3. line on lang: langchange
			c.weightx = 0.0;
			c.gridy = 2;
			c.gridx = 0;
			c.gridwidth = 2;
			languagePanel.add(new JLabel(" "),c);
			//4. line on lang: langchange
			c.weightx = 0.0;
			c.weighty = 0.0;
			c.gridy = 3;
			c.gridx = 0;
			c.gridwidth = 2;
			languagePanel.add(translationSP,c);
		
		//TAB 1 - Panel 1: Logpanel
			c.gridy = 0;
			c.gridx = 0;
			c.gridwidth = 1;
			logPanel.add(logLabel,c);
			c.gridx = 1;
			c.weightx = 1.0;
			logPanel.add(logLevelBox,c);

//TAB 4: Updates
			c.insets = new Insets( 15, 5, 5 ,5 );
			c.gridy = 0;
			c.weighty = 0;
			updatePanel.add(autoUpdatePanel,c);
			c.gridy = 1;
			c.weightx = 1.0;
			updatePanel.add(searchUpdatePanel,c);
			c.gridy = 2;
			c.weighty = 1;
			updatePanel.add(new JLabel(""),c);
			
	// autoUpdatePanel
			c.insets = new Insets( 5, 5, 0 ,5 );
			c.gridy = 0;
			c.weighty = 0;
			autoUpdatePanel.add(checkForUpdatesOnStartCB,c);
			c.insets = new Insets( 0, 5, 5 ,5 );
			c.gridy = 1;
			c.weighty = 0;
			autoUpdatePanel.add(hideUpateWindowCB,c);
			
	//BUTTONPANEL
		c.insets = new Insets( 5, 5, 5 ,5 );
		c.weightx = 0;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 1;
		buttonPanel.add(saveAndExitButton,c);
		c.gridx = 1;
		buttonPanel.add(saveButton,c);
		c.weightx = 1.0;
		c.gridx = 2;
		buttonPanel.add(new JLabel(""),c);
		c.weightx = 0.0;
		c.gridx = 3;
		buttonPanel.add(abortButton,c);

		dirChooser = new JFileChooser();
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		abortButton.addActionListener(new ExitListener());
		saveButton.addActionListener(new SaveListener());
		saveAndExitButton.addActionListener(new SaveAndExitListener());
		
		browseMP3Player.addActionListener(new MP3Listener());
		browseRipper.addActionListener(new RipperPathListener());
		browseGenerellPath.addActionListener(new BrowseListener());
		browseWebBrowserPath.addActionListener(new WebBrowserListener());
		browseFileBrowserPath.addActionListener(new FileBrowserListener());
		activeTrayIcon.addActionListener(new ChangeTrayFields());
		useAnotherLnfBox.addActionListener(new ChangeTrayFields());
		useInternalAudioPlayerCB.addActionListener(new ChangeTrayFields());
		startInSystemTrayCB.addActionListener(new StartProgrammChangeListener());
		
		//set new language
		setLanguage();
		//load the old settings from file and set the right components active
		load();
		//look for the right index in combobox, if the old value was != null
		if(lookAndFeelBox_className != null) {
			for(int i=0; i < lookAndFeelInfos.length; i++) {
				if(lookAndFeelInfos[i].getClassName().equals(lookAndFeelBox_className)) {
					LookAndFeelBox.setSelectedIndex(i);
					break;
				}
			}
		}
		
		statusBox.setSelectedIndex(4);
		nameBox.setSelectedIndex(4);
		currentTrackBox.setSelectedIndex(4);
		
		//set default panel when start
		mainPanel.add(lookAndFeelPanel);
		list.setSelectedIndex(0);
		 
		repaintCommon();

		//get new dimension of the window
        Dimension frameDim = new Dimension(900,550);
        setSize(frameDim);
    	
        //get resolution
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        
        //calculates the app. values
        int x = (screenDim.width - frameDim.width)/2;
        int y = (screenDim.height - frameDim.height)/2;
        
        //set location
        
        setLocation(x, y);
		setVisible(true);
		
        //escape for exit
        KeyStroke escStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        //register all Strokes
        getRootPane().registerKeyboardAction(new ExitListener(), escStroke,
                JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	public void setLanguage() {
		try {
			//title of window
			setTitle(trans.getString("pref"));

			//general panel
			activeTrayIcon.setText(trans.getString("showSysTray"));
			showTextCheckBox.setText(trans.getString("showTextUnderIcons"));
			useInternalAudioPlayerCB.setText(trans.getString("settings.useInternalAudioPlayer"));
			windowClosing.setText(trans.getString("actionX"));
			windowActionBox.removeAllItems();
			windowActionBox.addItem(trans.getString("X.doNothing"));
			windowActionBox.addItem(trans.getString("X.Exit"));
			windowActionBox.addItem(trans.getString("X.inTray"));

			//action panel , I don't know how to do it easier
			explainActionLabel.setText(trans.getString("whenClickAction"));
			actions[0]=trans.getString("X.doNothing");
			actions[1]=trans.getString("action.OpenBrowser");
			actions[2]=	trans.getString("action.editStream");
			actions[3]=	trans.getString("action.startStop");
			actions[4]=	trans.getString("action.playStream");
			
			statusBox.removeAllItems();
			statusBox.addItem(actions[0]);
			statusBox.addItem(actions[1]);
			statusBox.addItem(actions[2]);
			statusBox.addItem(actions[3]);
			statusBox.addItem(actions[4]);
			
			nameBox.removeAllItems();
			nameBox.addItem(actions[0]);
			nameBox.addItem(actions[1]);
			nameBox.addItem(actions[2]);
			nameBox.addItem(actions[3]);
			nameBox.addItem(actions[4]);
			
			currentTrackBox.removeAllItems();
			currentTrackBox.addItem(actions[0]);
			currentTrackBox.addItem(actions[1]);
			currentTrackBox.addItem(actions[2]);
			currentTrackBox.addItem(actions[3]);
			currentTrackBox.addItem(actions[4]);
			
			logLevelBox.removeAllItems();
			logLevel[0] = trans.getString("Settings.LogString.Nothing");
			logLevel[1] = trans.getString("Settings.LogString.Error");
			logLevel[2] = trans.getString("Settings.LogString.Normal");
			logLevel[3] = trans.getString("Settings.LogString.Everything");
			logLevelBox.addItem(logLevel[0]);
			logLevelBox.addItem(logLevel[1]);
			logLevelBox.addItem(logLevel[2]);
			logLevelBox.addItem(logLevel[3]);
			
			//path panel
			ripLabel.setText(trans.getString("pathStreamripper"));
			mediaPlayer.setText(trans.getString("pathToMp3Player"));
			generellPathLabel.setText(trans.getString("genSavePath"));
			fileBrowserLabel.setText(trans.getString("filebrowserPath"));
			webBrowserLabel.setText(trans.getString("webBrowserPath"));
			
			//languages panel
			reqRestart.setText(trans.getString("reqRestart"));
			translationTA.setText(trans.getString("settings.translationTA"));

			
			//buttons
			abortButton.setText(trans.getString("abortButton"));
			saveAndExitButton.setText(trans.getString("okButton"));
			saveButton.setText(trans.getString("save"));
			
			//labels
			statusLabel.setText(trans.getString("status"));
			nameLabel.setText(trans.getString("streamname"));
			currentTrackLabel.setText(trans.getString("curTitle"));
			lnfLabel.setText(trans.getString("Settings.lnfLabel"));
			useAnotherLnfBox.setText(trans.getString("Settings.useAnotherLnfBox"));
			startInSystemTrayCB.setText(trans.getString("Settings.startInSystemTray"));
			logLabel.setText(trans.getString("Settings.logLabel"));
			
			sysTrayTabTitle.setTitle(trans.getString("Settings.title.SysTray"));
			lookAndFeelTabTitle.setTitle(trans.getString("Settings.title.LookAndFeel"));
			actionsTabTitle.setTitle(trans.getString("Settings.title.Actions"));
			languageTabTitle.setTitle(trans.getString("Settings.title.Language"));
			logTabTitle.setTitle(trans.getString("Settings.title.Log"));
			internalAudioTitle.setTitle(trans.getString("Settings.title.InternalAudio"));
			pathTitle.setTitle(trans.getString("Settings.title.Paths"));
			programStartTitle.setTitle(trans.getString("Settings.title.ProgramStart"));
			
			autoSearchUpdateTitle.setTitle(trans.getString("Settings.Updates.Header.AutoUpdate"));
			searchNowUpdatesTitle.setTitle(trans.getString("Settings.Updates.Header.UpdateSearch"));
			checkForUpdatesOnStartCB.setText(trans.getString("Settings.Updates.CheckStartup"));
			hideUpateWindowCB.setText(trans.getString("Settings.Updates.HideUpdateWindow"));
			
			Object elements[][] = {
					{trans.getString("Settings.Panel.LookAndFeel"),commonPrefIcon},
					{trans.getString("Settings.Panel.AudioAndPrograms"),pathPrefIcon},
					{trans.getString("Settings.Panel.LanguageAndLog"),audioPlayerPrefIcon},
					{trans.getString("Settings.Panel.Update"),udpatePrefIcon}};
			list.setListData(elements);

		} catch ( MissingResourceException e ) { 
			SRSOutput.getInstance().logE("Error in Translation for the Settings: \n"+e.getStackTrace().toString());
	    }		
	}
	
	private Gui_Settings2 gimme() {
		return this;
	}

	public class WebBrowserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int i = dirChooser.showOpenDialog(gimme());
			if(i == JFileChooser.APPROVE_OPTION) {
				webBrowserField.setText(dirChooser.getSelectedFile().toString());
			}
		}
	}
	
	public class FileBrowserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int i = dirChooser.showOpenDialog(gimme());
			if(i == JFileChooser.APPROVE_OPTION) {
				fileBrowserField.setText(dirChooser.getSelectedFile().toString());
			}
		}
	}
	
	
	public class RipperPathListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int i = dirChooser.showOpenDialog(gimme());
			if(i == JFileChooser.APPROVE_OPTION) {
				ripperPathField.setText(dirChooser.getSelectedFile().toString());
			}
		}
	}
	
	
	public class MP3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int i = dirChooser.showOpenDialog(gimme());
			if(i == JFileChooser.APPROVE_OPTION) {
				shoutcastPlayer.setText(dirChooser.getSelectedFile().toString());
			}
		}
	}
	
	public class BrowseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int i = dirChooser.showOpenDialog(gimme());
			if(i == JFileChooser.APPROVE_OPTION) {
				generellPathField.setText(dirChooser.getSelectedFile().toString());
			}
		}
	}
	
	public class SaveAndExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			save();
			dispose();
		}
	}
	
	public class SaveListener implements ActionListener	{
		public void actionPerformed(ActionEvent e) {
			save();
		}
	}
	
	/**
	 * Saves all preferences in an xml file:
	 * 	file "Settings-StreamRipStar.xml" 
	 *
	 */
	public void save() {
		String savePath =  new Control_GetPath().getStreamRipStarPath();
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance(); 
		
		try {
			XMLEventWriter writer = outputFactory.createXMLEventWriter(
					new FileOutputStream(savePath+"/Settings-StreamRipStar.xml" ) );
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			
			//header for the file
			XMLEvent header = eventFactory.createStartDocument();
			XMLEvent startRootSettings = eventFactory.createStartElement( "", "", "Settings" );
			
			XMLEvent checkForUpdatesOnStartCB_S = eventFactory.createAttribute( "checkForUpdatesOnStartCB",  String.valueOf(checkForUpdatesOnStartCB.isSelected()));
			XMLEvent hideUpateWindowCB_S = eventFactory.createAttribute( "hideUpateWindowCB",  String.valueOf(hideUpateWindowCB.isSelected()));
			XMLEvent startInSystTrayCB_S = eventFactory.createAttribute( "startInSysTray",  String.valueOf(startInSystemTrayCB.isSelected()));
			XMLEvent activeIconCB_S = eventFactory.createAttribute( "activeTrayIcon",  String.valueOf( activeTrayIcon.isSelected()));
			XMLEvent lookAndFeelCB_S = eventFactory.createAttribute( "useAnotherLnfBox",  String.valueOf( useAnotherLnfBox.isSelected()));
			XMLEvent showTextCB_S = eventFactory.createAttribute( "showTextCB", String.valueOf( showTextCheckBox.isSelected()) );
			XMLEvent useInternalAudioPlayerCB_S = eventFactory.createAttribute( "useInternalAudioPlayerCB", String.valueOf( useInternalAudioPlayerCB.isSelected()) );
			XMLEvent ripperPathTF_S = eventFactory.createAttribute( "ripperPathTF", ripperPathField.getText()); 
			XMLEvent shoutcastTF_S = eventFactory.createAttribute( "shoutcastTF", shoutcastPlayer.getText()); 
			XMLEvent generellPathTF_S = eventFactory.createAttribute( "generellPathTF", generellPathField.getText()); 
			XMLEvent fileBrowserTF_S = eventFactory.createAttribute( "fileBrowserTF", fileBrowserField.getText()); 
			XMLEvent webBrowserTF_S = eventFactory.createAttribute( "webBrowserTF", webBrowserField.getText()); 
			XMLEvent statusBox_index = eventFactory.createAttribute( "statusBox_index", String.valueOf( statusBox.getSelectedIndex())); 
			XMLEvent nameBox_inde = eventFactory.createAttribute( "nameBox_index", String.valueOf( nameBox.getSelectedIndex())); 
			XMLEvent currentTrackBox_index = eventFactory.createAttribute( "currentTrackBox_index", String.valueOf( currentTrackBox.getSelectedIndex())); 
			XMLEvent langMenu_index = eventFactory.createAttribute( "langMenu_index", String.valueOf( langMenu.getSelectedIndex()));
			XMLEvent windowActionBox_index = eventFactory.createAttribute( "windowActionBox_index",  "1"); 
			if(activeTrayIcon.isSelected()) {
				windowActionBox_index = eventFactory.createAttribute( "windowActionBox_index",  String.valueOf( windowActionBox.getSelectedIndex())); 
			}
			XMLEvent lookAndFeel_index = eventFactory.createAttribute( "LookAndFeelBox_className",  "null"); 
			if(useAnotherLnfBox.isSelected()) {
				lookAndFeel_index = eventFactory.createAttribute( "LookAndFeelBox_className",lookAndFeelInfos[LookAndFeelBox.getSelectedIndex()].getClassName());
			}
			XMLEvent logLevel_index = eventFactory.createAttribute( "logLevel_index",  String.valueOf(logLevelBox.getSelectedIndex())); 
			XMLEvent endRoot = eventFactory.createEndElement( "", "", "Settings" ); 
			XMLEvent endDocument = eventFactory.createEndDocument();
			
			//finally write into file
			writer.add( header ); 
			writer.add( startRootSettings );
			writer.add( checkForUpdatesOnStartCB_S );
			writer.add( hideUpateWindowCB_S );
			writer.add( activeIconCB_S );
			writer.add( startInSystTrayCB_S );
			writer.add( lookAndFeelCB_S ); 
			writer.add( showTextCB_S ); 
			writer.add( useInternalAudioPlayerCB_S ); 
			writer.add( ripperPathTF_S ); 
			writer.add( shoutcastTF_S ); 
			writer.add( generellPathTF_S ); 
			writer.add( fileBrowserTF_S ); 
			writer.add( webBrowserTF_S ); 
			writer.add( statusBox_index ); 
			writer.add( nameBox_inde ); 
			writer.add( currentTrackBox_index ); 
			writer.add( langMenu_index ); 
			writer.add( windowActionBox_index ); 
			writer.add( lookAndFeel_index );
			writer.add( logLevel_index );
			writer.add( endRoot ); 
			writer.add( endDocument ); 
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} 
		
		String[] path = new String[5];
		path[0] = ripperPathField.getText();
		path[1] = shoutcastPlayer.getText();
		path[2] = generellPathField.getText();
		path[3] = webBrowserField.getText();
		path[4] = fileBrowserField.getText();
		
		String newlnfClassName = null;
		if(useAnotherLnfBox.isSelected()) {
			newlnfClassName = lookAndFeelInfos[LookAndFeelBox.getSelectedIndex()].getClassName();
		}
		
		int[] actions = new int[4];
		actions[0] = statusBox.getSelectedIndex();
		actions[1] = nameBox.getSelectedIndex();
		actions[2] = currentTrackBox.getSelectedIndex();
		actions[3] = windowActionBox.getSelectedIndex();
		
		mainGui.setNewRuntimePrefs(actions,showTextCheckBox.isSelected(),activeTrayIcon.isSelected(),
				newlnfClassName,useInternalAudioPlayerCB.isSelected(), checkForUpdatesOnStartCB.isSelected(),
				hideUpateWindowCB.isSelected());
		mainGui.getControlStream().setPaths(path);
		
		//update the log Level
		int level = logLevelBox.getSelectedIndex();
		
		switch(level) 
		{
			case 0:
				SRSOutput.getInstance().setLoglevel(SRSOutput.LOGLEVEL.Nothing);
				break;
			case 1:
				SRSOutput.getInstance().setLoglevel(SRSOutput.LOGLEVEL.Error);
				break;
			case 2:
				SRSOutput.getInstance().setLoglevel(SRSOutput.LOGLEVEL.Normal);
				break;
			case 3:
				SRSOutput.getInstance().setLoglevel(SRSOutput.LOGLEVEL.Debug);
				break;
			default:
				SRSOutput.getInstance().setLoglevel(SRSOutput.LOGLEVEL.Normal);	
		}
		
	}
	
	/**
	 * load the settings from xml file
	 * and set checkboxes, textfields etc. correct
	 */
	public void load() {
		String loadPath =  new Control_GetPath().getStreamRipStarPath();
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance(); 
			XMLStreamReader parser;
			parser = factory.createXMLStreamReader( new FileInputStream(loadPath+"/Settings-StreamRipStar.xml" ) );
			while ( parser.hasNext() ) { 
	 
				switch ( parser.getEventType() ) { 
					case XMLStreamConstants.START_DOCUMENT: 
						SRSOutput.getInstance().log( "Loading file Settings-StreamRipStar.xml" ); 
						break; 
				 
				    case XMLStreamConstants.END_DOCUMENT: 
				    	SRSOutput.getInstance().log( "End of read settings " ); 
				    	parser.close(); 
				    	break; 
				 
				    case XMLStreamConstants.START_ELEMENT: 
				    	for ( int i = 0; i < parser.getAttributeCount(); i++ ) {
				    		if(parser.getAttributeLocalName( i ).equals("activeTrayIcon")) {
				    			activeTrayIcon.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("checkForUpdatesOnStartCB")) {
				    			checkForUpdatesOnStartCB.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("hideUpateWindowCB")) {
				    			hideUpateWindowCB.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("showTextCB")) {
				    			showTextCheckBox.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("startInSysTray")) {
				    			startInSystemTrayCB.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("useInternalAudioPlayerCB")) {
				    			useInternalAudioPlayerCB.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("ripperPathTF")) {
				    			ripperPathField.setText(parser.getAttributeValue(i));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("shoutcastTF")) {
				    			shoutcastPlayer.setText(parser.getAttributeValue(i));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("generellPathTF")) {
				    			generellPathField.setText(parser.getAttributeValue(i));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("fileBrowserTF")) {
				    			fileBrowserField.setText(parser.getAttributeValue(i));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("webBrowserTF")) {
				    			webBrowserField.setText(parser.getAttributeValue(i));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("statusBox_index")) {
				    			statusBox.setSelectedIndex(Integer.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("nameBox_index")) {
				    			nameBox.setSelectedIndex(Integer.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("currentTrackBox_index")) {
				    			currentTrackBox.setSelectedIndex(Integer.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("langMenu_index")) {
				    			langMenu.setSelectedIndex(Integer.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("windowActionBox_index")) {
				    			windowActionBox.setSelectedIndex(Integer.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("useAnotherLnfBox")) {
				    			useAnotherLnfBox.setSelected(Boolean.valueOf(parser.getAttributeValue(i)));
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("LookAndFeelBox_className")) {
				    			String tmp = parser.getAttributeValue(i);
				    			if(tmp.equals("null")) {
				    				lookAndFeelBox_className = null;
				    			} else {
				    				lookAndFeelBox_className = parser.getAttributeValue(i);
				    			}
				    		}
				    		else if (parser.getAttributeLocalName( i ).equals("logLevel_index")) {
				    			this.logLevelBox.setSelectedIndex(Integer.valueOf(parser.getAttributeValue(i)));
				    		}
		
				    	}
				    	break; 
				 
				    default: 
				    	break; 
				  }
				parser.next(); 
			}

		} catch (FileNotFoundException e) {
			SRSOutput.getInstance().logE("No configuartion file found: Settings-StreamRipStar.xml");
			fillWithFoundPrograms();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	public void fillWithFoundPrograms() {
		String[][] programms = new Control_TestStartFirst().searchPrograms();
		
		if(programms != null) {
			ripperPathField.setText(programms[3][0]);
			shoutcastPlayer.setText(programms[2][0]);
			webBrowserField.setText(programms[1][0]);
			fileBrowserField.setText(programms[0][0]);
		}
	}


	public void repaintCommon() {
		if(activeTrayIcon.isSelected()) {
			windowActionBox.setEnabled(true);
			windowClosing.setEnabled(true);
		}
		else {
			windowActionBox.setEnabled(false);
			windowClosing.setEnabled(false);
			//set status: close when click on x
			windowActionBox.setSelectedIndex(1);
			startInSystemTrayCB.setSelected(false);
		}
		
		if(useAnotherLnfBox.isSelected()) {
			LookAndFeelBox.setEnabled(true);
			lnfLabel.setEnabled(true);
		} else {
			LookAndFeelBox.setEnabled(false);
			lnfLabel.setEnabled(false);
		}
		
		if(this.useInternalAudioPlayerCB.isSelected()){
			mediaPlayer.setEnabled(false);
			shoutcastPlayer.setEnabled(false);
			browseMP3Player.setEnabled(false);
		} else {
			mediaPlayer.setEnabled(true);
			shoutcastPlayer.setEnabled(true);
			browseMP3Player.setEnabled(true);
		}
	}
	
	/**
	 * return the own object
	 * @return the own object
	 */
	public Gui_Settings2 getMe() {
		return this;
	}
	
	/**
	 * only hide the window
	 */
	public class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	

	public class ChangeTrayFields implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaintCommon();
		}
	}
	
	public class StartProgrammChangeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(startInSystemTrayCB.isSelected())
			{
				activeTrayIconTMP = activeTrayIcon.isSelected();
				activeTrayIcon.setSelected(true);
				repaintCommon();
			} else {
				activeTrayIcon.setSelected(activeTrayIconTMP);
				startInSystemTrayCB.setSelected(false);
				repaintCommon();
			}
		}
	}
	
	public class ClickOnListListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			if(list != null & list.getSelectedIndex() > -1)
			{
				switch (list.getSelectedIndex())
				{
					case 0:	//common preferences are selected
						mainPanel.add(lookAndFeelPanel);
						break;
					case 1:
						mainPanel.add(pathAudioPanel);
						break;
					case 2:
						mainPanel.add(langLogPanel);
						break;
					case 3:
						mainPanel.add(updatePanel);
						break;
					default:
						mainPanel.add(lookAndFeelPanel);
				}
			}
		}
		
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	private class IconCellRenderer implements ListCellRenderer
	{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus)
		{
			Icon icon = null;
			String theText = null;

			JLabel label = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
					isSelected, cellHasFocus);

			if (value instanceof Object[]) {
				Object values[] = (Object[]) value;
				theText = (String) values[0];
				icon = (Icon) values[1];
			}

			label.setText(theText);
			label.setIcon(icon);
			return label;
		}
	}
	
	private class OnlyOneActivePanel extends JPanel
	{
		private static final long serialVersionUID = -2917467613922489238L;
		
		public JPanel activePanel = null;
		
		public OnlyOneActivePanel()
		{
			super();
			init();
		}
		
		private void init()
		{
			setLayout(new BorderLayout());
		}
		
		public void add(JPanel newPanel)
		{
			if(activePanel != null)
			{
				this.remove(activePanel);
			}
			activePanel = newPanel;
			super.add(newPanel, BorderLayout.CENTER);
			this.repaint();
			this.updateUI();
		}
	}

}
