import java.util.Date;

public class Pagos_Repostajes {
    /*ATRIBUTOS*/
    //Atributos heredados de cliente
    private int id_Cliente; //Atributo de la clase 'Cliente'
    //Atributos propios
    private int id; //Se asigna automáticamente
    private Date fecha; //Fecha actual
    private float importe; //Mayor a 0 y con max 2 decimales (cantidad en euros)
    private float litros; //Mayor a 0 y con max 2 decimales
    private String combustible; //Solo se puede elegir entre "Gasolina 95" o "Diésel"

    /*MÉTODOS*/
    //Métodos getters y setters para todos los atributos que se pidan por pantalla
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    public float getImporte() {
        return importe;
    }

    public void setLitros(float litros) {
        this.litros = litros;
    }
    public float getLitros() {
        return litros;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
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
