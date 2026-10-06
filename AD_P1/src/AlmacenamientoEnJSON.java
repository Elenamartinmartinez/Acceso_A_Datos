import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoEnJSON implements Almacenamiento{
    //Rutas a los ficheros en JSON (Estén o no creados)
    private final Path archivoClientesJson = Path.of("datos-practica2", "clientes.json");
    private final Path archivoPagosJson = Path.of("datos-practica2", "pagos.json");

    public AlmacenamientoEnJSON () throws IOException { //Crea los ficheros en caso de que no existan
        Files.createDirectory(archivoClientesJson.getParent());
        if (Files.notExists(archivoClientesJson)) Files.createFile(archivoClientesJson);
        if (Files.notExists(archivoPagosJson)) Files.createFile(archivoPagosJson);
    }

    /*Métodos implementados de la interfaz de almacenamiento*/
    @Override
    public List<Cliente> leerCliente() throws IOException { //Guarda los nuevos clientes en un '.JSON'
        List<Cliente> lista = new ArrayList<>();
        int numeroLinea = 0;
        for (String linea : Files.readAllLines(archivoClientesJson, StandardCharsets.UTF_8)) {
            numeroLinea++;
            if (linea.isBlank()) continue;
            try {
                List<String> c = decodificar(linea);
                if (c.size() != 4) throw new IllegalArgumentException("Número de campos incorrecto");
                lista.add(new Cliente(Integer.parseInt(c.get(0)), c.get(1), c.get(2), c.get(3)));
            } catch (RuntimeException e) {
                throw new IOException("Registro inválido en clientes.json, línea " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirCliente(Cliente c) throws IOException { //Formato con el que los guarda
        anexar(archivoClientesJson, c.getId() + "," + codificar(c.getNombre()) + "," +
                codificar(c.getTelefono()) + "," + codificar(c.getMatricula()));
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() throws IOException { //Guarda los nuevos pagos en un '.JSON'
        List<Pagos_Repostajes> lista = new ArrayList<>();
        int numeroLinea = 0;
        for (String linea : Files.readAllLines(archivoPagosJson, StandardCharsets.UTF_8)) {
            numeroLinea++;
            if (linea.isBlank()) continue;
            try {
                List<String> c = decodificar(linea);
                if (c.size() != 6) throw new IllegalArgumentException("Número de campos incorrecto");
                lista.add(new Pagos_Repostajes(Integer.parseInt(c.get(0)),
                        Integer.parseInt(c.get(1)), java.time.LocalDate.parse(c.get(2)),
                        Double.parseDouble(c.get(3)), Double.parseDouble(c.get(4)), c.get(5)));
            } catch (RuntimeException e) {
                throw new IOException("Registro inválido en pagos.json, línea " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirPagos (Pagos_Repostajes p) throws IOException { //Formato con el que los guarda
        anexar(archivoPagosJson, p.getId() + "," + p.getIdCliente() + "," + p.getFecha() + "," + p.getImporte() + "," + p.getLitros() + ";" + codificar(p.getCombustible()));
    }

    /*Métodos para pasar los datos al fichero*/
    private void anexar(Path archivo, String linea) throws IOException {
        Files.writeString(archivo, linea + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    //El texto mantiene la misma estructura aunque se codifique
    private String codificar(String texto) {
        return java.util.Base64.getEncoder().encodeToString(texto.getBytes(StandardCharsets.UTF_8));
    }

    private List<String> decodificar(String linea) {
        String[] partes = linea.split(",", -1);
        List<String> campos = new ArrayList<>();
        for (int i = 0; i < partes.length; i++) {
            if ((partes.length == 4 && i > 0) || (partes.length == 6 && i == 5)) {
                campos.add(new String(java.util.Base64.getDecoder().decode(partes[i]), StandardCharsets.UTF_8));
            } else campos.add(partes[i]);
        }
        return campos;
    }
}

/*
Formato JSON
{
   "clientes": [
    {
    }
    {
    }
    {
    }
   ]
}
*/