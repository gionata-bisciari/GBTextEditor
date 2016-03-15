package com.gb1498.GBTextEditor.gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Salva extends JPanel{

	private static final long serialVersionUID = 5098374780926515349L;
	
	private JFileChooser browser;
	
	public Salva(){
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.browser = new JFileChooser();
		this.browser.setApproveButtonToolTipText("Salva il file con nome");
		this.browser.setApproveButtonText("Salva");
		this.browser.setDialogTitle("Salva");
		
		if(GBTextEditor.getUri()==null){
			switch(browser.showSaveDialog(Salva.this)){
				case JFileChooser.APPROVE_OPTION:
					try {
						File f = browser.getSelectedFile();
		    			BufferedWriter write = new BufferedWriter(new FileWriter(f));
		    			String stringa = GBTextEditor.getEditorpane().getText().trim();
						write.write(stringa);
		    			write.close();
						GBTextEditor.setUri(f.toURI());
						JOptionPane.showMessageDialog(this, "File salvato con successo", "File salvato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(this, "File inesistente", "File inesistente", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
						e.printStackTrace();
					}
					break;
				case JFileChooser.CANCEL_OPTION:
					JOptionPane.showMessageDialog(this, "File non salvato", "Operazione annullata", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
					break;
				case JFileChooser.ERROR_OPTION:
					JOptionPane.showMessageDialog(this, "Si è presentato un errore indefinito, riprova", "Errore", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
					break;
				default:
					break;
			}
		}
		else{
			try {
				File f = new File(GBTextEditor.getUri());
    			BufferedWriter write = new BufferedWriter(new FileWriter(f));
    			String stringa = GBTextEditor.getEditorpane().getText().trim();
				write.write(stringa);
    			write.close();
				JOptionPane.showMessageDialog(this, "File salvato con successo", "File salvato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "File inesistente", "File inesistente", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
				e.printStackTrace();
			}
		}
	}
	public Salva(boolean b) {
		this();
	}
	public static void main(String[] args){
		new Salva();
	}
}