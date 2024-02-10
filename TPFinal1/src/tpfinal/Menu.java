package tpfinal;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {

    public static void MenuGeneral() 
    {
        Scanner entrada = new Scanner(System.in);
        try 
        {
            while (true) 
            {
                mostrarMenu();

                int opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) 
                {
                    case 1:
                        consultarProductos();
                        break;
                    case 2:
                        listarProductos();
                        break;
                    case 3:
                        verificarProductosVencidos();
                        break;
                    case 4:
                    	modificarDatosProductos();
                    	break;
                    case 5:
                        agregarNuevoProducto();
                        break;
                    case 6:
                        agregarPersonal();
                        break;
                    case 7:
                        consultarPersonal();
                        break;
                    case 8:
                    	eliminarRegistros();
                    	break;
                    case 0:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            }
        } 
        catch (NoSuchElementException e) 
        {
            System.out.println("Error: No hay más elementos en la entrada.");
        } 
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            entrada.nextLine();
        }
    }


	private static void mostrarMenu() 
    {
        System.out.println("\n----- Menú Principal -----");
        System.out.println("1. Consultar Productos");
        System.out.println("2. Listar Productos");
        System.out.println("3. Verificar Productos Vencidos");
        System.out.println("4. Modificar Datos de Productos");
        System.out.println("5. Agregar Nuevo Producto");
        System.out.println("6. Agregar Personal");
        System.out.println("7. Consultar Personal");
        System.out.println("8. Eliminar Registros");
        System.out.println("0. Salir");
        System.out.println("---------------------------");
    }

    private static void agregarPersonal() 
    {
        Personal.crearPersonal();

    }

    private static void consultarPersonal() 
    {
        Scanner entrada = new Scanner(System.in);
        while (true) 
        {
            System.out.println("\n----Consultar Personal-----");
            System.out.println("1. Consultar Legajo");
            System.out.println("2. Datos de Empleados");
            System.out.println("3. Lista de Personal");
            System.out.println("0. Volver al menu anterior");
            System.out.println("-----------------------------");

            try 
            {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) 
                {
                	case 1:
                		Personal.obtenerLegajoPorDni();
                		break;
                    case 2:
                        Personal.obtenerDatosPorLegajo();                   
                        break;
                    case 3:
                        Personal.mostrarListaPersonal();                    
                        break;
                    case 0:
                        mostrarMenu();
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        break;
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }
    }
    
    private static void agregarNuevoProducto() 
    {
        Scanner entrada = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\nCategoria de Producto");
            System.out.println("1. Lácteos");
            System.out.println("2. Regalería");
            System.out.println("0. Volver al menú anterior");

            try 
            {
                int opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) 
                {
                    case 1:
                        Lacteo.crearLacteo();
                                                
                        break;
                    case 2:
                        Regaleria.crearRegaleria();
                                                
                        break;
                    case 0:
                        mostrarMenu();
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        break;
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }
    }

    private static void verificarProductosVencidos() 
    {
        Lacteo.listarLacteosVencidos();
    }

    private static void listarProductos() 
    {
        Scanner entrada = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\nLista de Productos");
            System.out.println("1. Lácteos");
            System.out.println("2. Regalería");
            System.out.println("3. Lista Completa de Productos");
            System.out.println("0. Volver al menú anterior");

            try 
            {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) 
                {
                    case 1:
                        Lacteo.mostrarColaLacteos();                    
                        break;
                    case 2:
                        Regaleria.mostrarPilaRegaleria();                   
                        break;
                    case 3:
                    	Producto.mostrarListaProductos();                  
                    	break;
                    case 0:
                        mostrarMenu();
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        break;
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }
    }

    private static void consultarProductos() 
    {
        Producto.consultarProductoPorCodigoBarra();
    }
    
    private static void eliminarRegistros()
    {
    	Scanner entrada = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\nEliminar Raegistros");
            System.out.println("1. Archivo de Productos");
            System.out.println("2. Archivo de Lacteos");
            System.out.println("3. Archivo de Regaleria");
            System.out.println("4. Archivo de Personal");
            System.out.println("0. Volver al menú anterior");

            try 
            {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) 
                {
                    case 1:
                    	ManejoArchivo.eliminarArchivoProductos("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoProductos.txt");               
                        break;
                    case 2:
                    	ManejoArchivo.eliminarArchivoLacteos("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\listaLacteos.txt");                  
                        break;
                    case 3:
                    	ManejoArchivo.eliminarArchivoRegaleria("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\listaRegaleria.txt");                
                    	break;
                    case 4:
                    	ManejoArchivo.eliminarArchivoPersonal("C:\\Users\\Nacho\\eclipse-workspace\\TPFinal\\src\\Archivos\\archivoPersonal.txt");
                    	break;
                    case 0:
                        mostrarMenu();
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        break;
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }
    }
    
    private static void modificarDatosProductos() 
    {
    	Scanner entrada = new Scanner(System.in);
    	while (true) 
        {
            System.out.println("\nModificar Datos de Productos");
            System.out.println("1. Modificar Precio");
            System.out.println("2. Modificar Stock");
            System.out.println("0. Volver al menú anterior");
             
            try 
            {
                int opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) 
                {
                    case 1:
                        Producto.altaBajaStock(Producto.listaProductos);                   
                        break;
                    case 2:
                        Producto.modificarPrecio(Producto.listaProductos);                   
                        break;
                    case 0:
                        mostrarMenu();
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        break;
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }
		
	}
}
