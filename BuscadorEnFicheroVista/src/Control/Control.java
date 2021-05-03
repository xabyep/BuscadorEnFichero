package Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import View.ParaVista;

public class Control implements Runnable {

	private ControlTextoImp controlTextoOrigen;
	private ControlTextoImp controlTextoBuscar;
	private ControlTextoImp controlTextoDestino;
	private ParaVista paraVista;
	private String texto;
	private Thread worker;
	private String accion;
	private final AtomicBoolean running = new AtomicBoolean(false);
	
	public Control(String rutaOrigen, String rutaBuscar, String rutaDestino, ParaVista those) {
		paraVista=those;
		controlTextoOrigen = new ControlTextoImp(rutaOrigen, those.getTextArea());
		if (null != rutaBuscar) {
			controlTextoBuscar = new ControlTextoImp(rutaBuscar, those.getTextArea());
		}
		controlTextoDestino = new ControlTextoImp(rutaDestino, those.getTextArea());
	}

	public void start(String texto) {
		this.texto = texto;
		if(worker==null) {
			worker = new Thread(this);
		}
		worker.start();
	}

	public void abort() {
		running.set(false);
		accion = "CANCELADO";
		end();
	}
	
	public void cancel() {
		running.set(false);
		accion = "CANCELADO";
	}

	@Override
	public void run() {
		running.set(true);
		accion = "FINALIZADO";
		List<String> buscadas = new ArrayList<String>();
		if (texto != null && !texto.isEmpty()) {
			buscadas.add(texto);
		} else {
			buscadas = controlTextoBuscar.leerFichero();
		}

		Map<String, Integer> textoNumeroLineas = new HashMap<String, Integer>();
		for (String textoBuscado : buscadas) {
			if (textoBuscado.indexOf("*")>-1) {
				String[] linea = textoBuscado.split("\\*");
				textoNumeroLineas.put(linea[0], Integer.valueOf(linea[1]));
			} else {
				textoNumeroLineas.put(textoBuscado, new Integer(0));
			}
		}
		String linea = "";

		do {
			linea = controlTextoOrigen.leerLinea();
			int numeroLineas = 0;
			boolean encontrado = false;
			for (Entry<String, Integer> entry : textoNumeroLineas.entrySet()) {
				if (linea.indexOf(entry.getKey())>-1) {
					controlTextoDestino.guardar(linea);
					numeroLineas = entry.getValue();
					encontrado = true;
					break;
				}
			}
			for (int i = 0; encontrado && i < numeroLineas; i++) {
				linea = controlTextoOrigen.leerLinea();
				if (!linea.isEmpty()) {
					controlTextoDestino.guardar(linea);
				}
			}

		} while (!linea.isEmpty()&&running.get());
		end();

	}
	
	private void end() {
		paraVista.getTextArea().append("PROCESO "+accion);
		paraVista.desactivarActivarBotones();
		controlTextoOrigen.cerrarFicheros();
		if (controlTextoBuscar != null) {
			controlTextoBuscar.cerrarFicheros();
		}
		controlTextoDestino.cerrarFicheros();
	}

}
