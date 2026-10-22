package com.example.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.example.servicio.UsuarioServicio;
import com.example.servicio.impl.UsuarioServicioImpl;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JTextField txtClave;
   
    public LoginFrame() {
        configurarVentana();
        formularioLogin();
        

        

    }
    private void configurarVentana(){
        setTitle("Ingreso al Sistema de Turismo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void formularioLogin(){
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel lblUsuario = new JLabel("Usuario:", SwingConstants.CENTER);
        JTextField txtUsuario = new JTextField();
        JLabel lblClave = new JLabel("Clave:", SwingConstants.CENTER);
        JTextField txtClave = new JPasswordField();
        JButton btnIngresar = new JButton("Ingresar");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblClave);
        panel.add(txtClave);
        panel.add(btnIngresar);

        add(panel, BorderLayout.CENTER);

        btnIngresar.addActionListener(e -> {
            ingresarUsuario();
        });
    }
    private void ingresarUsuario(){
        String usuario = txtUsuario.getText();
        String clave = txtClave.getText();
        UsuarioServicio usuarioServicio = new UsuarioServicioImpl();
        boolean ingresoPermitido = usuarioServicio.validarUsuario(usuario, clave);
        if (ingresoPermitido) {
            JOptionPane.showMessageDialog(this, "Ingreso exitoso");
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            this.dispose(); 
        } else {
                JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

}