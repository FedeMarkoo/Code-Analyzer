package BackEnd;

import java.util.Hashtable;

public class Proyectos {
	public String nombre;
	public Hashtable<String, Proyecto> proyectos = new Hashtable<>();

	public void add(Archivo a) {
		if (proyectos.containsKey(a.proyecto))
			proyectos.get(a.proyecto).add(a);
		else
			proyectos.put(a.proyecto, new Proyecto(a));
	}

	public Proyecto[] get() {
		Proyecto[] a = new Proyecto[proyectos.size()];
		proyectos.values().toArray(a);
		return a;
	}

	public Proyecto get(String p) {
		return proyectos.get(p);
	}
}