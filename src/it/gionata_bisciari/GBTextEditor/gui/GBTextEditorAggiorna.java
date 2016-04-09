package it.gionata_bisciari.GBTextEditor.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GBTextEditorAggiorna extends JPanel {
	
	private static final long serialVersionUID = -7526071532270056046L;
	
	public GBTextEditorAggiorna(){
		if(GBTextEditor.getUri()!=null){
			if(System.getProperty("os.name").contains("Windows")){
				try {
					UIManager.setLookAndFeel(GBTextEditor.TEMA);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
			try {
				File f = new File(GBTextEditor.getUri());
				BufferedReader read = new BufferedReader(new FileReader(f));
				String raw = read.readLine();
				String stringa = "";
				while(raw!=null){
					stringa = stringa + raw + "\n";
					raw = read.readLine();	
				}
				GBTextEditor.getEditorPane().setText(stringa);
				read.close();
				JOptionPane.showMessageDialog(this, "File aggiornato", "File aggiornato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-INFO.png")));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "File inesistente", "File inesistente", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-Errore.png")));
				e.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(this, "Impossibile aggiornare, non hai aperto alcun file o non hai salvato il file corrente", "File inesistente", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-Errore.png")));
		}
	}
	
	public static void main(String[] args){
		new GBTextEditorAggiorna();
	}
	
}
