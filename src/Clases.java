import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clases {
	public String nombre;
	public String codigo;
	public ArrayList<Funcion> funciones = new ArrayList<>();

	public Clases(String clase, String full, String cod) {
		nombre = clase;
		extraerCodigoDeClase(full, cod);
		sacarClasesQueEstenDentroDeLaClase();
		getFuncionesDeClase(nombre, codigo);
	}

	private void sacarClasesQueEstenDentroDeLaClase() {
		int indexOf = codigo.indexOf("{");
		Matcher match = Pattern.compile("class\\s+\\w+[ ,_]*(?:extends\\s+\\w+)?\\s*(?:implements[\\s\\w,]+)?")
				.matcher(codigo.substring(indexOf));
		if (match.find()) {
			String cod = codigo;
			int fin = match.start() + indexOf;
			int inicio = fin;
			cod = cod.substring(fin);
			int index = cod.indexOf("{") + 1;
			fin += index;
			cod = cod.substring(index);
			int nivelini = nivel(cod) - 1;
			while (nivelini != nivel(cod)) {
				index = cod.indexOf("}") + 1;
				fin += index;
				cod = cod.substring(index);
			}
			String claseMetida = codigo.substring(inicio, fin);
			codigo = codigo.replace(claseMetida, "");
		}

	}

	private void extraerCodigoDeClase(String full, String cod) {
		String codigo = cod;
		int fin = cod.indexOf(full);
		int inicio = fin;
		cod = cod.substring(fin);
		int index = cod.indexOf("{") + 1;
		fin += index;
		cod = cod.substring(index);
		int nivelini = nivel(cod) - 1;
		while (nivelini != nivel(cod)) {
			index = cod.indexOf("}") + 1;
			fin += index;
			cod = cod.substring(index);
		}
		this.codigo = codigo.substring(inicio, fin).trim();
	}

	private int nivel(String cod) {
		int largo = cod.length();
		int abiertas = largo - cod.replace("{", "").length();
		int cerradas = largo - cod.replace("}", "").length();
		int nivel = cerradas - abiertas;
		return nivel;
	}

	private void getFuncionesDeClase(String clase, String cod) {
		Pattern pat = Pattern.compile(Variables.funciones);
		Matcher match = pat.matcher(cod);
		while (match.find()) {
			String group0 = match.group(0).trim();
			if (!group0.startsWith("new")) {
				Funcion funcion = new Funcion(match.group(1), group0, cod);
				cod = cod.replace(funcion.codigo, "");
				funciones.add(funcion);
			} else {
				String substring = group0.substring(group0.indexOf("{"));
				Matcher match2 = Pattern.compile(Variables.funciones).matcher(substring);
				if (match2.find()) {
					Funcion funcion = new Funcion(match.group(1), substring, cod);
					cod = cod.replace(funcion.codigo, "");
					funciones.add(funcion);
				}
			}

			match = pat.matcher(cod);
		}
	}

	@Override
	public String toString() {
		return nombre;
	}

}
