package mcCabe;

public class NodoCondicion extends Nodo {
	public NodoCondicion(String cod, String tru, String fals) {
		super(cod);
		verdadero = new Nodo(tru);
		falso = new Nodo(fals);
	}

	public NodoCondicion(String cod, String tru) {
		super(cod);
		verdadero = new Nodo(tru);
	}

	public Nodo verdadero, falso;
}
