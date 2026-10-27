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
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import com.example.Main;
import com.example.modelo.Actividad;
import com.example.servicio.impl.ActividadServicioImpl;


public class RegistrarseActividadFrame extends JFrame{
    private JTextField txtNombre;
    private JTextField txtCupo;
    private JComboBox<String> cmbDisponible;
    private JTextField txtFechaInicio;
    private JButton btnGuardar;
    private JButton btnVolver;

    public RegistrarseActividadFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Registro Actividad");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null); // Centra pantallas
    }
    
    private void inicializarComponentes() {
        formularioActividad();
    }
    private void formularioActividad(){
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 20, 15));

        panelFormulario.add(new JLabel("Nombre", SwingConstants.CENTER));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Cupos", SwingConstants.CENTER));
        txtCupo = new JTextField();
        panelFormulario.add(txtCupo);

        panelFormulario.add(new JLabel("Disponible", SwingConstants.CENTER));
        cmbDisponible = new JComboBox<>(new String[]{"Sí", "No"});
        panelFormulario.add(cmbDisponible);

        panelFormulario.add(new JLabel("Fecha Inicio", SwingConstants.CENTER));
        txtFechaInicio = new JTextField();
        panelFormulario.add(txtFechaInicio);

        btnVolver = new JButton("Volver");
        btnGuardar = new JButton("Guardar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnGuardar.addActionListener(e -> {
            guardarActividad();
        });
        btnVolver.addActionListener(e -> {
            volver();
        });
    }
    private void volver() {
        this.dispose();
        AdministradorActividadFrame administradorActividadFrame = new AdministradorActividadFrame();
        administradorActividadFrame.setVisible(true);
    }

    private void guardarActividad() {
        if (txtNombre.getText().trim().isEmpty() || txtCupo.getText().trim().isEmpty() || txtFechaInicio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nombre = txtNombre.getText().trim();
            int cupos = Integer.parseInt(txtCupo.getText().trim());
            String fechaInicio = txtFechaInicio.getText().trim();
            String seleccion = (String) cmbDisponible.getSelectedItem();
            boolean disponible = seleccion.equals("Sí");
            
            final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaFun = LocalDate.parse(fechaInicio, FORMATTER);
            
            Actividad actividad = new Actividad(0, nombre, cupos, disponible, fechaFun);
            ActividadServicioImpl servicioImpl = new ActividadServicioImpl();
            servicioImpl.guardarActividad(actividad, Main.listaActividad);
            
            JOptionPane.showMessageDialog(this, "Actividad guardada correctamente");
            limpiarFormulario();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo cupos debe ser un número entero válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida (Formato requerido: dd/MM/yyyy)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario(){
        txtNombre.setText("");
        txtCupo.setText("");
        cmbDisponible.setSelectedIndex(0);
        txtFechaInicio.setText("");
    }
}
