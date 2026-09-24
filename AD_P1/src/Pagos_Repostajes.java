import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Pagos_Repostajes {
    /*ATRIBUTOS*/
    //Formato de la fecha
    private static final DateTimeFormatter formatoFCH = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //Atributos para registrar el pago
    private int id;
    private int idCliente;
    private LocalDate fecha;
    private double importe;
    private double litros;
    private String combustible;

    /*CONSTRUCTOR*/
    public Pagos_Repostajes(int id, int idCliente, LocalDate fecha, double importe, double litros, String combustible) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.importe = importe;
        this.litros = litros;
        this.combustible = combustible.trim();
    }

    /*MÉTODOS*/
    // Getters y Setters para todos los atributos
    public int getId() {
        return id;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public double getImporte() {
        return importe;
    }
    public double getLitros() {
        return litros;
    }
    public String getCombustible() {
        return combustible;
    }

    //Como se mostrará en el fichero correspondiente
    public String toCSV() {
        return "[ID: "+id+" || Fecha: "+fecha.format(formatoFCH)+" || Importe: "+importe+" || Litros: "+litros+" || Combustible: "+combustible+"]";
    }

    //Reconstruir el objeto desde CSV
    public static Pagos_Repostajes fromCSV (String linea) {
        String[] partes = linea.split(";", -1);
        return new Pagos_Repostajes ( Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), LocalDate.parse(partes[2], formatoFCH), Double.parseDouble(partes[3]), Double.parseDouble(partes[4]), partes[5]);
    }

    //Devuelve por pantalla la información del pago
    @Override
    public String toString() {
        return "[ID: "+id+" || Fecha: "+fecha.format(formatoFCH)+" || Importe: "+importe+" || Litros: "+litros+" || Combustible: "+combustible+"]";
    }
}
