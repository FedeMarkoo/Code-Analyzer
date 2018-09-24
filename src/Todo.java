import java.io.File;
import java.util.ArrayList;

public class Todo {
	public String codigo;
	public ArrayList<Archivo> archivos = new ArrayList<>();

	public Todo(String pathCarpeta) {
		cargar(pathCarpeta);
		evaluarFans();
	}

	private void evaluarFans() {
		for (Archivo archivo : archivos)
			for (Clase clase : archivo.clases)
				for (Metodo metodo : clase.metodo)
					metodo.fans();
	}

	public String toString() {
		String out = "";
		for (Archivo archivo : archivos) {
			out += "\n\n" + archivo + "\n";
			for (Clase clase : archivo.clases) {
				out += "\n" + clase + "\n";
				for (Metodo metodo : clase.metodo) {
					out += metodo + "\n";
				}
			}
		}
		return out;
	}

	private void cargar(String pathCarpeta) {
		File dir = new File(pathCarpeta);
		if (dir.isFile()) {
			if (dir.getName().endsWith(".java"))
				archivos.add(new Archivo(pathCarpeta));
			return;
		}
		if (dir.isDirectory())
			for (String path : dir.list())
				cargar(pathCarpeta + "\\" + path);
		return;
	}
}