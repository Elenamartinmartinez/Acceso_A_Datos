package dominio.clientes;

/** Representa una tarjeta de fidelización de un cliente. */
public class TarjetaCliente {
    private final Integer id, idCliente;
    private final String numero;
    private final boolean activa;
    public TarjetaCliente(Integer id, Integer idCliente, String numero, boolean activa) {
        if (idCliente == null || numero == null || numero.trim().isEmpty()) throw new IllegalArgumentException("Datos obligatorios");
        this.id = id; this.idCliente = idCliente; this.numero = numero.trim(); this.activa = activa;
    }
    public Integer getId() { return id; }
    public Integer getIdCliente() { return idCliente; }
    public String getNumero() { return numero; }
    public boolean isActiva() { return activa; }
}
