package cl.duocuc.salgado.mich.app;

import cl.duocuc.salgado.mich.dao.ConexionDB;
import cl.duocuc.salgado.mich.ui.Menu;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        // Comprobar conexión con la base de datos
        try (Connection connection = ConexionDB.conectar()) {
            System.out.println("[MySQL] Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("[MySQL] Error al conectar.");
            System.out.println(e.getMessage());
        }

        // Iniciar interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.init();
        });
    }
}