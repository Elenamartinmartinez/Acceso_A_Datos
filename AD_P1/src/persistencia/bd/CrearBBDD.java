package persistencia.bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBBDD {
    //Creamos una tabla para los clientes
    private static final String CREAR_CLIENTES = """
            CREATE TABLE IF NOT EXISTS CLIENTES (
                ID INTEGER PRIMARY KEY,
                NOMBRE TEXT NOT NULL,
                TLFNO TEXT NOT NULL,
                MATRICULA TEXT NOT NULL
            )""";
    //Creamos una tabla para los pagos
    private static final String CREAR_PAGOS = """
            CREATE TABLE IF NOT EXISTS PAGOS_REPOSTAJES (
                ID INTEGER PRIMARY KEY,
                CLIENTE_ID INTEGER NOT NULL,
                FECHA TEXT NOT NULL,
                IMPORTE REAL NOT NULL,
                LITROS REAL NOT NULL,
                COMBUSTIBLE TEXT NOT NULL,
                FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTES(ID)
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
