package com.example.gui;

import javax.swing.JButton;
/*CREATE TABLE club (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    nombre            VARCHAR(100) NOT NULL UNIQUE,
    socios            INT NOT NULL DEFAULT 0,
    activo            BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_fundacion   DATE NOT NULL,
    direccion         VARCHAR(150),
    email_contacto    VARCHAR(100),
    CONSTRAINT chk_club_socios CHECK (socios >= 0)
); */
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

public class ClubFrame extends JFrame {
    private JTextField txtNombre;
    private JTextField txtSocios;
    private JTextField txtActivo;
    private JTextField txtFechaFundacion;
    private JTextField txtDireccion;
    private JTextField txtEmailContacto;

    private JButton btnGuardar;
    private JButton btnVolver;

    public ClubFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Club");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // Centra pantallas
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 20, 15));

        panelFormulario.add(new JLabel("Nombre", SwingConstants.CENTER));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Socios", SwingConstants.CENTER));
        txtSocios = new JTextField();
        panelFormulario.add(txtSocios);

        panelFormulario.add(new JLabel("Activo", SwingConstants.CENTER));
        txtActivo = new JTextField();
        panelFormulario.add(txtActivo);

        panelFormulario.add(new JLabel("Fecha Fundacion", SwingConstants.CENTER));
        txtFechaFundacion = new JTextField();
        panelFormulario.add(txtFechaFundacion);

        panelFormulario.add(new JLabel("Direccion", SwingConstants.CENTER));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Email Contacto", SwingConstants.CENTER));
        txtEmailContacto = new JTextField();
        panelFormulario.add(txtEmailContacto);

        btnVolver = new JButton("Volver");
        btnGuardar = new JButton("Guardar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolver);
        panelBotones.add(btnGuardar);

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

        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }
    private void guardarClub(){
        String nombre = txtNombre.getText().trim();
        int socios = Integer.parseInt(txtSocios.getText().trim());
        boolean activo = Boolean.parseBoolean(txtActivo.getText().trim());
        String fechaFundacion = txtFechaFundacion.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String emailContacto = txtEmailContacto.getText().trim();

        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaFun = LocalDate.parse(fechaFundacion,FORMATTER);

        if (nombre.isEmpty() || txtSocios.getText().isEmpty() || txtActivo.getText().isEmpty() || fechaFundacion.isEmpty() || direccion.isEmpty() || emailContacto.isEmpty()){
            JOptionPane.showMessageDialog(this, "Complete todos los campos","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        Club club = new Club(0, nombre, socios, activo, fechaFun, direccion, emailContacto);

        ClubServicioImpl servicios = new ClubServicioImpl();
        servicios.guardarClub(club, Main.listaClub);

        JOptionPane.showMessageDialog(this, "Club guardado correctamente");
        limpiarFormulario();
    }

    private void limpiarFormulario(){
        txtNombre.setText("");
        txtSocios.setText("");
        txtActivo.setText("");
        txtFechaFundacion.setText("");
        txtDireccion.setText("");
        txtEmailContacto.setText("");
    }

}
