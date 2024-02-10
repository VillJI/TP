package tpfinal;

public class Main {

	public static void main(String[] args) {
		ManejoArchivo.crearCarpeta("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos");
		ManejoArchivo.crearArchivoProductos("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoProductos.txt");
		ManejoArchivo.crearArchivoPersonal("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoPersonal.txt");
		Lacteo.crearArchivo("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\\\src\\Archivos\\listaLacteos.txt");
		Regaleria.crearArchivo("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\listaRegaleria.txt");
		
		Producto.leerArchivoProductos("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoProductos.txt");
		Lacteo.leerArchivo("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\\\src\\Archivos\\listaLacteos.txt");
		Regaleria.leerArchivo("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\listaRegaleria.txt");
		Personal.leerArchivoPersonal("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoPersonal.txt");
			
		Menu.MenuGeneral();
	}
}
		
	