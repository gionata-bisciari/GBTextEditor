package com.gb1498.GBTextEditor.gui.listeners;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.gb1498.GBTextEditor.gui.GBTextEditor;

public class GBTextEditorMenuListener implements MenuListener {
	
	private GBTextEditor gui;
	
	public GBTextEditorMenuListener(GBTextEditor gui){
		this.gui = gui;
	}

	@Override
	public void menuCanceled(MenuEvent m) {
		if(m.getSource()==this.gui.getMnSalva()){
			this.disabilita();
		}
	}

	@Override
	public void menuDeselected(MenuEvent m) {
		if(m.getSource()==this.gui.getMnSalva()){
			this.disabilita();
		}
	}

	@Override
	public void menuSelected(MenuEvent m) {
		if(m.getSource()==this.gui.getMnSalva()){
			this.disabilita();
		}
	}
	
	private void disabilita(){
		if(GBTextEditor.getBuffer()!=null){
			if(GBTextEditor.getEditorPane().getText().equals(GBTextEditor.getBuffer())){
				gui.getMntmSalva().setEnabled(false);
			}
			else{
				gui.getMntmSalva().setEnabled(true);
			}
		}
	}
}
