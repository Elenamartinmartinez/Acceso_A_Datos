package dominio.estaciones;

import java.time.LocalDateTime;

/** Registra una incidencia de una estación o surtidor. */
public class Incidencia {
    private final Integer id, idEstacion, idSurtidor;
    private final String descripcion;
    private final LocalDateTime fecha;
    public Incidencia(Integer id, Integer idEstacion, Integer idSurtidor, String descripcion, LocalDateTime fecha) {
        if (idEstacion == null || descripcion == null || descripcion.trim().isEmpty() || fecha == null) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.idEstacion = idEstacion; this.idSurtidor = idSurtidor; this.descripcion = descripcion.trim(); this.fecha = fecha;
    }
    public Integer getId() { return id; }
    public Integer getIdEstacion() { return idEstacion; }
    public Integer getIdSurtidor() { return idSurtidor; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFecha() { return fecha; }
}
