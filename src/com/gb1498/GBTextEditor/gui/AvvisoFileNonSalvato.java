package com.gb1498.GBTextEditor.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AvvisoFileNonSalvato extends JDialog {
	
	private GBTextEditorFrame frame;

	/**
	 * 
	 */
	private static final long serialVersionUID = -400333688080292789L;
	private final JPanel contentPanel = new JPanel();
	private boolean reset;

	/**
	 * Create the dialog.
	 * @param frame 
	 */
	public AvvisoFileNonSalvato(GBTextEditorFrame frame, boolean reset) {
		this.frame = frame;
		this.reset = reset;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AvvisoFileNonSalvato.class.getResource("/com/gb1498/GBTextEditor/icons/IconaAttenzione.png")));
		setTitle("ATTENZIONE!");
		setBounds(100, 100, 296, 113);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblVuoiSalvareIl = new JLabel("Vuoi salvare il file prima di crearne uno nuovo?");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblVuoiSalvareIl)
					.addContainerGap(366, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblVuoiSalvareIl)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						setVisible(false);
						dispose();
					}
				});
				okButton.addActionListener(new FileBrowserSalva(this.frame,this.reset));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
}
