package dominio.catalogo;

/** Define la información común y el cálculo de importe de un producto. */
public abstract class Producto {
    private final Integer id;
    private final String nombre;
    private final double precioUnitario;
    protected Producto(Integer id, String nombre, double precioUnitario) {
        if (nombre == null || nombre.trim().isEmpty() || !Double.isFinite(precioUnitario) || precioUnitario < 0) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.nombre = nombre.trim(); this.precioUnitario = precioUnitario;
    }
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecioUnitario() { return precioUnitario; }
    public abstract void validarCantidad(double cantidad);
    public abstract double calcularImporte(double cantidad);
}
