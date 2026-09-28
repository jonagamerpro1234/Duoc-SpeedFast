package cl.duocuc.salgado.mich.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos SpeedFast.
 */
public class ConexionDB {

    private static final String URL ="jdbc:mysql://localhost:3306/speedfast_db";
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

    /**
     * Desconecta la base de datos de manera segura.
     *
     * @param conexion conexión que será cerrada
     * @throws SQLException si ocurre un error al cerrar la conexión
     */
    public static void desconectar(Connection conexion) throws SQLException {

        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

}