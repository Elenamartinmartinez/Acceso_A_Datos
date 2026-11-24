package dominio.catalogo;

/** Representa un producto vendido en unidades enteras. */
public class ProductoEnvasado extends Producto {
    public ProductoEnvasado(Integer id, String nombre, double precioUnitario) { super(id, nombre, precioUnitario); }
    @Override public void validarCantidad(double cantidad) {
        if (!Double.isFinite(cantidad) || cantidad <= 0 || cantidad != Math.rint(cantidad)) throw new IllegalArgumentException("La cantidad debe ser entera");
    }
    @Override public double calcularImporte(double cantidad) { validarCantidad(cantidad); return cantidad * getPrecioUnitario(); }
}
