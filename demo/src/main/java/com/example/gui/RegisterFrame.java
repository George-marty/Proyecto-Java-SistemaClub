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

import com.example.DAO.UsuarioDAO;
import com.example.modelo.Usuario;
import com.example.servicio.UsuarioServicio;
import com.example.servicio.impl.UsuarioServicioImpl;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {
        setTitle("Registro al sistema de Club");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel lblUsuario = new JLabel("Usuario:", SwingConstants.CENTER);
        JTextField txtUsuario = new JTextField();
        JLabel lblClave = new JLabel("Clave", SwingConstants.CENTER);
        JPasswordField txtClave = new JPasswordField();
        JButton btnIngresar = new JButton("Registrarse");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblClave);
        panel.add(txtClave);
        panel.add(btnIngresar);

        add(panel, BorderLayout.CENTER);

        btnIngresar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String clave = new String(txtClave.getPassword());

            // Instanciamos la clase que implementa la interfaz
            UsuarioServicio usuarioServicio = new UsuarioServicioImpl();
            Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(usuario, clave);
            // Invocamos el método a través del objeto/instancia
            // boolean ingresoPermitido = usuarioServicio.validarUsuario(usuario, clave);
            boolean registroExitoso = usuarioRegistrado.getId() != 0;
            // Si el ingreso es permitido, se muestra un mensaje de éxito y se abre la
            // ventana principal
            if (registroExitoso) {
                JOptionPane.showMessageDialog(this, "registro exitoso");
                // Se abre la ventana principal de la aplicación
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                this.dispose(); // Cierra la ventana de login
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
