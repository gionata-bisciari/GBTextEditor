package com.gb1498.GBTextEditor.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorActionListener;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorChangeListener;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorKeyAdapter;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorMenuListener;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorMouseAdapter;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorMouseWheelListener;

public class GBTextEditor extends JFrame{
	
	private static final long serialVersionUID = 1018702002621459644L;
	
	public static final String TEMA = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	private static URI uri = null;
	
	private boolean ctrlPressed = false;
	
	private static String buffer;

	private final GBTextEditor frame = this;
	
	private final JMenuBar menuBar = new JMenuBar();
	
	private final JMenu mnFile = new JMenu("File");
	private final JMenu mnInfo = new JMenu("Info");
	private final JMenu mnImpostazioni = new JMenu("Impostazioni");
	private final JMenu mnFont = new JMenu("Font");
	private final JMenu mnModificaGrandezzaCarattere = new JMenu("Modifica grandezza carattere (CTRL+MouseWheel)");
	private final JMenu mnProgrammazione = new JMenu("Programmazione");
	private final JMenu mnSalva = new JMenu("Salva...");
	
	private final JMenuItem mntmNuovo = new JMenuItem("Nuovo");
	private final JMenuItem mntmApri = new JMenuItem("Apri");
	private final JMenuItem mntmSalva = new JMenuItem("Salva");
	private final JMenuItem mntmCambia = new JMenuItem("Cambia");
	private final JMenuItem mntmSitoCreatore = new JMenuItem("Sito creatore");
	private final JMenuItem mntmCompila = new JMenuItem("Compila");
	private final JMenuItem mntmEsegui = new JMenuItem("Esegui");
	private final JMenuItem mntmSalvaConNome = new JMenuItem("Salva con nome");
	private final JMenuItem mntmEsci = new JMenuItem("Esci");
	private final JMenuItem mntmAggiorna = new JMenuItem("Aggiorna");
	private final JSpinner grandezzaFont = new JSpinner();
	
	private final JMenu mnGeneraHelloworld = new JMenu("Genera \"HelloWorld\"");
	private final JMenuItem mntmJava = new JMenuItem("Java");
	private final JMenuItem mntmC = new JMenuItem("C");
	
	private final JMenu popupMenuSalva = new JMenu("Salva...");
	private final JMenuItem popupMenuItemNuovo = new JMenuItem("Nuovo");
	private final JMenuItem popupMenuItemApri = new JMenuItem("Apri");
	private final JMenuItem popupMenuItemSalva = new JMenuItem("Salva");
	private final JMenuItem popupMenuItemSalvaConNome = new JMenuItem("Salva con nome");
	private final JMenuItem popupMenuItemAggiorna = new JMenuItem("Aggiorna");
	
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea numeroRighe = new JTextArea();
	private static final JEditorPane editorPane = new JEditorPane();

	private static final GBTextEditorCmdOutPut out = new GBTextEditorCmdOutPut();
	
	private final GBTextEditorActionListener GBTE_AL = new GBTextEditorActionListener(this);
	private final GBTextEditorMouseAdapter GBTE_MA = new GBTextEditorMouseAdapter(this);
	private final GBTextEditorMouseWheelListener GBTE_MWL = new GBTextEditorMouseWheelListener(this);
	private final GBTextEditorKeyAdapter GBTE_KA = new GBTextEditorKeyAdapter(this);
	private final GBTextEditorMenuListener GBTE_ML = new GBTextEditorMenuListener(this);
	private final GBTextEditorChangeListener GBTE_CL = new GBTextEditorChangeListener(this);
	private final JLabel label = new JLabel("           ");
	private final JPopupMenu popupMenu = new JPopupMenu();
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					GBTextEditor window = new GBTextEditor();
					//window.frmGBTextEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GBTextEditor() {
		if(System.getProperty("os.name").contains("Windows")){
			try {
				UIManager.setLookAndFeel(GBTextEditor.TEMA);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		GBTextEditor.out.setVisible(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String righe = "";
		for(int i=1;i<=10000;i++){
			righe = righe + i +"\n";
		}
	
		getNumeroRighe().setEnabled(false);
		getNumeroRighe().setDisabledTextColor(Color.GRAY);
		getNumeroRighe().setBackground(Color.CYAN);
		getNumeroRighe().setEditable(false);
		getNumeroRighe().setText(righe);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditorIcon.png")));
		this.setMinimumSize(new Dimension(854, 480));
		this.setTitle("GBTextEditor");
		this.setBounds(100, 100, 854, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 1262, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
		);
		
		getNumeroRighe().setToolTipText("Numero righe");
		getScrollPane().setRowHeaderView(getNumeroRighe());
		editorPane.addMouseWheelListener(this.GBTE_MWL);
		GBTextEditor.editorPane.addKeyListener(this.GBTE_KA);
		getScrollPane().setViewportView(editorPane);
		
		addPopup(editorPane, popupMenu);
		this.getContentPane().setLayout(groupLayout);
		
		this.setJMenuBar(menuBar);
		
		mnFile.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnFile.setToolTipText("Operazioni inerenti a file");
		mnFile.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-File-16x16.png")));
		menuBar.add(mnFile);
		
		mntmNuovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNuovo.setToolTipText("Crea un nuovo file (sar\u00E0 chiesto se si desidera salvare o meno il file corrente)");
		mntmNuovo.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-New-16x16.png")));
		mntmNuovo.addActionListener(GBTE_AL);
		mnFile.add(mntmNuovo);
		
		mntmApri.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmApri.setToolTipText("Apri file (sar\u00E0 chiesto se si desidera salvare o meno il file corrente)");
		mntmApri.addActionListener(GBTE_AL);
		mntmApri.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Apri-16x16.png")));
		mnFile.add(mntmApri);
		
		mnSalva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnSalva.setToolTipText("Opzioni di salvataggio");
		mnSalva.addMenuListener(this.GBTE_ML);
		mnFile.add(mnSalva);
		
		mntmSalva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSalva.setToolTipText("Salva il file corrente ");
		mntmSalva.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Salva-1-16x16.png")));
		mntmSalva.addActionListener(this.GBTE_AL);
		mnSalva.add(mntmSalva);
		
		mntmSalvaConNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSalvaConNome.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Salva-2-16x16.png")));
		mntmSalvaConNome.setToolTipText("Salva il file corrente con nome");
		mntmSalvaConNome.addActionListener(this.GBTE_AL);
		mnSalva.add(mntmSalvaConNome);
		
		mntmAggiorna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmAggiorna.setToolTipText("Ricarica il file");
		mntmAggiorna.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Aggiorna-16x16.png")));
		mntmAggiorna.addActionListener(this.GBTE_AL);
		mnFile.add(mntmAggiorna);
		
		mnProgrammazione.setToolTipText("Opzioni per programmatori");
		mnProgrammazione.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Compila-1-16x16.png")));
		menuBar.add(mnProgrammazione);
		
		mntmCompila.setToolTipText("Compila il file");
		mntmCompila.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCompila.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Programming-16x16.png")));
		mntmCompila.addActionListener(this.GBTE_AL);
		//mntmCompila.setEnabled(false);
		mnProgrammazione.add(mntmCompila);
		
		
		mntmEsegui.setToolTipText("Esegui il file compilato");
		mntmEsegui.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmEsegui.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Esegui-16x16.png")));
		mntmEsegui.addActionListener(this.GBTE_AL);
		//mntmEsegui.setEnabled(false);
		mnProgrammazione.add(mntmEsegui);
		mnGeneraHelloworld.setToolTipText("Genera un file sorgente di test \"HelloWorld\"");
		mnGeneraHelloworld.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		mnProgrammazione.add(mnGeneraHelloworld);
		
		mntmJava.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmJava.setToolTipText("Genera un \"HelloWorld\" in linguaggio di programmazione Java");
		mntmJava.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Java-24x24.png")));
		mntmJava.addActionListener(this.GBTE_AL);
		mnGeneraHelloworld.add(mntmJava);
		
		mntmC.setToolTipText("Genera un \"HelloWorld\" in linguaggio di programmazione C");
		mntmC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmC.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-C-16x16.png")));
		mntmC.addActionListener(this.GBTE_AL);
		mnGeneraHelloworld.add(mntmC);
		
		mnImpostazioni.setToolTipText("Impostazioni dell'applicazione");
		mnImpostazioni.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnImpostazioni.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Opzioni-16x16.png")));
		menuBar.add(mnImpostazioni);
		
		mnFont.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnFont.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Font-16x16.png")));
		mnFont.setToolTipText("Opzioni font");
		mnImpostazioni.add(mnFont);
		
		mntmCambia.setEnabled(false);
		mntmCambia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCambia.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Font-1-16x16.png")));
		mntmCambia.setToolTipText("Cambia font dell'editor");
		mnFont.add(mntmCambia);
		
		
		mnModificaGrandezzaCarattere.setToolTipText("Modifica la grandezza del font");
		mnModificaGrandezzaCarattere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnModificaGrandezzaCarattere.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Font-2-16x16.png")));
		mnModificaGrandezzaCarattere.addMouseListener(this.GBTE_MA);
		mnFont.add(mnModificaGrandezzaCarattere);
		
		grandezzaFont.setToolTipText("Grandezza font");
		grandezzaFont.setModel(new SpinnerNumberModel(12, 10, 60, 1));
		grandezzaFont.addChangeListener(this.GBTE_CL);
		grandezzaFont.addMouseWheelListener(this.GBTE_MWL);
		mnModificaGrandezzaCarattere.add(grandezzaFont);
		
		mnModificaGrandezzaCarattere.add(label);
		
		mnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnInfo.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO-16x16.png")));
		mnInfo.setToolTipText("Informazioni sul programma");
		mnInfo.setActionCommand("Info");
		menuBar.add(mnInfo);
		
		mntmSitoCreatore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSitoCreatore.setToolTipText("Sar\u00E0 aperta una finestra nel browser con il sito del creatore");
		mntmSitoCreatore.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-LINK-16x16.png")));
		mntmSitoCreatore.addActionListener(this.GBTE_AL);
		mnInfo.add(mntmSitoCreatore);
		
		mntmEsci.addActionListener(GBTE_AL);
		mntmEsci.setMinimumSize(new Dimension(70, 25));
		mntmEsci.setToolTipText("Chiudi il programma (sarà chiesto se si desidera salvare il file)");
		mntmEsci.setPreferredSize(new Dimension(70, 25));
		mntmEsci.setMaximumSize(new Dimension(70, 25));
		mntmEsci.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-EXIT-16x16.png")));
		mntmEsci.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmEsci);
		
		popupMenuItemNuovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenuItemNuovo.setToolTipText("Crea un nuovo file (sar\u00E0 chiesto se si desidera salvare o meno il file corrente)");
		popupMenuItemNuovo.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-New-16x16.png")));
		popupMenuItemNuovo.addActionListener(GBTE_AL);
		popupMenu.add(this.popupMenuItemNuovo);
		
		popupMenuItemApri.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenuItemApri.setToolTipText("Apri file (sar\u00E0 chiesto se si desidera salvare o meno il file corrente)");
		popupMenuItemApri.addActionListener(GBTE_AL);
		popupMenuItemApri.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Apri-16x16.png")));
		popupMenu.add(this.popupMenuItemApri);
		
		popupMenuItemSalva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenuItemSalva.setToolTipText("Salva il file corrente ");
		popupMenuItemSalva.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Salva-1-16x16.png")));
		popupMenuItemSalva.addActionListener(this.GBTE_AL);
		popupMenuSalva.add(this.popupMenuItemSalva);
		
		popupMenuItemSalvaConNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenuItemSalvaConNome.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Salva-2-16x16.png")));
		popupMenuItemSalvaConNome.setToolTipText("Salva il file corrente con nome");
		popupMenuItemSalvaConNome.addActionListener(this.GBTE_AL);
		popupMenuSalva.add(this.popupMenuItemSalvaConNome);
		
		popupMenuSalva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenuSalva.setToolTipText("Opzioni di salvataggio");
		popupMenuSalva.addMenuListener(this.GBTE_ML);
		popupMenu.add(this.popupMenuSalva);
		
		popupMenuItemAggiorna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenuItemAggiorna.setToolTipText("Ricarica il file");
		popupMenuItemAggiorna.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Aggiorna-16x16.png")));
		popupMenuItemAggiorna.addActionListener(this.GBTE_AL);
		popupMenu.add(this.popupMenuItemAggiorna);
		
		this.setVisible(true);
		
		
	}

	public JMenuItem getMntmAggiorna() {
		return mntmAggiorna;
	}

	/**
	 * @return the temaWindows
	 */
	public String getTemaWindows() {
		return TEMA;
	}

	/**
	 * @return the uri
	 */
	public static URI getUri() {
		return uri;
	}

	/**
	 * @return the frame
	 */
	public GBTextEditor getFrame() {
		return frame;
	}

	/**
	 * @param uri the uri to set
	 */
	public static void setUri(URI uri) {
		GBTextEditor.uri = uri;
	}

	/**
	 * @return the editorpane
	 */
	public static JEditorPane getEditorPane() {
		return editorPane;
	}

	/**
	 * @return the out
	 */
	/*public static CmdOutPut getOut() {
		return out;
	}*/

	/**
	 * @return the mntmNuovo
	 */
	public JMenuItem getMntmNuovo() {
		return mntmNuovo;
	}

	/**
	 * @return the mntmApri
	 */
	public JMenuItem getMntmApri() {
		return mntmApri;
	}

	/**
	 * @return the mntmSalva
	 */
	public JMenuItem getMntmSalva() {
		return mntmSalva;
	}

	/**
	 * @return the mntmCambia
	 */
	public JMenuItem getMntmCambia() {
		return mntmCambia;
	}

	/**
	 * @return the mntmSitoCreatore
	 */
	public JMenuItem getMntmSitoCreatore() {
		return mntmSitoCreatore;
	}

	/**
	 * @return the mntmCompila
	 */
	public JMenuItem getMntmCompila() {
		return mntmCompila;
	}

	/**
	 * @return the mntmEsegui
	 */
	public JMenuItem getMntmEsegui() {
		return mntmEsegui;
	}

	/**
	 * @return the mntmSalvaConNome
	 */
	public JMenuItem getMntmSalvaConNome() {
		return mntmSalvaConNome;
	}

	/**
	 * @return the mntmEsci
	 */
	public JMenuItem getMntmEsci() {
		return mntmEsci;
	}

	/**
	 * @return the numeroRighe
	 */
	public JTextArea getNumeroRighe() {
		return numeroRighe;
	}

	/**
	 * @return the ctrlPressed
	 */
	public boolean isCtrlPressed() {
		return this.ctrlPressed;
	}

	/**
	 * @param ctrlPressed the ctrlPressed to set
	 */
	public void setCtrlPressed(boolean ctrlPressed) {
		this.ctrlPressed = ctrlPressed;
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @return the buffer
	 */
	public static String getBuffer() {
		String string = null;
		if(buffer!=null){
			string = new String(buffer);
		}
		else{
			string = null;
		}
		return string;
	}

	/**
	 * @param buffer the buffer to set
	 */
	public static void setBuffer(String buffer) {
		GBTextEditor.buffer = new String(buffer);
	}
	
	public static void resetBuffer(){
		buffer = null;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the tema
	 */
	public static String getTema() {
		return TEMA;
	}

	/**
	 * @return the menuBar
	 */
	public JMenuBar getMenubar() {
		return menuBar;
	}

	/**
	 * @return the mnFile
	 */
	public JMenu getMnFile() {
		return mnFile;
	}

	/**
	 * @return the mnInfo
	 */
	public JMenu getMnInfo() {
		return mnInfo;
	}

	/**
	 * @return the mnImpostazioni
	 */
	public JMenu getMnImpostazioni() {
		return mnImpostazioni;
	}

	/**
	 * @return the mnFont
	 */
	public JMenu getMnFont() {
		return mnFont;
	}

	/**
	 * @return the mnModificaGrandezzaCarattere
	 */
	public JMenu getMnModificaGrandezzaCarattere() {
		return mnModificaGrandezzaCarattere;
	}

	/**
	 * @return the mnProgrammazione
	 */
	public JMenu getMnProgrammazione() {
		return mnProgrammazione;
	}

	/**
	 * @return the mnSalva
	 */
	public JMenu getMnSalva() {
		return mnSalva;
	}

	/**
	 * @return the gBTE_AL
	 */
	public GBTextEditorActionListener getGBTE_AL() {
		return GBTE_AL;
	}

	/**
	 * @return the gBTE_MA
	 */
	public GBTextEditorMouseAdapter getGBTE_MA() {
		return GBTE_MA;
	}

	/**
	 * @return the gBTE_CPMWL
	 */
	public GBTextEditorMouseWheelListener getGBTE_CPMWL() {
		return GBTE_MWL;
	}

	/**
	 * @return the gBTE_KA
	 */
	public GBTextEditorKeyAdapter getGBTE_KA() {
		return GBTE_KA;
	}

	/**
	 * @return the out
	 */
	public static GBTextEditorCmdOutPut getOut() {
		return out;
	}

	/**
	 * @return the grandezzaFont
	 */
	public JSpinner getGrandezzaFont() {
		return grandezzaFont;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * @return the popupMenuItemNuovo
	 */
	public JMenuItem getPopupMenuItemNuovo() {
		return popupMenuItemNuovo;
	}

	/**
	 * @return the popupMenuItemApri
	 */
	public JMenuItem getPopupMenuItemApri() {
		return popupMenuItemApri;
	}

	/**
	 * @return the popupMenuItemSalva
	 */
	public JMenuItem getPopupMenuItemSalva() {
		return popupMenuItemSalva;
	}

	/**
	 * @return the popupMenuItemSalvaConNome
	 */
	public JMenuItem getPopupMenuItemSalvaConNome() {
		return popupMenuItemSalvaConNome;
	}

	/**
	 * @return the popupMenuItemAggiorna
	 */
	public JMenuItem getPopupMenuItemAggiorna() {
		return popupMenuItemAggiorna;
	}

	/**
	 * @return the popupMenuSalva
	 */
	public JMenu getPopupMenuSalva() {
		return popupMenuSalva;
	}

	/**
	 * @return the mnGeneraHelloworld
	 */
	public JMenu getMnGeneraHelloworld() {
		return mnGeneraHelloworld;
	}

	/**
	 * @return the mntmJava
	 */
	public JMenuItem getMntmJava() {
		return mntmJava;
	}

	/**
	 * @return the mntmC
	 */
	public JMenuItem getMntmC() {
		return mntmC;
	}
	
	public static void resetURI(){
		setUri(null);
	}
	
	public static void resetEditorPane(){
		editorPane.setText("");
	}
}
