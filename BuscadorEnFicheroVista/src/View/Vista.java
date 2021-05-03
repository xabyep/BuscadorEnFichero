package View;

import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Vista extends JFrame {
	protected JTextField txtOrigen;
	protected JTextField txtCampoBuscado;
	protected JTextArea textArea;
	protected JButton btnBuscarBuscado;
	protected JButton btnBuscarOrigen;
	protected JButton btnBorrarOrigen;
	protected JButton btnBorrarBuscado;
	protected JButton btnBuscar;
	protected JTextField textFicheroBuscado;
	private JScrollPane scrollPane;
	protected JButton btnBorrarBuscadoTexto;
	protected JButton btnCancelar;

	

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 417);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel lblBuscador = new JLabel("BUSCADOR");
		lblBuscador.setFont(new Font("Broadway", Font.BOLD, 15));
		lblBuscador.setBounds(173, 11, 176, 14);
		getContentPane().add(lblBuscador);
		
		btnBuscarOrigen = new JButton("Buscar...");
		btnBuscarOrigen.setBounds(32, 64, 87, 23);
		getContentPane().add(btnBuscarOrigen);
		
		txtOrigen = new JTextField();
		txtOrigen.setEditable(false);
		txtOrigen.setBounds(125, 65, 261, 20);
		getContentPane().add(txtOrigen);
		txtOrigen.setColumns(10);
		
		JLabel lblSeleccionaElFichero = new JLabel("Selecciona el fichero sobre el que realizar la b\u00FAsqueda:");
		lblSeleccionaElFichero.setBounds(33, 39, 381, 14);
		getContentPane().add(lblSeleccionaElFichero);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 115, 381, 107);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblIntroduzcaCadenaDe = new JLabel("Introduzca cadena de texto a buscar:");
		lblIntroduzcaCadenaDe.setBounds(10, 11, 361, 14);
		panel.add(lblIntroduzcaCadenaDe);
		
		txtCampoBuscado = new JTextField();
		txtCampoBuscado.setBounds(10, 25, 338, 20);
		panel.add(txtCampoBuscado);
		txtCampoBuscado.setColumns(10);
		
		JLabel lblSeleccioneElFichero = new JLabel("Seleccione el fichero que contiene los elementos a buscar:");
		lblSeleccioneElFichero.setBounds(10, 50, 361, 14);
		panel.add(lblSeleccioneElFichero);
		
		btnBuscarBuscado = new JButton("Buscar...");
		btnBuscarBuscado.setBounds(10, 73, 90, 23);
		panel.add(btnBuscarBuscado);
		
		textFicheroBuscado = new JTextField();
		textFicheroBuscado.setEditable(false);
		textFicheroBuscado.setBounds(102, 75, 247, 20);
		panel.add(textFicheroBuscado);
		textFicheroBuscado.setColumns(10);
		
		btnBorrarBuscado = new JButton("...");
		//btnBorrarBuscado.setIcon(new ImageIcon("resources/papelera.png"));
		btnBorrarBuscado.setBounds(350, 75, 24, 21);
		panel.add(btnBorrarBuscado);
		
		btnBorrarBuscadoTexto = new JButton("...");
		btnBorrarBuscadoTexto.setBounds(350, 24, 24, 21);
		panel.add(btnBorrarBuscadoTexto);
		
		JLabel lblDeLasDos = new JLabel("Seleccione una de las dos opciones:");
		lblDeLasDos.setBounds(32, 98, 381, 14);
		getContentPane().add(lblDeLasDos);
		
		btnBorrarOrigen = new JButton("...");
		//btnBorrarOrigen.setIcon(new ImageIcon("resources/papelera.png"));
		btnBorrarOrigen.setBounds(390, 65, 24, 21);
		getContentPane().add(btnBorrarOrigen);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(32, 233, 382, 33);
		getContentPane().add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		scrollPane.setBounds(32, 277, 381, 49);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		JLabel lblFcoJavierDaz = new JLabel("Fco. Javier D\u00EDaz");
		lblFcoJavierDaz.setFont(new Font("Javanese Text", Font.ITALIC, 8));
		lblFcoJavierDaz.setBounds(377, 359, 67, 22);
		getContentPane().add(lblFcoJavierDaz);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(135, 342, 185, 23);
		getContentPane().add(btnCancelar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTextField getTxtOrigen() {
		return txtOrigen;
	}

	public void setTxtOrigen(JTextField txtOrigen) {
		this.txtOrigen = txtOrigen;
	}

	public JTextField getTxtCampoBuscado() {
		return txtCampoBuscado;
	}

	public void setTxtCampoBuscado(JTextField txtCampoBuscado) {
		this.txtCampoBuscado = txtCampoBuscado;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getBtnBuscarBuscado() {
		return btnBuscarBuscado;
	}

	public void setBtnBuscarBuscado(JButton btnBuscarBuscado) {
		this.btnBuscarBuscado = btnBuscarBuscado;
	}

	public JButton getBtnBuscarOrigen() {
		return btnBuscarOrigen;
	}

	public void setBtnBuscarOrigen(JButton btnBuscarOrigen) {
		this.btnBuscarOrigen = btnBuscarOrigen;
	}

	public JButton getBtnBorrarOrigen() {
		return btnBorrarOrigen;
	}

	public void setBtnBorrarOrigen(JButton btnBorrarOrigen) {
		this.btnBorrarOrigen = btnBorrarOrigen;
	}

	public JButton getBtnBorrarBuscado() {
		return btnBorrarBuscado;
	}

	public void setBtnBorrarBuscado(JButton btnBorrarBuscado) {
		this.btnBorrarBuscado = btnBorrarBuscado;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JTextField getTextFicheroBuscado() {
		return textFicheroBuscado;
	}

	public void setTextFicheroBuscado(JTextField textFicheroBuscado) {
		this.textFicheroBuscado = textFicheroBuscado;
	}

	public JButton getBtnBorrarBuscadoTexto() {
		return btnBorrarBuscadoTexto;
	}

	public void setBtnBorrarBuscadoTexto(JButton btnBorrarBuscadoTexto) {
		this.btnBorrarBuscadoTexto = btnBorrarBuscadoTexto;
	}
}
