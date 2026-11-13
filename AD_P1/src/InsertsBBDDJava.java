import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertsBBDDJava {
    //Maximos para las tablas
    private static final int MAX_Clientes = 500;
    private static final int MAX_Pagos = 2000;

    //Opciones de combustible
    private static final String[] Combustibles = {"Gasolina 95","Gasolina 98","Diésel"};

    private InsertsBBDDJava() {}

    public static void cargar() throws SQLException{
        //Conectamos a la base de datos
        try (Connection conexion = ConexionBD.obtener()) {
            conexion.setAutoCommit(false);

            try {
            }
        }
    }
//Solo comprobamos si hay datos en clientes, no puede haber datos en pagos_repostaje si no hay clientes
    private static boolean datos (Connection conexion) throws SQLException{
        try (Statement sentencia = conexion.createStatement();ResultSet rs = sentencia.executeQuery("SELECT COUNT(*) FROM clientes")) {

        }
    }
}
