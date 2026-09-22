import java.util.*;

public class Main {
    //Definiciones globales de los objetos de ficheros
    private static Repositorio_Clientes repoClientes = new Repositorio_Clientes();
    private static Repositorio_Pagos repoPagos = new Repositorio_Pagos();

    //Listas globales
    private static List<Cliente> c = new ArrayList<>();
    private static List<Pagos_Repostajes> p = new ArrayList<>();

    //Contador global para guardar las identificaciones de los clientes
    private static int contIdClientes = 1;
    //Contador global para guardar las identificaciones de los pagos realizados
    private static int contIdPagos = 1;

    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args) {
        sc = new Scanner(System.in);

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
        //Para dar de alta un cliente pedimos toda la info menos el id que se genera automáticamente
        System.out.print("1.- Nombre: ");
        String nombre = sc.next();

        System.out.print("2.- Teléfono: ");
        String tlfno = sc.next();

        System.out.print("3.- Matrícula: ");
        String matricula = sc.next();

        //Los tres campos que se piden son obligatorios, en caso de dejar alguno en blanco, no es un cliente válido
        if (nombre.isBlank() || tlfno.isBlank() || matricula.isBlank()) {
            System.out.println("¡Error!, todos los campos deben rellenarse");
            System.out.println("Cliente no válido");
        }

        //Guardamos al nuevo cliente una vez sus datos sean correctos (y le damos su identificador)
        Cliente nuevoC = new Cliente(contIdClientes++, nombre, tlfno, matricula);
        c.add(nuevoC);

        repoClientes.guardarCliente(nuevoC);//Guardamos el nuevo cliente en el repositorio

        System.out.println("¡Cliente creado!");
        System.out.println("Su identificador es: "+nuevoC.getId()); //Mostramos por pantalla el id
    }

    //Listar a los clientes ya registrados
    public static void mostrarClientes () {
        if (c.isEmpty()) { //En caso de que no se haya registrado ningún cliente todavía
            System.out.println("No hay clientes registrados...");
            return;
        }

        //En caso de que si haya clientes registrados se mostrarán por pantalla
        for (Cliente clientes : c) {
            System.out.println(clientes);
        }
    }

    //Buscar clientes
    public static void buscarClientes () {
        sc = new Scanner(System.in);
        boolean existe = false;

        System.out.print("Introduzca el nombre del cliente a buscar: ");
        String nombre = sc.next();

        for (Cliente cliente : c) {
            
        }
    }

    //Procesar un pago
    public static void procesarPago () {
        if (c.isEmpty()) { //No se puede registrar ningún pago sin clientes registrados
            System.out.println("No hay clientes registrados");
            return;
        }

        mostrarClientes(); //Mostrar los clientes registrados
        System.out.println("ID del cliente que desee realizar un pago: ");
        int idCliente = sc.nextInt();

        //Comprobamos que el cliente existe
        Cliente clienteSelec = null;
        for (Cliente client : c) {
            if (client.getId() == idCliente) { //Seleccionamos el ID correspondiente
                clienteSelec = client;
            }
        }

        if (clienteSelec == null) {
            System.out.println("No existe un cliente con ese ID...");
        }

        //Pedimos los datos restantes para registrar el pago
        System.out.println("1.- Fecha (dd/MM/aaa): ");
        String fecha = sc.next();

        System.out.println("2.- Importe: ");
        double importe = sc.nextDouble();

        System.out.println("3.- Litros: ");
        double litros = sc.nextDouble();

        System.out.println("4.- Combustible: ");
        String combustible = sc.next();

        Pagos_Repostajes pago = new Pagos_Repostajes(contIdPagos++, idCliente, fecha, importe, litros, combustible);
        p.add(pago);

        repoPagos.guardarPago(pago); //Guardamos el nuevo pago en el repositorio
    }

    //Consultar pagos
    public static void mostrarPagos () {
        if (p.isEmpty()) { //En caso de que no se haya registrado ningún cliente todavía
            System.out.println("No hay pagos registrados...");
            return;
        }

        //En caso de que si haya clientes registrados se mostrarán por pantalla
        for (Pagos_Repostajes pagos : p) {
            System.out.println(pagos);
        }
    }
}