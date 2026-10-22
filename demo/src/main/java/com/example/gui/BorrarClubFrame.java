package com.example.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import com.example.Main;
import com.example.DAO.ClubDAO;
import com.example.modelo.Club;

public class BorrarClubFrame extends JFrame {
    private JComboBox<Club> cmbIdClub;
    private JButton btnBorrar;
    private JButton btnvolver;

    public BorrarClubFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Borrar Cliente");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        formularioClubBorrar();
    }

    private void formularioClubBorrar() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new GridLayout(1, 1, 20, 25));
        panelFormulario.add(new JLabel("Ingrese Id Club a Borrar:", SwingConstants.CENTER));
        cmbIdClub = new JComboBox<>();
        cargarIdsClubes();
        panelFormulario.add(cmbIdClub);

        btnBorrar = new JButton("Borrar");
        btnvolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnBorrar);
        panelBotones.add(btnvolver);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnBorrar.addActionListener(e -> {
            borrarClub();
        });
        btnvolver.addActionListener(e -> {
            volverClub();
        });
    }
    

    private void borrarClub() {
        Club idSeleccionado = (Club) cmbIdClub.getSelectedItem();

        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un ID de club válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idCliBorrar = idSeleccionado.getIdClub();

        if (idCliBorrar > 0) {
            try {

                boolean borrado = ClubDAO.borrarClub(idCliBorrar);
                if (borrado) {
                    JOptionPane.showMessageDialog(this, "Club con ID " + idCliBorrar + " borrado correctamente.");
                    Main.listaClub.remove(idSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo borrar el club. Verifique el ID.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(this,
                        "No se puede borrar este club porque tiene inscripciones asociadas.\nElimine las inscripciones primero.",
                        "Error de integridad",
                        JOptionPane.ERROR_MESSAGE);
            }

            catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar borrar el club", "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void cargarIdsClubes() {
        cmbIdClub.removeAllItems();
        for (Club club : Main.listaClub) {
            cmbIdClub.addItem(club);
        }
    }

    private void volverClub() {
        this.dispose();
    }
}
