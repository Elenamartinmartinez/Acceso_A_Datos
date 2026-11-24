package dominio.ventas;

/** Representa un producto y su cantidad dentro de una venta. */
public class LineaVenta {
    private final Integer id, idVenta, idProducto;
    private final double cantidad, importe;
    public LineaVenta(Integer id, Integer idVenta, Integer idProducto, double cantidad, double importe) {
        if (idVenta == null || idProducto == null || !Double.isFinite(cantidad) || cantidad <= 0 || !Double.isFinite(importe) || importe < 0) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.idVenta = idVenta; this.idProducto = idProducto; this.cantidad = cantidad; this.importe = importe;
    }
    public Integer getId() { return id; }
    public Integer getIdVenta() { return idVenta; }
    public Integer getIdProducto() { return idProducto; }
    public double getCantidad() { return cantidad; }
    public double getImporte() { return importe; }
}
