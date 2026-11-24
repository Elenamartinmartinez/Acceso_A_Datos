package dominio.estaciones;

/** Representa una estación de servicio. */
public class EstacionServicio {
    private final Integer id;
    private final String nombre;
    private final String direccion;
    public EstacionServicio(Integer id, String nombre, String direccion) {
        if (nombre == null || nombre.trim().isEmpty() || direccion == null || direccion.trim().isEmpty()) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.nombre = nombre.trim(); this.direccion = direccion.trim();
    }
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
}
