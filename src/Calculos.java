import java.io.File;

public class Calculos {

	public static void main(String a[]) {
		String pathCarpeta = "C:\\Users\\fedem\\Escritorio\\Facultad";

		listar(pathCarpeta);
	}

	private static void listar(String pathCarpeta) {
		File dir = new File(pathCarpeta);
		if (dir.isFile()) {
			if (dir.getName().endsWith(".java")) {
				Archivo archivo = new Archivo(pathCarpeta);
				System.out.println(archivo);
				for (Clases clase : archivo.clases) {
					System.out.println("Clase: " + clase);
					for (Funcion funcion : clase.funciones) {
						System.out.println(funcion);
					}
					System.out.println("\n");
				}
			}
			return;
		}
		if (dir.isDirectory()) {
			for (String path : dir.list()) {
				listar(pathCarpeta + "\\" + path);
			}
			return;
		}
	}
}
