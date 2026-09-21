package com.example;


import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.example.gui.LoginFrame;
import com.example.modelo.Club;


public class Main {
    public static final ArrayList<Club> listaClub = new ArrayList<>();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            LoginFrame ventana = new LoginFrame();
            ventana.setVisible(true);
        }
    );
        
        
    }
}