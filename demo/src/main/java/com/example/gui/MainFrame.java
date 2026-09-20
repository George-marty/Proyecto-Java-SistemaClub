package com.example.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
        construirModuloSistema();

    }
    public void construirModuloClub() {
        JButton btnRegistrarCli = new JButton("Registrar Club");
        JButton btnListarCli = new JButton("Listar Club");
        

        panelBotones.add(btnRegistrarCli);
        panelBotones.add(btnListarCli);
     

        btnRegistrarCli.addActionListener(e -> registrarClub());
        btnListarCli.addActionListener(e -> listarClub());

        
        

    }
    private void registrarClub() {
        ClubFrame ventanaCluFrame = new ClubFrame();
        ventanaCluFrame.setVisible(true);
        this.dispose();

    }

    private void listarClub() {
        ListadoClubFrame listadoClubFrame = new ListadoClubFrame();
        listadoClubFrame.setVisible(true);
        this.dispose();
    }

    private void construirModuloActividad() {
        JButton btnRegistrarAct = new JButton("Registrar Actividad");
        JButton btnListarAct = new JButton("Listar Actividad");
        

        panelBotones.add(btnRegistrarAct);
        panelBotones.add(btnListarAct);
       

        btnRegistrarAct.addActionListener(e -> registrarActividad());
        btnListarAct.addActionListener(e-> listarActividad());
       
    }

    private void registrarActividad(){
        ActividadFrame ventanaActFrame = new ActividadFrame();
            ventanaActFrame.setVisible(true);
            this.dispose();

    }
    private void listarActividad(){
        ListadoActividadFrame listadoActividadFrame = new ListadoActividadFrame();
            listadoActividadFrame.setVisible(true);
            this.dispose();
    }

    private void construirModuloSistema() {
        JButton btnSalir = new JButton("Salir");
       
        panelBotones.add(btnSalir);

        btnSalir.addActionListener(e-> salir());
    }
    private void salir(){
        System.out.println("Saliendo del sistema...");
            System.exit(0);

    }


}
