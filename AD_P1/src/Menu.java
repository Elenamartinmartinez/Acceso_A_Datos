import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private final Gasolinera gasolinera;
    private final Scanner sc = new Scanner(System.in);
    private final Entrada entrada = new Entrada(sc);

    public Menu(Gasolinera gasolinera) { this.gasolinera = gasolinera; }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1" -> altaCliente();
                    case "2" -> listarClientes();
                    case "3" -> buscarClientes();
                    case "4" -> procesarPago();
                    case "5" -> consultarPagos();
                    case "0" -> { System.out.println("Hasta pronto."); salir = true; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("No se pudo completar la operación: " + e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\\n=== GESTIÓN DE GASOLINERA ===");
        System.out.println("1. Dar de alta un cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar clientes");
        System.out.println("4. Procesar un pago de repostaje");
        System.out.println("5. Consultar pagos");
        System.out.println("0. Salir");
    }

    private void altaCliente() throws IOException { //Pide los datos y en caso de que no haya ningún problema, el cliente será registrado
        Cliente c = gasolinera.registrarCliente(entrada.texto("Nombre: "), entrada.texto("Teléfono: "), entrada.texto("Matrícula: "));
        System.out.println("Cliente registrado con ID " + c.getId());
    }

    private void listarClientes() { //Mustra los clientes por pantalla
        if (gasolinera.getClientesOrdenados().isEmpty()) { //Se asegura primero de si hay o no clientes registrados
            System.out.println("No hay clientes registrados."); return;
        }
        System.out.println("ID | NOMBRE | TELÉFONO | MATRÍCULA"); //Si hay clientes, los muestra por pantalla
        gasolinera.getClientesOrdenados().forEach(System.out::println);
    }

    private void buscarClientes() {
        String t = entrada.texto("Texto que buscar: "); //Al cliente se le podría buscar por cualquiera de sus datos
        var resultados = gasolinera.buscarClientes(t);
        if (resultados.isEmpty()) System.out.println("No se han encontrado clientes."); //En caso de que el cliente no exista
        else resultados.forEach(System.out::println); //Y si existe, se muestra por pantalla
    }

    private void procesarPago() throws IOException {
        if (gasolinera.getClientesOrdenados().isEmpty()) { //Si la lista de clientes está vacía, no se puede crear ningún pago
            System.out.println("Primero debes registrar un cliente."); return;
        }
        listarClientes();
        int id = entrada.entero("ID del cliente: ");
        if (gasolinera.buscarClientePorId(id) == null) { //Y aunque la lista de clientes tenga datos, si el cliente no existe, el pago tampoco
            System.out.println("No existe ese cliente."); return;
        }
        //Datos para registrar el pago:
        String fechaTexto = entrada.fecha("Fecha (dd/MM/aaaa; vacío para hoy): ");
        LocalDate fecha = LocalDate.parse(fechaTexto);
        double importe = entrada.cantidad("Importe (€): ");
        double litros = entrada.cantidad("Litros: ");
        String combustible = entrada.texto("Combustible: ");

        Pagos_Repostajes p = gasolinera.registrarPago(id, fecha, importe, litros, combustible);

        System.out.printf("Pago %d registrado para %s: %.2f €.%n", p.getId(), gasolinera.nombreCliente(id), p.getImporte());
    }

    private void consultarPagos() {
        var pagos = gasolinera.getPagosOrdenados();
        if (pagos.isEmpty()) { //Si no hay ningún pago, solo muestra el mensaje de aviso
            System.out.println("No hay pagos registrados.");
            return;
        }

        System.out.println("ID | CLIENTE | FECHA | IMPORTE | LITROS | COMBUSTIBLE");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Pagos_Repostajes p : pagos) {
            System.out.printf("%d || %s || %s || %.2f € || %.2f || %s%n", p.getId(), gasolinera.nombreCliente(p.getIdCliente()), p.getFecha().format(f), p.getImporte(), p.getLitros(), p.getCombustible());
        }
    }
}