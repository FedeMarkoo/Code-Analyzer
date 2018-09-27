public class Metodo {
	public Clase clase;
	public String nombre;
	public String codigo;
	public String codigoCompleto;
	public String tipo;
	public int cc;
	public int lineasComentadas;
	public int lineasCodigo;
	public int[] fanIn = new int[2]; // [0] de la clase [1] de todo lo que mando
	public int[] fanOut = new int[2];
	public Halstead halstead;
	private boolean modoAvanzado = false;

	public Metodo(String group, String full, String cod, Clase clase) {
		nombre = group;
		this.clase = clase;
		extraerCodigoDeFuncion(full, cod);
		tipo(codigo);
		int[] lineas = Evaluar.comentarios(codigoCompleto);
		cc = Evaluar.cc(codigo);
		lineasComentadas = lineas[0];
		lineasCodigo = lineas[1];
		halstead = new Halstead(codigo);
	}

	private void tipo(String full) {
		if (full.contains("static")) {
			full = full.replace("static", "").trim();
			tipo = "Static ";
		} else
			tipo = "";
		if (full.startsWith("private "))
			tipo += "Private";
		else if (full.startsWith("public "))
			tipo += "Public";
		else if (full.startsWith("protected "))
			tipo += "Protected";
		else
			tipo += "Default";
	}

	private void extraerCodigoDeFuncion(String full, String cod) {
		full = full.substring(Evaluar.inicioMetodo(full));

		String codigo = cod;
		int fin = cod.indexOf(full);
		int inicio = fin;
		cod = cod.substring(fin);
		int index = cod.indexOf("{") + 1;
		fin += index;
		modoAvanzado = nivel(cod) != 1;
		cod = cod.substring(index);
		int nivelini = nivel(cod) - 1;
		while (nivelini != nivel(cod)) {
			index = cod.indexOf("}") + 1;
			fin += index;
			cod = cod.substring(index);
		}
		this.codigoCompleto = codigo.substring(inicio, fin).trim();
		this.codigo = Evaluar.eliminarComentarios(codigoCompleto);
	}

	private int nivel(String cod) {
		if (modoAvanzado)
			cod = cod.replaceAll("(?:\\(|,\\s*)\".*\"(?:\\)|,)", "()");
		int largo = cod.length();
		int abiertas = largo - cod.replace("{", "").length();
		int cerradas = largo - cod.replace("}", "").length();
		int nivel = cerradas - abiertas;
		return nivel;
	}

	@Override
	public String toString() {
		return "CC: " + cc + "\tComentarios: " + lineasComentadas + "\tCodigo: " + lineasCodigo + "\tFanInC:" + fanIn[0]
				+ "\tFanInT:" + fanIn[1] + "\tHalstead: " + halstead + "\tTipo: " + tipo + "\tMetodo: " + nombre;
	}

	public void fans_Y_Halstead() {
		fan();
	}

	private void fan() {
		fanIn[0] = clase.fan_inClase(this);
		if (tipo.equals("Private"))
			fanIn[1] = fanIn[0];
		else
			fanIn[1] = Evaluar.fan_inTodo(this);
	}
}