/**
 * 
 */
package it.gionata_bisciari.GBTextEditor.gui.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import it.gionata_bisciari.GBTextEditor.gui.GBTextEditor;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorSalva;;

/**
 * @author giona
 *
 */
public class GBTextEditorWindowListener implements WindowListener {
	
	public GBTextEditor gui;
	
	public GBTextEditorWindowListener(GBTextEditor gui){
		this.gui = gui;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(controllaSalva()){
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
}
