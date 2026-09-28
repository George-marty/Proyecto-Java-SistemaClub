package com.example.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
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
    private JTextField txtDisponible;
    private JTextField txtFechaInicio;
    private JTextField txtDescripcion;
    

    private JButton btnGuardar;
    private JButton btnVolver;

    public RegistrarseActividadFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Registro Actividad");
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

        panelFormulario.add(new JLabel("Cupos", SwingConstants.CENTER));
        txtCupo = new JTextField();
        panelFormulario.add(txtCupo);

        panelFormulario.add(new JLabel("Disponible", SwingConstants.CENTER));
        txtDisponible = new JTextField();
        panelFormulario.add(txtDisponible);

        panelFormulario.add(new JLabel("Fecha Inicio", SwingConstants.CENTER));
        txtFechaInicio = new JTextField();
        panelFormulario.add(txtFechaInicio);

        panelFormulario.add(new JLabel("Descripcion", SwingConstants.CENTER));
        txtDescripcion = new JTextField();
        panelFormulario.add(txtDescripcion);

        

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

    private void guardarActividad(){
        String nombre = txtNombre.getText().trim();
        int cupos = Integer.parseInt(txtCupo.getText().trim());
        boolean disponible = Boolean.parseBoolean(txtDisponible.getText().trim());
        String fechaInicio = txtFechaInicio.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        

        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaFun = LocalDate.parse(fechaInicio,FORMATTER);

        if (nombre.isEmpty() || txtCupo.getText().isEmpty() || txtDisponible.getText().isEmpty() || fechaInicio.isEmpty() || descripcion.isEmpty() ){
            JOptionPane.showMessageDialog(this, "Complete todos los campos","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        
        
        Actividad actividad = new Actividad(0,nombre, cupos, disponible, fechaFun, descripcion);

        ActividadServicioImpl servicioImpl = new ActividadServicioImpl();
        servicioImpl.guardarActividad(actividad, Main.listaActividad);

        JOptionPane.showMessageDialog(this, "Actividad guardado correctamente");
        limpiarFormulario();
    }

    private void limpiarFormulario(){
        txtNombre.setText("");
        txtCupo.setText("");
        txtDisponible.setText("");
        txtFechaInicio.setText("");
        txtDescripcion.setText("");
    }
}
