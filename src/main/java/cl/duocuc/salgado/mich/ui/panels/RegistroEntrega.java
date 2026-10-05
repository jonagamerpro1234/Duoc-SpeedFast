package cl.duocuc.salgado.mich.ui.panels;

import cl.duocuc.salgado.mich.dao.EntregaDAO;
import cl.duocuc.salgado.mich.dao.EntregaDAOImpl;
import cl.duocuc.salgado.mich.dao.PedidoDAO;
import cl.duocuc.salgado.mich.dao.PedidoDAOImpl;
import cl.duocuc.salgado.mich.dao.RepartidorDAO;
import cl.duocuc.salgado.mich.dao.RepartidorDAOImpl;
import cl.duocuc.salgado.mich.model.Entrega;
import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.Repartidor;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RegistroEntrega extends JFrame {

    private JComboBox<Pedido> cmbPedido;
    private JComboBox<Repartidor> cmbRepartidor;
    private JButton btnGuardar;

    private final EntregaDAO entregaDAO;
    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final ZonaDeCarga zonaDeCarga;

    public RegistroEntrega(ZonaDeCarga zonaDeCarga) {

        this.zonaDeCarga = zonaDeCarga;

        entregaDAO = new EntregaDAOImpl();
        pedidoDAO = new PedidoDAOImpl();
        repartidorDAO = new RepartidorDAOImpl();

        setTitle("Registrar Entrega");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanel();
        setupListeners();

        cargarDatos();
    }

    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(new JLabel("Pedido:"));

        cmbPedido = new JComboBox<>();
        panel.add(cmbPedido);
        panel.add(new JLabel("Repartidor:"));

        cmbRepartidor = new JComboBox<>();
        panel.add(cmbRepartidor);
        panel.add(new JLabel());

        btnGuardar = new JButton("Guardar");
        panel.add(btnGuardar);

        add(panel);
    }

    private void setupListeners() {
        btnGuardar.addActionListener(e -> guardarEntrega());
    }

    private void cargarDatos() {

        List<Pedido> pedidos = pedidoDAO.listarTodos();
        for (Pedido pedido : pedidos) {
            cmbPedido.addItem(pedido);
        }

        List<Repartidor> repartidores = repartidorDAO.listarTodos(zonaDeCarga);
        for (Repartidor repartidor : repartidores) {
            cmbRepartidor.addItem(repartidor);
        }
    }

    private void guardarEntrega() {

        Pedido pedido = (Pedido) cmbPedido.getSelectedItem();
        Repartidor repartidor = (Repartidor) cmbRepartidor.getSelectedItem();

        if (pedido == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido.");
            return;
        }

        if (repartidor == null) {
            JOptionPane.showMessageDialog(this,"Debe seleccionar un repartidor.");
            return;
        }

        Entrega entrega = new Entrega(
                pedido.getId(),
                repartidor.getId(),
                LocalDate.now(),
                LocalTime.now()
        );

        entregaDAO.guardar(entrega);
        JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
        dispose();
    }
}