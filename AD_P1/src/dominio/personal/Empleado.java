package dominio.personal;

/** Representa a un empleado. */
public class Empleado {
    private final Integer id;
    private final String nombre, documento;
    public Empleado(Integer id, String nombre, String documento) {
        if (nombre == null || nombre.trim().isEmpty() || documento == null || documento.trim().isEmpty()) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.nombre = nombre.trim(); this.documento = documento.trim();
    }
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
}
