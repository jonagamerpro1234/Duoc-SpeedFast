package cl.duocuc.salgado.mich.ui;

import javax.swing.*;

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
        setResizable(false);


    }

    //Iniciador de la ventana
    public void init() {
        setVisible(true);
    }

    private void setupPanel(){

    }

}
