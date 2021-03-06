package it.gionata_bisciari.GBTextEditor.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GBTextEditorApri extends JPanel{

	private static final long serialVersionUID = 4829497184822151779L;
	
	private JFileChooser browser;
	
	public GBTextEditorApri(){
		
		if(System.getProperty("os.name").contains("Windows")){
			try {
				UIManager.setLookAndFeel(GBTextEditor.TEMA);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		
		this.browser = new JFileChooser();
		this.browser.setApproveButtonToolTipText("Apri il file");
		this.browser.setApproveButtonText("Apri");
		this.browser.setDialogTitle("Apri");
		
		switch(browser.showOpenDialog(GBTextEditorApri.this)){
			case JFileChooser.APPROVE_OPTION:
				try {
					File f = this.browser.getSelectedFile();
					BufferedReader read = new BufferedReader(new FileReader(f));
					String raw = read.readLine();
					String linea = "";
					while(raw!=null){
						linea = linea + raw + "\n";
						raw = read.readLine();
					}
					GBTextEditor.setUri(f.toURI());
					GBTextEditor.setBuffer(linea);
					GBTextEditor.getEditorPane().setText(new String(linea));
					read.close();
					JOptionPane.showMessageDialog(this, "File aperto con successo", "File aperto", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-INFO.png")));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "File inesistente", "File inesistente", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-Errore.png")));
					e.printStackTrace();
				}
				break;
			case JFileChooser.CANCEL_OPTION:
				JOptionPane.showMessageDialog(this, "File non aperto", "Operazione annullata", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-INFO.png")));
				break;
			case JFileChooser.ERROR_OPTION:
				JOptionPane.showMessageDialog(this, "Si è presentato un errore indefinito, riprova", "Errore", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("	icons/GBTextEditor-Errore.png")));
				break;
			default:
				break;
		}
	}
	public static void main(String[] args){
		new GBTextEditorApri();
	}
}
