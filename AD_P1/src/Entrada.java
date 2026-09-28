import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Entrada {
    private final Scanner sc;
    public Entrada(Scanner sc) { this.sc = sc; }

    public String texto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("El campo no puede quedar vacío.");
        }
    }

    public String fecha(String mensaje) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        while (true) {
            System.out.print(mensaje);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return LocalDate.now().toString();
            try { return LocalDate.parse(s, f).toString(); }
            catch (DateTimeParseException e) { System.out.println("Fecha no válida."); }
        }
    }

    public int entero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n > 0) return n;
            } catch (NumberFormatException ignored) { }
            System.out.println("Introduce un entero positivo.");
        }
    }

    public double cantidad(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String s = sc.nextLine().trim().replace(',', '.');
            if (!s.matches("\\d+(\\.\\d{1,2})?")) {
                System.out.println("Introduce un número positivo con máximo dos decimales.");
                continue;
            }
            try {
                double n = Double.parseDouble(s);
                if (Double.isFinite(n) && n > 0) return n;
            } catch (NumberFormatException ignored) { }
            System.out.println("La cantidad debe ser mayor que cero.");
        }
    }
}
