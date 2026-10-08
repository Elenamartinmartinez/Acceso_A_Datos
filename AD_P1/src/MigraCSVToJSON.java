import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MigraCSVToJSON {
    private final Path origen; //Ruta de inicio (CSV)
    private final Path destino; //Ruta de destino (JSON)

    public MigraCSVToJSON(Path origen, Path destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public void migracion () throws IOException {
        //Leer los datos del fichero de CSV
        AlmacenamientoEnCSV csv = new AlmacenamientoEnCSV(origen); //Ruta de origen
        //Listas con los clientes y pagos
        List<Cliente> clientes = csv.leerCliente();
        List<Pagos_Repostajes> pagos = csv.leerPagos();

        //Ruta de destino
        Files.createDirectory(destino);

        try {
            //Iniciar/crear almacenamiento en JSON
            AlmacenamientoEnJSON json = new AlmacenamientoEnJSON();

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
        }
    }
}