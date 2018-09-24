import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluar {
	private static String codigos = "";
	private static Pattern pat = Pattern.compile("[\\w_]");

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
			} else if (!linea.startsWith("\\\\")) {
				int indexOf = linea.indexOf("\\\\");
				if (indexOf != -1) {
					codigo += linea.substring(0, indexOf);
				} else
					codigo += linea;
			}
		}
		return codigo;
	}

	public static int[] comentarios(String codigo) {
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
			} else if (!linea.startsWith("\\\\")) {
				lineascodigo++;
				if (linea.contains("\\\\"))
					lineascomentarios++;
			}
		}
		return new int[] { lineascomentarios, lineascodigo };
	}

	public static int fan_inTodo(Metodo metodo) {
		int fain = 0;
		Matcher match = Pattern.compile("[^\\w\\d_]" + metodo.nombre + "[^\\w\\d_]").matcher(codigos);
		while (match.find())
			fain++;
		return fain;
	}

	public static void setCodigos(String codigos) {
		Evaluar.codigos += codigos;
	}

	public static int inicioMetodo(String cod) {
		Matcher m = pat.matcher(cod);
		if (m.find())
			return m.start();
		else
			return 0;
	}

}
