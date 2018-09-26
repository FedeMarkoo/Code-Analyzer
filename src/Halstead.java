import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Halstead {
	/*
	 * N1Operadores: Que pueden ser todas las palabras reservadas (if, do, while,
	 * class, etc), los calificadores (como const, static) las palabras reservadas,
	 * y los operadores en expresiones (+, -, <>, ==, !=, <=, >>, etc).
	 */

	/*
	 * N2 Operandos: Pueden ser los identificadores que no sean palabras reservadas,
	 * las constantes numéricas, los identificadores de tipos (bool, string, char,
	 * int, long, etc), los caracteres y strings constantes.
	 */

	public int N1 = 0, N2 = 0, n2 = 0, n1 = 0;
	private HashMap<String, Integer> operadores_N1 = new HashMap<>();
	private HashMap<String, Integer> operandos__N2 = new HashMap<>();
	private Pattern pat = Pattern.compile(
			"((?:[\\w\\.\\d]+\\.)*[\\w\\d]+\\s*\\()|([\\*\\+\\=\\<\\>\\!\\-\\?]+|\\&\\&|\\|\\||\\s*(?:if|while|do|try|catch|case|switch|for|throw)[^\\w\\d]*)");

	public String toString() {
		return String.format("N: %-4d n: %-4d N1: %-4d N2: %-4d n1: %-4d n2: %-4d V: %-4.2f D: %-4.2f L: %-4.2f E: %-4.2f T: %-4.2f", N(),n(),N1,N2,n1,n2,V(),D(),L(),E(),T());
	}

	// Largo del Programa: N = N1 + N2
	public int N() {
		return N1 + N2;
	}

	// Tamaño del Vocabulario del programa: n = n1 + n2
	public int n() {
		return n1 + n2;
	}

	// Volumen del Programa: V = N * log2(n)
	public double V() {
		return (int) (N() * (Math.log(n()) / Math.log(2)));
	}

	// Nivel de Dificultad: D = (n1/2) * (N2/n2)
	public double D() {
		return (n1 / 2) * (N2 / n2);
	}

	// Nivel de Programa: L = 1/D
	public double L() {
		return 1 / D();
	}

	// Esfuerzo de Implementación: E = V*D
	public double E() {
		return V() * D();
	}

	// Tiempo de Entendimiento: T = E/18 (18 es el numero que Halstead encontró
	// experimentalmente para expresar esta magnitud en segundos)
	public double T() {
		return E() / 18;
	}

	public void add(String group) {
		Matcher mat = pat.matcher(group);
		if (mat.find()) {
			if (!operadores_N1.containsKey(group)) {
				operadores_N1.put(group, null);
				n1++;
			}
			N1++;
		} else {
			if (!operandos__N2.containsKey(group)) {
				if (group.contains("("))
					group += ")";
				operandos__N2.put(group, null);
				n2++;
			}
			N2++;
		}

	}
}
