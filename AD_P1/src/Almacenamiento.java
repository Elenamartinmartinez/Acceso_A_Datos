import java.util.*;

public interface Almacenamiento {
    public List<Cliente> leerCliente ();
    public boolean escribirCliente (Cliente cliente);

    public List<Pagos_Repostajes> leerPagos();
    public boolean escribirPagos (Pagos_Repostajes pagos);
}
