package dominio.catalogo;

/** Representa un producto vendido por cantidad decimal. */
public class ProductoGranel extends Producto {
    public ProductoGranel(Integer id, String nombre, double precioUnitario) { super(id, nombre, precioUnitario); }
    @Override public void validarCantidad(double cantidad) {
        if (!Double.isFinite(cantidad) || cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
    }
    @Override public double calcularImporte(double cantidad) { validarCantidad(cantidad); return cantidad * getPrecioUnitario(); }
}
