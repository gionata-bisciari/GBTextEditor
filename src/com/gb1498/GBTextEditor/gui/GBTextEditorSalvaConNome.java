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

public class GBTextEditorSalvaConNome extends JPanel{

	private static final long serialVersionUID = 5098374780926515349L;
	
	private JFileChooser browser;
	
	public GBTextEditorSalvaConNome(){
		
		if(System.getProperty("os.name").contains("Windows")){
			try {
				UIManager.setLookAndFeel(GBTextEditor.TEMA);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		
		this.browser = new JFileChooser();
		this.browser.setApproveButtonToolTipText("Salva il file con nome");
		this.browser.setApproveButtonText("Salva");
		this.browser.setDialogTitle("Salva");
		switch(browser.showSaveDialog(GBTextEditorSalvaConNome.this)){
			case JFileChooser.APPROVE_OPTION:
				try {
					if(this.fileEsistente()){
						switch (this.avvisoSovrascrizione()){
							case 1:
								File f = browser.getSelectedFile();
								BufferedWriter write = new BufferedWriter(new FileWriter(f));
								String stringa = GBTextEditor.getEditorPane().getText().trim();
								GBTextEditor.setBuffer(new String(stringa));
								write.write(stringa);
								write.close();
								GBTextEditor.setUri(f.toURI());
								JOptionPane.showMessageDialog(this, "File salvato con successo", "File salvato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
								break;
							case 0:
								JOptionPane.showMessageDialog(this, "Operazione annullata", "Operazione annullata", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
								break;
							case -1:
								JOptionPane.showMessageDialog(this, "Operazione annullata", "Operazione annullata", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
								break;
							default:
								JOptionPane.showMessageDialog(this, "Errore indefinito, riprova", "ERRORE", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
								break;
						}
					}
					else{
						File f = browser.getSelectedFile();
						BufferedWriter write = new BufferedWriter(new FileWriter(f));
						String stringa = GBTextEditor.getEditorPane().getText().trim();
						GBTextEditor.setBuffer(new String(stringa));
						write.write(stringa);
						write.close();
						GBTextEditor.setUri(f.toURI());
						JOptionPane.showMessageDialog(this, "File salvato con successo", "File salvato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
					}
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
	
	public int avvisoSovrascrizione(){
		int valore;
		switch (JOptionPane.showConfirmDialog(this, "Il file specificato esiste già, devo sovrascriverlo?", "File esistente", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")))) {
			case JOptionPane.YES_OPTION:
				valore = 1;
				break;
			case JOptionPane.NO_OPTION:
				valore = 0;
				break;
			case JOptionPane.CANCEL_OPTION:
				valore = -1;
				break;
			case JOptionPane.CLOSED_OPTION:
				valore = -1;
				break;
			default:
				valore = -2;
				break;
		}
		return valore;
	}
	
	public boolean fileEsistente(){
		return browser.getSelectedFile().exists();
	}
	public static void main(String[] args){
		new GBTextEditorSalvaConNome();
	}
}