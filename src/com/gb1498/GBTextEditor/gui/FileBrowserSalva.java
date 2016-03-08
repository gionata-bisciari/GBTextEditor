package com.gb1498.GBTextEditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

public class FileBrowserSalva extends JFileChooser implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7192880835004117243L;
	private GBTextEditorFrame frame;
	private boolean reset;
	
	public FileBrowserSalva(GBTextEditorFrame frame,boolean reset){
		this.frame = frame;
		this.reset = reset;
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