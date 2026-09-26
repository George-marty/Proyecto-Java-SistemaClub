package com.example.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;


import com.example.DAO.ClubDAO;
import com.example.modelo.Club;

public class AdministradorClub extends JFrame{
    private JButton btnVolver;
    private JButton btnBorrar;
    private JButton btnRegistrarse;
    private JButton btnModificar;

    public AdministradorClub(){
        configurarVentana();
        inicializarComponentes();
    }
    private void configurarVentana(){
        setTitle("Listado Club");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void inicializarComponentes(){
        ClubDAO clubDAO = new ClubDAO();
        String [] columnas = {"id","nombre","socios","activo","fecha_fundacion","direccion","email_contacto"};
        DefaultTableModel modelo = new DefaultTableModel(null,columnas);
        
        for (Club club : clubDAO.obtenerClub()) {
            Object[] fila = {club.getId(),club.getNombre(),club.getSocios(),club.isActivo(),club.getFechaFundacion(),club.getDireccion(),club.getEmailContacto()};
            modelo.addRow(fila);
            
        }
        JTable miJtable = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(miJtable);
        JPanel panelBotones = new JPanel();

        btnVolver = new JButton("volver");
        btnBorrar = new JButton("Borrar");
        btnRegistrarse = new JButton("Registrarse");
        btnModificar = new JButton("Modificar");

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        panelBotones.add(btnRegistrarse);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnVolver);
        
        
        
        add(panelPrincipal, BorderLayout.CENTER);
       
        btnVolver.addActionListener(e -> {volver();});

        btnBorrar.addActionListener(e -> {borrar();});
        
        btnRegistrarse.addActionListener(e -> {registrarse();});
        btnModificar.addActionListener(e -> {modificarClub();});

    }
    private void volver() {
        this.dispose(); 
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true); 
    }
    private void borrar(){
        this.dispose();
        BorrarClubFrame borrarClubFrame = new BorrarClubFrame();
        borrarClubFrame.setVisible(true);
    }
    private void registrarse(){
        this.dispose();
        RegistrarseClubFrame registrarseClubFrame = new RegistrarseClubFrame();
        registrarseClubFrame.setVisible(true);
        
    }
    private void modificarClub(){
        this.dispose();
        ModificarClubFrame modificarClubFrame = new ModificarClubFrame();
        modificarClubFrame.setVisible(true);
    }


}
