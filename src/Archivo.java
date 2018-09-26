import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Archivo {
	public ArrayList<Clase> clases = new ArrayList<>();
	private String nombre;

	public Archivo(String path) {
		this(new File(path));
	}

	public Archivo(File file) {
		String obtenerTexto = obtenerTexto(file);
		getClases(obtenerTexto);
	}

	private String obtenerTexto(File file) {
		nombre = file.getAbsolutePath();
		Scanner text;
		try {
			text = new Scanner(file);
			String todo = "";
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

	private void getClases(String cod) {
		Pattern pat = Pattern.compile(Variables.clases);
		Matcher match = pat.matcher(cod);
		while (match.find()) {
			String full = match.group(0);
			String clase = match.group(1);
			clases.add(new Clase(clase, full, cod));
		}
	}

	@Override
	public String toString() {
		return nombre;
	}

}
