import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
        import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoEnCSV implements Almacenamiento {
    private final Path archivoClientes = Path.of("datos-practica1", "clientes.csv");
    private final Path archivoPagos = Path.of("datos-practica1", "pagos.csv");

    public AlmacenamientoEnCSV(Path origen) throws IOException {
        Files.createDirectories(archivoClientes.getParent());
        if (Files.notExists(archivoClientes)) Files.createFile(archivoClientes);
        if (Files.notExists(archivoPagos)) Files.createFile(archivoPagos);
    }

    public AlmacenamientoEnCSV() throws IOException {
        this(Path.of("datos-practica1"));
    }

    @Override
    public List<Cliente> leerCliente() throws IOException {
        List<Cliente> lista = new ArrayList<>();
        int numeroLinea = 0;
        for (String linea : Files.readAllLines(archivoClientes, StandardCharsets.UTF_8)) {
            numeroLinea++;
            if (linea.isBlank()) continue;
            try {
                String[] c = linea.split(";", -1);
                if (c.length != 4) throw new IllegalArgumentException("Número de campos incorrecto");
                lista.add(new Cliente(Integer.parseInt(c[0]), c[1], c[2], c[3]));
            } catch (RuntimeException e) {
                throw new IOException("Registro inválido en clientes.csv, línea " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirCliente(Cliente c) throws IOException {
        String linea = c.getId() + ";" + c.getNombre() + ";" + c.getTelefono() + ";" + c.getMatricula();
        Files.writeString(archivoClientes, linea + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() throws IOException {
        List<Pagos_Repostajes> lista = new ArrayList<>();
        int numeroLinea = 0;
        for (String linea : Files.readAllLines(archivoPagos, StandardCharsets.UTF_8)) {
            numeroLinea++;
            if (linea.isBlank()) continue;
            try {
                String[] c = linea.split(";", -1);
                if (c.length != 6) throw new IllegalArgumentException("Número de campos incorrecto");
                lista.add(new Pagos_Repostajes(
                        Integer.parseInt(c[0]),
                        Integer.parseInt(c[1]),
                        LocalDate.parse(c[2]),
                        Double.parseDouble(c[3]),
                        Double.parseDouble(c[4]),
                        c[5]));
            } catch (RuntimeException e) {
                throw new IOException("Registro inválido en pagos.csv, línea " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirPagos (Pagos_Repostajes p) throws IOException {
        String linea = p.getId() + ";" + p.getIdCliente() + ";" + p.getFecha() + ";" + p.getImporte() + ";" + p.getLitros() + ";" + p.getCombustible();
        Files.writeString(archivoPagos, linea + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}