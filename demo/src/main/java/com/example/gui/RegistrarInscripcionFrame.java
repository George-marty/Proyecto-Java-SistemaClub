package com.example.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

import com.example.Main;

import com.example.modelo.Actividad;
import com.example.modelo.Club;
import com.example.modelo.Inscripcion;
import com.example.servicio.impl.InscripcionServicioImpl;

public class RegistrarInscripcionFrame extends JFrame {
    private JComboBox<Club> cmbIdClub;
    private JComboBox<Actividad> cmbIdActividad;
    private JTextField txtPrecio;
    private JButton btnGuardar;
    private JButton btnVolver;

    public RegistrarInscripcionFrame() {
        configurarVentana();
        inicializarComponentes();
        cargarIdClub();
        cargarIdActividad();
    }

    private void configurarVentana() {
        setTitle("Registrar Inscripcion");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // Centra pantallas
    }
    
    private void inicializarComponentes() {
        formularioInscripcion();
    }
    private void formularioInscripcion(){
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 20, 15));

        panelFormulario.add(new JLabel("ID Club", SwingConstants.CENTER));
        cmbIdClub = new JComboBox<>();
        panelFormulario.add(cmbIdClub);

        panelFormulario.add(new JLabel("ID Actividad", SwingConstants.CENTER));
        cmbIdActividad = new JComboBox<>();
        panelFormulario.add(cmbIdActividad);

        panelFormulario.add(new JLabel("Precio", SwingConstants.CENTER));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        btnGuardar = new JButton("Guardar");
        btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnGuardar.addActionListener(e -> guardar());
        btnVolver.addActionListener(e -> volver());
    }
    //Se carga al comboBox la lista del club para obtener su idClub
    private void cargarIdClub() {
        cmbIdClub.removeAllItems();
        for (Club club : Main.listaClub) {
            cmbIdClub.addItem(club);

        }
    }
    //Se carga al comboBox la lista de la actividad para obtener su idActividad
    private void cargarIdActividad() {
        cmbIdActividad.removeAllItems();
        for (Actividad actividad : Main.listaActividad) {
            cmbIdActividad.addItem(actividad);
        }
    }

    
    private void guardar() {
        if (txtPrecio.getText().trim().isEmpty() || cmbIdClub.getSelectedItem() == null
                || cmbIdActividad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos y seleccione las opciones", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int precio;
        try {
            precio = Integer.parseInt(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Club clubSeleccionado = (Club) cmbIdClub.getSelectedItem();
        Actividad actividadSeleccionada = (Actividad) cmbIdActividad.getSelectedItem();
        Inscripcion inscripcion = new Inscripcion(0, clubSeleccionado, actividadSeleccionada, precio);
        InscripcionServicioImpl inscripcionServicioImpl = new InscripcionServicioImpl();
        inscripcionServicioImpl.guardarInscripcion(inscripcion, Main.listaInscripcion);
        JOptionPane.showMessageDialog(this, "Inscripción guardada correctamente");
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtPrecio.setText("");
        if (cmbIdClub.getItemCount() > 0)
            cmbIdClub.setSelectedIndex(0);
        if (cmbIdActividad.getItemCount() > 0)
            cmbIdActividad.setSelectedIndex(0);
    }

    private void volver() {
        this.dispose();
        AdministradorInscripcionFrame administradorInscripcionFrame = new AdministradorInscripcionFrame();
        administradorInscripcionFrame.setVisible(true);
    }
}