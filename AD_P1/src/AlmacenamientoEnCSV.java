import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class AlmacenamientoEnCSV implements Almacenamiento{

    Path directorio;
    Path archivoClientes;
    Path archivoPagos;

    public AlmacenamientoEnCSV () throws IOException {
        directorio = Path.of("datos");
        Files.createDirectory(directorio);
        archivoClientes = directorio.resolve("clientes");
        archivoPagos = directorio.resolve("pagos");
    }


    @Override
    public List<Cliente> leerCliente() {

        try (BufferedReader bf = Files.newBufferedReader(archivoClientes);){



        } catch (IOException e) {
            //System.out.println("Error: "+e);
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public boolean escribirCliente(Cliente cliente) {
        try (BufferedWriter bw = Files.newBufferedWriter(archivoClientes, StandardOpenOption.CREATE_NEW)) {
            //Respetar formato CSV
            bw.write(cliente.toString());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() {
        return List.of();
    }

    @Override
    public boolean escribirPagos(Pagos_Repostajes pagos) {
        return false;
    }
}
