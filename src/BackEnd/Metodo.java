package BackEnd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metodo {
	public Clase clase;
	public String nombre;
	public String codigo;
	public String codigoCompleto;
	public String parametros;
	public int cparametros;
	public String tipo;
	public int cc;
	public int lineasComentadas;
	public int lineasCodigo;
	public int[] fanIn = new int[2]; // [0] de la clase [1] de todo lo que mando
	public int fanOut;
	public Halstead halstead;
	private boolean modoAvanzado = false;
	private static Pattern compile = Pattern.compile("[\\w\\d_]+\\s*\\(");
	public int nivelAlerta;
	public String recomendacion = "";
	private static String[] metodos = { "Cobertura de Sentencias", "Cobertura de Decision", "Cobertura de Condicion",
			"Cobertura de Condicio Multiple", "Cobertura de Condicion Decision", "Cobertura de Caminos" };

	public Metodo(String group, String full, String cod, Clase clase) {
		nombre = group;
		parametros = "(" + full.split("\\(|\\)")[1] + ")";
		cparametros = parametros.length() - parametros.replace(",", "").length();
		this.clase = clase;
		extraerCodigoDeFuncion(full, cod);
		tipo(codigo);
		int[] lineas = Evaluar.contarComentarios(codigoCompleto);
		cc = Evaluar.cc(codigo);
		lineasComentadas = lineas[0];
		lineasCodigo = lineas[1];
		halstead = new Halstead(codigo);
	}

	private void tipo(String full) {
		if (full.matches(".*public\\s+" + nombre + "\\s*\\(.*")) {
			tipo = "Constructor";
			return;
		}
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

	public void extraerCodigoDeFuncion(String full, String cod, int i) { // de prueba
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
		return nombre + parametros;
	}

	public void fans_Y_Halstead(Analizador analizador) {
		fanIn[0] = Evaluar.fanIn(this, clase);
		if (tipo.contains("Private"))
			fanIn[1] = fanIn[0];
		else
			fanIn[1] = Evaluar.fan_inTodo(this, analizador);

		Matcher m = compile.matcher(codigo);
		while (m.find())
			if (!m.group().matches("(?:if|for|while|switch)\\s*\\("))
				fanOut++;
		fanOut--;

		alertaYNiveles();
	}

	private void alertaYNiveles() {
		nivelAlerta = (int) ((2 * fanIn[1]) * Math.log(20 / cc));
		if (clase.cc < cc)
			clase.cc = cc;
		if (clase.nivelAlerta < nivelAlerta)
			clase.nivelAlerta = nivelAlerta;
		if (cc > 10)
			recomendacion += "Modularizar para reducir su complejidad\n";
		if (fanIn[1] > 7)
			recomendacion += "Testear el metodo de forma rigurosa\n";

		int op = nivelAlerta > 5 ? 5 : nivelAlerta;
		if (op >= 0 && cc > 1)
			recomendacion += "Se recomienda realizar metodo de " + metodos[op] + "\n";
	}
}
