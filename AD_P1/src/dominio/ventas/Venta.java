package dominio.ventas;

import java.time.LocalDateTime;

/** Representa una venta realizada a un cliente en una estación. */
public class Venta {
    private final Integer id, idCliente, idEstacion;
    private final LocalDateTime fecha;
    public Venta(Integer id, Integer idCliente, Integer idEstacion, LocalDateTime fecha) {
        if (idEstacion == null || fecha == null) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.idCliente = idCliente; this.idEstacion = idEstacion; this.fecha = fecha;
    }
    public Integer getId() { return id; }
    public Integer getIdCliente() { return idCliente; }
    public Integer getIdEstacion() { return idEstacion; }
    public LocalDateTime getFecha() { return fecha; }
}
