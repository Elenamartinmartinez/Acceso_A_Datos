package persistencia.bd;

import java.sql.*;
import java.time.LocalDate;

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
            //En caso de que la base de datos de clientes ya tenga los datos iniciales de los clientes y pagos ya creados
            if (datos(conexion)) {
                System.out.println("Ya existen los datos iniciales en las bases de datos");
            }

            conexion.setAutoCommit(false);
            try {
                System.out.println("Cargando datos iniciales....");

                insertarClientes(conexion);
                insertarPagos(conexion);
                conexion.commit();

                System.out.println("!Datos iniciales cargados¡");
                System.out.println("Clientes: "+MAX_Clientes);
                System.out.println("Pagos: "+MAX_Pagos);
            } catch (SQLException e) {
                System.out.println("Ha ocurrido algún error al cargar los datos iniciales..."+e.getMessage());
                conexion.rollback(); //La base de datos vuelve a antes del commit realizado
            }
        }
    }
//Solo comprobamos si hay datos en clientes, no puede haber datos en pagos_repostaje si no hay clientes
    private static boolean datos (Connection conexion) throws SQLException{
        try (Statement sentecia = conexion.createStatement();
             ResultSet rs = sentecia.executeQuery("SELECT COUNT(*) FROM clientes")) {
            return rs.next() && rs.getInt(1) > 0;
        }
    }
    //Insertar los datos en la tabla de clientes
    private static void insertarClientes(Connection conexion) throws SQLException{
        //Para añadir la información
        String sql = "INSERT INTO clientes (id, nombre, tlfno, matricula) VALUES (?,?,?,?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)){
            for (int i = 1; i <= MAX_Clientes; i++) { //Añade clientes hasta llegar al límite
                sentencia.setInt(1, i);
                sentencia.setString(2, String.format("Cliente %04d", i));
                sentencia.setString(3, String.format("6%08d", i));
                sentencia.setString(4, String.format("%04dKLM", i));
                sentencia.addBatch();
            }
            sentencia.executeBatch();
        }
    }
    //Insertar los datos en la tabla de pagos de repostaje
    private static void insertarPagos(Connection conexion) throws SQLException{
        //Para añadir la información
        String sql = "INSERT INTO pagos_repostajes (id, cliente_id, fecha, importe, litros, combustible) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            for (int i = 1; i <= MAX_Pagos; i++) {//Añade pagos hasta llegar al límite
                double litros = 10 + (i % 50);
                double importe = Math.round(litros * 1.55 * 100)/100.0;
                int clienteid =((i * 37) % MAX_Clientes) + 1;
                LocalDate fecha = LocalDate.of(2025, 1, 1).plusDays(i % 365);

                sentencia.setInt(1, i);
                sentencia.setInt(2, clienteid);
                sentencia.setString(3, fecha.toString());
                sentencia.setDouble(4, importe);
                sentencia.setDouble(5, litros);
                sentencia.setString(6, Combustibles[i % Combustibles.length]);

                sentencia.addBatch();
            }
            sentencia.executeBatch();
        }
    }
}
