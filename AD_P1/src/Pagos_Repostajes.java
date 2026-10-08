import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        //Ninguno de los campos puede quedar en blanco
        if (id <= 0 || idCliente <= 0) {
            System.out.println("Identificadores no válidos");
        }
        if (fecha == null) {
            System.out.println("La fecha es obligatoria");
        }
        if (combustible == null || combustible.trim().isEmpty()){
            System.out.println("El tipo de combustible es obligatorio");
        }
        validarCantidad(importe, "Importe");
        validarCantidad(litros, "Litros");

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

    //Validar cantidad de litros o el importe, comprobando que sigan los requisitos de ser positivos y los decimales
    private static void validarCantidad(double n, String campo) {
        if (!Double.isFinite(n) || n <= 0 || Math.abs(n * 100 - Math.round(n * 100)) > 0.000001){
            System.out.println("El campo ["+campo+"] debe ser un número positivo y tener un máximo de 2 decimales");
        }
    }

    //Devuelve por pantalla la información del pago
    @Override
    public String toString() {
        return "[ID: "+id+" || Fecha: "+fecha.format(formatoFCH)+" || Importe: "+importe+" || Litros: "+litros+" || Combustible: "+combustible+"]";
    }
}