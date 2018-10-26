package mcCabe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dibujar {
	public Nodo nodo = new Nodo("");

	public static String test() {
		return "		codigo = codigo.replace(\"\\\\\\\"\", \"\").replaceAll(\"\\\"[^\\\"]*\\\"|'[^']*'\", \"\");\r\n"
						+ "		int lineascomentarios = 0;\r\n" + "		int lineascodigo = 0;\r\n"
						+ "		boolean comentarioMultilinea = false;\r\n"
						+ "		for (String linea : codigo.split(\"\\n\")) {\r\n"
						+ "			linea = linea.trim();\r\n" + "			if (comentarioMultilinea) {\r\n"
						+ "				lineascomentarios++;\r\n"
						+ "				comentarioMultilinea = !linea.contains(\"*/\");\r\n"
						+ "			} else if (linea.startsWith(\"/*\"))   {\r\n"
						+ "				comentarioMultilinea = true;\r\n" + "				lineascomentarios++;\r\n"
						+ "			} else if (!linea.startsWith(\"//\")) {\r\n"
						+ "				if (linea.length() > 2)\r\n" + "					lineascodigo++;\r\n"
						+ "				if (linea.contains(\"//\"))\r\n"
						+ "					lineascomentarios++;\r\n" + "			} else\r\n"
						+ "				lineascomentarios++;\r\n" + "		}\r\n"
						+ "		return new int[] { lineascomentarios, lineascodigo };";
	}

	public static Nodo resolver(String cod) {
		Nodo.n = 0;
		Nodo n = dibujar(cod);

		n.hijos = contarHijos(n);
		return n;
	}

	private static int contarHijos(Nodo n) {
		if (n == null)
			return 0;
		if (n.getClass() == Nodo.class)
			return n.hijos = 2 + contarHijos(n.siguiente);
		else {
			NodoCondicion c = (NodoCondicion) n;
			int max = 0;
			max = Math.max(max, contarHijos(c.falso));
			max = Math.max(max, contarHijos(c.verdadero));
			max = Math.max(max, contarHijos(c.siguiente));
			return c.hijos = max + 1;
		}
	}

	static Pattern p = Pattern.compile("\\s(if|for|while|switch|do|else if)\\s*\\(([^\\n]+)\\)\\s*(?:\\n|\\{)");

	public static Nodo dibujar(String cod) {
		cod = " " + cod;
		Matcher m = p.matcher(cod);
		if (m.find()) {
			Nodo n = new Nodo(cod.substring(0, m.start()));
			String pregunta = m.group(2), tipo = m.group(1), siguiente = cod.substring(m.end() - 1);
			n.add(new NodoCondicion(pregunta, tipo, siguiente, cod));
			return n;
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
