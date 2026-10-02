package cl.duocuc.salgado.mich.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestiona la conexión con la base de datos SpeedFast.
 */
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "speedfast_db";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    /**
     * Verifica que la base de datos SpeedFast exista.
     * Si no existe, la crea.
     */
    public static void verificarBaseDatos() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = conexion.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("[MySQL] Base de datos '" + DATABASE + "' verificada correctamente.");
        } catch (SQLException e) {
            System.out.println("[MySQL] Error al verificar la base de datos.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Establece una conexión con la base de datos SpeedFast.
     *
     * @return conexión activa con MySQL
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection conectar() throws SQLException {
        String urlDatabase = URL + DATABASE;
        return DriverManager.getConnection(urlDatabase, USER, PASSWORD);
    }

    /**
     * Cierra una conexión activa con MySQL.
     * @param conexion conexión que será cerrada
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