package cl.duocuc.salgado.mich.ui;

import cl.duocuc.salgado.mich.app.Main;
import cl.duocuc.salgado.mich.dao.ConexionDB;
import cl.duocuc.salgado.mich.ui.panels.RegistroPedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ventana principal del menu
 */
public class Menu extends JFrame {

    private JPanel contentPanel;
    private JButton btnRegistrarPedido;
    private JButton btnListarPedidos;
    private JButton btnRegistrarRepartidor;
    private JButton btnListarRepartidores;
    private JButton btnSalir;

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

    // Iniciador de la ventana
    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 1, 10, 10));

        btnRegistrarPedido = new JButton("Registrar Pedido");
        btnListarPedidos = new JButton("Listar Pedidos");
        btnRegistrarRepartidor = new JButton("Registrar Repartidor");
        btnListarRepartidores = new JButton("Listar Repartidores");
        btnSalir = new JButton("Salir");

        contentPanel.add(btnRegistrarPedido);
        contentPanel.add(btnListarPedidos);
        contentPanel.add(btnRegistrarRepartidor);
        contentPanel.add(btnListarRepartidores);
        contentPanel.add(btnSalir);

        setContentPane(contentPanel);
    }

    private void setupListeners() {

        btnRegistrarPedido.addActionListener(e -> {
            RegistroPedido  registroPedido = new RegistroPedido();
            registroPedido.init();
            System.out.println("[MENU] Se a abierto el meno de registro de pedidos");
        });

        btnListarPedidos.addActionListener(e -> {
            System.out.println("Listar Pedidos");
        });

        btnRegistrarRepartidor.addActionListener(e -> {

        });

        btnListarRepartidores.addActionListener(e -> {
            System.out.println("Listar Repartidores");
        });

        btnSalir.addActionListener(e -> {
            ConexionDB.desconectar(Main.getConexion());
            //Interrumpir Hilos
            Thread.currentThread().interrupt();
            System.out.println("[WARNING] Se han interrumpido todo los hilos");
            System.exit(0);
        });

        // Desconectar conexión de DB al momento de cerrar la ventana
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