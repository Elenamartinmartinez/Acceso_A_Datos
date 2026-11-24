package dominio.clientes;

import java.time.LocalDate;

/** Vincula un cliente con una campaña. */
public class InscripcionCampana {
    private final Integer id, idCliente, idCampana;
    private final LocalDate fecha;
    public InscripcionCampana(Integer id, Integer idCliente, Integer idCampana, LocalDate fecha) {
        if (idCliente == null || idCampana == null || fecha == null) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.idCliente = idCliente; this.idCampana = idCampana; this.fecha = fecha;
    }
    public Integer getId() { return id; }
    public Integer getIdCliente() { return idCliente; }
    public Integer getIdCampana() { return idCampana; }
    public LocalDate getFecha() { return fecha; }
}
