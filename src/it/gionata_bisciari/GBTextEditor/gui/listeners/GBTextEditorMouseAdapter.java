package it.gionata_bisciari.GBTextEditor.gui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import it.gionata_bisciari.GBTextEditor.gui.GBTextEditor;

public class GBTextEditorMouseAdapter extends MouseAdapter {
	
	private GBTextEditor gui;

	public GBTextEditorMouseAdapter(GBTextEditor gui){
		this.gui = gui;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		gui.getGrandezzaFont().setValue(GBTextEditor.getEditorPane().getFont().getSize());
	}
}
