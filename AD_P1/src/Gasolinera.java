import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Gasolinera {
    private final Almacenamiento almacenamiento;
    private final List<Cliente> clientes;
    private final List<Pagos_Repostajes> pagos;
    private int siguienteIdCliente;
    private int siguienteIdPago;

    public Gasolinera(Almacenamiento almacenamiento) throws IOException {
        this.almacenamiento = almacenamiento;
        clientes = new ArrayList<>(almacenamiento.leerCliente());
        pagos = new ArrayList<>(almacenamiento.leerPagos());
        siguienteIdCliente = 1;
        //clientes.stream().mapToInt(Cliente::getId).max().orElse(0) + 1; <- Otra opción
        siguienteIdPago = 1;
    }

    public List<Cliente> getClientesOrdenados() { //Se ordena por nombre y en caso de que dos nombres se repitan, se ordena por id
        return clientes.stream().sorted(Comparator.comparing(Cliente::getNombre, String.CASE_INSENSITIVE_ORDER).thenComparingInt(Cliente::getId)).toList();
    }

    public List<Cliente> buscarClientes(String texto) {
        String t = texto.toLowerCase(Locale.ROOT);
        return getClientesOrdenados().stream().filter(c -> c.getNombre().toLowerCase(Locale.ROOT).contains(t)
                || c.getTelefono().toLowerCase(Locale.ROOT).contains(t) || c.getMatricula().toLowerCase(Locale.ROOT).contains(t)).toList();
    }

    public Cliente buscarClientePorId(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Cliente registrarCliente(String nombre, String telefono, String matricula) throws IOException {
        String mat = matricula.trim();
        if (clientes.stream().anyMatch(c -> c.getMatricula().equalsIgnoreCase(mat))) {
            System.out.println("La matrícula introducida ya está registrada...");
        }

        Cliente nuevo = new Cliente(siguienteIdCliente, nombre, telefono, mat); //Crea un nuevo cliente

        almacenamiento.escribirCliente(nuevo); //Añade el nuevo cliente al fichero correspondiente
        clientes.add(nuevo); //Mismo cliente, pero a la lista que se muestra por pantalla
        siguienteIdCliente++; //Por cada cliente se añade uno al id
        return nuevo;
    }

    public Pagos_Repostajes registrarPago(int idCliente, LocalDate fecha, double importe, double litros, String combustible) throws IOException {
        if (buscarClientePorId(idCliente) == null){
            System.out.println("No existe ningún cliente con esa id...");
        }

        Pagos_Repostajes pago = new Pagos_Repostajes(siguienteIdPago, idCliente, fecha, importe, litros, combustible); //Se registra un nuevo pago

        almacenamiento.escribirPagos(pago); //Añade el nuevo cliente al fichero correspondiente
        pagos.add(pago); //Mismo pago, pero a la lista que se muestra por pantalla
        siguienteIdPago++; //Por cada pago se añade uno al id
        return pago;
    }

    public List<Pagos_Repostajes> getPagosOrdenados() {
        return pagos.stream().sorted(Comparator.comparing(Pagos_Repostajes::getFecha).reversed().thenComparing(Comparator.comparingInt(Pagos_Repostajes::getId).reversed())).toList();
    }

    public String nombreCliente(int id) {
        Cliente c = buscarClientePorId(id);
        return c == null ? "Cliente desconocido" : c.getNombre();
    }
}