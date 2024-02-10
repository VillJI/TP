package tpfinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public abstract class Producto {
	private int codigoBarra;
	private Categoria Categoria;
    private int codigoCategoria;
    private String marca;
    private String descripcion;
    private double precio;
    private int stock;

   
    public Producto(int codigoBarra, String marca, String descripcion, double precio, int stock) 
    {
        this.codigoBarra = codigoBarra;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    
    public static ArrayList <Producto> listaProductos = new ArrayList<Producto>();

    public abstract String obtenerInformacion();
    
    
	public int getCodigoBarra() 
	{
		return codigoBarra;
	}

	public void setCodigoBarra(int codigoBarra) 
	{
		this.codigoBarra = codigoBarra;
	}

	public int getCodigoCategoria() 
	{
		return codigoCategoria;
	}
	
	public Categoria getCategoria() 
	{
		return Categoria;
	}

	public void setCategoria(Categoria categoria) 
	{
		Categoria = categoria;
	}

	public void setCodigoCategoria(int codigoCategoria) 
	{
		this.codigoCategoria = codigoCategoria;
	}
	
	public String getMarca() 
	{
		return marca;
	}
	
	public void setMarca(String marca) 
	{
		this.marca = marca;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public double getPrecio()
	{
		return precio;
	}

	public void setPrecio(double precio) 
	{
		this.precio = precio;
	}

	public int getStock() 
	{
		return stock;
	}

	public void setStock(int stock) 
	{
		this.stock = stock;
	}
	
	public static void mostrarListaProductos() 
	{
        try
        {
			if (listaProductos.isEmpty()) 
	        {
	            System.out.println("La lista de productos está vacía.");
	            System.out.println("---------------------------------");
	        } 
	        else 
	        {
	            System.out.println("\nLista de Productos:");
	            System.out.println("---------------------");
	            for (Producto producto : listaProductos) 
	            {
	                System.out.println(producto.obtenerInformacion());
	                System.out.println("----------------------------------");
	            }
	        }
		}
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al mostrar la lista de productos: " + e.getMessage());
        }
    }
	
	public static void consultarProductoPorCodigoBarra() 
	{
        Scanner dato = new Scanner(System.in);
        try
        {
	        System.out.println("\nIngrese el código de barras para buscar el producto:");
	        int codigoBusqueda = dato.nextInt();
	
	        boolean encontrado = false;
	
	        for (Producto producto : listaProductos) 
	        {
	            if (producto.getCodigoBarra() == codigoBusqueda) 
	            {
	                System.out.println("Producto encontrado:");
	                System.out.println("--------------------");
	                System.out.println(producto.obtenerInformacion());
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado) 
	        {
	            System.out.println("No se encontró ningún producto con ese código de barras.");
	            System.out.println("--------------------------------------------------------");
	        }
        }
        catch (InputMismatchException e) 
        {
            System.out.println("Error: Ingrese un número válido para buscar el producto.");
        } 
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al consultar el producto: " + e.getMessage());
        }
    }
	
	public static void altaBajaStock(ArrayList<Producto> listaProductos) 
	{
        Scanner entrada = new Scanner(System.in);
        try 
        {
            System.out.print("\nIngrese el código de barras del producto: ");
            int codigoBusqueda = entrada.nextInt();
            entrada.nextLine();

            boolean encontrado = false;

            for (Producto producto : listaProductos) 
            {
                if (producto.getCodigoBarra() == codigoBusqueda) 
                {
                    System.out.println("Producto encontrado:");
                    System.out.println("--------------------");
                    System.out.println(producto.obtenerInformacion());

                    int opcion;
                    System.out.println("¿Qué desea hacer?");
                    System.out.println("1. Reducir Stock.");
                    System.out.println("2. Aumentar Stock.");
                    opcion = entrada.nextInt();
                    entrada.nextLine();

                    switch (opcion) 
                    {
                        case 1:
                            System.out.print("Ingrese la cantidad a reducir: ");
                            int cantidadReducir = entrada.nextInt();
                            entrada.nextLine();
                            if (cantidadReducir < 0 || cantidadReducir > producto.getStock()) 
                            {
                                System.out.println("Cantidad inválida. No se puede reducir el stock por debajo de cero.");
                            } 
                            else 
                            {
                                producto.setStock(producto.getStock() - cantidadReducir);
                                System.out.println("Stock actualizado.");
                                System.out.println("Nuevo stock: " + producto.getStock());
                            }
                            break;
                        case 2:
                            System.out.print("Ingrese la cantidad a aumentar: ");
                            int cantidadAumentar = entrada.nextInt();
                            entrada.nextLine();
                            if (cantidadAumentar < 0) 
                            {
                                System.out.println("Cantidad inválida.");
                            } 
                            else 
                            {
                                producto.setStock(producto.getStock() + cantidadAumentar);
                                System.out.println("Stock actualizado.");
                                System.out.println("Nuevo stock: " + producto.getStock());
                            }
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) 
            {
                System.out.println("No se encontró ningún producto con ese código de barras.");
                System.out.println("--------------------------------------------------------");
            }
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Error: Ingrese un número válido para buscar el producto.");
        } 
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al realizar la operación: " + e.getMessage());
        }
	}
	
	public static void modificarPrecio(ArrayList<Producto> listaProductos) 
	{
        Scanner entrada = new Scanner(System.in);
        try 
        {
            System.out.print("\nIngrese el código de barras del producto: ");
            int codigoBusqueda = entrada.nextInt();
            entrada.nextLine();

            boolean encontrado = false;

            for (Producto producto : listaProductos) 
            {
                if (producto.getCodigoBarra() == codigoBusqueda) 
                {
                    System.out.println("Producto encontrado:");
                    System.out.println("--------------------");
                    System.out.println(producto.obtenerInformacion());

                    System.out.print("Ingrese el nuevo precio: ");
                    double nuevoPrecio = entrada.nextDouble();
                    entrada.nextLine();
                    if (nuevoPrecio < 0) 
                    {
                        System.out.println("Precio inválido.");
                    } 
                    else 
                    {
                        producto.setPrecio(nuevoPrecio);
                        System.out.println("Precio actualizado.");
                        System.out.println("Nuevo precio: " + producto.getPrecio());
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) 
            {
                System.out.println("No se encontró ningún producto con ese código de barras.");
                System.out.println("--------------------------------------------------------");
            }
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Error: Ingrese un número válido para buscar el producto.");
        } 
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al realizar la operación: " + e.getMessage());
        }
    }
    
	public static void escribirArchivoProductos(String ruta, ArrayList<Producto> listaProductos) 
	{
	    try 
	    {
	        File archivoProductos = new File(ruta);

	        if (!listaProductos.isEmpty()) 
	        {
	            Producto ultimoProducto = listaProductos.get(listaProductos.size() - 1);

	            try (PrintWriter escritura = new PrintWriter(new FileWriter(archivoProductos, true))) 
	            {
	                if (ultimoProducto instanceof Lacteo) 
	                {
	                    Lacteo lacteo = (Lacteo) ultimoProducto;
	                    escritura.println("LACTEO;" + lacteo.getCodigoBarra() + 
	                    						 ";" + lacteo.getMarca() + 
	                    						 ";" + lacteo.getDescripcion() + 
	                    						 ";" + lacteo.getPrecio() + 
	                    						 ";" + lacteo.getStock() + 
	                    						 ";" + lacteo.getFechaFabricacion() + 
	                    						 ";" + lacteo.getFechaVencimiento());
	                } 
	                else if (ultimoProducto instanceof Regaleria) 
	                {
	                    Regaleria regaleria = (Regaleria) ultimoProducto;
	                    escritura.println("REGALERIA;" + regaleria.getCodigoBarra() + 
	                    							";" + regaleria.getMarca() + 
	                    							";" + regaleria.getDescripcion() + 
	                    							";" + regaleria.getPrecio() + ";" + regaleria.getStock() + 
	                    							";" + regaleria.getMaterialFabricacion());
	                }
	                System.out.println("Datos guardados en el archivo con éxito.");
	            }
	        } 
	        else 
	        {
	            System.out.println("La lista de productos está vacía. No hay elementos para guardar en el archivo.");
	        }
	    } 
	    catch (FileNotFoundException error) 
	    {
	        System.out.println(error.getMessage());
	    } 
	    catch (IOException error) 
	    {
	        System.out.println(error.getMessage());
	    }
	} 

	public static void leerArchivoProductos(String ruta)
	{
	    try (BufferedReader lectura = new BufferedReader(new FileReader(ruta)))
{
	        String linea = lectura.readLine();
	        while (linea != null) 
	        {
	            String[] partes = linea.split(";");
	            if (partes.length > 0) 
	            {
	                String categoria = partes[0];
	                int codigoBarra = Integer.parseInt(partes[1]);
	                String marca = partes[2];
	                String descripcion = partes[3];
	                double precio = Double.parseDouble(partes[4]);
	                int stock = Integer.parseInt(partes[5]);

	                if (categoria.equals("LACTEO") && partes.length == 8) 
	                {
	                    LocalDate fechaFabricacion = LocalDate.parse(partes[6]);
	                    LocalDate fechaVencimiento = LocalDate.parse(partes[7]);

	                    Lacteo lacteo = new Lacteo(codigoBarra, marca, descripcion, precio, stock, fechaFabricacion, fechaVencimiento);
	                    listaProductos.add(lacteo);
	                } 
	                else if (categoria.equals("REGALERIA") && partes.length == 7) 
	                {
	                    String materialFabricacion = partes[6];

	                    Regaleria regaleria = new Regaleria(codigoBarra, marca, descripcion, precio, stock, materialFabricacion);
	                    listaProductos.add(regaleria);
	                }
	            }
	            linea = lectura.readLine();
	        }
	    } 
	    catch (IOException e) 
	    {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    } 
	    catch (NumberFormatException | DateTimeParseException e) 
	    {
	        System.out.println("Error al procesar los datos del archivo: " + e.getMessage());
	    }
	}
}