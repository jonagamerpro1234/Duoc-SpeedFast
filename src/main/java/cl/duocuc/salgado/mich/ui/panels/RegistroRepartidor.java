package cl.duocuc.salgado.mich.ui.panels;

import cl.duocuc.salgado.mich.dao.RepartidorDAO;
import cl.duocuc.salgado.mich.dao.RepartidorDAOImpl;
import cl.duocuc.salgado.mich.model.Repartidor;
import cl.duocuc.salgado.mich.service.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class RegistroRepartidor extends JFrame {

    private JTextField txtNombre;
    private JButton btnGuardar;

    private final RepartidorDAO repartidorDAO;
    private final ZonaDeCarga zonaDeCarga;

    public RegistroRepartidor(ZonaDeCarga zonaDeCarga) {

        this.zonaDeCarga = zonaDeCarga;
        this.repartidorDAO = new RepartidorDAOImpl();

        setTitle("Registrar Repartidor");
        setSize(400, 200);
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
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);
        panel.add(new JLabel());
        btnGuardar = new JButton("Guardar");
        panel.add(btnGuardar);
        add(panel);
    }

    private void setupListeners() {
        btnGuardar.addActionListener(e -> guardarRepartidor());
    }

    private void guardarRepartidor() {

        String nombre =
                txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del repartidor.");
            txtNombre.requestFocus();
            return;
        }

        if (nombre.length() < 3) {
            JOptionPane.showMessageDialog(this, "El nombre debe tener al menos 3 caracteres.");
            txtNombre.requestFocus();
            return;
        }

        Repartidor repartidor = new Repartidor(nombre, zonaDeCarga);
        repartidorDAO.guardar(repartidor);

        JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente.");
        txtNombre.setText("");
        txtNombre.requestFocus();
    }
}