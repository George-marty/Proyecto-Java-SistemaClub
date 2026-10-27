package com.example.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.example.Main;
import com.example.modelo.Club;
import com.example.servicio.impl.ClubServicioImpl;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegistrarseClubFrame extends JFrame {
    private JTextField txtNombre;
    private JTextField txtSocios;
    private JComboBox<String> cmbActivo;
    private JTextField txtFechaFundacion;
    private JButton btnGuardar;
    private JButton btnVolver;

    public RegistrarseClubFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Club");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // Centra pantallas
    }

    private void inicializarComponentes() {
        formularioClub();
    }

    private void formularioClub() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 20, 15));

        panelFormulario.add(new JLabel("Nombre", SwingConstants.CENTER));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Socios", SwingConstants.CENTER));
        txtSocios = new JTextField();
        panelFormulario.add(txtSocios);

        panelFormulario.add(new JLabel("Activo", SwingConstants.CENTER));
        cmbActivo = new JComboBox<>(new String[] { "Activo", "No Activo" });
        panelFormulario.add(cmbActivo);

        panelFormulario.add(new JLabel("Fecha Fundacion", SwingConstants.CENTER));
        txtFechaFundacion = new JTextField();
        panelFormulario.add(txtFechaFundacion);

        btnVolver = new JButton("Volver");
        btnGuardar = new JButton("Guardar");
        panelFormulario.add(new JLabel("Cruz Martin", SwingConstants.HORIZONTAL));

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnGuardar.addActionListener(e -> {
            guardarClub();
        });
        btnVolver.addActionListener(e -> {
            volver();
        });
    }

    private void volver() {
        this.dispose();
        AdministradorClubFrame administradorClub = new AdministradorClubFrame();
        administradorClub.setVisible(true);
    }

    private void guardarClub() {
        if (txtNombre.getText().trim().isEmpty() || txtSocios.getText().trim().isEmpty()
                || txtFechaFundacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String nombre = txtNombre.getText().trim();
            String sociosTexto = txtSocios.getText().trim();
            String fechaFundacion = txtFechaFundacion.getText().trim();
            String seleccion = (String) cmbActivo.getSelectedItem();

            ClubServicioImpl servicios = new ClubServicioImpl();
            servicios.validarClub(nombre, sociosTexto); // 1. validar texto crudo

            int socios = Integer.parseInt(sociosTexto); // 2. recién ahora convertir
            boolean activo = seleccion.equals("Activo");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaFun = LocalDate.parse(fechaFundacion, formatter);

            Club club = new Club(0, nombre, socios, activo, fechaFun);
            servicios.guardarClub(club, Main.listaClub);

            JOptionPane.showMessageDialog(this, "Club guardado correctamente");
            limpiarFormulario();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "La fecha ingresada no es válida (Formato requerido: dd/MM/yyyy)",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtSocios.setText("");
        cmbActivo.setSelectedIndex(0);
        txtFechaFundacion.setText("");
    }

}
