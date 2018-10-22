package BackEnd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluar {
	private static Pattern pat = Pattern.compile("[\\w_]");

	public static int cc(String cod) {
		Matcher match = Variables.patcc.matcher(cod);
		int cc = 1;
		while (match.find()) {
			cc++;
		}
		return cc;
	}

	public static String eliminarComentarios(String cod) {
		boolean comentarioMultilinea = false;
		String codigo = "";
		for (String linea : cod.split("\n")) {
			linea = linea.trim();
			if (comentarioMultilinea) {
				comentarioMultilinea = !linea.contains("*/");
			} else if (linea.startsWith("/*")) {
				comentarioMultilinea = true;
			} else if (!linea.startsWith("//")) {
				int indexOf = linea.indexOf("//");
				if (indexOf != -1) {
					codigo += linea.substring(0, indexOf);
				} else
					codigo += linea;
			}
		}
		return codigo;
	}

	public static int[] contarComentarios(String codigo) {
		codigo = codigo.replace("\\\"", "").replaceAll("\"[^\"]*\"|'[^']*'", "");
		int lineascomentarios = 0;
		int lineascodigo = 0;
		boolean comentarioMultilinea = false;
		for (String linea : codigo.split("\n")) {
			linea = linea.trim();
			if (comentarioMultilinea) {
				lineascomentarios++;
				comentarioMultilinea = !linea.contains("*/");
			} else if (linea.startsWith("/*")) {
				comentarioMultilinea = true;
				lineascomentarios++;
			} else if (!linea.startsWith("//")) {
				if (linea.length() > 2)
					lineascodigo++;
				if (linea.contains("//"))
					lineascomentarios++;
			} else
				lineascomentarios++;
		}
		return new int[] { lineascomentarios, lineascodigo };
	}

	public static int fan_inTodo(Metodo metodo, Analizador a) {
		int fain = 0;
		String nombre = metodo.nombre;
		if (metodo.tipo.contains("Constructor"))
			nombre = "(?:" + nombre + "|this)";
		Pattern compile = Pattern.compile("[^\\w\\d_]" + nombre +  "\\s*\\((.*)\\)\\s*[^{\\s]");
		for (Proyecto p : a.proyectos.get())
			for (sourceP sp : p.get())
				for (Packag pk : sp.get())
					for (Archivo ar : pk.archivos)
						for (Clase c : ar.clases)
							fain += fanIn(metodo, compile, c);

		return fain;
	}

	public static int fanIn(Metodo metodo, Pattern compile, Clase c) {
		int fain = 0;
		if (c.codigo.contains(metodo.clase.nombre)) {
			Matcher match = compile.matcher(c.codigo);
			while (match.find())
				if (cuentaComas(match.group(1), metodo.cparametros))
					fain++;
		}

		return fain;
	}

	public static int fanIn(Metodo metodo, Clase c) {
		int fain = 0;
		String nombre = metodo.nombre;
		if (metodo.tipo.contains("Constructor"))
			nombre = "(?:" + nombre + "|this)";
		Pattern compile = Pattern.compile("[^\\w\\d_]" + nombre + "\\s*\\((.*)\\)\\s*[^{\\s]");
		Matcher match = compile.matcher(c.codigo);
		while (match.find())
			if (cuentaComas(match.group(1), metodo.cparametros))
				fain++;
		return fain;
	}

	private static boolean cuentaComas(String c2, int i) {
		c2 = c2.replace("\\\"", "").replaceAll("\"[^\"]*\"|'[^']*'", "");
		char[] c = c2.toCharArray();
		int id = -1, t = c2.length();
		int nivel = 0;
		int comas = 0;
		while (++id < t && nivel >= 0) {
			if (nivel == 0 && c[id] == ',')
				comas++;
			else if (c[id] == '(')
				nivel++;
			else if (c[id] == ')')
				nivel--;
		}
		return comas == i;
	}

	public static int inicioMetodo(String cod) {
		Matcher m = pat.matcher(cod);
		if (m.find())
			return m.start();
		else
			return 0;
	}

}
