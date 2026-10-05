package cl.duocuc.salgado.mich.ui.panels;

import cl.duocuc.salgado.mich.dao.PedidoDAO;
import cl.duocuc.salgado.mich.dao.PedidoDAOImpl;
import cl.duocuc.salgado.mich.model.Pedido;
import cl.duocuc.salgado.mich.model.enums.EstadoPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    private JTextField txtDireccion;
    private JComboBox<EstadoPedido> cmbEstado;

    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnRecargar;

    private final PedidoDAO pedidoDAO;

    private List<Pedido> pedidos;

    public ListaPedidos() {

        pedidoDAO = new PedidoDAOImpl();

        setTitle("SpeedFast - Lista de Pedidos");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanel();
        setupListeners();
        cargarPedidos();
    }

    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /*
         * Tabla
         */
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Dirección", "Tipo", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPedidos = new JTable(modeloTabla);
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        /*
         * Panel de edición
         */
        JPanel panelEdicion = new JPanel(new GridLayout(2, 2, 10, 10));
        panelEdicion.setBorder(BorderFactory.createTitledBorder("Editar pedido seleccionado"));
        panelEdicion.add(new JLabel("Dirección:"));

        txtDireccion = new JTextField();
        panelEdicion.add(txtDireccion);
        panelEdicion.add(new JLabel("Estado:"));

        cmbEstado = new JComboBox<>(EstadoPedido.values());
        panelEdicion.add(cmbEstado);
        panelPrincipal.add(panelEdicion, BorderLayout.NORTH);

        /*
         * Botones
         */
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnRecargar = new JButton("Recargar");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRecargar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        setContentPane(panelPrincipal);
    }

    private void setupListeners() {

        /*
         * Seleccionar pedido de la tabla
         */
        tablaPedidos.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        cargarDatosSeleccionados();
                    }
                });

        /*
         * Actualizar
         */
        btnActualizar.addActionListener(
                e -> actualizarPedido()
        );

        /*
         * Eliminar
         */
        btnEliminar.addActionListener(
                e -> eliminarPedido()
        );

        /*
         * Recargar
         */
        btnRecargar.addActionListener(
                e -> cargarPedidos()
        );
    }

    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        pedidos = pedidoDAO.listarTodos();

        for (Pedido pedido : pedidos) {

            modeloTabla.addRow(
                    new Object[]{
                            pedido.getId(),
                            pedido.getDireccionEntrega(),
                            pedido.getTipoPedido().getNombre(),
                            pedido.getEstado()
                    }
            );
        }

        limpiarEdicion();
    }

    private void cargarDatosSeleccionados() {

        int fila =
                tablaPedidos.getSelectedRow();

        if (fila < 0 || fila >= pedidos.size()) {
            return;
        }

        Pedido pedido = pedidos.get(fila);

        txtDireccion.setText(
                pedido.getDireccionEntrega()
        );

        cmbEstado.setSelectedItem(
                pedido.getEstado()
        );
    }

    private void actualizarPedido() {

        int fila =
                tablaPedidos.getSelectedRow();

        if (fila < 0 || fila >= pedidos.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido."
            );

            return;
        }

        String direccion =
                txtDireccion.getText().trim();

        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "La dirección no puede estar vacía."
            );

            txtDireccion.requestFocus();

            return;
        }

        Pedido pedido = pedidos.get(fila);

        EstadoPedido estado =
                (EstadoPedido) cmbEstado.getSelectedItem();

        pedido.setEstado(estado);

        /*
         * La dirección de Pedido es final,
         * por lo que actualmente no podemos
         * modificarla desde el objeto.
         *
         * Por esta razón, solo actualizamos
         * el estado mediante el DAO.
         */
        pedidoDAO.actualizar(pedido);

        JOptionPane.showMessageDialog(
                this,
                "Pedido actualizado correctamente."
        );

        cargarPedidos();
    }

    private void eliminarPedido() {

        int fila =
                tablaPedidos.getSelectedRow();

        if (fila < 0 || fila >= pedidos.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido."
            );

            return;
        }

        Pedido pedido = pedidos.get(fila);

        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar el pedido #"
                                + pedido.getId()
                                + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        pedidoDAO.eliminar(
                pedido.getId()
        );

        JOptionPane.showMessageDialog(
                this,
                "Pedido eliminado correctamente."
        );

        cargarPedidos();
    }

    private void limpiarEdicion() {

        txtDireccion.setText("");

        if (cmbEstado.getItemCount() > 0) {
            cmbEstado.setSelectedIndex(0);
        }

        tablaPedidos.clearSelection();
    }
}