import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Halstead {

	/*
	 * N1 Operadores: Que pueden ser todas las palabras reservadas (if, do, while,
	 * class, etc), los calificadores (como const, static) las palabras reservadas,
	 * y los operadores en expresiones (+, -, <>, ==, !=, <=, >>, etc).
	 */

	/*
	 * N2 Operandos: Pueden ser los identificadores que no sean palabras reservadas,
	 * las constantes num�ricas, los identificadores de tipos (bool, string, char,
	 * int, long, etc), los caracteres y strings constantes.
	 */

	public double N1 = 0, N2 = 0, n2 = 0, n1 = 0;
	private HashMap<String, Integer> operadores_N1 = new HashMap<>();
	private HashMap<String, Integer> operandos__N2 = new HashMap<>();
	private Pattern pat = Pattern.compile(
			"((?:[\\w\\.\\d]+\\.)*[\\w\\d]+\\s*\\()|([\\*\\+\\=\\<\\>\\!\\-\\?]+|\\&\\&|\\|\\||\\s*(?:if|while|do|try|catch|case|switch|for|throw)[^\\w\\d]*)");

	private static final Pattern p = Pattern.compile(
			"(\"[^\\\"]*\"|\'[^\\\']*\'|[\\w\\d_.]+\\(?|[\\*\\+\\=\\<\\>\\!\\-\\?]+|[^/]\\/[^/]|\\&\\&|\\|\\|)");

	public Halstead(String codigo) {
		new Thread() {
			public void run() {
				Matcher match = p.matcher(codigo.substring(codigo.indexOf("{")));
				while (match.find()) {
					add(match.group(0));
				}
			}
		}.start();

	}

	public String toString() {
		return String.format(
				"N: %-4d n: %-4d N1: %-4d N2: %-4d n1: %-4d n2: %-4d V: %-4.2f D: %-4.2f L: %-4.2f E: %-4.2f T: %-4.2f",
				N(), n(), (int) N1, (int) N2, (int) n1, (int) n2, V(), D(), L(), E(), T());
	}

	// Largo del Programa: N = N1 + N2
	/**
	 * LONGITUD Halstead permite obtener una medida de la longitud, N, de un
	 * programa, que es calculada como:
	 * 
	 * longitud N = N1 + N2
	 * 
	 * N es una simple medida del tama�o de un programa. Cuanto m�s grande sea el
	 * tama�o de N, mayor ser� la dificultad para comprender el programa y mayor el
	 * esfuerzo para mantenerlo.
	 * 
	 * N es una medida alternativa al simple conteo de l�neas de c�digo. Aunque es
	 * casi igual de f�cil de calcular, N es m�s sensible a la complejidad que el
	 * contar el n�mero de l�neas porque N no asume que todas las instrucciones son
	 * igual de f�cil o de dif�cil de entender.
	 * 
	 */
	public int N() {
		return (int) (N1 + N2);
	}

	// Tama�o del Vocabulario del programa: n = n1 + n2
	/**
	 * devuelve la cantidad de palabras distintas en el codigo
	 */
	public int n() {
		return (int) (n1 + n2);
	}

	// Volumen del Programa: V = N * log2(n)
	/**
	 * VOLUMEN La medida de longitud, N, es usada en otra estimaci�n de tama�o de
	 * Halstead llamada volumen, V,
	 * 
	 * Mientras que la longitud es una simple cuenta (o estimaci�n) del total de
	 * operadores y operandos, el volumen da un peso extra al n�mero de operadores y
	 * operandos �nicos.
	 * 
	 * Esta medida de volumen se puede interpretar como el n�mero de "comparaciones
	 * mentales" necesarias para escribir un programa de longitud N. Esta
	 * interpretaci�n sugiere que la mente humana sigue un proceso de b�squeda
	 * binaria para seleccionar un token de un vocabulario de tama�o n.
	 * 
	 * Por ejemplo, si dos programas tienen la misma longitud N pero uno tiene mayor
	 * n�mero de operadores y operandos �nicos, que naturalmente lo hacen m�s
	 * dif�cil de entender y mantener, este tendr� un mayor volumen.
	 * 
	 * La f�rmula es la siguiente:
	 * 
	 * volumen V = N x log2 (n)
	 * 
	 * donde n = n1 + n2
	 * 
	 */
	public double V() {
		return (int) (N() * (Math.log(n()) / Math.log(2)));
	}

	// Nivel de Dificultad: D = (n1/2) * (N2/n2)'
	/**
	 * DIFICULTAD Para definir la dificultad D del programa, se usa la f�rmula
	 * siguiente:
	 * 
	 * dificultad D = (n1 * N2) / (n2 *2)
	 * 
	 * Dificultad = ((operadores unicos)*(operandos totales))/(operadores unicos *2)
	 */
	public double D() {
		return (n1 / 2) * (N2 / n2);
	}

	// Nivel de Programa: L = 1/D
	public double L() {
		return 1 / D();
	}

	// Esfuerzo de Implementaci�n: E = V*D
	/**
	 * Esfuerzo E = D * V � V / L
	 * 
	 * Donde el volumen V es multiplicado por la medida de dificultad D con la que
	 * se hizo el programa.
	 * 
	 * Atendiendo a varios estudios emp�ricos, el esfuerzo, E, es incluso una medida
	 * mejor de la entendibilidad (comprensi�n) que N.
	 */
	public double E() {
		return V() * D();
	}

	// Tiempo de Entendimiento: T = E/18 (18 es el numero que Halstead encontr�
	// experimentalmente para expresar esta magnitud en segundos)
	public double T() {
		return E() / 18;
	}

	private void add(String group) {
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
