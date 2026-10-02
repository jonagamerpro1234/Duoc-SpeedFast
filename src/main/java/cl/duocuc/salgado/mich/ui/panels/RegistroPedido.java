package cl.duocuc.salgado.mich.ui.panels;

import cl.duocuc.salgado.mich.dao.PedidoDAO;
import cl.duocuc.salgado.mich.dao.PedidoDAOImpl;
import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.PedidoComida;
import cl.duocuc.salgado.mich.model.PedidoEncomienda;
import cl.duocuc.salgado.mich.model.PedidoExpress;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegistroPedido extends JFrame {

    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JButton btnGuardar;

    public RegistroPedido() {

        setTitle("Registrar Pedido");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        setupPanel();
        setupListeners();
    }

    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        panel.add(new JLabel("Tipo:"));

        cmbTipo = new JComboBox<>();

        cmbTipo.addItem("Comida");
        cmbTipo.addItem("Encomienda");
        cmbTipo.addItem("Express");

        panel.add(cmbTipo);

        btnGuardar = new JButton("Guardar");
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void setupListeners() {

        btnGuardar.addActionListener(e -> guardarPedido());
    }

    private void guardarPedido() {

        try {

            int id = Integer.parseInt(txtId.getText());
            String direccion = txtDireccion.getText();

            if (direccion.isBlank()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una dirección.");
                return;
            }

            String tipo = Objects.requireNonNull(cmbTipo.getSelectedItem()).toString();
            Pedido pedido;

            switch (tipo) {

                case "Comida":
                    pedido = new PedidoComida(id, direccion, 0);
                    break;

                case "Encomienda":
                    pedido = new PedidoEncomienda(id, direccion, 0);
                    break;

                case "Express":
                    pedido = new PedidoExpress(id, direccion, 0);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo de pedido no válido.");
                    return;
            }

            PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
            pedidoDAO.guardar(pedido);

            JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.");

            txtId.setText("");
            txtDireccion.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.");
        }
    }
}