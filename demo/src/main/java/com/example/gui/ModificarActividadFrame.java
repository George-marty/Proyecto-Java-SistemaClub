package com.example.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.Main;
import com.example.DAO.ActividadDAO;
import com.example.modelo.Actividad;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ModificarActividadFrame extends JFrame {
    private JComboBox<Actividad> cmbIdActividad;
    private JTextField txtNombre; 
    private JTextField txtCupos;
    private JComboBox<String> cmbDisponible;
    private JTextField txtFechaInicio;
    private JButton btnActualizar;
    private JButton btnVolver;

    public ModificarActividadFrame() {
        configurarVentana();
        inicializarComponentes();
        
    }

    private void configurarVentana() {
        setTitle("Modificar Actividad"); 
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        formularioActividadModificar();
        cargarIdActividad();
    }
    private void formularioActividadModificar(){
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10)); // Ajustado a 6 filas según los campos

        panelFormulario.add(new JLabel("ID de la Actividad:"));
        cmbIdActividad = new JComboBox<>();
        panelFormulario.add(cmbIdActividad);

        panelFormulario.add(new JLabel("Nuevo nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Cupo:"));
        txtCupos = new JTextField();
        panelFormulario.add(txtCupos);

        panelFormulario.add(new JLabel("Disponible:"));
        cmbDisponible = new JComboBox<>(new String[] {"Disponible", "No Disponible"});
        panelFormulario.add(cmbDisponible);

        panelFormulario.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFechaInicio = new JTextField();
        panelFormulario.add(txtFechaInicio);

        btnActualizar = new JButton("Actualizar");
        btnVolver = new JButton("Volver");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        btnActualizar.addActionListener(e -> actualizarActividad());
        btnVolver.addActionListener(e -> volver());
    }

    private void actualizarActividad() {
        if (cmbIdActividad.getSelectedItem() == null || txtNombre.getText().trim().isEmpty() || txtCupos.getText().trim().isEmpty() || txtFechaInicio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos y seleccione un ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Actividad actividadSeleccionada = (Actividad) cmbIdActividad.getSelectedItem();
            int idActividad = actividadSeleccionada.getIdActividad();
            String nombre = txtNombre.getText().trim();
            int cupos = Integer.parseInt(txtCupos.getText().trim());
            String fechaInicio = txtFechaInicio.getText().trim();
            String seleccion = (String) cmbDisponible.getSelectedItem();
            boolean disponible = seleccion.equals("Disponible");
            final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaIni = LocalDate.parse(fechaInicio, FORMATTER);

            Actividad actividadModificada = new Actividad(idActividad, nombre, cupos, disponible, fechaIni);

            ActividadDAO actividadDAO = new ActividadDAO();
            boolean modificado = actividadDAO.modificarActividad(actividadModificada);

            if (modificado) {
                JOptionPane.showMessageDialog(this, "Actividad modificada correctamente.");
                limpiarFormulario();
                cargarIdActividad(); 
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar. Verifique el ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo cupos debe ser un número entero válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida (Formato requerido: dd/MM/yyyy)", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void volver() {
        this.dispose();
        
        AdministradorActividadFrame administradorActividadFrame = new AdministradorActividadFrame();
        administradorActividadFrame.setVisible(true);
        
    }

    private void cargarIdActividad() {
        cmbIdActividad.removeAllItems();
        for (Actividad actividad : Main.listaActividad) {
            cmbIdActividad.addItem(actividad);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtCupos.setText("");
        cmbDisponible.setSelectedIndex(0);
        txtFechaInicio.setText("");
    }
}