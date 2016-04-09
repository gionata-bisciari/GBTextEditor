package it.gionata_bisciari.GBTextEditor.gui;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;

import it.gionata_bisciari.GBTextEditor.gui.listeners.GBTextEditorAvvisoFileNonSalvatoActionListener;

import java.awt.Dimension;
import java.awt.Point;

public class GBTextEditorAvvisoFileNonSalvato extends JDialog {

	private static final long serialVersionUID = 4833248178369250183L;
	
	private final JLabel lblPrimaDiContinuare = new JLabel("Prima di continuare l'operazione vuoi salvare il file corrente?");
	
	private final JButton btnSi = new JButton("Si");
	private final JButton btnNo = new JButton("No");
	private final JButton btnAnnulla = new JButton("Annulla");
	
	private final GBTextEditorAvvisoFileNonSalvatoActionListener AFNSAL = new GBTextEditorAvvisoFileNonSalvatoActionListener(this);
	
	private int scelta = -1;
	
	public static final int SI = 1;
	public static final int NO = 0;
	public static final int ANNULLA = -1;
	
	public GBTextEditorAvvisoFileNonSalvato() {
		setLocation(new Point(200, 200));
		setAlwaysOnTop(true);
		if(System.getProperty("os.name").contains("Windows")){
			try {
				UIManager.setLookAndFeel(GBTextEditor.TEMA);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		setMaximumSize(new Dimension(375, 105));
		setMinimumSize(new Dimension(375, 105));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrimaDiContinuare)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(109)
							.addComponent(btnSi, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNo, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(64)
							.addComponent(btnAnnulla, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPrimaDiContinuare)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAnnulla)
						.addComponent(btnNo)
						.addComponent(btnSi))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		
		setResizable(false);
		setModal(true);
		setType(Type.POPUP);
		setTitle("ATTENZIONE!!!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GBTextEditorAvvisoFileNonSalvato.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-Attenzione.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(groupLayout);

		btnSi.setToolTipText("Salva e continua");
		btnNo.setToolTipText("Continua senza salvare");
		btnAnnulla.setToolTipText("Annulla operazione");
		
		btnSi.addActionListener(this.AFNSAL);
		btnNo.addActionListener(this.AFNSAL);
		btnAnnulla.addActionListener(this.AFNSAL);
		
		this.setVisible(true);
	}

	/**
	 * @return the btnSi
	 */
	public JButton getBtnSi() {
		return btnSi;
	}

	/**
	 * @return the btnNo
	 */
	public JButton getBtnNo() {
		return btnNo;
	}

	/**
	 * @return the btnAnnulla
	 */
	public JButton getBtnAnnulla() {
		return btnAnnulla;
	}

	public int getScelta() {
		return scelta;
	}
	
	public void setScelta(int scelta){
		this.scelta = scelta;
	}
}
