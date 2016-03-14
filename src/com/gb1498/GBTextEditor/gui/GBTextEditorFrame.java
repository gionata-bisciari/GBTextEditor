package com.gb1498.GBTextEditor.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GBTextEditorFrame extends JPanel{

	private static final long serialVersionUID = 1018702002621459644L;
	
	public static URI uri;

	public GBTextEditorFrame frame = this;
	
	private JFrame frmGbtexteditor = new JFrame();
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu mnFile = new JMenu("File");
	
	private JMenuItem mntmNuovo = new JMenuItem("Nuovo");
	private JMenuItem mntmApri = new JMenuItem("Apri");
	private JMenuItem mntmSalva = new JMenuItem("Salva");
	private final JMenu mnHelp = new JMenu("Info");
	private final JMenu mnImpostazioni = new JMenu("Impostazioni");
	private final JMenu mnFont = new JMenu("Font");
	private final JMenuItem mntmCambia = new JMenuItem("Cambia");
	
	private boolean ctrlPressed = false;
	private final JScrollPane scrollPane = new JScrollPane();
	
	private JTextArea numeroRighe = new JTextArea();
	public static JEditorPane editorPane = new JEditorPane();
	private final JMenu mnModificaGrandezzaCarattere = new JMenu("Modifica grandezza carattere (CTRL+MouseWheel)");
	private final JTextField textField = new JTextField();
	private final JLabel lblGrandezzaCarattere = new JLabel("Grandezza carattere:");
	private final JMenuItem mntmSitoCreatore = new JMenuItem("Sito creatore");
	private final JMenu mnCompila = new JMenu("Programmazione");
	private final JMenuItem mntmCompila = new JMenuItem("Compila");
	private final JMenuItem mntmEsegui = new JMenuItem("Esegui");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GBTextEditorFrame window = new GBTextEditorFrame();
					window.frmGbtexteditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GBTextEditorFrame() {
		mnModificaGrandezzaCarattere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textField.setText(editorPane.getFont().getSize()+"");
			}
		});
		mnModificaGrandezzaCarattere.setToolTipText("Modifica lagrandezza del font");
		mnModificaGrandezzaCarattere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		mnModificaGrandezzaCarattere.add(lblGrandezzaCarattere);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int newSize = Integer.parseInt(textField.getText());
				if(newSize>=10&&newSize<=60){
					Font modified = new Font("modified", Font.PLAIN, newSize);
					editorPane.setFont(modified);
					numeroRighe.setFont(modified); 
				}
			}
		});
		mnModificaGrandezzaCarattere.add(textField);
		textField.setToolTipText("Inserisci la grandezza del carattere desiderata e digita INVIO");
		textField.setColumns(10);
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
	
		numeroRighe.setEnabled(false);
		numeroRighe.setDisabledTextColor(Color.GRAY);
		numeroRighe.setBackground(Color.CYAN);
		numeroRighe.setEditable(false);
		this.numeroRighe.setText(righe);
		
		this.frmGbtexteditor.setIconImage(Toolkit.getDefaultToolkit().getImage(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditorIcon.png")));
		this.frmGbtexteditor.setMinimumSize(new Dimension(1280, 720));
		this.frmGbtexteditor.setTitle("GBTextEditor");
		this.frmGbtexteditor.setBounds(100, 100, 1280, 720);
		this.frmGbtexteditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(frmGbtexteditor.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1262, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
		);
		
		numeroRighe.setToolTipText("Numero righe");
		scrollPane.setRowHeaderView(numeroRighe);
		editorPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(ctrlPressed){
					int newSize = editorPane.getFont().getSize()-e.getWheelRotation();
					if(newSize>=10&&newSize<=60){
						Font modified = new Font("modified", Font.PLAIN, newSize);
						editorPane.setFont(modified);
						numeroRighe.setFont(modified); 
					}
				}
				else{
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue()+e.getWheelRotation()*10);
				}
			}
		});
		editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode()==KeyEvent.VK_CONTROL){
					//System.out.println("ctrl p");
					ctrlPressed = true;
				}
				else if((k.getKeyCode()==KeyEvent.VK_S)&&ctrlPressed){
					//System.out.println("ctrl+s");
					salva();
				}
				else if((k.getKeyCode()==KeyEvent.VK_O)&&ctrlPressed){
					//System.out.println("ctrl+o");
					apri();
				}
				else if((k.getKeyCode()==KeyEvent.VK_N)&&ctrlPressed){
					//System.out.println("ctrl+n");
					apri();
				}
			}
			@Override
			public void keyReleased(KeyEvent k) {
				if(k.getKeyCode()==KeyEvent.VK_CONTROL){
					//System.out.println("ctrl r");
					ctrlPressed = false;
				}
			}
		});
		scrollPane.setViewportView(editorPane);
		frmGbtexteditor.getContentPane().setLayout(groupLayout);
		
		frmGbtexteditor.setJMenuBar(menuBar);
		mnFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnFile.setToolTipText("Operazioni inerenti a file");
		mnFile.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-File-16x16.png")));
		menuBar.add(mnFile);
		mntmNuovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNuovo.setToolTipText("Crea un nuovo file (sar\u00E0 chiesto se si desidera salvare o meno il file corrente)");
		mntmNuovo.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-New-16x16.png")));
		mntmNuovo.addMouseListener(new MouseAdapter() {
			//@Override
			public void mousePressed(MouseEvent arg0) {
				if(!controllaEditorVuoto()){
					new AvvisoFileNonSalvato(frame,true);
				}
			}
		});
		mnFile.add(mntmNuovo);
		mntmApri.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmApri.setToolTipText("Apri file (sar\u00E0 chiesto se si desidera salvare o meno il file corrente)");
		mntmApri.addActionListener(this.apri());
		
		mntmApri.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Apri-16x16.png")));
		
		mnFile.add(mntmApri);
		mntmSalva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSalva.setToolTipText("Salva il file corrente ");
		mntmSalva.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Salva-1-16x16.png")));
		mnFile.add(mntmSalva);
		mnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnHelp.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO-16x16.png")));
		mnHelp.setToolTipText("Informazioni sul programma");
		mnHelp.setActionCommand("Info");
		mnCompila.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Compila-1-16x16.png")));
		
		menuBar.add(mnCompila);
		mntmCompila.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					System.out.println(uri.getPath());
					String path = "C:" + uri.getPath();
					System.out.println(path);
					String command = ("C:/Programmi/Java/jdk1.8.0_74/bin/javac.exe " + path + " -d " + (path.substring(0,path.lastIndexOf("/"))));
					Process process = Runtime.getRuntime().exec(command);
					//process.waitFor();
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String linea = br.readLine();
					while(linea!=null){
						System.out.println(linea);
						linea = br.readLine();
					}
					process.waitFor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mntmCompila.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Programming-16x16.png")));
		
		mnCompila.add(mntmCompila);
		mntmEsegui.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Esegui-16x16.png")));
		mntmEsegui.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					System.out.println(uri.getPath());
					String path = "C:" + uri.getPath();
					System.out.println(path);
					String command = ("C:/Programmi/Java/jdk1.8.0_74/bin/java.exe " + path);
					Process process = Runtime.getRuntime().exec(command);
					//process.waitFor();
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String linea = br.readLine();
					while(linea!=null){
						System.out.println(linea);
						linea = br.readLine();
					}
					//process.waitFor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} /*catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		
		mnCompila.add(mntmEsegui);
	
		mnImpostazioni.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnImpostazioni.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Opzioni-16x16.png")));
		
		menuBar.add(mnImpostazioni);
		mnFont.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnFont.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Font-16x16.png")));
		mnFont.setToolTipText("Opzioni font");
		
		mnImpostazioni.add(mnFont);
		mntmCambia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCambia.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Font-1-16x16.png")));
		mntmCambia.setToolTipText("Cambia font dell'editor");
		
		mnFont.add(mntmCambia);
		mnModificaGrandezzaCarattere.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Font-2-16x16.png")));
		
		mnFont.add(mnModificaGrandezzaCarattere);
		
		menuBar.add(mnHelp);
		mntmSitoCreatore.setToolTipText("Sar\u00E0 aperta una finestra nel browser con il sito del creatore");
		mntmSitoCreatore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				final String strTarget = "www.gb1498.com";
	            Desktop desktop = Desktop.getDesktop();
	            try{
	            	if(desktop.isSupported(Desktop.Action.BROWSE)){
	            		URI uri = new URI(strTarget);
	            		desktop.browse(uri);
	            	}
	            }
	            catch(URISyntaxException g){
	            	g.printStackTrace();
	            }
	            catch(IOException g){
	            	g.printStackTrace();
	            }
			}
		});
		mntmSitoCreatore.setIcon(new ImageIcon(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-LINK-16x16.png")));
		
		mnHelp.add(mntmSitoCreatore);
		
		mntmSalva.addActionListener(this.salva());
	}

	public JEditorPane getEditorPane(){
		return GBTextEditorFrame.editorPane;
	}

	public Component getContentPane() {
		return this.getContentPane();
	}
	public static void resetEditorPane(){
		editorPane.setText("");
	}
	
	public static boolean controllaEditorVuoto(){
		boolean vuoto = false;
		if(GBTextEditorFrame.editorPane.getText().equals("")){
			vuoto = true;
		}
		return vuoto;
	}
	
	public void controllaSalva(){
		if(!controllaEditorVuoto()){
			new AvvisoFileNonSalvato(this,true);
		}
	}
	
	public FileBrowserSalva salva(){
		return new FileBrowserSalva(this,false);
	}
	
	public FileBrowserApri apri(){
		return new FileBrowserApri(this);
	}
}
