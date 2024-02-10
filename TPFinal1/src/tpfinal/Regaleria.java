package tpfinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Regaleria extends Producto {
    private String materialFabricacion;

    
    public Regaleria(int codigoBarra, String marca, String descripcion, double precio, int stock, String materialFabricacion) 
    {
        super(codigoBarra, marca, descripcion, precio, stock);
        this.materialFabricacion = materialFabricacion;
        setCategoria(Categoria.REGALERIA);
        setCodigoCategoria(Categoria.REGALERIA.getCodigo());
    }
    
    public static Stack<Regaleria> pilaRegaleria = new Stack<Regaleria>();
    

	public String obtenerInformacion() 
	{
		return  "Categoria: " + this.getCategoria() +
				"\nCodigo de Barras: " + this.getCodigoBarra() +
				"\nMarca: " + this.getMarca() +
 				"\nDescripción: " + this.getDescripcion() +
 				"\nMaterial: " + this.getMaterialFabricacion() +
	            "\nPrecio: $" + this.getPrecio() +
	            "\nCantidad en stock: " + this.getStock();
	}

	public String getMaterialFabricacion() 
	{
		return materialFabricacion;
	}

	public void setMaterialFabricacion(String materialFabricacion) 
	{
		this.materialFabricacion = materialFabricacion;
	}
	
	public static void crearRegaleria() 
	{
        Scanner entrada = new Scanner(System.in);

        try 
        {
        	System.out.println("\nIngrese el código de barras:");
            int codigoBarra = validarNumeroEntero("código de barras");

            for (Producto producto : listaProductos) 
            {
                if (producto.getCodigoBarra() == codigoBarra) 
                {
                    System.out.println("El código de barras ya existe en la lista de productos. No se puede crear el producto.");
                    return;
                }
            }
            System.out.println("Ingrese la marca:");
            String marca = entrada.nextLine();
            
            System.out.println("Ingrese la descripción:");
            String descripcion = entrada.nextLine();
            
            System.out.println("Ingrese material de fabricacion:");
            String materialFabricacion = entrada.nextLine();
            
            System.out.println("Ingrese el precio:");
            double precio = validarNumeroDecimal("precio");
            
            System.out.println("Ingrese el stock:");
            int stock = validarNumeroEntero("stock");
            
            Regaleria nuevaRegaleria = new Regaleria(codigoBarra, marca, descripcion, precio, stock, materialFabricacion);

            pilaRegaleria.push(nuevaRegaleria);
            listaProductos.add(nuevaRegaleria);
            Regaleria.escribirArchivo("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\listaRegaleria.txt", Regaleria.pilaRegaleria);
            Producto.escribirArchivoProductos("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoProductos.txt", Producto.listaProductos);

            System.out.println("Producto creado con éxito.");
            System.out.println("--------------------------"); 
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Error: Ingrese un formato válido para el código de barras, precio o stock.");
        } 
        catch (Exception e) 
        {
            System.out.println("Error: Algo salió mal. Verifique los datos ingresados.");
        } 
    }
	
	public static void mostrarPilaRegaleria() 
	{
	    if (pilaRegaleria.isEmpty()) 
	    {
	        System.out.println("La lista de Regaleria está vacía.");
	        System.out.println("---------------------------------");
	    } 
	    else 
	    {
	        System.out.println("\nLista de Regaleria:");
	        System.out.println("---------------------");
	        for (Regaleria regaleria : pilaRegaleria) 
	        {
	            System.out.println(regaleria.obtenerInformacion());
	            System.out.println("-------------------------------");
	        }
	    }
	}
	
	public static int validarNumeroEntero(String tipoNumero) 
	{
        Scanner entrada = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                int numero = entrada.nextInt();
                entrada.nextLine();
                
                if (numero >= 0) 
                {
                    return numero;
                } 
                else 
                {
                    System.out.println("Error: Ingrese un número válido.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido para " + tipoNumero + ".");
                entrada.nextLine();
            }
        }
    }

	public static double validarNumeroDecimal(String tipoNumero) 
	{
        Scanner entrada = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                double numero = entrada.nextDouble();
                entrada.nextLine();
                
                if (numero >= 0) 
                {
                    return numero;
                } 
                else 
                {
                    System.out.println("Error: Ingrese un número válido.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido para " + tipoNumero + ".");
                entrada.nextLine();
            }
        }
    }
    
    public static void crearArchivo(String ruta) 
    {
        File listaRegaleria = new File(ruta);

        try 
        {
            if (listaRegaleria.exists()) 
            {
                System.out.println("El archivo ya existe.");
            } 
            else 
            {
                if (listaRegaleria.createNewFile()) 
                {
                    System.out.println("Archivo creado con éxito.");
                } 
                else 
                {
                    System.out.println("No se pudo crear el archivo.");
                }
            }
        } 
        catch (IOException error) 
        {
            System.out.println(error.getMessage());
        }
    }

    public static void escribirArchivo(String ruta, Stack<Regaleria> pilaRegaleria) 
    {
        try 
        {
            if (!pilaRegaleria.isEmpty()) 
            {
                Regaleria ultimaRegaleria = pilaRegaleria.peek();

                PrintWriter escritura = new PrintWriter(new FileWriter(ruta, true));
                escritura.println("REGALERIA;" + ultimaRegaleria.getCodigoBarra() +
                        				   ";" + ultimaRegaleria.getMarca() +
                        				   ";" + ultimaRegaleria.getDescripcion() +
                        				   ";" + ultimaRegaleria.getMaterialFabricacion() +
                        				   ";" + ultimaRegaleria.getPrecio() +
                        				   ";" + ultimaRegaleria.getStock());
                escritura.close();
            } 
            else 
            {
                System.out.println("No hay elementos para agregar al archivo.");
            }
        } 
        catch (FileNotFoundException error) 
        {
            System.out.println(error.getMessage());
        } 
        catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
    
    public static void leerArchivo(String ruta) 
    {
        try (BufferedReader lectura = new BufferedReader(new FileReader(ruta)))
        {
            String contenido = lectura.readLine();
            while (contenido != null) 
            {
                String[] partes = contenido.split(";");
                if (partes.length >= 6 && partes[0].equals("REGALERIA")) 
                {
                    int codigoBarra = Integer.parseInt(partes[1]);
                    String marca = partes[2];
                    String descripcion = partes[3];
                    String materialFabricacion = partes[4];
                    double precio = Double.parseDouble(partes[5]);
                    int stock = Integer.parseInt(partes[6]);

                    Regaleria regaleria = new Regaleria(codigoBarra, marca, descripcion, precio, stock, materialFabricacion);
                    pilaRegaleria.push(regaleria);
                } 
                else 
                {
                    System.out.println("La línea no tiene el formato esperado: " + contenido);
                }
                contenido = lectura.readLine();
            }
        } 
        catch (IOException error) 
        {
            System.out.println(error.getMessage());
        }
    }
}