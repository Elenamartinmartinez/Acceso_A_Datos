import java.io.IOException;
import java.util.*;

public interface Almacenamiento {
    //Cliente
    List<Cliente> leerCliente () throws IOException;
    void escribirCliente (Cliente cliente) throws IOException;

    //
    List<Pagos_Repostajes> leerPagos() throws IOException;
    void escribirPagos (Pagos_Repostajes pagos) throws IOException;
}