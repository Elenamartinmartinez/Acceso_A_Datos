import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoEnCSV implements Almacenamiento{

    Path directorio;
    Path archivoClientes;
    Path archivoPagos;

    public AlmacenamientoEnCSV () throws IOException {
        /*DIRECTORIO*/
        directorio = Path.of("datos");

        //En caso de que no exista el directorio, se crea automáticamente
        if (!Files.exists(directorio)) {
            Files.createDirectory(directorio);
        }

        /*ARCHIVOS DE DIRECTORIO*/
        archivoClientes = directorio.resolve("clientes.csv");
        archivoPagos = directorio.resolve("pagos.csv");

        //En caso de que los archivos no existan, se crean automáticamente
        if (!Files.exists(archivoClientes)) {
            Files.createFile(archivoClientes);
        }
    }

    @Override
    public List<Cliente> leerCliente() {
        List<Cliente> listaCli = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(archivoClientes, StandardCharsets.UTF_8);){
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    listaCli.add(Cliente.fromCSV(linea)); //Lee cada cliente y lo añade
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaCli;
    }

    @Override
    public boolean escribirCliente(Cliente cliente) {
        try (BufferedWriter bw = Files.newBufferedWriter(archivoClientes, StandardOpenOption.CREATE_NEW)) {
            //Respetar formato CSV
            bw.write(cliente.toCSV());
            bw.newLine();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() {
       List<Pagos_Repostajes> listaPag = new ArrayList<>();

        try (BufferedReader bf = Files.newBufferedReader(archivoPagos);){
            String linea;
            while ((linea = bf.readLine()) != null) {
                if (!linea.isBlank()) {
                    listaPag.add(Pagos_Repostajes.fromCSV(linea));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaPag;
    }

    @Override
    public boolean escribirPagos(Pagos_Repostajes pagos) {
        try (BufferedWriter bw = Files.newBufferedWriter(archivoPagos, StandardOpenOption.CREATE_NEW)) {
            //Respetar formato CSV
            bw.write(pagos.toCSV());
            bw.newLine();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
            return false;
        }
    }
}