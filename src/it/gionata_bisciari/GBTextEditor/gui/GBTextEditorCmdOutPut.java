package it.gionata_bisciari.GBTextEditor.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GBTextEditorCmdOutPut extends JFrame {
	
	private static final long serialVersionUID = -8556501078215368532L;

	private JTextArea textArea;
	
	public GBTextEditorCmdOutPut() {
		if(System.getProperty("os.name").contains("Windows")){
			try {
				UIManager.setLookAndFeel(GBTextEditor.TEMA);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		setMinimumSize(new Dimension(400, 300));
		setTitle("CMD OutPut");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GBTextEditorCmdOutPut.class.getResource("/it/gionata_bisciari/GBTextEditor/gui/icons/GBTextEditor-CMD.png")));
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
		);
		
		textArea = new JTextArea();
		textArea.setToolTipText("Output");
		textArea.setEditable(false);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setViewportView(textArea);
		getContentPane().setLayout(groupLayout);
	}

	

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * @param textArea the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}
