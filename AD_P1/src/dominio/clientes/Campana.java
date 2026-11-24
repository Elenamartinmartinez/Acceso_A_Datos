package dominio.clientes;

import java.time.LocalDate;

/** Define una campaña comercial para clientes. */
public class Campana {
    private final Integer id;
    private final String nombre;
    private final LocalDate inicio, fin;
    public Campana(Integer id, String nombre, LocalDate inicio, LocalDate fin) {
        if (nombre == null || nombre.trim().isEmpty() || inicio == null || fin == null || fin.isBefore(inicio)) throw new IllegalArgumentException("Datos no válidos");
        this.id = id; this.nombre = nombre.trim(); this.inicio = inicio; this.fin = fin;
    }
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getInicio() { return inicio; }
    public LocalDate getFin() { return fin; }
}
