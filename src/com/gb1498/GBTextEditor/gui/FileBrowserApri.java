package com.gb1498.GBTextEditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

public class FileBrowserApri extends JFileChooser implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7192880835004117243L;
	private GBTextEditorFrame frame;
	
	public FileBrowserApri(GBTextEditorFrame frame){
		this.frame = frame;
	}

    public void actionPerformed(ActionEvent e) {
    	try {
    		//GBTextEditorFrame.editorPane.setText("");
    		int n = this.showOpenDialog(frame);
    		if (n == JFileChooser.APPROVE_OPTION) {
    			File f = this.getSelectedFile();
    			BufferedReader read = new BufferedReader(new FileReader(f));
    			String stringa = "";
    			String line = null;
				while((line = read.readLine())!= null) {
    				stringa = stringa + line + "\n";
    			}
    			read.close();
    			GBTextEditorFrame.editorPane.setText(stringa);
    		}
    	} catch (Exception ex) {}
    }
}