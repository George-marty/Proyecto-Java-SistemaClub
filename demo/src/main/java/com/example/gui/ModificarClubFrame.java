package com.example.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.Main;
import com.example.DAO.ClubDAO;
import com.example.modelo.Club;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ModificarClubFrame extends JFrame {
    private JComboBox<Club> cmbIdClub;
    private JTextField txtNombre;
    private JTextField txtSocios;
    private JComboBox<String> cmbActivo;
    private JTextField txtFechaFundacion; 
    
    private JButton btnActualizar;
    private JButton btnVolver;

    public ModificarClubFrame() {
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

        panelFormulario.add(new JLabel("ID del Club:"));
        
        cmbIdClub = new JComboBox<>();
        cargarIdsClubes(); 
        panelFormulario.add(cmbIdClub);

        panelFormulario.add(new JLabel("Nuevo nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Socios:"));
        txtSocios = new JTextField();
        panelFormulario.add(txtSocios);

        panelFormulario.add(new JLabel("Activo:"));
        cmbActivo = new JComboBox<>(new String[]{"Activo", "No Activo"});
        panelFormulario.add(cmbActivo);

        panelFormulario.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFechaFundacion = new JTextField();
        panelFormulario.add(txtFechaFundacion);

        

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
        if (txtNombre.getText().trim().isEmpty() ||
            txtSocios.getText().trim().isEmpty()  ||
            txtFechaFundacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idClub;
        String nombre;
        int socios;
        boolean activo;
        LocalDate fechaFun;
        
        try {
            // 1. Obtenemos el objeto Club seleccionado y extraemos su ID numérico
            Club clubSeleccionado = (Club) cmbIdClub.getSelectedItem();
            if (clubSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un club válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            idClub = clubSeleccionado.getIdClub();

            // 2. Parseamos el resto de los campos
            socios = Integer.parseInt(txtSocios.getText().trim());
            nombre = txtNombre.getText().trim();
            String seleccionActivo = (String) cmbActivo.getSelectedItem();
            activo = seleccionActivo.equals("Activo");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fechaFun = LocalDate.parse(txtFechaFundacion.getText().trim(), formatter);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Revise los datos ingresados (número o fecha inválida).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Club club = new Club(idClub, nombre, socios, activo, fechaFun);

        try {
            ClubDAO clubDAO = new ClubDAO();
            boolean modificado = clubDAO.modificarClub(club);

            if (modificado) {
                JOptionPane.showMessageDialog(this, "Club modificado correctamente.");
                // Opcional: Si quieres actualizar la lista global en memoria o cerrar la ventana
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar. Verifique el ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar modificar el club", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }   
    }
    private void cargarIdsClubes() {
        cmbIdClub.removeAllItems();
        for (Club club : Main.listaClub) {
            cmbIdClub.addItem(club);
        }
    }

    private void volver() {
        this.dispose();
        AdministradorClubFrame admin = new AdministradorClubFrame();
        admin.setVisible(true);
    }
}