package dominio.estaciones;

/** Representa un surtidor de una estación. */
public class Surtidor {
    private final Integer id, idEstacion;
    private final int numero;
    private final boolean activo;
    public Surtidor(Integer id, Integer idEstacion, int numero, boolean activo) {
        if (idEstacion == null || numero <= 0) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.idEstacion = idEstacion; this.numero = numero; this.activo = activo;
    }
    public Integer getId() { return id; }
    public Integer getIdEstacion() { return idEstacion; }
    public int getNumero() { return numero; }
    public boolean isActivo() { return activo; }
}
