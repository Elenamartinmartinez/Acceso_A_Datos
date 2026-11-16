import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBBDD {
    //Creamos una tabla para los clientes
    private static final String CREAR_CLIENTES = """
            CREATE TABLE IF NOT EXISTS clientes (
                id INTEGER PRIMARY KEY,
                nombre TEXT NOT NULL,
                tlfno TEXT NOT NULL,
                matricula TEXT NOT NULL
            )""";
    //Creamos una tabla para los pagos
    private static final String CREAR_PAGOS = """
            CREATE TABLE IF NOT EXISTS pagos_repostajes (
                id INTEGER PRIMARY KEY,
                cliente_id INTEGER NOT NULL,
                fecha TEXT NOT NULL,
                importe REAL NOT NULL,
                litros REAL NOT NULL,
                combustible TEXT NOT NULL,
                
                FOREIGN KEY (cliente_id) REFERENCES clientes(id)
            )""";

    private CrearBBDD () {}

    public static void crear () throws SQLException {
        //Probamos la conexión a la base de datos
        try (Connection conexion = ConexionBD.obtener(); Statement sentencia = conexion.createStatement()) {
            sentencia.execute(CREAR_CLIENTES);
            sentencia.execute(CREAR_PAGOS);

            System.out.println("¡Base de datos inicializada!");
        }
    }
}
