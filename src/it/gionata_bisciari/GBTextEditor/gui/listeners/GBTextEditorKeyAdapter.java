package it.gionata_bisciari.GBTextEditor.gui.listeners;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import it.gionata_bisciari.GBTextEditor.gui.GBTextEditor;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorAggiorna;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorApri;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorSalva;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorSalvaConNome;

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
		else if(k.getKeyCode()==KeyEvent.VK_F5){
			this.aggiornaFile();
		}
		else if(k.getKeyCode()==KeyEvent.VK_ENTER){
			int newSize = (int) gui.getGrandezzaFont().getValue();
			if(newSize>=10&&newSize<=60){
				Font modified = new Font("modified", Font.PLAIN, newSize);
				GBTextEditor.getEditorPane().setFont(modified);
				gui.getNumeroRighe().setFont(modified); 
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
		GBTextEditor.getEditorPane().setText("");
	}
	
	public void resetURI(){
		GBTextEditor.setUri(null);
	}
	
	public static boolean controllaEditorVuoto(){
		boolean vuoto = false;
		if(GBTextEditor.getEditorPane().getText().equals("")){
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
					new GBTextEditorSalva();
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
		new GBTextEditorSalva();
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
	public void aggiornaFile(){
		new GBTextEditorAggiorna();
	}
}
