import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
      try {
          Scanner sc = new Scanner(System.in);
          Path destino = MigraCSVToJSON.ejecutarMig(Path.of("datos-practica2"), sc);
          //Almacenamiento alm = new AlmacenamientoEnCSV(destino);
          Almacenamiento alm = new AlmacenamientoEnJSON(destino);
          Gasolinera g = new Gasolinera(alm);
          new Menu(g, sc).iniciar();
      } catch (IOException | IllegalArgumentException e) {
          System.out.println("No se pudo iniciar la aplicación: " + e);
      }
  }
}