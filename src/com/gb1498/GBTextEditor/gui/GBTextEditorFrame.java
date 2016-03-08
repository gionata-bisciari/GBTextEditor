package com.gb1498.GBTextEditor.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GBTextEditorFrame extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1018702002621459644L;

	//private GBTextEditor txtEditor = new GBTextEditor(this);

	private JFrame frmGbtexteditor = new JFrame();;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnFile = new JMenu("File");
	private JMenuItem mntmNuovo = new JMenuItem("Nuovo");
	private JMenuItem mntmApri = new JMenuItem("Apri");
	private JMenuItem mntmSalva = new JMenuItem("Salva");
	
	public static JEditorPane editorPane = new JEditorPane();
	private JScrollBar scrollBar = new JScrollBar();

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
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String righe = "";
		for(int i=1;i<=1000;i++){
			righe = righe + i +"\n";
		}
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setToolTipText("Numero righe");
		this.textArea.setText(righe);
		this.frmGbtexteditor.setIconImage(Toolkit.getDefaultToolkit().getImage(GBTextEditorFrame.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditorIcon.png")));
		this.frmGbtexteditor.setMinimumSize(new Dimension(1280, 720));
		this.frmGbtexteditor.setTitle("GBTextEditor");
		this.frmGbtexteditor.setBounds(100, 100, 450, 300);
		this.frmGbtexteditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		GroupLayout groupLayout = new GroupLayout(frmGbtexteditor.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 621, Short.MAX_VALUE))
					.addContainerGap())
		);
		frmGbtexteditor.getContentPane().setLayout(groupLayout);
		
		frmGbtexteditor.setJMenuBar(menuBar);
		menuBar.add(mnFile);
		mntmNuovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new AvvisoFileNonSalvato(frame,true);
			}
		});
		mnFile.add(mntmNuovo);
		
		mnFile.add(mntmApri);
		mnFile.add(mntmSalva);
		mntmApri.addActionListener(new FileBrowserApri(this));
		/*
		mntmApri.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new AvvisoFileNonSalvato(frame,false);
				new FileBrowserApri(frame);
			}
		});
		*/
		mntmSalva.addActionListener(new FileBrowserSalva(this,false));
		//scrollBar.setDropTarget(dt);
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
	
	public GBTextEditorFrame frame = this;
	private final JTextArea textArea = new JTextArea();
}
