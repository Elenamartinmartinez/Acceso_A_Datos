import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.*;
import java.nio.*;

public class Main {
    //Definiciones globales de los objetos de ficheros
    private static Almacenamiento almacenamiento;

    //Listas globales
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pagos_Repostajes> pagos = new ArrayList<>();

    //Contador global para guardar las identificaciones de los clientes
    private static int contIdClientes = 1;
    //Contador global para guardar las identificaciones de los pagos realizados
    private static int contIdPagos = 1;

    private static Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatoFCH = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main (String[] args) {
        sc = new Scanner(System.in);

        //Iniciamos el almacenamiento
        try {
            almacenamiento = new AlmacenamientoEnCSV();
            clientes = almacenamiento.leerCliente();
            pagos = almacenamiento.leerPagos();

            //Sincronizar contadores con los id
            contIdClientes = clientes.stream().mapToInt(Cliente::getId).max().orElse(0) + 1;
            contIdPagos = pagos.stream().mapToInt(Pagos_Repostajes::getId).max().orElse(0) + 1;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        /*Menú de opciones*/
        int op;

        do {
            //Menú que muestra las opciones a elegir
            System.out.println("----------------------------------------------------------");
            mostrarMenu();
            System.out.println("----------------------------------------------------------");
            System.out.println();
            System.out.print("Opción: ");//Pide que se elija una de las opciones anteriores
            op = sc.nextInt();

            try {
                switch (op) {
                    case 1 -> crearCliente(sc);
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
        } while (op != 0);

        try {
            Almacenamiento alm = new AlmacenamientoEnCSV(); //Creamos el repositorio del tipo que queramos
        } catch (IOException e) {
            System.out.println("¡Error! algo no ha salido bien al crear el repositorio..."); //En caso de que haya algún problema con la creación del fichero
            throw new RuntimeException(e); //Mostrar el error por pantalla
        }
    }

    /*Métodos*/
    //Mostrar el menú
    public static void mostrarMenu () {
        System.out.println("=== GESTIÓN GASOLINERA ===");
        System.out.println("1.- Dar de alta un cliente");
        System.out.println("2.- Listar clientes");
        System.out.println("3.- Buscar clientes");
        System.out.println("4.- Procesar un pago de repostaje");
        System.out.println("5.- Consultar pagos");
        System.out.println("0.- Salir");
    }


    //Crear un cliente
    public static void crearCliente (Scanner sc) {
        //Para dar de alta un cliente pedimos toda la info menos el 'id' que se genera automáticamente
        System.out.print("1.- Nombre: ");
        String nombre = sc.next();

        System.out.print("2.- Teléfono: ");
        String tlfno = sc.next().trim();

        System.out.print("3.- Matrícula: ");
        String matricula = sc.next().trim();

        //Los tres campos que se piden son obligatorios, en caso de dejar alguno en blanco, no es un cliente válido
        if (nombre.isBlank() || tlfno.isBlank() || matricula.isBlank()) {
            System.out.println("¡Error!, todos los campos deben rellenarse");
            System.out.println("Cliente no válido");
        }

        //La matrícula no puede repetirse, es única por cliente
        String matrMayus = matricula.toUpperCase();
        for (Cliente c: clientes) {
            if (c.getMatricula().equals(matrMayus)) {
                System.out.println("¡Error! esa matrícula ya ha sido registrada");
                return;
            }
        };

        //Guardamos al nuevo cliente una vez sus datos sean correctos (y le damos su identificador)
        Cliente nuevoC = new Cliente(contIdClientes++, nombre, tlfno, matricula);
        clientes.add(nuevoC);
        almacenamiento.escribirCliente(nuevoC); //Escribe un nuevo cliente en el fichero de clientes

        //Guardamos el nuevo cliente en el repositorio
        System.out.println("¡Cliente creado!");
        System.out.println("Su identificador es: "+nuevoC.getId()); //Mostramos por pantalla el 'id'
    }

    //Listar a los clientes ya registrados
    public static void mostrarClientes () {
        if (clientes.isEmpty()) { //En caso de que no se haya registrado ningún cliente todavía
            System.out.println("No hay clientes registrados...");
            return;
        }

        //En caso de que si haya clientes registrados se mostrarán por pantalla
        for (Cliente clientes : clientes) {
            System.out.println(clientes);
        }
    }

    //Buscar clientes
    public static void buscarClientes () {
        System.out.print("Texto que buscar: ");
        String texto = sc.nextLine().trim().toLowerCase();

        if (texto.isBlank()) {
            System.out.println("El texto de búsqueda no puede estar vacío.");
            return;
        }

        List<Cliente> Coincidentes = clientes.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(texto) ||
                        c.getTelefono().toLowerCase().contains(texto) ||
                        c.getMatricula().toLowerCase().contains(texto))
                .sorted(Comparator.comparing(Cliente::getNombre, String.CASE_INSENSITIVE_ORDER)
                        .thenComparingInt(Cliente::getId))
                .toList();

        if (Coincidentes.isEmpty()) {
            System.out.println("No se han encontrado clientes.");
        } else {
            System.out.println(clientes);
        }
    }

    //Procesar un pago
    public static void procesarPago () {
        if (clientes.isEmpty()) { //No se puede registrar ningún pago sin clientes registrados
            System.out.println("No hay clientes registrados");
            return;
        }

        mostrarClientes(); //Mostrar los clientes registrados
        System.out.println("ID del cliente que desee realizar un pago: ");
        int idCliente = sc.nextInt();

        //Comprobamos que el cliente existe
        Cliente clienteSelec = null;
        for (Cliente client : clientes) {
            if (client.getId() == idCliente) { //Seleccionamos el ID correspondiente
                clienteSelec = client;
            }
        }

        if (clienteSelec == null) {
            System.out.println("No existe un cliente con ese ID...");
        }

        //Pedimos los datos restantes para registrar el pago
        System.out.println("1.- Fecha (dd/MM/aaa): ");
        String fechat = sc.next();
        LocalDate fecha;
        if (fechat.isBlank()) {
            fecha = LocalDate.now(); //En caso de que no se introduzca una fecha, se pondrá la fecha del día por defecto
        } else {
            try {
                fecha = LocalDate.parse(fechat, formatoFCH); //Para darle los valores y el formato correcto a la fecha
            } catch (DateTimeParseException e) {
                System.out.println("¡Error! La fecha no es válida");
                return;
            }
        }

        System.out.println("2.- Importe: ");
        double importe = sc.nextDouble();

        System.out.println("3.- Litros: ");
        double litros = sc.nextDouble();

        System.out.println("4.- Combustible: ");
        String combustible = sc.next();

        Pagos_Repostajes pago = new Pagos_Repostajes(contIdPagos++, idCliente, fecha, importe, litros, combustible);
        pagos.add(pago);

        //Guardamos el nuevo pago en el repositorio

    }

    //Consultar pagos
    public static void mostrarPagos () {
        if (pagos.isEmpty()) { //En caso de que no se haya registrado ningún cliente todavía
            System.out.println("No hay pagos registrados...");
            return;
        }

        //En caso de que si haya clientes registrados se mostrarán por pantalla
        for (Pagos_Repostajes pagos : pagos) {
            System.out.println(pagos);
        }
    }
}