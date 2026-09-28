package com.example.gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.DAO.ActividadDAO;


public class BorrarActividadFrame extends  JFrame{
    private JTextField id;
    private JButton btnBorrar;
    private JButton btnVolver;

    public BorrarActividadFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Borrar Actividad");
        setSize(400, 200);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(1, 2, 20, 25));
        panelFormulario.add(new JLabel("Ingrese Id Actividad a Borrar:", SwingConstants.CENTER));
        // El JTextField para el nombre
        id = new JTextField();
        panelFormulario.add(id);

        btnBorrar = new JButton("Borrar");
        btnVolver = new JButton("Volver");
        

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolver);
        panelBotones.add(btnBorrar);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Aqui declaro los eventos de los botones 
        btnBorrar.addActionListener(e -> { borrarActividad(); });

        btnVolver.addActionListener(e -> { volver(); });

    }

    private void volver() {
        this.dispose();
        AdministradorActividadFrame administradorActividadFrame = new AdministradorActividadFrame();
        administradorActividadFrame.setVisible(true);
        
    }

    private void borrarActividad() {

        if (id.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID del Actividad a borrar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                try {
                    Integer.parseInt(id.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

        int idCliBorrar = Integer.parseInt(id.getText().trim());
        
        if (idCliBorrar > 0) {
            // Llamar al método para borrar el cliente
            boolean borrado;
            try {
                
                borrado = ActividadDAO.borrarActividad(idCliBorrar);
                if (borrado) {
                javax.swing.JOptionPane.showMessageDialog(this, "Actividad con ID " + idCliBorrar + " borrado correctamente.");
                id.setText(""); // Limpiar el campo de texto después de borrar
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "No se pudo borrar el cliente. Verifique el ID.");
                        }
                } catch (Exception e) {
                // Si el método lanza una excepción, la capturamos y mostramos un mensaje de error
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar borrar la Actividad");
                    e.printStackTrace();
                    }
                

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese un ID mayor a cero.");
            }
                                                        
    }
}
