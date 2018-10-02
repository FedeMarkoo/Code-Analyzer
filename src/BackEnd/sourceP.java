package BackEnd;

import java.util.Hashtable;

public class sourceP {

	public String nombre;
	public Hashtable<String, Packag> p = new Hashtable<>();

	public sourceP(Archivo a) {
		nombre = a.sourcePackage;
		p.put(a.packages, new Packag(a));
	}

	public void add(Archivo a) {
		if (p.containsKey(a.packages))
			p.get(a.packages).add(a);
		else
			p.put(a.packages, new Packag(a));
	}

	public Packag get(String p) {
		return this.p.get(p);
	}

	public Packag[] get() {
		Packag[] a = new Packag[p.size()];
		p.values().toArray(a);
		return a;
	}

	public String toString() {
		return nombre;
	}
}