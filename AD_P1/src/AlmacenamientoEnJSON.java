import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoEnJSON implements Almacenamiento{

    //Rutas a los ficheros en JSON (Estén o no creados)
    private final Path archivoClientesJson;
    private final Path archivoPagosJson;

    public AlmacenamientoEnJSON () throws IOException {
        this(Path.of("datos-practica2"));
    }

    public AlmacenamientoEnJSON (Path directorio) throws IOException { //Crea los ficheros en caso de que no existan
        Files.createDirectory(directorio);//Se crea el directorio que contendrá ambos ficheros

        archivoClientesJson = directorio.resolve("clientes.json"); //Fichero para almacenar los clientes
        archivoPagosJson = directorio.resolve("pagos.json"); //Fichero para almacenar los pagos

        //Para poner los corchetes al principio y final del archivo '.json' y crear los ficheros en caso de que no existan
        if (Files.notExists(archivoClientesJson)) {
            Files.writeString(archivoClientesJson, "[]", StandardCharsets.UTF_8);
        }
        if (Files.notExists(archivoPagosJson)) {
            Files.writeString(archivoPagosJson, "[]", StandardCharsets.UTF_8);
        }
    }

    /*Métodos implementados de la interfaz de almacenamiento*/
    @Override
    public List<Cliente> leerCliente() throws IOException { //Guarda los nuevos clientes en un 'JSON'
        List<Cliente> lista = new ArrayList<>();
        String contenido = Files.readString(archivoClientesJson, StandardCharsets.UTF_8).trim();

        if (contenido.equals("[]") || contenido.isEmpty()) { return lista; }//Le todas las lineas del fichero

        contenido = contenido.substring(1, contenido.length() - 1).trim(); //Quita los corchetes externos y se queda con el resto de la info del cliente
        if (contenido.isEmpty()) { return lista; }

        String[] objetos = contenido.split("\\},\\s*\\{"); //Separa a los clientes por comas
        int numeroLinea = 0;

        for (String obj : objetos) {
            numeroLinea++;
            try {
                // Limpiamos las llaves y comillas para extraer los pares clave-valor
                obj = obj.replace("{", "").replace("}", "").replace("\"", "");
                String[] pares = obj.split(",");

                int id = 0;
                String nombre = "";
                String telefono = "";
                String matricula = "";

                for (String par : pares) {
                    String[] kv = par.split(":");
                    if (kv.length < 2) continue;
                    String clave = kv[0].trim();
                    String valor = kv[1].trim();

                    switch (clave) {
                        case "id" -> id = Integer.parseInt(valor);
                        case "nombre" -> nombre = valor;
                        case "telefono" -> telefono = valor;
                        case "matricula" -> matricula = valor;
                    }
                }
                lista.add(new Cliente(id, nombre, telefono, matricula));

            } catch (Exception e) { //En caso de no poder leer el cliente, se muestra por pantalla
                throw new IOException("Registro inválido en clientes.json en la posición " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirCliente(Cliente c) throws IOException { //Formato con el que los guarda
        List<Cliente> clientes = leerCliente();
        clientes.add(c);

        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cli = clientes.get(i);
            sb.append(String.format("  {\"id\": %d, \"nombre\": \"%s\", \"telefono\": \"%s\", \"matricula\": \"%s\"}",
                    cli.getId(), cli.getNombre(), cli.getTelefono(), cli.getMatricula()));
            if (i < clientes.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        Files.writeString(archivoClientesJson, sb.toString(), StandardCharsets.UTF_8);
    }

    @Override
    public List<Pagos_Repostajes> leerPagos() throws IOException { //Guarda los nuevos pagos en un '.JSON'
        List<Pagos_Repostajes> lista = new ArrayList<>();

        String contenido = Files.readString(archivoPagosJson, StandardCharsets.UTF_8).trim();//Lee el contenido de pagos

        if (contenido.equals("[]") || contenido.isEmpty()) { return lista; }

        contenido = contenido.substring(1, contenido.length() - 1).trim(); //Quita los corchetes externos y se queda con el resto de la info del cliente
        if (contenido.isEmpty()) { return lista; }

        String[] objetos = contenido.split("\\},\\s*\\{"); //Separa a los clientes por comas
        int numeroLinea = 0;

        for (String obj : objetos) {
            numeroLinea++;
            try {// Limpiamos las llaves y comillas para extraer los pares clave-valor
                obj = obj.replace("{", "").replace("}", "").replace("\"", "");
                String[] pares = obj.split(",");

                int id = 0;
                int idCliente = 0;
                LocalDate fecha = null;
                double importe = 0.0;
                double litros = 0.0;
                String combustible = "";

                for (String par : pares) {
                    String[] kv = par.split(":");
                    if (kv.length < 2) continue;
                    String clave = kv[0].trim();
                    String valor = kv[1].trim();

                    switch (clave) {
                        case "id" -> id = Integer.parseInt(valor);
                        case "clienteId" -> idCliente = Integer.parseInt(valor);
                        case "fecha" -> fecha = LocalDate.parse(valor);
                        case "importe" -> importe = Double.parseDouble(valor);
                        case "litros" -> litros = Double.parseDouble(valor);
                        case "combustible" -> combustible = valor;
                    }
                }
                lista.add(new Pagos_Repostajes(id, idCliente, fecha, importe, litros, combustible));

            } catch (Exception e) { //En caso de no poder leer el cliente, se muestra por pantalla
                throw new IOException("Registro inválido en pagos.json en la posición " + numeroLinea, e);
            }
        }
        return lista;
    }

    @Override
    public void escribirPagos (Pagos_Repostajes p) throws IOException { //Formato con el que los guarda
        List<Pagos_Repostajes> pagos = leerPagos();
        pagos.add(p);

        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < pagos.size(); i++) {
            Pagos_Repostajes pag = pagos.get(i);
            //Formato en el que se guardan los '.json'
            sb.append(String.format("  {\"id\": %d, \"clienteId\": %d, \"fecha\": \"%s\", \"importe\": %.2f, \"litros\": %.2f, \"combustible\": \"%s\"}",
                    pag.getId(), pag.getIdCliente(), pag.getFecha().toString(), pag.getImporte(), pag.getLitros(), pag.getCombustible()));
            if (i < pagos.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        Files.writeString(archivoPagosJson, sb.toString(), StandardCharsets.UTF_8);//Escribe el cliente en el '.json'
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