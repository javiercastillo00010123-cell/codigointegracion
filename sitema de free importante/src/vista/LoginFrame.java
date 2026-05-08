package vista;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    public LoginFrame() {
        setTitle("Inicio de sesión - Sistema Freestyle");
        setSize(400, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Sistema de Batallas Freestyle", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        txtCorreo = new JTextField();
        txtPassword = new JPasswordField();

        panelFormulario.add(new JLabel("Correo:"));
        panelFormulario.add(txtCorreo);
        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(txtPassword);

        add(panelFormulario, BorderLayout.CENTER);

        btnIngresar = new JButton("Ingresar");
        add(btnIngresar, BorderLayout.SOUTH);

        btnIngresar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar correo y contraseña.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.validarLogin(correo, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido: " + usuario.getNombre()
                    + "\nRol: " + usuario.getNombreRol());

            MenuPrincipal menu = new MenuPrincipal(usuario);
            menu.setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
        }
    }
}