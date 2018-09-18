import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluar {
	public static int cc(String cod) {
		cod = eliminarComentarios(cod);
		Pattern pat = Pattern
				.compile("\\W(?:if|while|for|case|try)\\W|(?:\\|\\||\\&\\&|(?:[^\\\"]+\\?[^;:]+:[^;:]+;))");
		Matcher match = pat.matcher(cod);
		int cc = 1;
		while (match.find())
			cc++;
		return cc;
	}

	private static String eliminarComentarios(String cod) {
		boolean comentarioMultilinea = false;
		String codigo = "";
		for (String linea : cod.split("\n")) {
			linea = linea.trim();
			if (comentarioMultilinea) {
				comentarioMultilinea = !linea.contains("*/");
			} else if (linea.startsWith("/*")) {
				comentarioMultilinea = true;
			} else if (!linea.startsWith("\\\\"))
				codigo += linea;
		}
		return codigo;
	}

	public static int comentarios(String codigo) {
		int i = 0;
		boolean comentarioMultilinea = false;
		for (String linea : codigo.split("\n")) {
			linea = linea.trim();
			if (comentarioMultilinea) {
				i++;
				comentarioMultilinea = !linea.contains("*/");
			} else if (linea.startsWith("/*")) {
				comentarioMultilinea = true;
				i++;
			} else if (linea.startsWith("\\\\"))
				i++;
		}
		return i;
	}
}
