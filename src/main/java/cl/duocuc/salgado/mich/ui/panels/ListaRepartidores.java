package cl.duocuc.salgado.mich.ui.panels;

import cl.duocuc.salgado.mich.dao.RepartidorDAO;
import cl.duocuc.salgado.mich.dao.RepartidorDAOImpl;
import cl.duocuc.salgado.mich.model.Repartidor;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaRepartidores extends JFrame {

    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;

    private JTextField txtNombre;

    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnRecargar;

    private final RepartidorDAO repartidorDAO;
    private final ZonaDeCarga zonaDeCarga;

    private List<Repartidor> repartidores;

    public ListaRepartidores(ZonaDeCarga zonaDeCarga) {

        this.zonaDeCarga = zonaDeCarga;
        this.repartidorDAO = new RepartidorDAOImpl();

        setTitle("SpeedFast - Lista de Repartidores");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setupPanel();
        setupListeners();

        cargarRepartidores();
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
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaRepartidores = new JTable(modeloTabla);
        tablaRepartidores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaRepartidores);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        /*
         * Edición
         */
        JPanel panelEdicion = new JPanel(new GridLayout(1, 2, 10, 10));
        panelEdicion.setBorder(BorderFactory.createTitledBorder("Editar repartidor"));
        panelEdicion.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();

        panelEdicion.add(txtNombre);
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

        tablaRepartidores.getSelectionModel().addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        cargarDatosSeleccionados();
                    }
                });

        btnActualizar.addActionListener(e -> actualizarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());
        btnRecargar.addActionListener(e -> cargarRepartidores());
    }

    private void cargarRepartidores() {

        modeloTabla.setRowCount(0);
        repartidores = repartidorDAO.listarTodos(zonaDeCarga);

        for (Repartidor repartidor : repartidores) {
            modeloTabla.addRow(new Object[]{repartidor.getId(), repartidor.getNombre()});
        }

        limpiarEdicion();
    }

    private void cargarDatosSeleccionados() {

        int fila = tablaRepartidores.getSelectedRow();
        if (fila < 0 || fila >= repartidores.size()) {
            return;
        }

        Repartidor repartidor = repartidores.get(fila);
        txtNombre.setText(repartidor.getNombre());
    }

    private void actualizarRepartidor() {

        int fila = tablaRepartidores.getSelectedRow();
        if (fila < 0 || fila >= repartidores.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un repartidor.");
            return;
        }

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre.");
            txtNombre.requestFocus();
            return;
        }

        if (nombre.length() < 3) {
            JOptionPane.showMessageDialog(this, "El nombre debe tener al menos 3 caracteres.");
            txtNombre.requestFocus();
            return;
        }

        Repartidor repartidor = repartidores.get(fila);
        repartidor.setNombre(nombre);
        repartidorDAO.actualizar(repartidor);

        JOptionPane.showMessageDialog(this, "Repartidor actualizado correctamente.");
        cargarRepartidores();
    }

    private void eliminarRepartidor() {

        int fila = tablaRepartidores.getSelectedRow();

        if (fila < 0 || fila >= repartidores.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un repartidor.");
            return;
        }

        Repartidor repartidor = repartidores.get(fila);
        int respuesta = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar al repartidor \""
                                + repartidor.getNombre()
                                + "\"?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        repartidorDAO.eliminar(repartidor.getId());

        JOptionPane.showMessageDialog(this, "Repartidor eliminado correctamente.");
        cargarRepartidores();
    }

    private void limpiarEdicion() {
        txtNombre.setText("");
        tablaRepartidores.clearSelection();
    }
}