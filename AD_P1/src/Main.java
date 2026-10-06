import java.io.IOException;

public class Main {
  public static void main(String[] args) {
        try {
            Almacenamiento almacenamiento = new AlmacenamientoEnJSON();
            Gasolinera gasolinera = new Gasolinera(almacenamiento);
            new Menu(gasolinera).iniciar();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("No se pudo iniciar la aplicación: " + e.getMessage());
        }
  }
}