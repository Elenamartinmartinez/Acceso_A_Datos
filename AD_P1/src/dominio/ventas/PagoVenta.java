package dominio.ventas;

import java.time.LocalDateTime;

/** Representa un pago asociado a una venta. */
public class PagoVenta {
    private final Integer id, idVenta;
    private final double importe;
    private final String metodo;
    private final LocalDateTime fecha;
    public PagoVenta(Integer id, Integer idVenta, double importe, String metodo, LocalDateTime fecha) {
        if (idVenta == null || !Double.isFinite(importe) || importe <= 0 || metodo == null || metodo.trim().isEmpty() || fecha == null) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.idVenta = idVenta; this.importe = importe; this.metodo = metodo.trim(); this.fecha = fecha;
    }
    public Integer getId() { return id; }
    public Integer getIdVenta() { return idVenta; }
    public double getImporte() { return importe; }
    public String getMetodo() { return metodo; }
    public LocalDateTime getFecha() { return fecha; }
}
