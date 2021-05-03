package Control;

import java.util.List;

public interface IControlTexto {
	public boolean guardar(String texto);
	public List<String> leerFichero();
	public String leerLinea();
}
