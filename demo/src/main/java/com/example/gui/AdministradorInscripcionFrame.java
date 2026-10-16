package com.example.gui;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

import com.example.DAO.InscripcionDAO;
import com.example.modelo.Inscripcion;

public class AdministradorInscripcionFrame extends JFrame{
    private JButton btnVolver;
    private JButton btnBorrar;
    private JButton btnRegistrarse;
    private JButton btnModificar;

    public AdministradorInscripcionFrame(){
        configurarVentana();
        inicializarComponentes();
    }

     private void configurarVentana(){
        setTitle("Listado Inscripcion");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void inicializarComponentes(){
        InscripcionDAO inscripcionDAO = new InscripcionDAO();
        String [] columnas = {"ID","Nombre_Club","Activo","Nombre_Actividad","Disponible","Precios"};
        DefaultTableModel modelo = new DefaultTableModel(null,columnas);
       
        for (Inscripcion inscripcion : inscripcionDAO.obtenerInscripcion()) {
            Object[] fila = {inscripcion.getIdInscripcion(),inscripcion.getClub().getNombre(),inscripcion.getClub().isActivo(),inscripcion.getActividad().getNombre(),inscripcion.getActividad().isDisponible(),inscripcion.getPrecios()};
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
        btnModificar.addActionListener(e -> {modificar();});
    }
     private void volver() {
        this.dispose(); 
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true); 
    }
    private void borrar(){
        BorrarClubFrame borrarClubFrame = new BorrarClubFrame();
        borrarClubFrame.setVisible(true);
    }
    private void registrarse(){
        this.dispose();
        RegistrarInscripcionFrame registrarInscripcionFrame = new RegistrarInscripcionFrame();
        registrarInscripcionFrame.setVisible(true);
        
        
    }
    private void modificar(){
        this.dispose();
        ModificarClubFrame modificarClubFrame = new ModificarClubFrame();
        modificarClubFrame.setVisible(true);
    }
}
