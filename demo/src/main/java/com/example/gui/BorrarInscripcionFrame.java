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
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(1, 2, 20, 25));
        panelFormulario.add(new JLabel("Seleccione Inscripción a Borrar:", SwingConstants.CENTER));
        
        cmbIdInscripcion = new JComboBox<>();
        cargarInscripciones();
        panelFormulario.add(cmbIdInscripcion);

        btnBorrar = new JButton("Borrar");
        btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolver);
        panelBotones.add(btnBorrar);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Eventos de los botones
        btnBorrar.addActionListener(e -> borrarInscripcion());
        btnVolver.addActionListener(e -> volver());
    }

    private void volver() {
        this.dispose();
        // Cambia esto por tu ventana de administración de inscripciones correspondiente
        // AdministradorInscripcionFrame adminFrame = new AdministradorInscripcionFrame();
        // adminFrame.setVisible(true);
    }

    private void borrarInscripcion() {
        // 1. Obtenemos el objeto Inscripcion seleccionado del ComboBox
        Inscripcion inscripcionSeleccionada = (Inscripcion) cmbIdInscripcion.getSelectedItem();
        
        if (inscripcionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una inscripción válida para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } 

        // 2. Extraemos el ID numérico de la inscripción
        int idInscripcionBorrar = inscripcionSeleccionada.getIdInscripcion();
        
        if (idInscripcionBorrar > 0) {
            boolean borrado;
            try {
                // 3. Llamamos al DAO para borrar en la base de datos
                InscripcionDAO inscripcionDAO = new InscripcionDAO();
                borrado = inscripcionDAO.borrarInscripcion(idInscripcionBorrar);
               
                if (borrado) {
                    JOptionPane.showMessageDialog(this, "Inscripción con ID " + idInscripcionBorrar + " borrada correctamente.");
                    
                    // 4. Limpiamos del ComboBox local y de la lista en memoria (si usas una lista global)
                    cmbIdInscripcion.removeItem(inscripcionSeleccionada); 
                    // Main.listaInscripcion.remove(inscripcionSeleccionada); 
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
        // Si tienes una lista global en memoria, la recorres aquí. 
        // O alternativamente puedes llamar directamente a un InscripcionDAO.obtenerInscripcion()
        for (Inscripcion inscripcion : Main.listaInscripcion) { // Asegúrate de tener esta lista en tu clase Main o cárgala del DAO
            cmbIdInscripcion.addItem(inscripcion);
        }
    }
}