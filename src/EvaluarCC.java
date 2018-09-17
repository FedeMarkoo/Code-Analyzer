import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluarCC {
	public static int evaluar(String cod) {
		Pattern pat = Pattern.compile("(if|while|for|case|try|\\|\\||\\&\\&|(?:[^\\\"]+\\?[^;:]+:[^;:]+;))");
		Matcher match = pat.matcher(cod);
		int cc = 1;
		while (match.find())
			cc++;
		return cc;
	}
}
