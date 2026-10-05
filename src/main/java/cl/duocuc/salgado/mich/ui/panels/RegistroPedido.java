package cl.duocuc.salgado.mich.ui.panels;

import cl.duocuc.salgado.mich.dao.PedidoDAOImpl;
import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.PedidoComida;
import cl.duocuc.salgado.mich.model.PedidoEncomienda;
import cl.duocuc.salgado.mich.model.PedidoExpress;
import cl.duocuc.salgado.mich.model.enums.EstadoPedido;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegistroPedido extends JFrame {

    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JComboBox<EstadoPedido> cmbEstado;
    private JButton btnGuardar;

    public RegistroPedido() {
        setTitle("Registrar Pedido");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanel();
        setupListeners();
    }

    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ID
        panel.add(new JLabel("ID:"));

        txtId = new JTextField();

        panel.add(txtId);

        // Dirección
        panel.add(new JLabel("Dirección:"));

        txtDireccion = new JTextField();

        panel.add(txtDireccion);

        // Tipo
        panel.add(new JLabel("Tipo:"));

        cmbTipo = new JComboBox<>();

        cmbTipo.addItem("Comida");
        cmbTipo.addItem("Encomienda");
        cmbTipo.addItem("Express");

        panel.add(cmbTipo);

        // Estado
        panel.add(new JLabel("Estado:"));

        cmbEstado = new JComboBox<>(
                new EstadoPedido[]{
                        EstadoPedido.PENDIENTE,
                        EstadoPedido.EN_REPARTO,
                        EstadoPedido.ENTREGADO
                }
        );

        panel.add(cmbEstado);

        // Botón
        panel.add(new JLabel());

        btnGuardar = new JButton("Guardar");

        panel.add(btnGuardar);

        add(panel);
    }

    private void setupListeners() {

        btnGuardar.addActionListener(
                e -> guardarPedido()
        );
    }

    private void guardarPedido() {

        try {

            String textoId = txtId.getText().trim();
            String direccion = txtDireccion.getText().trim();

            // Validar ID
            if (textoId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID.");
                txtId.requestFocus();
                return;
            }

            int id = Integer.parseInt(textoId);

            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "El ID debe ser mayor que cero.");
                txtId.requestFocus();
                return;
            }

            // Validar dirección
            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una dirección.");
                txtDireccion.requestFocus();
                return;
            }

            String tipo = Objects.requireNonNull(cmbTipo.getSelectedItem()).toString();
            Pedido pedido;

            switch (tipo) {

                case "Comida":
                    pedido = new PedidoComida(id, direccion, 0);
                    break;

                case "Encomienda":
                    pedido = new PedidoEncomienda(
                                    id,
                                    direccion,
                                    0
                            );
                    break;

                case "Express":
                    pedido = new PedidoExpress(
                                    id,
                                    direccion,
                                    0
                            );
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Tipo de pedido no válido.");
                    return;
            }

            // Aplicar estado seleccionado
            EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();
            pedido.setEstado(estado);

            // Guardar en BD
            PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
            pedidoDAO.guardar(pedido);
            JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.");

            // Limpiar formulario
            txtId.setText("");
            txtDireccion.setText("");

            cmbTipo.setSelectedIndex(0);
            cmbEstado.setSelectedIndex(0);

            txtId.requestFocus();

        } catch (NumberFormatException e) {w
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
            txtId.requestFocus();
        }
    }
}