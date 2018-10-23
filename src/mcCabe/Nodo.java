package mcCabe;

public class Nodo {
	public String codigo;
	public Nodo siguiente;

	public Nodo(String cod) {
		codigo=cod;
	}

	public Nodo add(Nodo nodo) {
		return siguiente = nodo;
	}

	@Override
	public String toString() {
		return codigo;
	}
	
	

}
