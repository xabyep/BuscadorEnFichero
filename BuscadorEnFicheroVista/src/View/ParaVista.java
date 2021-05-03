package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Control.Control;

public class ParaVista extends Vista {
	private String rutaOrigen;
	private String rutaBuscado;
	private String sufijoNuevo = "_BUSC";
	private Control control;
	private ParaVista those;

	public ParaVista() {
		aniadirListener();
		those=this;
	}

	private void aniadirListener() {
		btnBuscarOrigen.addActionListener(actionBuscarOrigen);
		btnBorrarOrigen.addActionListener(actionBorrarOrigen);
		btnBuscarBuscado.addActionListener(actionBuscado);
		btnBorrarBuscado.addActionListener(actionBorrarBuscado);
		btnBorrarBuscadoTexto.addActionListener(actionBorrarBuscadoTexto);
		txtCampoBuscado.addKeyListener(keyListener);
		btnBuscar.addMouseListener(actionBuscar);
		btnCancelar.addActionListener(accionCancelar);
		this.addWindowListener(accionCerrar);
		
	}
	
	WindowAdapter accionCerrar = new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
	       if(control!=null) {
	    	   control.abort();
	       }
	    }
	};
	
	ActionListener accionCancelar = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	       if(control!=null) {
	    	   control.cancel();
	       }
	    }
	};
	

	MouseAdapter actionBuscar = new MouseAdapter() {
		public void mouseReleased(java.awt.event.MouseEvent e) {
			textArea.setText("");
			String rutaDestino = obtenerRutaDestino();
			control = new Control(rutaOrigen, rutaBuscado, rutaDestino,those);
			String texto = txtCampoBuscado.getText();
            desactivarActivarBotones();
            control.start(texto);
		}
	
	};
	
	

	ActionListener actionBuscarOrigen = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			File fichero = obtenerFichero(txtOrigen);
			if (fichero != null) {
				rutaOrigen = fichero.getAbsolutePath();
			}
			activarBotonBuscar();
		}
	};
	
	KeyAdapter keyListener = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			if(!txtCampoBuscado.getText().isEmpty()) {
				btnBuscar.setEnabled(true);
				borrarFicheroBuscado();
			}
			activarBotonBuscar();
		}
	};

	ActionListener actionBorrarOrigen = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			borrarFicheroOrigen();
			activarBotonBuscar();
		}
	};

	ActionListener actionBuscado = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			File fichero = obtenerFichero(textFicheroBuscado);
			if (fichero != null) {
				rutaBuscado = fichero.getAbsolutePath();
				txtCampoBuscado.setText("");
			}
			activarBotonBuscar();
			
		}

	};

	ActionListener actionBorrarBuscado = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			borrarFicheroBuscado();
			activarBotonBuscar();
		}
	};
	
	ActionListener actionBorrarBuscadoTexto = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			borrarFicheroBuscadoBuscado();
			activarBotonBuscar();
		}
	};
	
	private void activarBotonBuscar() {
		textArea.setText("");
		if(rutaOrigen != null && (rutaBuscado!=null||!txtCampoBuscado.getText().isEmpty())) {
			btnBuscar.setEnabled(true);
		}else {
			btnBuscar.setEnabled(false);
		}
	}
	
	public void desactivarActivarBotones() {
		btnBorrarBuscado.setEnabled(!btnBorrarBuscado.isEnabled());
		btnBorrarBuscadoTexto.setEnabled(!btnBorrarBuscadoTexto.isEnabled());
		btnBorrarOrigen.setEnabled(!btnBorrarOrigen.isEnabled());
		btnBuscar.setEnabled(!btnBuscar.isEnabled());
		btnBuscarBuscado.setEnabled(!btnBuscarBuscado.isEnabled());
		btnBuscarOrigen.setEnabled(!btnBuscarOrigen.isEnabled());
		txtCampoBuscado.setEnabled(!txtCampoBuscado.isEnabled());
		btnCancelar.setEnabled(!btnCancelar.isEnabled());
	};
	
	private void borrarFicheroOrigen() {
		rutaOrigen = null;
		txtOrigen.setText("");
	}
	
	private void borrarFicheroBuscado() {
		rutaBuscado = null;
		textFicheroBuscado.setText("");
	}
	
	private void borrarFicheroBuscadoBuscado() {
		txtCampoBuscado.setText("");
	}

	private File obtenerFichero(JTextField campoTexto) {
		File fichero = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = fileChooser.showOpenDialog(getContentPane());
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			fichero = fileChooser.getSelectedFile();
			campoTexto.setText(fichero.getName());
		}
		return fichero;
	}
	
	private String obtenerRutaDestino() {
		String nombreFichero =  rutaOrigen.substring(rutaOrigen.lastIndexOf("\\"));
		String rutaNueva = rutaOrigen.substring(0,rutaOrigen.lastIndexOf("\\"));
		String nombreNuevo = nombreFichero;
		if(nombreFichero.contains(".")) {
			String extension = nombreFichero.substring(nombreFichero.lastIndexOf("."));
			String nombre = nombreFichero.substring(0, nombreFichero.lastIndexOf("."));
			nombreNuevo = nombre+sufijoNuevo+extension;
		}
		rutaNueva = rutaNueva+nombreNuevo;
		return rutaNueva;
	}

}
