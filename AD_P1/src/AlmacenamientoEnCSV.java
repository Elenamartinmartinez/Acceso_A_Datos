import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.List;

public class AlmacenamientoEnCSV implements Almacenamiento{

    Path directorio;
    Path archivoClientes;
    Path archivoPagos;

    public AlmacenamientoEnCSV () throws IOException {
        directorio = Path.of("D:","User","Alumno Mañana","Documents","2DAM","Acceso_A_Datos","Acceso_A_Datos","AD_P1");
        //D:\Users\Alumno Mañana\Documents\2DAM\Acceso_A_Datos\Acceso_A_Datos\AD_P1

        Files.createDirectory(directorio);
        archivoClientes = directorio.resolve("clientes.csv");
        archivoPagos = directorio.resolve("pagos.csv");
    }

    @Override
    public List<Cliente> leerCliente() {
        try (BufferedReader br = Files.newBufferedReader(archivoClientes, StandardCharsets.UTF_8);){
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea); //Muestra cada cliente
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public boolean escribirCliente(Cliente cliente) {
        try (BufferedWriter bw = Files.newBufferedWriter(archivoClientes, StandardOpenOption.CREATE_NEW)) {
            //Respetar formato CSV
            bw.write(cliente.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() {
        try (BufferedReader bf = Files.newBufferedReader(archivoPagos);){
            String linea;

            while ((linea = bf.readLine()) != null) {
                System.out.println(linea); //Muestra cada pago por pantalla
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public boolean escribirPagos(Pagos_Repostajes pagos) {
        try (BufferedWriter bw = Files.newBufferedWriter(archivoPagos, StandardOpenOption.CREATE_NEW)) {
            //Respetar formato CSV
            bw.write(pagos.toString());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}