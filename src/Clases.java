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
		getFuncionesDeClase(nombre, codigo);
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
		this.codigo = codigo.substring(inicio, fin);
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
		while (match.find())
			if (!match.group(0).trim().startsWith("new"))
				funciones.add(new Funcion(match.group(1), match.group(0), cod));
	}
}
