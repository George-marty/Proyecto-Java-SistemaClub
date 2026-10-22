package com.example.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.Main;
import com.example.DAO.InscripcionDAO;
import com.example.modelo.Actividad;
import com.example.modelo.Club;
import com.example.modelo.Inscripcion;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ModificarInscripcionFrame extends JFrame {
    private JComboBox<Inscripcion> cmbIdInscripcion;
    private JComboBox<Club> cmbClub;
    private JComboBox<Actividad> cmbActividad;
    private JTextField txtPrecio;
    private JButton btnActualizar;
    private JButton btnVolver;

    public ModificarInscripcionFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Modificar Inscripción");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        formularioInscripcionModificacion();
        cargarInscripciones();
        cargarClubes();
        cargarActividades();
    }

    private void formularioInscripcionModificacion() {
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        panelFormulario.add(new JLabel("Seleccionar Inscripción:"));
        cmbIdInscripcion = new JComboBox<>();

        panelFormulario.add(cmbIdInscripcion);

        panelFormulario.add(new JLabel("Nuevo Club:"));
        cmbClub = new JComboBox<>();

        panelFormulario.add(cmbClub);

        panelFormulario.add(new JLabel("Nueva Actividad:"));
        cmbActividad = new JComboBox<>();

        panelFormulario.add(cmbActividad);

        panelFormulario.add(new JLabel("Nuevo Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        btnActualizar = new JButton("Actualizar");
        btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        cmbIdInscripcion.addActionListener(e -> autocompletarDatos());

        btnActualizar.addActionListener(e -> actualizarInscripcion());
        btnVolver.addActionListener(e -> volver());
    }

    private void cargarInscripciones() {
        cmbIdInscripcion.removeAllItems();
        for (Inscripcion inscripcion : Main.listaInscripcion) {
            cmbIdInscripcion.addItem(inscripcion);
        }
    }

    private void cargarClubes() {
        cmbClub.removeAllItems();
        for (Club club : Main.listaClub) {
            cmbClub.addItem(club);
        }
    }

    private void cargarActividades() {
        cmbActividad.removeAllItems();
        for (Actividad actividad : Main.listaActividad) {
            cmbActividad.addItem(actividad);
        }
    }

    private void autocompletarDatos() {
        Inscripcion seleccionada = (Inscripcion) cmbIdInscripcion.getSelectedItem();
        if (seleccionada != null) {

            txtPrecio.setText(String.valueOf(seleccionada.getPrecios()));

        }
    }

    private void actualizarInscripcion() {
        if (txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete el campo de precio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idInscripcion;
        int nuevoPrecio;
        Club clubSeleccionado;
        Actividad actividadSeleccionada;

        try {

            Inscripcion inscripcionSel = (Inscripcion) cmbIdInscripcion.getSelectedItem();
            if (inscripcionSel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una inscripción válida.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            idInscripcion = inscripcionSel.getIdInscripcion();

            clubSeleccionado = (Club) cmbClub.getSelectedItem();
            actividadSeleccionada = (Actividad) cmbActividad.getSelectedItem();

            if (clubSeleccionado == null || actividadSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un club y una actividad válidos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            nuevoPrecio = Integer.parseInt(txtPrecio.getText().trim());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Revise los datos ingresados (el precio debe ser un número entero).",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Inscripcion inscripcionActualizada = new Inscripcion();
        inscripcionActualizada.setIdInscripcion(idInscripcion);
        inscripcionActualizada.setClub(clubSeleccionado);
        inscripcionActualizada.setActividad(actividadSeleccionada);
        inscripcionActualizada.setPrecios(nuevoPrecio);

        try {
            InscripcionDAO inscripcionDAO = new InscripcionDAO();
            boolean modificado = inscripcionDAO.modificarInscripcion(inscripcionActualizada);

            if (modificado) {
                JOptionPane.showMessageDialog(this, "Inscripción modificada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar la inscripción. Verifique los datos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar modificar la inscripción", "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void volver() {
        this.dispose();
        AdministradorInscripcionFrame admin = new AdministradorInscripcionFrame();
        admin.setVisible(true);
    }
}