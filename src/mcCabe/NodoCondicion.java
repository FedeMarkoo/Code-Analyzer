package mcCabe;

public class NodoCondicion extends Nodo {
	public Nodo verdadero, falso;

	public NodoCondicion(String pregunta, String tipo, String sig) {
		super(tipo + " - " + pregunta);
		sig = sig.trim();
		int largo = 0;
		if (sig.startsWith("{")) {
			String cod = extraerCodigo(sig);
			largo = cod.length();
			verdadero = Dibujar.dibujar(cod);
		} else {
			largo = sig.indexOf(";");
			verdadero = Dibujar.dibujar(sig.substring(0, largo));
		}
		String resto = sig.substring(largo);
		if (tipo.contains("if") && resto.trim().startsWith("else")) {
			int coma = resto.indexOf(";");
			int llave = resto.indexOf("{");
			if (llave < 0)
				llave = 999999999;

			if (llave < coma) {
				String cod = extraerCodigo(resto);
				largo += cod.length();
				falso = Dibujar.dibujar(cod);
			} else {
				int largo2 = resto.indexOf(";");
				largo += largo2;
				falso = Dibujar.dibujar(resto.substring(0, largo2));
			}
		}
		resto = sig.substring(largo);
		siguiente = Dibujar.dibujar(resto);
		if (siguiente.equals(falso))
			siguiente = null;
	}

	private String extraerCodigo(String cod) {
		String codigo = cod;
		int fin = 0;
		int inicio = fin;
		cod = cod.substring(fin);
		int index = cod.indexOf("{") + 1;
		fin += index;
		cod = cod.substring(index);
		int nivelini = nivel(cod) - 1;
		if (nivelini < 0)
			nivelini = 0;
		while (nivelini != nivel(cod)) {
			index = cod.indexOf("}") + 1;
			fin += index;
			cod = cod.substring(index);
		}
		return codigo.substring(inicio, fin);
	}

	private int nivel(String cod) {
		int largo = cod.length();
		int abiertas = largo - cod.replace("{", "").length();
		int cerradas = largo - cod.replace("}", "").length();
		int nivel = cerradas - abiertas;
		return nivel;
	}
}
