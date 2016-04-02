package com.gb1498.GBTextEditor.gui.listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gb1498.GBTextEditor.gui.GBTextEditor;
import com.gb1498.GBTextEditor.gui.GBTextEditorAggiorna;
import com.gb1498.GBTextEditor.gui.GBTextEditorApri;
import com.gb1498.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;
import com.gb1498.GBTextEditor.gui.GBTextEditorSalva;
import com.gb1498.GBTextEditor.gui.GBTextEditorSalvaConNome;

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
		else if(e.getSource()==(this.gui.getMntmCompila())){
			/*
			if(GBTextEditor.getUri()!=null){
				ProcessBuilder builder = new ProcessBuilder("ping","localhost");
				try{
				Process process = builder.start();
				Thread ioThread = new Thread(){
					@Override
					public void run(){
						try{
							BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
							String linea = null;
							GBTextEditor.getOut().setVisible(true);
							while((linea = br.readLine()) != null){
								//System.out.println(linea);
								GBTextEditor.getOut().getTextArea().append(linea + "\n");
							}
							br.close();
						}
						catch (Exception e){
							e.printStackTrace();;
						}
					}
				};
				ioThread.start();
				process.waitFor();
				process.destroy();
				GBTextEditor.getOut().getTextArea().append("fine processo");
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(gui, "Prima di compilare un file devi salvarlo!", "Errore di compilazione", JOptionPane.ERROR_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-Errore.png")));
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
		else if(e.getSource()==this.gui.getMntmAggiorna()){
			this.aggiornaFile();
		}
		else if(e.getSource()==(this.gui.getPopupMenuItemNuovo())){
			if(controllaSalva()){
				this.resetEditorPane();
				this.resetURI();
			}
		}
		else if(e.getSource()==(this.gui.getPopupMenuItemApri())){
			this.controllaSalva();
			this.apriFile();
		}
		else if(e.getSource()==(this.gui.getPopupMenuItemSalva())){
			this.salvaFile();
		}
		else if(e.getSource()==(this.gui.getPopupMenuItemSalvaConNome())){
			this.salvaFileConNome();
		}
		else if(e.getSource()==this.gui.getPopupMenuItemAggiorna()){
			this.aggiornaFile();
		}
	}
	
	public void resetEditorPane(){
		GBTextEditor.getEditorPane().setText("");
	}
	
	public void resetURI(){
		GBTextEditor.setUri(null);
	}
	
	public static boolean controllaEditorVuoto(){
		boolean vuoto = false;
		if(GBTextEditor.getEditorPane().getText().equals("")){
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
					new GBTextEditorSalva();
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
		new GBTextEditorSalva();
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
		GBTextEditor.resetBuffer();
	}
	
	public void aggiornaFile(){
		new GBTextEditorAggiorna();
	}
}
