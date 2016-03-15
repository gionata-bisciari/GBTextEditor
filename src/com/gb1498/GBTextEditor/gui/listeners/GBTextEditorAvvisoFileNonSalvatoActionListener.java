package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.gb1498.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;

public class GBTextEditorAvvisoFileNonSalvatoActionListener implements ActionListener{
	
	private GBTextEditorAvvisoFileNonSalvato dialog;

	public GBTextEditorAvvisoFileNonSalvatoActionListener(GBTextEditorAvvisoFileNonSalvato dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//SI
		if(e.getSource().equals(this.dialog.getBtnSi())){
			dialog.setScelta(GBTextEditorAvvisoFileNonSalvato.SI);
			dialog.setVisible(false);
			dialog.dispose();
		}
		//NO
		else if(e.getSource().equals(this.dialog.getBtnNo())){
			dialog.setScelta(GBTextEditorAvvisoFileNonSalvato.NO);
			dialog.setVisible(false);
			dialog.dispose();
		}
		//ANNULLA
		else if(e.getSource().equals(this.dialog.getBtnAnnulla())){
			dialog.setScelta(GBTextEditorAvvisoFileNonSalvato.ANNULLA);
			dialog.setVisible(false);
			dialog.dispose();
		}
		else{
			
		}
	}
}
