import java.util.*;
import java.io.*;

public class Main {

    public static void Gasolinera (String[] args) {
        Scanner sc = new Scanner(System.in);

        /*Menú de opciones*/
        int op;

        do {
            //Menú que muestra las opciones a elegir
            System.out.println("=== GESTIÓN GASOLINERA ===");
            System.out.println("1.- Dar de alta un cliente");
            System.out.println("2.- Listar clientes");
            System.out.println("3.- Buscar clientes");
            System.out.println("4.- Procesar un pago de repostaje");
            System.out.println("5.- Consultar pagos");
            System.out.println("0.- Salir");

            System.out.println("Opción: ");//Pide que se elija una de las opciones anteriores
            op = sc.nextInt();

            try {
                switch (op) {
                    case 1 -> crearCliente();
                    case 2 -> mostrarClientes();
                    case 3 -> buscarClientes();
                    case 4 -> procesarPago();
                    case 5 -> mostrarPagos();
                    case 0 -> System.out.println("Fin");
                    default -> System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido");
            }
        } while (op != 5);
    }

    /*Métodos*/
    //Crear un cliente
    public static void crearCliente () {
        

    }

    //Listar a los clientes ya registrados
    public static void mostrarClientes () {


    }

    //Buscar clientes
    public static void buscarClientes () {


    }

    //Procesar un pago
    public static void procesarPago () {


    }

    //Consultar pagos
    public static void mostrarPagos () {


    }
}