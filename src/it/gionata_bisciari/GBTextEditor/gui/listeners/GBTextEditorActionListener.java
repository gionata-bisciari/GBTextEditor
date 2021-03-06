package it.gionata_bisciari.GBTextEditor.gui.listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import it.gionata_bisciari.GBTextEditor.gui.GBTextEditor;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorAggiorna;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorApri;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorAvvisoFileNonSalvato;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorSalva;
import it.gionata_bisciari.GBTextEditor.gui.GBTextEditorSalvaConNome;

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
				GBTextEditor.resetEditorPane();
				GBTextEditor.resetURI();
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
			String url = "www.gionata-bisciari.it";
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
			/*TODO
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
				GBTextEditor.resetEditorPane();
				GBTextEditor.resetURI();
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
		else if(e.getSource()==this.gui.getMntmJava()){
			this.generaHelloWorldJava();
		}
		else if(e.getSource()==this.gui.getMntmC()){
			this.generaHelloWorldC();
		}
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
		GBTextEditor.resetEditorPane();
		GBTextEditor.resetBuffer();
	}
	
	public void aggiornaFile(){
		new GBTextEditorAggiorna();
	}
	
	public void generaHelloWorldJava(){
		if(this.controllaSalva()){
			GBTextEditor.resetURI();
			String javaHL = ("public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Ciao mondo\");\n\t}\n}");
			GBTextEditor.setBuffer(javaHL);
			GBTextEditor.getEditorPane().setText(new String(javaHL));
			JOptionPane.showMessageDialog(null, "HelloWorld in linguaggio Java generato con successo", "HelloWorld generato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
		}
	}
	
	public void generaHelloWorldC(){
		if(this.controllaSalva()){
			GBTextEditor.resetURI();
			String cHL = ("#include <stdio.h>\n\nmain(){\n\tprintf(\"HelloWorld\");\n}");
			GBTextEditor.setBuffer(cHL);
			GBTextEditor.getEditorPane().setText(new String(cHL));
			JOptionPane.showMessageDialog(null, "HelloWorld in linguaggio C generato con successo", "HelloWorld generato", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(GBTextEditor.class.getResource("/com/gb1498/GBTextEditor/icons/GBTextEditor-INFO.png")));
		}
	}
}
