
public class Funcion {
	public String nombre;
	public String codigo;
	public int cc;
	public int lineasComentadas;

	public Funcion(String group, String full, String cod) {
		nombre = group;
		extraerCodigoDeFuncion(full, cod);
		cc = Evaluar.cc(codigo);
		lineasComentadas = Evaluar.comentarios(codigo);
	}

	private void extraerCodigoDeFuncion(String full, String cod) {
		String codigo = cod;
		int fin = cod.indexOf(full);
		int inicio = fin;
		cod = cod.substring(fin);
		int index = cod.indexOf("{") + 1;
		fin += index;
		cod = cod.substring(index);
		int nivelini = nivel(cod) - 1;
		while (nivelini != nivel(cod)) {
			index = cod.indexOf("}") + 1;
			fin += index;
			cod = cod.substring(index);
		}
		this.codigo = codigo.substring(inicio, fin).trim();
	}

	private int nivel(String cod) {
		int largo = cod.length();
		int abiertas = largo - cod.replace("{", "").length();
		int cerradas = largo - cod.replace("}", "").length();
		int nivel = cerradas - abiertas;
		return nivel;
	}

	@Override
	public String toString() {
		return "CC: " + cc + "\tLineas Comentadas: " + lineasComentadas + "\tMetodo: " + nombre;
	}

}
