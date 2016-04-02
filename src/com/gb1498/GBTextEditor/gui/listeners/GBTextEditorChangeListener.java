package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.Font;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.gb1498.GBTextEditor.gui.GBTextEditor;

public class GBTextEditorChangeListener implements ChangeListener {
	
	GBTextEditor gui;
	
	public GBTextEditorChangeListener(GBTextEditor gui){
		this.gui = gui;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		int newSize = (int) gui.getGrandezzaFont().getValue();
		if(newSize>=10&&newSize<=60){
			Font modified = new Font("modified", Font.PLAIN, newSize);
			GBTextEditor.getEditorPane().setFont(modified);
			gui.getNumeroRighe().setFont(modified); 
		}
	}
}
