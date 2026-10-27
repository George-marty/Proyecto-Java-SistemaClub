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
import com.example.DAO.InscripcionDAO;
import com.example.modelo.Inscripcion;

public class BorrarInscripcionFrame extends JFrame {
    private JComboBox<Inscripcion> cmbIdInscripcion;
    private JButton btnBorrar;
    private JButton btnVolver;

    public BorrarInscripcionFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Borrar Inscripción");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        borrarFormularioInscripcion();
    }
    private void borrarFormularioInscripcion(){
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(1, 2, 20, 25));
        panelFormulario.add(new JLabel("Seleccione Inscripción a Borrar:", SwingConstants.CENTER));
        
        cmbIdInscripcion = new JComboBox<>();
        cargarInscripciones();
        panelFormulario.add(cmbIdInscripcion);

        btnBorrar = new JButton("Borrar");
        btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnBorrar);
        panelBotones.add(btnVolver);
        

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnBorrar.addActionListener(e -> borrarInscripcion());
        btnVolver.addActionListener(e -> volver());
    }

    private void volver() {
        this.dispose();
        AdministradorInscripcionFrame adminFrame = new AdministradorInscripcionFrame();
        adminFrame.setVisible(true);
    }

    private void borrarInscripcion() {
        Inscripcion inscripcionSeleccionada = (Inscripcion) cmbIdInscripcion.getSelectedItem();
        
        if (inscripcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una inscripción válida para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        int idInscripcionBorrar = inscripcionSeleccionada.getIdInscripcion();
        
        if (idInscripcionBorrar > 0) {
            boolean borrado;
            try {
               
                InscripcionDAO inscripcionDAO = new InscripcionDAO();
                borrado = inscripcionDAO.borrarInscripcion(idInscripcionBorrar);
               
                if (borrado) {
                    JOptionPane.showMessageDialog(this, "Inscripción con ID " + idInscripcionBorrar + " borrada correctamente.");
                    
                    cmbIdInscripcion.removeItem(inscripcionSeleccionada); 
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo borrar la inscripción. Verifique el ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar borrar la inscripción", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void cargarInscripciones() {
        cmbIdInscripcion.removeAllItems();
        for (Inscripcion inscripcion : Main.listaInscripcion) {
            cmbIdInscripcion.addItem(inscripcion);
        }
    }
}