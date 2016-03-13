package com.gb1498.GBTextEditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

public class FileBrowserSalva extends JFileChooser implements ActionListener {
	
	private JFrame finestra = new JFrame("Salva");
	private static final long serialVersionUID = 7192880835004117243L;
	private GBTextEditorFrame frame;
	private boolean reset;
	
	private static int nInit = 0;
	
	public FileBrowserSalva(GBTextEditorFrame frame,boolean reset){
		nInit++;
		finestra.setAlwaysOnTop(true);
		finestra.setMinimumSize(new Dimension(455, 305));
		finestra.setIconImage(Toolkit.getDefaultToolkit().getImage(FileBrowserSalva.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Salva-1-16x16.png")));
		this.frame = frame;
		this.reset = reset;
		finestra.getContentPane().add(this);
		if(nInit==1){
			finestra.dispose();
		}
		else{
			finestra.setVisible(true);		
		}
	}

	public void actionPerformed(ActionEvent e) {
    	try {
    		int n = this.showSaveDialog(frame);
    		if (n == JFileChooser.APPROVE_OPTION) {
    			File f = this.getSelectedFile();
    			BufferedWriter write = new BufferedWriter(new FileWriter(f));
    			String stringa = GBTextEditorFrame.editorPane.getText();
				write.write(stringa);
    			write.close();
    			if(this.reset){
    				GBTextEditorFrame.editorPane.setText("");
    			}
    		}
    	} catch (Exception ex) {}
    }
}