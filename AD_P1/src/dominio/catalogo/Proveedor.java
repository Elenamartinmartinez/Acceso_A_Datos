package dominio.catalogo;

/** Representa un proveedor del catálogo. */
public class Proveedor {
    private final Integer id;
    private final String nombre, contacto;
    public Proveedor(Integer id, String nombre, String contacto) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre es obligatorio");
        this.id = id; this.nombre = nombre.trim(); this.contacto = contacto == null ? null : contacto.trim();
    }
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
}
