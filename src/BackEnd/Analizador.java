package BackEnd;
import java.io.File;
import java.util.ArrayList;

public class Analizador {
	public String codigo;
	public ArrayList<Archivo> archivos = new ArrayList<>();

	public Analizador(String pathCarpeta) {
		agregar(pathCarpeta);
	}

	public void agregar(String pathCarpeta) {
		cargar(pathCarpeta);
		evaluarFans();
	}

	public static void main(String a[]) {
		long startTime = System.nanoTime();
		String pathCarpeta;
		if (a.length != 0 && a[0].length() != 0) {
			File f = new File(a[0]);
			if (!f.isFile() && !f.isDirectory())
				pathCarpeta = a[0];
			else
				pathCarpeta = Variables.dir;
		} else
			pathCarpeta = Variables.dir;

		Analizador t = new Analizador(pathCarpeta);
		System.out.println(t);
		long endTime = System.nanoTime()- startTime;
		System.out.println("Tardo: " + String.format("%,dns", endTime) + "\tArchivo: " + t.archivos.size());
	}

	private void evaluarFans() {
		for (Archivo archivo : archivos)
			for (Clase clase : archivo.clases)
				for (Metodo metodo : clase.metodo)
					metodo.fans_Y_Halstead();
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