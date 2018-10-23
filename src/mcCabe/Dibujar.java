package mcCabe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dibujar {
	public Nodo nodo;

	public static void main(String a[]) {
		new Dibujar().dibujar(
				"codigo = codigo.replace(\"\\\\\\\"\", \"\").replaceAll(\"\\\"[^\\\"]*\\\"|'[^']*'\", \"\");\r\n"
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
						+ "		return new int[] { lineascomentarios, lineascodigo };");
	}

	public Nodo dibujar(String cod) {
		Nodo n = nodo;
		int i = 0;
		Matcher m = Pattern.compile("\\s(if|for|while|switch|do)\\s*\\(([^\\n]+)\\)(?:\\s+|\\{)").matcher(cod);
		while (m.find()) {
			// agrega el codigo anterior a la pregunta
			n = n.add(new Nodo(cod.substring(i, m.start(1))));

			// agrega la pregunta
			String condicion = cod.substring(m.start(2), m.end(2));
			String porTrue = cod.substring(m.end() - 1);
			String cad2 = porTrue.substring(0, porTrue.indexOf(";"));
			if (cad2.contains("{")) {
				do
					porTrue = porTrue.substring(0, porTrue.indexOf("}"));
				while (porTrue.replace("{", "").length() > porTrue.replace("}", "").length());
			}
			n = n.add(new NodoCondicion(condicion, porTrue));

			i = m.start();
		}
		return nodo;
	}
}
