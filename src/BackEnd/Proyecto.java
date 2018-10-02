package BackEnd;

import java.util.Hashtable;

public class Proyecto {

	public String nombre;
	public Hashtable<String, sourceP> sourcePackage = new Hashtable<>();

	public Proyecto(Archivo a) {
		nombre = a.proyecto;
		sourcePackage.put(a.sourcePackage, new sourceP(a));
	}

	@Override
	public String toString() {
		return nombre;
	}

	public void add(Archivo a) {
		if (sourcePackage.containsKey(a.sourcePackage))
			sourcePackage.get(a.sourcePackage).add(a);
		else
			sourcePackage.put(a.sourcePackage, new sourceP(a));
	}

	public sourceP get(String p) {
		return sourcePackage.get(p);
	}

	public sourceP[] get() {
		sourceP[] a = new sourceP[sourcePackage.size()];
		sourcePackage.values().toArray(a);
		return a;
	}
}