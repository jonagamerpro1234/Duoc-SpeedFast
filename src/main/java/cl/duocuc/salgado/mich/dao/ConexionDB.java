package cl.duocuc.salgado.mich.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos SpeedFast.
 */
public class ConexionDB {

    private static final String URL ="jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "speedfast_db";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    /**
     * Establece una conexión con la base de datos.
     *
     * @return conexión activa con MySQL
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //Metodo encargado de crear la base de datos
    private void crearBaseDatos() throws SQLException {

    }

    /**
     * Verifica si existe la base de datos
     * @return retorna {@code true en casodo de existir | false el caso de no existir y procede a crear la Base Datos sin tablas}
     */
    public boolean verificarBaseDato(){
        return false;
    }

    /**
     *
     * @param conexion
     */
    public static void desconectar(Connection conexion) {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                    System.out.println("[MySQL] Conexion cerrada correctamente.");
                }
            } catch (SQLException e) {
                System.out.println("[MySQL] Error al cerrar la conexion.");
                System.out.println(e.getMessage());
            }
        }
    }

}