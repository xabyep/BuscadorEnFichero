package Control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;


public class ControlTextoImp implements IControlTexto{
	
	
	private File archivo;
	
	private BufferedReader bufferedR;
	
	private BufferedWriter bufferW;
	
	private Long numeroLinea = 0L;
	
	private JTextArea textArea;
	
	public ControlTextoImp(String ruta, JTextArea textArea) {
		crearArchivo(ruta);
		this.textArea=textArea;
	}

	@Override
	public boolean guardar(String texto) {
		try {
			abrirFlujoTextoW();
			bufferW.write(texto);
			bufferW.flush();
		} catch (IOException e) {
			textArea.append("Error al guardar el fichero\n");
			return false;
		}
		return true;
	}
	
	public void cerrarFicheros() {
		try {
			if(bufferedR != null) {
				bufferedR.close();
			}
			if(bufferW != null) {
				bufferW.close();
			}
		} catch (IOException e) {
			textArea.append("Error al cerrar los ficheros\n");
		}
		
	}

	@Override
	public List<String> leerFichero() {
		List<String> texto=new ArrayList<String>();
		String lineaLeida = "";
		try {
			abrirFlujoTextoR();
			while((lineaLeida=bufferedR.readLine())!=null){
				texto.add(lineaLeida);
				numeroLinea++;
			}
			bufferedR.close();
		} catch (IOException e) {
			textArea.append("Error al leer el fichero, línea: "+ numeroLinea + "\n" );
		}
		return texto;
	}
	

	@Override
	public String leerLinea() {
		StringBuilder texto=new StringBuilder();
		try {
			abrirFlujoTextoR();
			String lineaLeida = bufferedR.readLine();
			if(null != lineaLeida) {
				texto.append(lineaLeida);
				texto.append("\n");
				textArea.setText("Procesando línea : "+ (++numeroLinea) + "\n" );
			}
		} catch (IOException e) {
			textArea.append("Error al leer el fichero, línea: "+ numeroLinea +"\n" );
		}
		return texto.toString();
	}

	private void crearArchivo(String ruta) {
		archivo = new File(ruta);
		if (!archivo.exists()) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				textArea.append("Error al crear el nuevo fichero: "+ruta+ "\n");
			}
		}
	}
	
	private void abrirFlujoTextoW() throws IOException {
		if(null == bufferW) {
			FileWriter fileWriter=new FileWriter(archivo,true);
			bufferW=new BufferedWriter(fileWriter);
		}
		
	}
	
	
	private void abrirFlujoTextoR() throws FileNotFoundException {
		if(null == bufferedR) {
			FileReader fileReader=new FileReader(archivo);
			bufferedR=new BufferedReader(fileReader);
		}
	}



}
