package com.example.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.DAO.ActividadDAO;

import com.example.modelo.Actividad;

import java.awt.BorderLayout;

import java.awt.GridLayout;


public class ModificarActividadFrame extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre; 
    private JTextField txtCupos;
    private JTextField txtDisponible;
    private JTextField txtFechaInicio;
    private JTextField txtDescripcion;
    private JButton btnActualizar;
    private JButton btnVolver;

    public ModificarActividadFrame() {
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

        panelFormulario.add(new JLabel("ID de la Actividad:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nuevo nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Cupo:"));
        txtCupos = new JTextField();
        panelFormulario.add(txtCupos);

        panelFormulario.add(new JLabel("Disponible (true/false):"));
        txtDisponible = new JTextField();
        panelFormulario.add(txtDisponible);

        panelFormulario.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFechaInicio = new JTextField();
        panelFormulario.add(txtFechaInicio);

        panelFormulario.add(new JLabel("Descripcion:"));
        txtDescripcion = new JTextField();
        panelFormulario.add(txtDescripcion);

       

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
        txtCupos.getText().trim().isEmpty() || txtDisponible.getText().trim().isEmpty() ||
        txtFechaInicio.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int id;
    int cupos;
    boolean disponible;
    LocalDate fechaFun;
    try {
        id = Integer.parseInt(txtId.getText().trim());
        cupos = Integer.parseInt(txtCupos.getText().trim());
        disponible = Boolean.parseBoolean(txtDisponible.getText().trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaFun = LocalDate.parse(txtFechaInicio.getText().trim(), formatter);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Revise los datos ingresados (número o fecha inválida).", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Actividad actividad = new Actividad(id, txtNombre.getText().trim(), cupos, disponible, fechaFun,
                          txtDescripcion.getText().trim());

    try{
        
        ActividadDAO actividadDAO = new ActividadDAO();

        boolean modificado = actividadDAO.modificarActividad(actividad);

        if (modificado) {
        JOptionPane.showMessageDialog(this, "Actividad modificado correctamente.");
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
        AdministradorActividadFrame administradorActividadFrame = new AdministradorActividadFrame();
        administradorActividadFrame.setVisible(true);
        
    }
}
