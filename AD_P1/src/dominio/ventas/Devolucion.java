package dominio.ventas;

import java.time.LocalDateTime;

/** Representa la devolución de una línea de venta. */
public class Devolucion {
    private final Integer id, idLineaVenta;
    private final double cantidad;
    private final LocalDateTime fecha;
    private final String motivo;
    public Devolucion(Integer id, Integer idLineaVenta, double cantidad, LocalDateTime fecha, String motivo) {
        if (idLineaVenta == null || !Double.isFinite(cantidad) || cantidad <= 0 || fecha == null) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.idLineaVenta = idLineaVenta; this.cantidad = cantidad; this.fecha = fecha; this.motivo = motivo == null ? null : motivo.trim();
    }
    public Integer getId() { return id; }
    public Integer getIdLineaVenta() { return idLineaVenta; }
    public double getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
}
