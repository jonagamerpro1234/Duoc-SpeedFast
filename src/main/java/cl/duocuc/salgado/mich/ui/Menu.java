package cl.duocuc.salgado.mich.ui;

import cl.duocuc.salgado.mich.app.Main;
import cl.duocuc.salgado.mich.dao.ConexionDB;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Ventana principal del menu
 */
public class Menu extends JFrame {

    private JPanel contentPane;

    public Menu() {


        /*
         * Configuración de ventana
         */
        setTitle("Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        setupPanel();
        setupListeners();

    }

    //Iniciador de la ventana
    public void init() {
        setVisible(true);
    }

    private void setupPanel(){

    }

    private void setupListeners() {

        //Desconectar conexion de DB al momento de cerrar la ventana
        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ConexionDB.desconectar(Main.getConexion());
                    }
                }
        );
    }


}
