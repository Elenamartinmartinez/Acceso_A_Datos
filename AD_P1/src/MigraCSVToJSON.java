import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MigraCSVToJSON {
    private final Path origen; //Ruta de inicio (CSV)
    private final Path destino; //Ruta de destino (JSON)

    public MigraCSVToJSON(Path origen, Path destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public static void ejecutarMig (Path directorioDestinoPorDefecto) {
        Path clientesJson = directorioDestinoPorDefecto.resolve("clientes.json");
        Path pagosJson = directorioDestinoPorDefecto.resolve("pagos.json");

        // Si ya existen los ficheros JSON, omitimos la migración (arranques posteriores)
        if (Files.exists(clientesJson) || Files.exists(pagosJson)) {
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("=== ASISTENTE ===");
        System.out.print("¿Existen ficheros ya creados y con datos en formato '.CSV' para migrar? (si/no): ");
        String respuesta = sc.nextLine().trim();

        if (respuesta.equalsIgnoreCase("si")) { //Si la respuesta es 'no' se pasa directamente al menú
            System.out.print("Introduzca la ruta de origen de los CSV: "); //Ruta de origen 'datos-practica1'
            String rutaOrigen = sc.nextLine().trim();

            System.out.print("Introduzca la ruta de destino para los JSON: "); //Ruta destino 'datos-practica2'
            String rutaDestino = sc.nextLine().trim();

            MigraCSVToJSON migrador = new MigraCSVToJSON(Path.of(rutaOrigen), Path.of(rutaDestino));
            try {
                migrador.migracion();
            } catch (IOException e) { //En caso de que la migración no sea posible
                System.out.println("Error la ejecución de la migración: " + e);
            }
        } else {
            System.out.println("Creando fichero...");
        }
    }

    public void migracion () throws IOException {
        Path clientesJson = destino.resolve("clientes.json");
        Path pagosJson = destino.resolve("pagos.json");


        if (Files.exists(clientesJson) || Files.exists(pagosJson) || Files.exists(destino)) {
            System.out.println("Migración cancelada: el destino ya existe o tiene datos.");
            return;
        }

        //Leer los datos del fichero de CSV
        AlmacenamientoEnCSV csv = new AlmacenamientoEnCSV(origen); //Ruta de origen
        //Listas con los clientes y pagos
        List<Cliente> clientes = csv.leerCliente();
        List<Pagos_Repostajes> pagos = csv.leerPagos();

        try {
            //Iniciar/crear almacenamiento en JSON
            AlmacenamientoEnJSON json = new AlmacenamientoEnJSON(destino);

            //Guardar los clientes de csv a json
            for (Cliente c : clientes) {
                json.escribirCliente(c); //va escribiendo los datos hasta que no haya clientes
            }
            //Guardar los pagos de csv a json
            for (Pagos_Repostajes p : pagos) {
                json.escribirPagos(p); //Va escribiendo los datos hasta que no haya pagos en csv
            }

            //Mostrar los resultados al usuario por pantalla
            System.out.println("¡Migración realizada correctamente!");
            System.out.println("Clientes migrados: "+clientes.size()); //Mostramos el número de clientes migrados
            System.out.println("Pagos migrados: "+pagos.size()); //Mostramos el número de pagos migrados a json

        } catch (IOException | RuntimeException e) { //Cancelar la migración de datos en caso de que haya algún error
            System.out.println("La migración ha sido cancelada..."+e);
            Files.deleteIfExists(clientesJson); //Elimina automáticamente el fichero si la migración falla
            Files.deleteIfExists(pagosJson); //Elimina automáticamente el fichero si la migración falla
        }
    }
}