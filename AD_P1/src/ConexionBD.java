import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Scanner;

public class ConexionBD {
    //Ruta para guardar los datos
//    private final Path almacenamientoTablas = Path.of("datos-practica3", "datos.db");
    static Scanner sc = new Scanner(System.in);
    //Directorio
    private static final String directorio = "datos-practica3";
    //url
    private static final String url = "jdbc:sqlite:"+directorio+"/gasolinera.db";

    private ConexionBD(){}

    public static Connection obtener () throws SQLException {
        //Probamos la existencia del directorio, si no existe, se crea
        if ((Files.exists(Path.of(url)))&&(Files.isDirectory(Path.of(url)))) {
            System.out.println("La ruta dada ya existe");
        } else if (!Files.exists(Path.of(url))) {
            System.out.println("La ruta dada no existe");
            System.out.print("¿Desea crearla?: "); //Dar la opción de crear la ruta
            String op = sc.nextLine();
            if (op.equalsIgnoreCase("no")){ //Si el usuario no quiere
                System.out.println("Okey, adios...");
            } else if (op.equalsIgnoreCase("si")) { //Si el usuario si quiere
                System.out.println("Creando directorio...");
                try { //Creamos el directorio
                    Files.createDirectories(Path.of(directorio));
                    System.out.println("¡Directorio a la base de datos creado!");
                } catch (IOException e) { //En caso de que hayas problemas con la creación del directorio
                    System.out.println("No se pudo crear el directorio de la base de datos...\n"+e.getMessage());
                }
            }
        }
        //Probamos la conexión a la base de datos
        Connection conexion = DriverManager.getConnection(url);
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos...\n"+e.getMessage());
        }
        return conexion;
    }
}