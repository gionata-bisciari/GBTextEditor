package com.gb1498.GBTextEditor.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.gb1498.GBTextEditor.gui.GBTextEditorFrame;

public class GBTextEditor{
	private GBTextEditorFrame frame;
	private File file;
	
	public GBTextEditor(GBTextEditorFrame frame){
		this.setFrame(frame);
	}
	public void apri(){
		String stringa = "";
		//FileBrowserApri fb = new FileBrowserApri(this.frame.getEditorPane());
		//this.file = fb.getFile();
		String path = this.file.getAbsolutePath();
		if(file.isFile()){
			FileReader f = null;
			try {
				f = new FileReader(path);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}
			BufferedReader b = new BufferedReader(f);
			String tmp = "";
			try {
				while((tmp = b.readLine())!=null){
					stringa = stringa + tmp + "\n";
					tmp = "";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				b.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		else{
			try {
				throw new IOException("\nIl file non esiste\n");
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		frame.getEditorPane().setText(stringa);
	}
	
	public void salva(){
		
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public GBTextEditorFrame getFrame() {
		return frame;
	}
	public void setFrame(GBTextEditorFrame frame) {
		this.frame = frame;
	}
}
