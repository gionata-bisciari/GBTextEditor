package com.gb1498.GBTextEditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URLEncoder;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

public class FileBrowserApri extends JFileChooser implements ActionListener {
	
	private JFrame finestra = new JFrame("Apri");
	private static final long serialVersionUID = 7192880835004117243L;
	protected GBTextEditorFrame frame;
	
	private static int nInit = 0;
	
	public FileBrowserApri(GBTextEditorFrame frame){
		nInit++;
		finestra.setAlwaysOnTop(true);
		finestra.setMinimumSize(new Dimension(455, 305));
		finestra.setIconImage(Toolkit.getDefaultToolkit().getImage(FileBrowserApri.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Apri-16x16.png")));
		this.frame = frame;
		finestra.getContentPane().add(this);
		if(nInit==1){
			finestra.dispose();
		}
		else{
			finestra.setVisible(true);		
		}
	}

    public void actionPerformed(ActionEvent e) {
    	frame.controllaSalva();
    	try {
    		int n = this.showOpenDialog(frame);
    		if (n == JFileChooser.APPROVE_OPTION) {
    			
    			String uri = this.getSelectedFile().getAbsolutePath();
    			System.out.println(uri);
    			uri = URLEncoder.encode(uri, "UTF-8");
    			uri = uri.replace("%5C", "/");
    			uri = uri.replace("%3A", ":");
    			System.out.println(uri);
    			GBTextEditorFrame.uri = URI.create(uri);
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
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
}