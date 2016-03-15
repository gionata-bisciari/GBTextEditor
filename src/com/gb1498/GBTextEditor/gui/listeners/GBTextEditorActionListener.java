package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gb1498.GBTextEditor.gui.GBTextEditorApri;
import com.gb1498.GBTextEditor.gui.GBTextEditor;
import com.gb1498.GBTextEditor.gui.Salva;
import com.gb1498.GBTextEditor.gui.GBTextEditorSalvaConNome;
import com.gb1498.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;

public class GBTextEditorActionListener implements ActionListener {
	
	public GBTextEditor gui;
	
	public GBTextEditorActionListener(GBTextEditor gui){
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==(this.gui.getMntmEsci())){
			if(controllaSalva()){
				System.exit(0);
			}
		}
		else if(e.getSource()==(this.gui.getMntmNuovo())){
			if(controllaSalva()){
				this.resetEditorPane();
				this.resetURI();
			}
		}
		else if(e.getSource()==(this.gui.getMntmApri())){
			this.controllaSalva();
			this.apriFile();
		}
		else if(e.getSource()==(this.gui.getMntmSalva())){
			this.salvaFile();
		}
		else if(e.getSource()==(this.gui.getMntmSalvaConNome())){
			this.salvaFileConNome();
		}
		else if(e.getSource()==(this.gui.getMntmSitoCreatore())){
			String url = "www.gb1498.com";
            Desktop desktop = Desktop.getDesktop();
            try{
            	if(desktop.isSupported(Desktop.Action.BROWSE)){
            		URI uri = new URI(url);
            		desktop.browse(uri);
            	}
            }
            catch(URISyntaxException | IOException ex){
            	ex.printStackTrace();
            }
		}
		else if(e.getSource()==(this.gui.getTxtGrandezzaFont())){
			int newSize = Integer.parseInt(gui.getTxtGrandezzaFont().getText());
			if(newSize>=10&&newSize<=60){
				Font modified = new Font("modified", Font.PLAIN, newSize);
				GBTextEditor.getEditorpane().setFont(modified);
				gui.getNumeroRighe().setFont(modified); 
			}
		}
		else if(e.getSource()==(this.gui.getMntmCompila())){
			/*TODO
			if(uri!=null){
				try {
					String path = "C:" + uri.getPath();
					String command = ("C:/Programmi/Java/jdk1.8.0_74/bin/javac.exe " + path + " -d " + (path.substring(0,path.lastIndexOf("/"))));
					Process process = Runtime.getRuntime().exec(command);
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String linea = br.readLine();
					while(linea!=null){
						System.out.println(linea);
						linea = br.readLine();
					}
					process.waitFor();
					process.destroy();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(frame, "Prima dicompilare un file devi salvarlo!", "Errore di compilazione", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
			}*/
			JOptionPane.showMessageDialog(gui, "Questa funzione non è stata implementata interamente per cui non è ancora accessibile", "Errore", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
		}
		else if(e.getSource()==(this.gui.getMntmEsegui())){
			/*TODO
			if(uri!=null){
				String path;
				String command = "";
				try {
					if(System.getProperty("os.name").contains("Windows")){
						path = "C:" + uri.getPath();
					}
					else{
						path = uri.getPath();
					}
					if(path.endsWith(".java")){
						path = path.replace(".java", "");
						command = ("C:/Program Files/Java/jdk1.8.0_74/bin/java.exe " + path);
					}
					else if(path.endsWith(".c")){
						if(System.getProperty("os.name").contains("Windows")){
							path = path.replace(".c", ".exe");
						}
						else{
							path = path.replace(".c", "");
						}
						command = (path);
					}
					else{
						JOptionPane.showMessageDialog(frame, ("Impossibile eseguire " + path), "Errore di esecuzione", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
					}
					Process process = Runtime.getRuntime().exec(command);
					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String raw = br.readLine();
					String linea = "";
					out.setVisible(true);
					while(raw!=null){
						linea = linea + raw + "\n";
						out.getTextArea().setText(linea);
						System.out.println(linea);
						raw = br.readLine();
					}
					process.waitFor();
					process.destroy();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(frame, "Prima di eseguire un file devi salvarlo!", "Errore di esecuzione", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
			}*/
			JOptionPane.showMessageDialog(gui, "Questa funzione non è stata implementata interamente per cui non è ancora accessibile", "Errore", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
		}
	}
	
	public void resetEditorPane(){
		GBTextEditor.getEditorpane().setText("");
	}
	
	public void resetURI(){
		GBTextEditor.setUri(null);
	}
	
	public static boolean controllaEditorVuoto(){
		boolean vuoto = false;
		if(GBTextEditor.getEditorpane().getText().equals("")){
			vuoto = true;
		}
		return vuoto;
	}
	
	public boolean controllaSalva(){
		boolean continua = true;
		if(!controllaEditorVuoto()){
			GBTextEditorAvvisoFileNonSalvato avv = new GBTextEditorAvvisoFileNonSalvato();
			switch (avv.getScelta()){ 
				case 1:
					new Salva();
					continua = true;
					break;
				case 0:
					continua = true;
					break;
				case -1:
					continua = false;
					break;
				default:
					break;
			}
		}
		return continua;
	}
	
	public void salvaFile(){
		new Salva();
	}
	
	public void salvaFileConNome(){
		new GBTextEditorSalvaConNome();
	}
	
	public void apriFile(){
		new GBTextEditorApri();
	}
	
	public void nuovoFile(){
		controllaSalva();
		this.resetEditorPane();
	}
}
