package tpfinal;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class ManejoArchivo {
		
	public static void crearCarpeta(String ruta) 
	{
        File carpeta = new File(ruta);
        if (carpeta.exists()) 
        {
            System.out.println("La carpeta ya existe.");
        } 
        else 
        {
            carpeta.mkdir();
            System.out.println("Carpeta creada con éxito.");
        }
    }

    public static void crearArchivoProductos(String ruta) 
    {
        File archivoProductos = new File(ruta);

        if (archivoProductos.exists()) 
        {
            System.out.println("El archivo ya existe.");
        } 
        else 
        {
            try (PrintWriter salida = new PrintWriter(archivoProductos)) 
            {
                System.out.println("Archivo creado con éxito.");
            } 
            catch (FileNotFoundException error) {
                System.out.println(error.getMessage());
            }
        }
    }
			
    public static void crearArchivoPersonal(String ruta)
	{
		File archivoPersonal = new File (ruta);
		
		if (archivoPersonal.exists() == true)
		{
			System.out.println("El archivo ya existe.");
		}
		else
		{
			try
			{
				PrintWriter salida = new PrintWriter(archivoPersonal);
				salida.close();
				System.out.println("Archivo creado con exito.");
			}
			catch (FileNotFoundException error)
			{
				System.out.println(error.getMessage());
			}
		}
	}	
	 
    public static void eliminarArchivoProductos(String ruta) 
    {
        File archivoProductos = new File(ruta);
        if (archivoProductos.exists()) 
        {
            if (archivoProductos.delete()) 
            {
                System.out.println("El archivo ha sido eliminado correctamente.");
            } 
            else 
            {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } 
        else 
        {
            System.out.println("No se eliminó nada porque el archivo no existe.");
        }
    }
   
    public static void eliminarArchivoPersonal(String ruta) 
    {
        File archivoPersonal = new File(ruta);
        if (archivoPersonal.exists()) 
        {
            if (archivoPersonal.delete()) 
            {
                System.out.println("El archivo ha sido eliminado correctamente.");
            } 
            else 
            {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } 
        else 
        {
            System.out.println("No se eliminó nada porque el archivo no existe.");
        }
    }  
    
    public static void eliminarArchivoLacteos(String ruta) 
    {
        File listaLacteos = new File(ruta);
        if (listaLacteos.exists()) 
        {
            if (listaLacteos.delete()) 
            {
                System.out.println("El archivo ha sido eliminado correctamente.");
            } 
            else 
            {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } 
        else 
        {
            System.out.println("No se eliminó nada porque el archivo no existe.");
        }
    }
    
    public static void eliminarArchivoRegaleria(String ruta) 
    {
        File listaRegaleria = new File(ruta);
        if (listaRegaleria.exists()) 
        {
            if (listaRegaleria.delete()) 
            {
                System.out.println("El archivo ha sido eliminado correctamente.");
            } 
            else 
            {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } 
        else 
        {
            System.out.println("No se eliminó nada porque el archivo no existe.");
        }
    }
	    
}