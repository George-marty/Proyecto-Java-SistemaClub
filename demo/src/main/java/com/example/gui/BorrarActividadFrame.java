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
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        formularioActividadBorrar();
    }
    private void formularioActividadBorrar(){
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(1, 2, 20, 25));
        panelFormulario.add(new JLabel("Ingrese Id Actividad a Borrar:", SwingConstants.CENTER));
        cmbIdActividad = new JComboBox<>();
        cargarIdsActividad();
        panelFormulario.add(cmbIdActividad);

        btnBorrar = new JButton("Borrar");
        btnVolver = new JButton("Volver");
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnBorrar);
        panelBotones.add(btnVolver);
        

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnBorrar.addActionListener(e -> { borrarActividad(); });
        btnVolver.addActionListener(e -> { volver(); });
    }


    private void borrarActividad() {
        Actividad idSeleccionado = (Actividad) cmbIdActividad.getSelectedItem();
        if (idSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID del Actividad a borrar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 

        int idCliBorrar = idSeleccionado.getIdActividad();
        
        if (idCliBorrar > 0) {
            boolean borrado;
            try {
                
                borrado = ActividadDAO.borrarActividad(idCliBorrar);
                if (borrado) {
                javax.swing.JOptionPane.showMessageDialog(this, "Actividad con ID " + idCliBorrar + " borrado correctamente.");
                cmbIdActividad.removeItem(idSeleccionado); 
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "No se pudo borrar el cliente. Verifique el ID.");
                        }
                } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(this,
                        "No se puede borrar esta Actividad porque tiene inscripciones asociadas.\nElimine las inscripciones primero.",
                        "Error de integridad",
                        JOptionPane.ERROR_MESSAGE);
            }
                
                
                catch (Exception e) {
                
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
    
    private void volver() {
        this.dispose();
        AdministradorActividadFrame administradorActividadFrame = new AdministradorActividadFrame();
        administradorActividadFrame.setVisible(true);
        
    }
}
