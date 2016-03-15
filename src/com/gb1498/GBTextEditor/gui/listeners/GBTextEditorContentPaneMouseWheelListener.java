package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.gb1498.GBTextEditor.gui.GBTextEditor;

public class GBTextEditorContentPaneMouseWheelListener implements MouseWheelListener {
	
	private GBTextEditor gui;

	public GBTextEditorContentPaneMouseWheelListener(GBTextEditor gui) {
		this.gui = gui;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(gui.isCtrlPressed()){
			int newSize = GBTextEditor.getEditorpane().getFont().getSize()-e.getWheelRotation();
			if(newSize>=10&&newSize<=60){
				Font modified = new Font("modified", Font.PLAIN, newSize);
				GBTextEditor.getEditorpane().setFont(modified);
				gui.getNumeroRighe().setFont(modified); 
			}
		}
		else{
			gui.getScrollPane().getVerticalScrollBar().setValue(gui.getScrollPane().getVerticalScrollBar().getValue()+e.getWheelRotation()*10);
		}
	}

}
