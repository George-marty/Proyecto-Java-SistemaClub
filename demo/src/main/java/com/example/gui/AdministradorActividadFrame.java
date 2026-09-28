package com.example.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

import com.example.DAO.ActividadDAO;
import com.example.modelo.Actividad;

public class AdministradorActividadFrame extends JFrame{
    private JButton btnVolver;
    private JButton btnBorrar;
    private JButton btnRegistrarse;
    private JButton btnModificar;

    public AdministradorActividadFrame(){
        configurarVentana();
        inicializarComponentes();
    }
    private void configurarVentana(){
        setTitle("Administrador Actividad");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void inicializarComponentes(){
        ActividadDAO actividadDAO = new ActividadDAO();
        String [] columnas = {"id","nombre","cupo","disponible","fecha_inicio","descripcion"};
        DefaultTableModel modelo = new DefaultTableModel(null,columnas);
        for (Actividad actividad : actividadDAO.obtenerActividad()) {
            Object[] fila = {actividad.getId(),actividad.getNombre(),actividad.getCupo(),actividad.isDisponible(),actividad.getFechaInicio(),actividad.getDescripcion()};
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
        btnModificar.addActionListener(e -> {modificarActividad();});
    }
    private void volver() {
        this.dispose(); 
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true); 
    }
    private void borrar(){
        this.dispose();
        BorrarActividadFrame borrarActividadFrame = new BorrarActividadFrame();
        borrarActividadFrame.setVisible(true);
       
    }
    private void registrarse(){
        this.dispose();
        RegistrarseActividadFrame registrarseActividadFrame = new RegistrarseActividadFrame();
        registrarseActividadFrame.setVisible(true);
        
    }
    private void modificarActividad(){
        this.dispose();
        ModificarActividadFrame modificarActividadFrame = new ModificarActividadFrame();
        modificarActividadFrame.setVisible(true);
        
    }
    
}
