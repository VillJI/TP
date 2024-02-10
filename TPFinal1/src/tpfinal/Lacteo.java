package tpfinal;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Lacteo extends Producto implements Fecha {
    private LocalDate fechaFabricacion;
    private LocalDate fechaVencimiento;

    public Lacteo(int codigoBarra, String marca, String descripcion, double precio, int stock, LocalDate fechaFabricacion, LocalDate fechaVencimiento) 
    {
        super(codigoBarra, marca, descripcion, precio, stock);
        this.fechaFabricacion = fechaFabricacion;
        this.fechaVencimiento = fechaVencimiento;
        setCategoria(Categoria.LACTEO);
        setCodigoCategoria(Categoria.LACTEO.getCodigo());
    }
    
    public static LinkedList<Lacteo> colaLacteos = new LinkedList<Lacteo>();

    public String obtenerInformacion() 
	{	
		return  "Categoria: " + this.getCategoria() +
				"\nCodigo de Barras: " + this.getCodigoBarra() +
				"\nMarca: " + this.getMarca() +
 				"\nDescripción: " + this.getDescripcion() +
	            "\nPrecio: $" + this.getPrecio() +
	            "\nCantidad en stock: " + this.getStock() +
	            "\nFabricacion: " + this.getFechaFabricacion() +
	            "\nVencimiento: " + this.getFechaVencimiento();
	}
   
    public String obtenerFechaVencimiento() 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaVencimiento.format(formatter);
    }

	public LocalDate getFechaFabricacion() 
	{
		return fechaFabricacion;
	}

	public void setFechaFabricacion(LocalDate fechaFabricacion) 
	{
		this.fechaFabricacion = fechaFabricacion;
	}

	public LocalDate getFechaVencimiento() 
	{
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) 
	{
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public void mostrarFechaFormateada() 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Fecha de fabricación: " + fechaFabricacion.format(formatter));
        System.out.println("Fecha de vencimiento: " + fechaVencimiento.format(formatter));
    }
	
	public String diferenciaFechas() 
    {
        LocalDate fechaActual = LocalDate.now();
        long diasDiferencia = fechaActual.until(fechaVencimiento).getDays();

        if (diasDiferencia > 10) 
        {
            return "VIGENTE";
        } 
        else if (diasDiferencia >= 1) 
        {
            return "PRONTO A VENCER";
        } 
        else if (diasDiferencia == 0) 
        {
            return "VENCE HOY";
        } 
        else 
        {
            return "VENCIDO";
        }
    }
    
	public static void crearLacteo() 
	{
        Scanner entrada = new Scanner(System.in);

        try 
        {
        	System.out.println("\nIngrese el código de barras:");
            int codigoBarra = validarNumeroEntero("código de barras");

            for (Producto producto : listaProductos) {
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
            
            System.out.println("Ingrese el precio:");
            double precio = validarNumeroDecimal("precio");
            
            System.out.println("Ingrese el stock:");
            int stock = validarNumeroEntero("stock");
            
            LocalDate fechaFabricacion = validarFechaElaboracion("fecha de fabricación");
            LocalDate fechaVencimiento = validarFechaVencimiento("fecha de vencimiento");

            Lacteo nuevoLacteo = new Lacteo(codigoBarra, marca, descripcion, precio, stock, fechaFabricacion, fechaVencimiento);

            colaLacteos.offer(nuevoLacteo);
            listaProductos.add(nuevoLacteo);
            Lacteo.escribirArchivo("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\listaLacteos.txt", Lacteo.colaLacteos);
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
	
	public static void listarLacteosVencidos() 
	{
	    LocalDate fechaActual = LocalDate.now();

	    System.out.println("\nLácteos vencidos:");
	    System.out.println("-------------------");
	    for (Lacteo lacteo : colaLacteos) 
	    {
	        if (lacteo.getFechaVencimiento().isBefore(fechaActual)) 
	        {
	            String estado = lacteo.diferenciaFechas();
	            System.out.println(lacteo.obtenerInformacion() + "\nEstado: " + estado);
	            System.out.println("----------------------------------");
	        }
	    }
	    System.out.println("\nLácteos que vencen hoy:");
	    System.out.println("------------------------");
	    for (Lacteo lacteo : colaLacteos) 
	    {
	        String estado = lacteo.diferenciaFechas();
	        if (estado.equals("VENCE HOY")) 
	        {
	            System.out.println(lacteo.obtenerInformacion() + "\nEstado: " + estado);
	            System.out.println("----------------------------------");
	        }
	    }
	    System.out.println("\nLácteos próximos a vencer:");
	    System.out.println("--------------------------");
	    for (Lacteo lacteo : colaLacteos) 
	    {
	        String estado = lacteo.diferenciaFechas();
	        if (estado.equals("PRONTO A VENCER")) 
	        {
	            System.out.println(lacteo.obtenerInformacion() + "\nEstado: " + estado);
	            System.out.println("----------------------------------");
	        }
	    }
	    System.out.println("\nLácteos vigentes:");
	    System.out.println("------------------");
	    for (Lacteo lacteo : colaLacteos) 
	    {
	        String estado = lacteo.diferenciaFechas();
	        if (estado.equals("VIGENTE")) 
	        {
	            System.out.println(lacteo.obtenerInformacion() + "\nEstado: " + estado);
	            System.out.println("----------------------------------");
	        }
	    }
	}
	
	public static void mostrarColaLacteos() 
	{
	    if (colaLacteos.isEmpty()) 
	    {
	        System.out.println("La lista de lácteos está vacía.");
	        System.out.println("-------------------------------");
	    } 
	    else 
	    {
	        System.out.println("\nLista de Lácteos:");
	        System.out.println("-------------------");
	        for (Lacteo lacteo : colaLacteos) 
	        {
	            System.out.println(lacteo.obtenerInformacion());
	            System.out.println("----------------------------------");
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
	
	public static LocalDate validarFechaElaboracion(String tipoFecha) throws DateTimeException 
	{
        Scanner entrada = new Scanner(System.in);

        while (true) 
        {
            try 
            {
                System.out.println("Ingrese la " + tipoFecha + " (formato dd-MM-yyyy):");
                String ingresoFecha = entrada.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fecha = LocalDate.parse(ingresoFecha, formatter);
                // Obtener valores individuales de día, mes y año
                int dia = fecha.getDayOfMonth();
                int mes = fecha.getMonthValue();
                int anio = fecha.getYear();
                // Validar si el día ingresado coincide con el número de días en el mes
                if (dia > Month.of(mes).length(Year.isLeap(anio))) 
                {
                    throw new DateTimeException("El día ingresado no es válido para el mes y año especificados.");
                }
                LocalDate fechaActual = LocalDate.now();
                if (fecha.isAfter(fechaActual)) 
                {
                    throw new Exception("La fecha no puede ser posterior a la actual.");
                }
                return fecha;
            } 
            catch (DateTimeParseException e) 
            {
                System.out.println("Error: Formato de fecha inválido. Ingrese la fecha nuevamente.");
            } 
            catch (Exception e) 
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
	
	public static LocalDate validarFechaVencimiento(String tipoFecha) throws DateTimeException 
	{
        Scanner entrada = new Scanner(System.in);

        while (true) 
        {
            try 
            {
                System.out.println("Ingrese la " + tipoFecha + " (formato dd-MM-yyyy):");
                String ingresoFecha = entrada.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fecha = LocalDate.parse(ingresoFecha, formatter);
                // Obtener valores individuales de día, mes y año
                int dia = fecha.getDayOfMonth();
                int mes = fecha.getMonthValue();
                int anio = fecha.getYear();
                // Validar si el día ingresado coincide con el número de días en el mes
                if (dia > Month.of(mes).length(Year.isLeap(anio))) 
                {
                    throw new DateTimeException("El día ingresado no es válido para el mes y año especificados.");
                }
                LocalDate fechaActual = LocalDate.now();
                if (fecha.isBefore(fechaActual)) 
                {
                    throw new Exception("La fecha no puede ser anterior a la actual.");
                }
                return fecha;
            } 
            catch (DateTimeParseException e) 
            {
                System.out.println("Error: Formato de fecha inválido. Ingrese la fecha nuevamente.");
            } 
            catch (Exception e) 
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
	
	public static void crearArchivo(String ruta) 
	{
        File listaLacteos = new File(ruta);

        try 
        {
            if (listaLacteos.exists()) 
            {
                System.out.println("El archivo ya existe.");
            } 
            else 
            {
                if (listaLacteos.createNewFile()) 
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

	public static void escribirArchivo(String ruta, LinkedList<Lacteo> colaLacteos) 
	{
	    try {
		        Lacteo ultimoLacteo = colaLacteos.peekLast();
		        if (ultimoLacteo != null) 
		        {
		            PrintWriter escritura = new PrintWriter(new FileWriter(ruta, true));
		            escritura.println("LACTEO;" + ultimoLacteo.getCodigoBarra() +
		                    				";" + ultimoLacteo.getMarca() +
		                    				";" + ultimoLacteo.getDescripcion() +
		                    				";" + ultimoLacteo.getPrecio() +
		                    				";" + ultimoLacteo.getStock() +
		                    				";" + ultimoLacteo.getFechaFabricacion() +
		                    				";" + ultimoLacteo.getFechaVencimiento());
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
	    catch (IOException error) 
	    {
	        System.out.println(error.getMessage());
	    }
	}

	public static void leerArchivo(String ruta) 
	{
	    try (BufferedReader lectura = new BufferedReader(new FileReader(ruta))) 
	    {
	        String linea = lectura.readLine();
	        while (linea != null) 
	        {
	            String[] partes = linea.split(";");
	            if (partes.length >= 8 && partes[0].equals("LACTEO")) 
	            {
	                int codigoBarra = Integer.parseInt(partes[1]);
	                String marca = partes[2];
	                String descripcion = partes[3];
	                double precio = Double.parseDouble(partes[4]);
	                int stock = Integer.parseInt(partes[5]);
	                LocalDate fechaFabricacion = LocalDate.parse(partes[6]);
	                LocalDate fechaVencimiento = LocalDate.parse(partes[7]);

	                Lacteo lacteo = new Lacteo(codigoBarra, marca, descripcion, precio, stock, fechaFabricacion, fechaVencimiento);
	                Lacteo.colaLacteos.offer(lacteo);
	            } 
	            else 
	            {
	                System.out.println("La línea no tiene el formato esperado: " + linea);
	            }
	            linea = lectura.readLine();
	        }
	    } 
	    catch (IOException e) 
	    {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }
	}
}

