package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.gb1498.GBTextEditor.gui.GBTextEditor;

public class GBTextEditorMouseWheelListener implements MouseWheelListener {
	
	private GBTextEditor gui;

	public GBTextEditorMouseWheelListener(GBTextEditor gui) {
		this.gui = gui;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getSource()==GBTextEditor.getEditorPane()){
			if(gui.isCtrlPressed()){
				int newSize = GBTextEditor.getEditorPane().getFont().getSize()-e.getWheelRotation();
				if(newSize>=8&&newSize<=80){
					Font modified = new Font("modified", Font.PLAIN, newSize);
					GBTextEditor.getEditorPane().setFont(modified);
					gui.getNumeroRighe().setFont(modified); 
				}
			}
			else{
				gui.getScrollPane().getVerticalScrollBar().setValue(gui.getScrollPane().getVerticalScrollBar().getValue()+e.getWheelRotation()*20);
			}
		}
	}
}
