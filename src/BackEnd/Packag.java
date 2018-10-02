package BackEnd;

import java.util.ArrayList;

public class Packag {

	public ArrayList<Archivo> archivos = new ArrayList<>();
	public String nombre;

	public Packag(Archivo a) {
		nombre = a.packages;
		archivos.add(a);
	}

	public void add(Archivo a) {
		archivos.add(a);
	}

	public ArrayList<Archivo> get() {
		return archivos;
	}

	public String toString() {
		return nombre;
	}
}
