package BackEnd;

import java.io.File;

public class Analizador {
	public String codigo;
	private long startTime;
	private long endTime;
	public Proyectos proyectos = new Proyectos();
	private int archivos = 0;

	public Analizador(String pathCarpeta) {
		agregar(pathCarpeta);
	}

	public void agregar(String pathCarpeta) {
		cargar(pathCarpeta);
		evaluarFans();
	}

	private void evaluarFans() {
		for (Proyecto p : proyectos.get())
			for (sourceP sp : p.get())
				for (Packag pk : sp.get())
					for (Archivo archivo : pk.archivos)
						for (Clase clase : archivo.clases)
							for (Metodo metodo : clase.metodo)
								metodo.fans_Y_Halstead(this);
		tardo("evaluar");
	}

	public String toString() {
		String out = "";
		for (Proyecto p : proyectos.get())
			for (sourceP sp : p.get())
				for (Packag pk : sp.get())
					for (Archivo archivo : pk.archivos) {
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
			if (dir.getName().endsWith(".java")) {
				Archivo archivo = new Archivo(pathCarpeta);
				proyectos.add(archivo);
				archivos++;
			}
			return;
		}
		if (dir.isDirectory())
			for (String path : dir.list())
				carga(pathCarpeta + "\\" + path);
	}

	private void tardo(String accion) {
		endTime = System.nanoTime() - startTime;
		System.out.println("Tardo " + String.format("%,dns", endTime) + " en " + accion + " " + archivos + " archivos");
	}
}