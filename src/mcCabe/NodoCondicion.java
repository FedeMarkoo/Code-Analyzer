package mcCabe;

public class NodoCondicion extends Nodo {
	public Nodo verdadero, falso;
	public int cantV, cantF;

	public NodoCondicion(String pregunta, String tipo, String sig, String completo) {

		nivel = ++n + "";

		
		sig = sig.trim();
		int largo = 0;
		int coma = sig.indexOf(";");
		int llave = sig.indexOf("{");
		if (llave < 0)
			llave = 999999999;

		if (llave < coma) {
			String cod = extraerCodigo(sig);
			largo = cod.length();
			verdadero = Dibujar.dibujar(cod);
		} else {
			largo = coma;
			if (largo >= 0)
				verdadero = Dibujar.dibujar(sig.substring(0, largo + 1));
			else
				verdadero = Dibujar.dibujar(sig);
		}
		String resto = "";
		try {
			resto = sig.substring(largo + 1);

			if (resto.length() > 3)
				resto += completo.substring(completo.indexOf(resto) + resto.length());
			if ((tipo.contains("if") && resto.trim().startsWith("else")) || tipo.contains("try")) {
				coma = resto.indexOf(";");
				llave = resto.indexOf("{");
				if (llave < 0)
					llave = 999999999;

				if (llave < coma) {
					String cod = extraerCodigo(resto);
					largo += cod.length();
					falso = Dibujar.dibujar(cod, resto);
				} else {
					int largo2 = coma;
					largo += largo2;
					falso = Dibujar.dibujar(resto.substring(0, largo2));
				}
			}
		} catch (Exception e) {
		}
		if (largo >= 0 && largo < sig.length())
			resto = sig.substring(largo + 1);
		else
			resto = sig;
		siguiente = Dibujar.dibujar(resto);
		if (siguiente.equals(falso) || resto.trim().startsWith("else"))
			siguiente = new Nodo(" ");
		
		if (verdadero == null)
			verdadero = new Nodo(" ");

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
