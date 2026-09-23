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

public class ListadoClubFrame extends JFrame{
    private JButton btnVolver;
    private JButton btnBorrar;

    public ListadoClubFrame(){
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

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        panelBotones.add(btnVolver);
        panelBotones.add(btnBorrar);
        add(panelPrincipal, BorderLayout.CENTER);
       
        btnVolver.addActionListener(e -> {
            volver();
        });

        btnBorrar.addActionListener(e -> {borrar();});
        

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

}
