package dominio.catalogo;

import java.time.LocalDate;

/** Define el suministro acordado entre un proveedor y un producto. */
public class AcuerdoSuministro {
    private final Integer id, idProveedor, idProducto;
    private final double precio;
    private final LocalDate inicio;
    public AcuerdoSuministro(Integer id, Integer idProveedor, Integer idProducto, double precio, LocalDate inicio) {
        if (idProveedor == null || idProducto == null || !Double.isFinite(precio) || precio < 0 || inicio == null) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.idProveedor = idProveedor; this.idProducto = idProducto; this.precio = precio; this.inicio = inicio;
    }
    public Integer getId() { return id; }
    public Integer getIdProveedor() { return idProveedor; }
    public Integer getIdProducto() { return idProducto; }
    public double getPrecio() { return precio; }
    public LocalDate getInicio() { return inicio; }
}
