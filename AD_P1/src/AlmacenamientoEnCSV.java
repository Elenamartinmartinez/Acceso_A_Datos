import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoEnCSV implements Almacenamiento {
    private final Path archivoClientes = Path.of("datos", "clientes.csv");
    private final Path archivoPagos = Path.of("datos", "pagos.csv");

    public AlmacenamientoEnCSV() throws IOException {
        Files.createDirectories(archivoClientes.getParent());
        if (Files.notExists(archivoClientes)) Files.createFile(archivoClientes);
        if (Files.notExists(archivoPagos)) Files.createFile(archivoPagos);
    }

    @Override
    public List<Cliente> leerCliente() throws IOException {
        List<Cliente> lista = new ArrayList<>();
        int numeroLinea = 0;
        for (String linea : Files.readAllLines(archivoClientes, StandardCharsets.UTF_8)) {
            numeroLinea++;
            if (linea.isBlank()) continue;
            try {
                List<String> c = decodificar(linea);
                if (c.size() != 4) throw new IllegalArgumentException("Número de campos incorrecto");
                lista.add(new Cliente(Integer.parseInt(c.get(0)), c.get(1), c.get(2), c.get(3)));
            } catch (RuntimeException e) {
                throw new IOException("Registro inválido en clientes.csv, línea " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirCliente(Cliente c) throws IOException {
        anexar(archivoClientes, c.getId() + ";" + codificar(c.getNombre()) + ";" +
                codificar(c.getTelefono()) + ";" + codificar(c.getMatricula()));
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() throws IOException {
        List<Pagos_Repostajes> lista = new ArrayList<>();
        int numeroLinea = 0;
        for (String linea : Files.readAllLines(archivoPagos, StandardCharsets.UTF_8)) {
            numeroLinea++;
            if (linea.isBlank()) continue;
            try {
                List<String> c = decodificar(linea);
                if (c.size() != 6) throw new IllegalArgumentException("Número de campos incorrecto");
                lista.add(new Pagos_Repostajes(Integer.parseInt(c.get(0)),
                        Integer.parseInt(c.get(1)), java.time.LocalDate.parse(c.get(2)),
                        Double.parseDouble(c.get(3)), Double.parseDouble(c.get(4)), c.get(5)));
            } catch (RuntimeException e) {
                throw new IOException("Registro inválido en pagos.csv, línea " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirPagos (Pagos_Repostajes p) throws IOException {
        anexar(archivoPagos, p.getId() + ";" + p.getIdCliente() + ";" + p.getFecha() + ";" + p.getImporte() + ";" + p.getLitros() + ";" + codificar(p.getCombustible()));
    }

    private void anexar(Path archivo, String linea) throws IOException {
        Files.writeString(archivo, linea + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    //El texto mantiene la misma estructura aunque se codifique
    private String codificar(String texto) {
        return java.util.Base64.getEncoder().encodeToString(texto.getBytes(StandardCharsets.UTF_8));
    }

    private List<String> decodificar(String linea) {
        String[] partes = linea.split(";", -1);
        List<String> campos = new ArrayList<>();
        for (int i = 0; i < partes.length; i++) {
            if ((partes.length == 4 && i > 0) || (partes.length == 6 && i == 5)) {
                campos.add(new String(java.util.Base64.getDecoder().decode(partes[i]), StandardCharsets.UTF_8));
            } else campos.add(partes[i]);
        }
        return campos;
    }
}