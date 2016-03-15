package com.gb1498.GBTextEditor.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorActionListener;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorContentPaneMouseWheelListener;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorKeyAdapter;
import com.gb1498.GBTextEditor.gui.listeners.GBTextEditorMouseAdapter;

public class GBTextEditor extends JFrame{
	
	private static final long serialVersionUID = 1018702002621459644L;
	
	private static final String TEMA = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	private static URI uri = null;
	
	private boolean ctrlPressed = false;

	private final GBTextEditor frame = this;
	
	private final JMenuBar menuBar = new JMenuBar();
	
	private final JMenu mnFile = new JMenu("File");
	private final JMenu mnInfo = new JMenu("Info");
	private final JMenu mnImpostazioni = new JMenu("Impostazioni");
	private final JMenu mnFont = new JMenu("Font");
	private final JMenu mnModificaGrandezzaCarattere = new JMenu("Modifica grandezza carattere (CTRL+MouseWheel)");
	private final JMenu mnProgrammazione = new JMenu("Programmazione");
	private final JMenu mnSalva = new JMenu("Salva");
	
	private final JMenuItem mntmNuovo = new JMenuItem("Nuovo");
	private final JMenuItem mntmApri = new JMenuItem("Apri");
	private final JMenuItem mntmSalva = new JMenuItem("Salva");
	private final JMenuItem mntmCambia = new JMenuItem("Cambia");
	private final JMenuItem mntmSitoCreatore = new JMenuItem("Sito creatore");
	private final JMenuItem mntmCompila = new JMenuItem("Compila");
	private final JMenuItem mntmEsegui = new JMenuItem("Esegui");
	private final JMenuItem mntmSalvaConNome = new JMenuItem("Salva con nome");
	private final JMenuItem mntmEsci = new JMenuItem("Esci");
	private final JLabel lblGrandezzaCarattere = new JLabel("Grandezza carattere:");
	private final JTextField txtGrandezzaFont = new JTextField();
	
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea numeroRighe = new JTextArea();
	private static final JEditorPane editorPane = new JEditorPane();

	//private static final CmdOutPut out = new CmdOutPut();
	
	private final GBTextEditorActionListener GBTE_AL = new GBTextEditorActionListener(this);
	private final GBTextEditorMouseAdapter GBTE_MA = new GBTextEditorMouseAdapter(this);
	private final GBTextEditorContentPaneMouseWheelListener GBTE_CPMWL = new GBTextEditorContentPaneMouseWheelListener(this);
	private final GBTextEditorKeyAdapter GBTE_KA = new GBTextEditorKeyAdapter(this);
	
	
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
		try {
			UIManager.setLookAndFeel(GBTextEditor.TEMA);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//GBTextEditor.out.setVisible(false);
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
		getContentPane().addMouseWheelListener(this.GBTE_CPMWL);
		/*editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode()==VK_CONTROL){
					ctrlPressed = true;
				}
				else if(k.getKeyCode()==)
			}
		});*/
		GBTextEditor.editorPane.addKeyListener(this.GBTE_KA);
		getScrollPane().setViewportView(editorPane);
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
		
		mnProgrammazione.setToolTipText("Opzioni per programmatori");
		mnProgrammazione.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Compila-1-16x16.png")));
		menuBar.add(mnProgrammazione);
		
		mntmCompila.setToolTipText("Compila il file");
		mntmCompila.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCompila.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Programming-16x16.png")));
		mntmCompila.addActionListener(this.GBTE_AL);
		mnProgrammazione.add(mntmCompila);
		
		
		mntmEsegui.setToolTipText("Esegui il file compilato");
		mntmEsegui.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmEsegui.setIcon(new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Esegui-16x16.png")));
		mntmEsegui.addActionListener(this.GBTE_AL);
		mnProgrammazione.add(mntmEsegui);
		
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
		mnModificaGrandezzaCarattere.add(lblGrandezzaCarattere);
		mnModificaGrandezzaCarattere.addMouseListener(this.GBTE_MA);
		mnFont.add(mnModificaGrandezzaCarattere);
		
		txtGrandezzaFont.setToolTipText("Inserisci la grandezza del carattere desiderata e digita INVIO");
		txtGrandezzaFont.setColumns(10);
		txtGrandezzaFont.addActionListener(this.GBTE_AL);
		mnModificaGrandezzaCarattere.add(txtGrandezzaFont);
		
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
		this.setVisible(true);
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
	public static JEditorPane getEditorpane() {
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
	 * @return the txtGrandezzaFont
	 */
	public JTextField getTxtGrandezzaFont() {
		return txtGrandezzaFont;
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
}
