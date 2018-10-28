package mcCabe;

public class Nodo {
	public String codigo;
	public int hijos;
	public Nodo siguiente;
	public String nivel;
	public static int n = 0;

	public Nodo() {

	}

	public Nodo(String cod) {
		codigo = cod;
		nivel = ++n + "";
	}

	public Nodo add(Nodo nodo) {
		return siguiente = nodo;
	}

	@Override
	public String toString() {
		return codigo;
	}

}
