import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Conectando a la base de datos...");
          try (Connection conexion = ConexionBD.obtener()){
            System.out.println("¡Se ha podido conectar a la base de datos!");
            System.out.println("Info de la base de datos: "+conexion.getMetaData().getDatabaseProductName()+" "+conexion.getMetaData().getDatabaseProductVersion());
              }
          CrearBBDD.crear();


          Path destino = MigraCSVToJSON.ejecutarMig(Path.of("datos-practica2"), sc);
          //Almacenamiento alm = new AlmacenamientoEnCSV(destino);
          Almacenamiento alm = new AlmacenamientoEnJSON(destino);
          Gasolinera g = new Gasolinera(alm);
          new Menu(g, sc).iniciar();
      } catch (IOException | IllegalArgumentException | SQLException e) {
          System.out.println("No se pudo iniciar la aplicación: " + e);
      }
  }
}