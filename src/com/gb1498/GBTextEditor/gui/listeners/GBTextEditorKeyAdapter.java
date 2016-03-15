package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.gb1498.GBTextEditor.gui.GBTextEditorApri;
import com.gb1498.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;
import com.gb1498.GBTextEditor.gui.GBTextEditor;
import com.gb1498.GBTextEditor.gui.Salva;
import com.gb1498.GBTextEditor.gui.GBTextEditorSalvaConNome;

public class GBTextEditorKeyAdapter extends KeyAdapter {
	
	private GBTextEditor gui;
	
	public GBTextEditorKeyAdapter(GBTextEditor gui){
		this.gui = gui;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_CONTROL){
			//System.out.println("ctrl p");
			this.gui.setCtrlPressed(true);
		}
		else if(k.getKeyCode()==KeyEvent.VK_S){
			//System.out.println("ctrl+s");
			//System.out.println(gui.isCtrlPressed());
			if(this.gui.isCtrlPressed()){
				gui.setCtrlPressed(false);
				this.salvaFile();
			}
		}
		else if(k.getKeyCode()==KeyEvent.VK_O){
			//System.out.println("ctrl+o");
			if(this.gui.isCtrlPressed()){
				if(this.controllaSalva()){
					gui.setCtrlPressed(false);
					this.apriFile();
				}
			}
		}
		else if(k.getKeyCode()==KeyEvent.VK_N){
			//System.out.println("ctrl+n");
			if(this.gui.isCtrlPressed()){
				if(this.controllaSalva()){
					gui.setCtrlPressed(false);
					this.nuovoFile();
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_CONTROL){
			//System.out.println("ctrl r");
			gui.setCtrlPressed(false);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent k){
		
	}
	
	public void resetEditorPane(){
		GBTextEditor.getEditorpane().setText("");
	}
	
	public void resetURI(){
		GBTextEditor.setUri(null);
	}
	
	public static boolean controllaEditorVuoto(){
		boolean vuoto = false;
		if(GBTextEditor.getEditorpane().getText().equals("")){
			vuoto = true;
		}
		return vuoto;
	}
	
	public boolean controllaSalva(){
		boolean continua = true;
		if(!controllaEditorVuoto()){
			GBTextEditorAvvisoFileNonSalvato avv = new GBTextEditorAvvisoFileNonSalvato();
			switch (avv.getScelta()){ 
				case 1:
					new Salva();
					continua = true;
					break;
				case 0:
					continua = true;
					break;
				case -1:
					continua = false;
					break;
				default:
					break;
			}
		}
		return continua;
	}
	
	public void salvaFile(){
		new Salva(false);
	}
	
	public void salvaFileConNome(){
		new GBTextEditorSalvaConNome();
	}
	
	public void apriFile(){
		new GBTextEditorApri();
	}
	
	public void nuovoFile(){
		controllaSalva();
		this.resetEditorPane();
	}
}
