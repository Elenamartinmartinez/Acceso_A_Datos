import java.util.Date;

public class Pagos_Repostajes {
    /*ATRIBUTOS*/
    private int id;
    private int idCliente;
    private String fecha;
    private double importe;
    private double litros;
    private String combustible;

    /*CONSTRUCTOR*/
    public Pagos_Repostajes(int id, int idCliente, String fecha, double importe, double litros, String combustible) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.importe = importe;
        this.litros = litros;
        this.combustible = combustible;
    }

    /*MÉTODOS*/
    // Getters y Setters para todos los atributos
    public int getId() {
        return id;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public String getFecha() {
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

    //Devuelve por pantalla la información del pago
    @Override
    public String toString() {
        return "[ID: "+id+" || Fecha: "+fecha+" || Importe: "+importe+" || Litros: "+litros+" || Combustible: "+combustible+"]";
    }
}
