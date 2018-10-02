package BackEnd;

import java.io.File;
import java.util.ArrayList;

public class Analizador {
	public String codigo;
	public ArrayList<Archivo> archivos = new ArrayList<>();
	private long startTime;
	private long endTime;

	public Analizador(String pathCarpeta) {
		agregar(pathCarpeta);
	}

	public void agregar(String pathCarpeta) {
		cargar(pathCarpeta);
		evaluarFans();
	}

	public static void main(String a[]) {
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
	}

	private void evaluarFans() {
		for (Archivo archivo : archivos)
			for (Clase clase : archivo.clases)
				for (Metodo metodo : clase.metodo)
					metodo.fans_Y_Halstead(this);
		tardo("evaluar");
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
		startTime = System.nanoTime();
		carga(pathCarpeta);
		tardo("leer");
		return;
	}

	private void carga(String pathCarpeta) {
		File dir = new File(pathCarpeta);
		if (dir.isFile()) {
			if (dir.getName().endsWith(".java"))
				archivos.add(new Archivo(pathCarpeta));
			return;
		}
		if (dir.isDirectory())
			for (String path : dir.list())
				carga(pathCarpeta + "\\" + path);
	}

	private void tardo(String accion) {
		endTime = System.nanoTime() - startTime;
		System.out.println(
				"Tardo " + String.format("%,dns", endTime) + " en " + accion + " " + archivos.size() + " archivos");
	}
}