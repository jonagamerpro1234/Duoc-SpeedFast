package cl.duocuc.salgado.mich.ui;

import cl.duocuc.salgado.mich.service.ZonaDeCarga;
import cl.duocuc.salgado.mich.ui.panels.ListaEntregas;
import cl.duocuc.salgado.mich.ui.panels.ListaPedidos;
import cl.duocuc.salgado.mich.ui.panels.ListaRepartidores;
import cl.duocuc.salgado.mich.ui.panels.RegistroEntrega;
import cl.duocuc.salgado.mich.ui.panels.RegistroPedido;
import cl.duocuc.salgado.mich.ui.panels.RegistroRepartidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ventana principal del menú.
 */
public class Menu extends JFrame {

    private JPanel contentPanel;

    private ZonaDeCarga zonaDeCarga;

    private JButton btnRegistrarPedido;
    private JButton btnListarPedidos;
    private JButton btnRegistrarRepartidor;
    private JButton btnListarRepartidores;
    private JButton btnRegistrarEntrega;
    private JButton btnListarEntregas;
    private JButton btnSalir;

    public Menu() {

        this.zonaDeCarga = new ZonaDeCarga();

        // Configuración de ventana
        setTitle("SpeedFast - Menú Principal");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setupPanel();
        setupListeners();
    }

    // Iniciador de la ventana
    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        contentPanel = new JPanel();

        contentPanel.setLayout(
                new GridLayout(7, 1, 10, 10)
        );

        btnRegistrarPedido =
                new JButton("Registrar Pedido");

        btnListarPedidos =
                new JButton("Listar Pedidos");

        btnRegistrarRepartidor =
                new JButton("Registrar Repartidor");

        btnListarRepartidores =
                new JButton("Listar Repartidores");

        btnRegistrarEntrega =
                new JButton("Registrar Entrega");

        btnListarEntregas =
                new JButton("Listar Entregas");

        btnSalir =
                new JButton("Salir");

        contentPanel.add(btnRegistrarPedido);
        contentPanel.add(btnListarPedidos);
        contentPanel.add(btnRegistrarRepartidor);
        contentPanel.add(btnListarRepartidores);
        contentPanel.add(btnRegistrarEntrega);
        contentPanel.add(btnListarEntregas);
        contentPanel.add(btnSalir);

        setContentPane(contentPanel);
    }

    private void setupListeners() {

        // Registrar pedido
        btnRegistrarPedido.addActionListener(e -> {

            RegistroPedido registroPedido =
                    new RegistroPedido();

            registroPedido.init();

            System.out.println(
                    "[MENU] Se ha abierto el menú de registro de pedidos."
            );
        });

        // Listar pedidos
        btnListarPedidos.addActionListener(e -> {

            ListaPedidos listaPedidos =
                    new ListaPedidos();

            listaPedidos.init();

            System.out.println(
                    "[MENU] Se ha abierto la lista de pedidos."
            );
        });

        // Registrar repartidor
        btnRegistrarRepartidor.addActionListener(e -> {

            RegistroRepartidor registroRepartidor =
                    new RegistroRepartidor(zonaDeCarga);

            registroRepartidor.init();

            System.out.println(
                    "[MENU] Se ha abierto el registro de repartidores."
            );
        });

        // Listar repartidores
        btnListarRepartidores.addActionListener(e -> {

            ListaRepartidores listaRepartidores =
                    new ListaRepartidores(zonaDeCarga);

            listaRepartidores.init();

            System.out.println(
                    "[MENU] Se ha abierto la lista de repartidores."
            );
        });

        // Registrar entrega
        btnRegistrarEntrega.addActionListener(e -> {

            RegistroEntrega registroEntrega =
                    new RegistroEntrega(zonaDeCarga);

            registroEntrega.init();

            System.out.println(
                    "[MENU] Se ha abierto el registro de entregas."
            );
        });

        // Listar entregas
        btnListarEntregas.addActionListener(e -> {

            ListaEntregas listaEntregas =
                    new ListaEntregas(zonaDeCarga);

            listaEntregas.init();

            System.out.println("[MENU] Se ha abierto la lista de entregas."
            );
        });

        // Salir
        btnSalir.addActionListener(e -> {
            System.out.println("[MENU] Cerrando aplicación...");
            dispose();
            System.exit(0);
        });

        // Cerrar ventana
        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(
                            WindowEvent e) {

                        System.out.println(
                                "[MENU] Aplicación cerrada."
                        );
                    }
                }
        );
    }
}