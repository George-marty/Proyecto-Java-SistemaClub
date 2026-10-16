package com.example.gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.Main;
import com.example.DAO.ActividadDAO;
import com.example.modelo.Actividad;


public class BorrarActividadFrame extends  JFrame{
    private JComboBox<Actividad> cmbIdActividad;
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
        cmbIdActividad = new JComboBox<>();
        cargarIdsActividad();
        panelFormulario.add(cmbIdActividad);

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
        Actividad idSeleccionado = (Actividad) cmbIdActividad.getSelectedItem();
        if (idSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID del Actividad a borrar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 

        int idCliBorrar = idSeleccionado.getIdActividad();
        
        if (idCliBorrar > 0) {
            // Llamar al método para borrar el cliente
            boolean borrado;
            try {
                
                borrado = ActividadDAO.borrarActividad(idCliBorrar);
                if (borrado) {
                javax.swing.JOptionPane.showMessageDialog(this, "Actividad con ID " + idCliBorrar + " borrado correctamente.");
                cmbIdActividad.removeItem(idSeleccionado); // Limpiar el campo de texto después de borrar
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "No se pudo borrar el cliente. Verifique el ID.");
                        }
                } catch (Exception e) {
                // Si el método lanza una excepción, la capturamos y mostramos un mensaje de error
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar borrar la Actividad");
                    e.printStackTrace();
                    }
                

        } 
                                                        
    }
    private void cargarIdsActividad(){
        cmbIdActividad.removeAllItems();
        for (Actividad actividad : Main.listaActividad) {
            cmbIdActividad.addItem(actividad);
        }
    }
}
