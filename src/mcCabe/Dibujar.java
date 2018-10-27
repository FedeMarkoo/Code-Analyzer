package mcCabe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dibujar {
	public Nodo nodo = new Nodo("");

	public static String test() {
		return "		codigo = codigo.replace(\"\\\\\\\"\", \"\").replaceAll(\"\\\"[^\\\"]*\\\"|'[^']*'\", \"\");\r\n"
				+ "		int lineascomentarios = 0;\r\n" + "		int lineascodigo = 0;\r\n"
				+ "		boolean comentarioMultilinea = false;\r\n"
				+ "		for (String linea : codigo.split(\"\\n\")) {\r\n" + "			linea = linea.trim();\r\n"
				+ "			if (comentarioMultilinea) {\r\n" + "				lineascomentarios++;\r\n"
				+ "				comentarioMultilinea = !linea.contains(\"*/\");\r\n"
				+ "			} else if (linea.startsWith(\"/*\"))   {\r\n"
				+ "				comentarioMultilinea = true;\r\n" + "				lineascomentarios++;\r\n"
				+ "			} else if (!linea.startsWith(\"//\")) {\r\n" + "				if (linea.length() > 2)\r\n"
				+ "					lineascodigo++;\r\n" + "				if (linea.contains(\"//\"))\r\n"
				+ "					lineascomentarios++;\r\n" + "			} else\r\n"
				+ "				lineascomentarios++;\r\n" + "		}\r\n"
				+ "		return new int[] { lineascomentarios, lineascodigo };";
	}

	public static Nodo resolver(String cod) {
		Nodo.n = 0;
		Nodo n = dibujar(cod);

		contarHijos(n);
		return n;
	}

	private static int[] contarHijos(Nodo n) {
		if (n == null)
			return new int[] { 0, 0 };
		if (n.getClass() == Nodo.class)
			return contarHijos(n.siguiente);
		else {
			NodoCondicion c = (NodoCondicion) n;
			int[] v = contarHijos(c.verdadero);
			int[] f = contarHijos(c.falso);
			int[] temp = new int[] { Math.max(v[0] + 1, f[0]), Math.max(v[1], f[1] + 1) };
			c.cantV = temp[0];
			c.cantF = temp[1];

			return temp;
		}
	}

	static Pattern p = Pattern.compile(
			"\\s(if|for|while|switch|do|else if|catch)\\s*\\(([^\\n]+)\\)\\s*(?:\\n|\\{)|\\s(try|finally)(?:\\s*|\\{)");

	public static Nodo dibujar(String cod) {
		cod = " " + cod;
		Matcher m = p.matcher(cod);
		if (m.find()) {
			String pregunta = " ", tipo = m.group(1), siguiente = cod.substring(m.end() - 1);
			if (!m.group(0).contains("try"))
				pregunta = m.group(2);
			else
				tipo = "try";
			Nodo n = new Nodo(cod.substring(0, m.start()));
			n.add(new NodoCondicion(pregunta, tipo, siguiente, cod));
			return n;// .codigo.trim().length()<3 ? n.siguiente : n;
		} else
			return new Nodo(cod);
	}

	public static Nodo dibujar(String cod, String resto) {
		String cod2 = resto.substring(resto.indexOf(cod) + cod.length());
		if (cod2.trim().startsWith("else"))
			return dibujar(cod + cod2);
		else
			return dibujar(cod);
	}

}
