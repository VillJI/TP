package tpfinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Personal {
    private String apellido;
    private String nombre;
    private String legajo;
    private int dni;

    static ArrayList<Personal> listaPersonal = new ArrayList<>();
    private static HashMap<Integer, String> dniLegajoMap = new HashMap<>();

    public Personal(String apellido, String nombre, String legajo, int dni) 
    {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
        this.dni = dni;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getApellido() 
    {
        return apellido;
    }

    public void setApellido(String apellido) 
    {
        this.apellido = apellido;
    }

    public String getLegajo() 
    {
        return legajo;
    }

    public void setLegajo(String legajo) 
    {
        this.legajo = legajo;
    }

    public int getDni() 
    {
        return dni;
    }

    public void setDni(int dni) 
    {
        this.dni = dni;
    }

    public static void obtenerLegajoPorDni() 
    {
    	Scanner entrada = new Scanner(System.in);

        System.out.println("Ingrese el DNI para obtener el legajo:");
        int dniIngresado = entrada.nextInt();

        String legajoEncontrado = dniLegajoMap.get(dniIngresado);
        if (legajoEncontrado != null) 
        {
            System.out.println("El legajo correspondiente al DNI " + dniIngresado + " es: " + legajoEncontrado);
        } 
        else 
        {
            System.out.println("No se encontró ningún legajo para el DNI ingresado.");
        }
    }
    
    public String obtenerInformacion() 
    {
    	return "Apellido: " + this.getApellido() +
    		   "\nNombre: " + this.getNombre() +
    		   "\nLegajo: " + this.getLegajo() +
    		   "\nDNI: " + this.getDni();
    }

    public static void obtenerDatosPorLegajo() throws InputMismatchException 
    {
        Scanner entrada = new Scanner(System.in);
        try 
        {
            System.out.println("\nIngrese el legajo del empleado para obtener sus datos:");
            String legajoBuscado = entrada.nextLine();
 
            boolean empleadoEncontrado = false;
            for (Personal empleado : listaPersonal) 
            {
                if (empleado.getLegajo().equals(legajoBuscado)) 
                {
                    System.out.println("Datos del empleado:");
                    System.out.println("-------------------");
                    System.out.println(empleado.obtenerInformacion());
                    empleadoEncontrado = true;
                    break;
                }
            }
            if (!empleadoEncontrado) 
            {
                System.out.println("Empleado no encontrado.");
                System.out.println("-----------------------");
            }
        } 
        catch (NoSuchElementException e) 
        {
            System.out.println("Error: No se encontró el elemento de entrada esperado.");
        }
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al obtener los datos del empleado: " + e.getMessage());
            entrada.nextLine();
        }
    }

    public static void crearPersonal() 
    {
        Scanner entrada = new Scanner(System.in);
        try 
        {
            System.out.println("Ingrese el apellido:");
            String apellido = obtenerCadenaValida(entrada, "apellido");
            
            System.out.println("\nIngrese el nombre:");
            String nombre = obtenerCadenaValida(entrada, "nombre");

            System.out.println("Ingrese el legajo:");
            String legajo = obtenerCadenaValida(entrada, "legajo");

            System.out.println("Ingrese el DNI:");
            int dni = obtenerNumeroValido(entrada, "DNI");

            Personal nuevoPersonal = new Personal(apellido, nombre, legajo, dni);

            listaPersonal.add(nuevoPersonal);
            dniLegajoMap.put(dni, legajo);
            Personal.escribirArchivoPersonal("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoPersonal.txt", Personal.listaPersonal);

            System.out.println("Empleado agregado con éxito."); 
            System.out.println("----------------------------"); 
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Error: Ingrese un dato válido para el nuevo personal.");
            entrada.nextLine();
        } 
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al crear un nuevo empleado: " + e.getMessage());
            entrada.nextLine();
        }
    }

    public static void mostrarListaPersonal() 
    {
        if (listaPersonal.isEmpty()) 
        {
            System.out.println("La lista de personal está vacía.");
            System.out.println("--------------------------------");
        } 
        else 
        {
            System.out.println("\nLista de Personal:");
            System.out.println("--------------------");
            for (Personal empleado : listaPersonal) 
            {
            	System.out.println("Apellido: " + empleado.getApellido());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Legajo: " + empleado.getLegajo());
                System.out.println("DNI: " + empleado.getDni());
                System.out.println("----------------------------------");
            }
        }
    }

    public static String obtenerCadenaValida(Scanner entrada, String tipo) 
    {
        while (true) 
        {
            try 
            {
                String cadena = entrada.nextLine();
                if (cadena.matches("[a-zA-Z]+")) 
                {
                    return cadena;
                } 
                else 
                {
                    System.out.println("Error: El " + tipo + " debe contener solo letras. Inténtelo de nuevo:");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: El formato de entrada no es válido para el " + tipo + ".");
                entrada.nextLine();
            }
        }
    }

    public static int obtenerNumeroValido(Scanner entrada, String tipo) 
    {
        while (true) 
        {
            try 
            {
                int numeroEntrada = entrada.nextInt();
                entrada.nextLine();
                if (numeroEntrada > 0) 
                {
                    return numeroEntrada;
                } 
                else 
                {
                    System.out.println("Error: El " + tipo + " debe ser un número positivo. Inténtelo de nuevo:");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: El formato de entrada no es válido para el " + tipo + ".");
                entrada.nextLine();
            }
        }
    }
    
    public static void escribirArchivoPersonal(String ruta, ArrayList<Personal> listaPersonal) 
    {
        try 
        {
            File archivoPersonal = new File(ruta);

            if (!listaPersonal.isEmpty()) 
            {
                Personal ultimoEmpleado = listaPersonal.get(listaPersonal.size() - 1);

                try (PrintWriter escritura = new PrintWriter(new FileWriter(archivoPersonal, true))) 
                {
                    escritura.println(ultimoEmpleado.getApellido() + 
                    					";" + ultimoEmpleado.getNombre() + 
                    					";" + ultimoEmpleado.getLegajo() + 
                    					";" + ultimoEmpleado.getDni());
                    System.out.println("Datos guardados en el archivo con éxito.");
                }
            } 
            else 
            {
                System.out.println("La lista de personal está vacía. No hay elementos para guardar en el archivo.");
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
    
    public static void leerArchivoPersonal(String ruta) 
    {
        try (BufferedReader lectura = new BufferedReader(new FileReader(ruta))) 
        {
            String linea = lectura.readLine();
            while (linea != null) 
            {
                String[] partes = linea.split(";");

                if (partes.length == 4) 
                {
                    String apellido = partes[0];
                    String nombre = partes[1];
                    String legajo = partes[2];
                    int dni = Integer.parseInt(partes[3]);
                    Personal empleado = new Personal(apellido, nombre, legajo, dni);
               
                    listaPersonal.add(empleado);
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
        catch (NumberFormatException e) 
        {
            System.out.println("Error al convertir el número de documento: " + e.getMessage());
        }
    }
}