package com.example.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.DAO.ClubDAO;
import com.example.modelo.Club;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ModificarClubFrame extends JFrame {
    private JTextField txtId, txtNombre, txtSocios, txtActivo, txtFechaFundacion, txtDireccion, txtEmailContacto;
    private JButton btnActualizar, btnVolver;

    public ModificarClubFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Modificar Club");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 10, 10));

        panelFormulario.add(new JLabel("ID del Club:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nuevo nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Socios:"));
        txtSocios = new JTextField();
        panelFormulario.add(txtSocios);

        panelFormulario.add(new JLabel("Activo (true/false):"));
        txtActivo = new JTextField();
        panelFormulario.add(txtActivo);

        panelFormulario.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFechaFundacion = new JTextField();
        panelFormulario.add(txtFechaFundacion);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Email:"));
        txtEmailContacto = new JTextField();
        panelFormulario.add(txtEmailContacto);

        btnActualizar = new JButton("Actualizar");
        btnVolver = new JButton("Volver");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        btnActualizar.addActionListener(e -> actualizarClub());
        btnVolver.addActionListener(e -> volver());
    }

    private void actualizarClub() {
    if (txtId.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() ||
        txtSocios.getText().trim().isEmpty() || txtActivo.getText().trim().isEmpty() ||
        txtFechaFundacion.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() ||
        txtEmailContacto.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int id;
    int socios;
    boolean activo;
    LocalDate fechaFun;
    try {
        id = Integer.parseInt(txtId.getText().trim());
        socios = Integer.parseInt(txtSocios.getText().trim());
        activo = Boolean.parseBoolean(txtActivo.getText().trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaFun = LocalDate.parse(txtFechaFundacion.getText().trim(), formatter);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Revise los datos ingresados (número o fecha inválida).", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Club club = new Club(id, txtNombre.getText().trim(), socios, activo, fechaFun,
                          txtDireccion.getText().trim(), txtEmailContacto.getText().trim());

    try{
        ClubDAO clubDAO = new ClubDAO();
        boolean modificado = clubDAO.modificarClub(club);

        if (modificado) {
        JOptionPane.showMessageDialog(this, "Club modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar. Verifique el ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }  }
     catch (Exception e) {
                // Si el método lanza una excepción, la capturamos y mostramos un mensaje de error
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar borrar el cliente");
                    e.printStackTrace();
                    }   
                

   
}

    private void volver() {
        this.dispose();
        AdministradorClub admin = new AdministradorClub();
        admin.setVisible(true);
    }
}