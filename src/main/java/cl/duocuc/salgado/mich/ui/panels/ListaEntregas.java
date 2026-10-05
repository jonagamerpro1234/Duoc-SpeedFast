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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ListaEntregas extends JFrame {

    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;

    private JComboBox<Pedido> cmbPedido;
    private JComboBox<Repartidor> cmbRepartidor;
    private JTextField txtFecha;
    private JTextField txtHora;

    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnRecargar;

    private final EntregaDAO entregaDAO;
    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final ZonaDeCarga zonaDeCarga;

    private List<Entrega> entregas;

    public ListaEntregas(ZonaDeCarga zonaDeCarga) {

        this.zonaDeCarga = zonaDeCarga;

        entregaDAO = new EntregaDAOImpl();
        pedidoDAO = new PedidoDAOImpl();
        repartidorDAO = new RepartidorDAOImpl();

        setTitle("SpeedFast - Lista de Entregas");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanel();
        setupListeners();

        cargarEntregas();
    }

    public void init() {
        setVisible(true);
    }

    private void setupPanel() {

        JPanel panelPrincipal = new JPanel(
                new BorderLayout(10, 10)
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        /*
         * Tabla
         */
        modeloTabla = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Pedido",
                        "Repartidor",
                        "Fecha",
                        "Hora"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        tablaEntregas = new JTable(modeloTabla);

        tablaEntregas.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(tablaEntregas);

        panelPrincipal.add(
                scrollPane,
                BorderLayout.CENTER
        );

        /*
         * Panel de edición
         */
        JPanel panelEdicion = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        panelEdicion.setBorder(
                BorderFactory.createTitledBorder(
                        "Editar entrega"
                )
        );

        panelEdicion.add(
                new JLabel("Pedido:")
        );

        cmbPedido = new JComboBox<>();
        panelEdicion.add(cmbPedido);

        panelEdicion.add(
                new JLabel("Repartidor:")
        );

        cmbRepartidor = new JComboBox<>();
        panelEdicion.add(cmbRepartidor);

        panelEdicion.add(
                new JLabel("Fecha (AAAA-MM-DD):")
        );

        txtFecha = new JTextField();
        panelEdicion.add(txtFecha);

        panelEdicion.add(
                new JLabel("Hora (HH:MM:SS):")
        );

        txtHora = new JTextField();
        panelEdicion.add(txtHora);

        panelPrincipal.add(
                panelEdicion,
                BorderLayout.NORTH
        );

        /*
         * Botones
         */
        JPanel panelBotones =
                new JPanel(new FlowLayout());

        btnActualizar =
                new JButton("Actualizar");

        btnEliminar =
                new JButton("Eliminar");

        btnRecargar =
                new JButton("Recargar");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRecargar);

        panelPrincipal.add(
                panelBotones,
                BorderLayout.SOUTH
        );

        setContentPane(panelPrincipal);

        cargarCombos();
    }

    private void setupListeners() {

        tablaEntregas
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        cargarDatosSeleccionados();
                    }
                });

        btnActualizar.addActionListener(
                e -> actualizarEntrega()
        );

        btnEliminar.addActionListener(
                e -> eliminarEntrega()
        );

        btnRecargar.addActionListener(
                e -> {

                    cargarCombos();
                    cargarEntregas();
                }
        );
    }

    private void cargarCombos() {

        cmbPedido.removeAllItems();
        cmbRepartidor.removeAllItems();

        List<Pedido> pedidos =
                pedidoDAO.listarTodos();

        for (Pedido pedido : pedidos) {
            cmbPedido.addItem(pedido);
        }

        List<Repartidor> repartidores =
                repartidorDAO.listarTodos(
                        zonaDeCarga
                );

        for (Repartidor repartidor : repartidores) {
            cmbRepartidor.addItem(repartidor);
        }
    }

    private void cargarEntregas() {

        modeloTabla.setRowCount(0);

        entregas =
                entregaDAO.listarTodos();

        for (Entrega entrega : entregas) {

            modeloTabla.addRow(
                    new Object[]{
                            entrega.getId(),
                            entrega.getIdPedido(),
                            entrega.getIdRepartidor(),
                            entrega.getFecha(),
                            entrega.getHora()
                    }
            );
        }

        limpiarEdicion();
    }

    private void cargarDatosSeleccionados() {

        int fila =
                tablaEntregas.getSelectedRow();

        if (fila < 0 ||
                fila >= entregas.size()) {

            return;
        }

        Entrega entrega =
                entregas.get(fila);

        seleccionarPedido(
                entrega.getIdPedido()
        );

        seleccionarRepartidor(
                entrega.getIdRepartidor()
        );

        txtFecha.setText(
                entrega.getFecha().toString()
        );

        txtHora.setText(
                entrega.getHora().toString()
        );
    }

    private void seleccionarPedido(int idPedido) {

        for (int i = 0;
             i < cmbPedido.getItemCount();
             i++) {

            Pedido pedido =
                    cmbPedido.getItemAt(i);

            if (pedido.getId() == idPedido) {

                cmbPedido.setSelectedIndex(i);

                return;
            }
        }
    }

    private void seleccionarRepartidor(
            int idRepartidor) {

        for (int i = 0;
             i < cmbRepartidor.getItemCount();
             i++) {

            Repartidor repartidor =
                    cmbRepartidor.getItemAt(i);

            if (repartidor.getId() == idRepartidor) {

                cmbRepartidor.setSelectedIndex(i);

                return;
            }
        }
    }

    private void actualizarEntrega() {

        int fila =
                tablaEntregas.getSelectedRow();

        if (fila < 0 ||
                fila >= entregas.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una entrega."
            );

            return;
        }

        Pedido pedido =
                (Pedido) cmbPedido.getSelectedItem();

        Repartidor repartidor =
                (Repartidor)
                        cmbRepartidor.getSelectedItem();

        String fechaTexto =
                txtFecha.getText().trim();

        String horaTexto =
                txtHora.getText().trim();

        if (pedido == null ||
                repartidor == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido y un repartidor."
            );

            return;
        }

        if (fechaTexto.isEmpty() ||
                horaTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar fecha y hora."
            );

            return;
        }

        try {

            LocalDate fecha =
                    LocalDate.parse(fechaTexto);

            LocalTime hora =
                    LocalTime.parse(horaTexto);

            Entrega entrega =
                    entregas.get(fila);

            entrega.setIdPedido(
                    pedido.getId()
            );

            entrega.setIdRepartidor(
                    repartidor.getId()
            );

            entrega.setFecha(fecha);
            entrega.setHora(hora);

            entregaDAO.actualizar(
                    entrega
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega actualizada correctamente."
            );

            cargarEntregas();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fecha u hora no válidas.\n"
                            + "Use AAAA-MM-DD y HH:MM:SS."
            );
        }
    }

    private void eliminarEntrega() {

        int fila =
                tablaEntregas.getSelectedRow();

        if (fila < 0 ||
                fila >= entregas.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una entrega."
            );

            return;
        }

        Entrega entrega =
                entregas.get(fila);

        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar la entrega #"
                                + entrega.getId()
                                + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        entregaDAO.eliminar(
                entrega.getId()
        );

        JOptionPane.showMessageDialog(
                this,
                "Entrega eliminada correctamente."
        );

        cargarEntregas();
    }

    private void limpiarEdicion() {

        txtFecha.setText("");
        txtHora.setText("");

        cmbPedido.setSelectedIndex(-1);
        cmbRepartidor.setSelectedIndex(-1);

        tablaEntregas.clearSelection();
    }
}