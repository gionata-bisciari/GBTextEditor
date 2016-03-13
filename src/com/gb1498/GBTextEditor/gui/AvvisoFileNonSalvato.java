package com.gb1498.GBTextEditor.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class AvvisoFileNonSalvato extends JDialog {
	
	@SuppressWarnings("unused")
	private GBTextEditorFrame frame;
	
	private boolean annullaOperazione = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = -400333688080292789L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private boolean reset;
	private JButton okButton;
	private JButton btnNo;

	/**
	 * Create the dialog.
	 * @param frame 
	 */
	private static int nInit = 0;
	
	public AvvisoFileNonSalvato(GBTextEditorFrame frame, boolean reset) {
		nInit++;
		setResizable(false);
		this.frame = frame;
		this.reset = reset;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AvvisoFileNonSalvato.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Attenzione.png")));
		setTitle("ATTENZIONE!");
		setBounds(100, 100, 265, 123);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblVuoiSalvareIl = new JLabel("Vuoi salvare il file?");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(69)
					.addComponent(lblVuoiSalvareIl, GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
					.addGap(73))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(11)
					.addComponent(lblVuoiSalvareIl, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Si");
				okButton.setIcon(new ImageIcon(AvvisoFileNonSalvato.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-SI-16x16.png")));
				okButton.setToolTipText("Salva su file");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						setVisible(false);
						dispose();
						frame.salva();
					}
				});
				//okButton.addActionListener();
				okButton.setActionCommand("Si");
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnNo = new JButton("No");
				btnNo.setIcon(new ImageIcon(AvvisoFileNonSalvato.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-NO-16x16.png")));
				btnNo.setToolTipText("Continua senza salvare");
				btnNo.addMouseListener(new MouseAdapter() {
					//@Override
					public void mousePressed(MouseEvent e) {
						frame.getEditorPane().setText("");
						setVisible(false);
						dispose();
					}
				});
				btnNo.setActionCommand("No");
			}
			
			JButton btnAnnulla = new JButton("Annulla");
			btnAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					setAnnullaOperazione(true);
					setVisible(false);
					dispose();
				}
			});
			btnAnnulla.setIcon(new ImageIcon(AvvisoFileNonSalvato.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-ANNULLA-16x16.png")));
			btnAnnulla.setToolTipText("Annulla operazione");
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(7)
						.addComponent(okButton, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNo, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAnnulla, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
						.addGap(6))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(btnNo)
							.addComponent(btnAnnulla, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
			);
			buttonPane.setLayout(gl_buttonPane);
		}
		//System.out.println(AvvisoFileNonSalvato.nInit);
		if(nInit==0){
			this.dispose();
		}
		else{
			this.setVisible(true);		
		}
	}

	public boolean isAnnullaOperazione() {
		return annullaOperazione;
	}

	public void setAnnullaOperazione(boolean annullaOperazione) {
		this.annullaOperazione = annullaOperazione;
	}
	
	public static void main(String[] args){
		new AvvisoFileNonSalvato(new GBTextEditorFrame(), false);
	}
}
