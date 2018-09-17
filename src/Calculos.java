import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculos {

	public static ArrayList<Clases> clases = new ArrayList<>();

	public static void main(String a[]) {
		String obtenerTexto = obtenerTexto(Variables.dir);

		getClases(obtenerTexto);

		for (Clases clase : clases) {
			System.out.println("Clase: " + clase.nombre);
			for (Funcion funcion : clase.funciones) {
				System.out.println(funcion.nombre + "\tCC: " + funcion.cc);
			}
		}
	}

	private static String obtenerTexto(String dir) {
		try {
			Scanner text = new Scanner(new File(dir));
			String todo = null;
			while (text.hasNextLine())
				todo += text.nextLine() + '\n';
			text.close();
			int largo = 0;
			while (largo != todo.length()) {
				largo = todo.length();
				todo = todo.replace("  ", " ");
			}
			return todo;
		} catch (Exception e) {
		}
		return "";
	}

	public static String[] getClases(String cod) {
		ArrayList<String> lista = new ArrayList<>();
		Pattern pat = Pattern.compile(Variables.clases);
		Matcher match = pat.matcher(cod);
		while (match.find()) {
			String full = match.group(0);
			String clase = match.group(1);
			lista.add(clase);
			clases.add(new Clases(clase, full, cod));
		}
		String[] a = new String[lista.size()];
		lista.toArray(a);
		return a;
	}

}
