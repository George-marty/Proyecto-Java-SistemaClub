package com.example.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MainFrame extends JFrame {
    private JPanel panelBotones;

    public MainFrame() {
        configurarVentana();
        inicializadorComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Club y Actividades");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializadorComponentes() {
        panelBotones = new JPanel(new GridLayout(0, 2, 10, 10));
        add(panelBotones, BorderLayout.CENTER);
        construirModuloClub();
        construirModuloActividad();
        construirModuloInscripcion();
        construirModuloSistema();
        

    }
    public void construirModuloClub() {
        JButton btnClubCli = new JButton("Club");
        panelBotones.add(btnClubCli);
        btnClubCli.addActionListener(e -> administradorClub());
    }
    private void administradorClub() {
        AdministradorClubFrame administradorClub = new AdministradorClubFrame();
        administradorClub.setVisible(true);
        this.dispose();
    }
    
    private void construirModuloActividad(){
        JButton btnActividadAct = new JButton("Actividad");
        panelBotones.add(btnActividadAct);
        btnActividadAct.addActionListener(e -> administradorActividad());
    }

    private void administradorActividad(){
        AdministradorActividadFrame administradorActividad = new AdministradorActividadFrame();
        administradorActividad.setVisible(true);
        this.dispose();
    }

    private void construirModuloInscripcion(){
        JButton btnInscripcion = new JButton("Inscripcion");
        panelBotones.add(btnInscripcion);
        btnInscripcion.addActionListener(e -> administradorInscripcion());
    }

    private void administradorInscripcion(){
        AdministradorInscripcionFrame administradorFrame = new AdministradorInscripcionFrame();
        administradorFrame.setVisible(true);
        this.dispose();
    }

    private void construirModuloSistema() {
        JButton btnSalir = new JButton("Salir");
        panelBotones.add(btnSalir);
        btnSalir.addActionListener(e-> salir());
    }
    private void salir(){
         SwingUtilities.invokeLater(() -> {
            LoginFrame ventana = new LoginFrame();  
            ventana.setVisible(true);
            this.dispose();
        });
    }
}
