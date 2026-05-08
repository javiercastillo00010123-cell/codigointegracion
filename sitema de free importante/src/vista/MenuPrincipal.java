package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private Usuario usuario;

    public MenuPrincipal(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú principal - " + usuario.getNombreRol());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Panel principal del sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JButton btnUsuarios = new JButton("Gestionar usuarios");
        JButton btnParticipantes = new JButton("Participantes");
        JButton btnJurados = new JButton("Jurados");
        JButton btnEventos = new JButton("Eventos");
        JButton btnBatallas = new JButton("Batallas");
        JButton btnPuntajes = new JButton("Puntajes");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Cerrar sesión");

        panelBotones.add(btnUsuarios);
        panelBotones.add(btnParticipantes);
        panelBotones.add(btnJurados);
        panelBotones.add(btnEventos);
        panelBotones.add(btnBatallas);
        panelBotones.add(btnPuntajes);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        aplicarPermisos(btnUsuarios, btnParticipantes, btnJurados, btnEventos, btnBatallas, btnPuntajes, btnHistorial);

        btnParticipantes.addActionListener(e -> {
        ParticipanteFrame participanteFrame = new ParticipanteFrame();
        participanteFrame.setVisible(true);
});

        btnSalir.addActionListener(e -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            dispose();
     });
      
        btnJurados.addActionListener(e -> {
         JuradoFrame juradoFrame = new JuradoFrame();
         juradoFrame.setVisible(true);
});
    
    }

    private void aplicarPermisos(
            JButton btnUsuarios,
            JButton btnParticipantes,
            JButton btnJurados,
            JButton btnEventos,
            JButton btnBatallas,
            JButton btnPuntajes,
            JButton btnHistorial
    ) {
        String rol = usuario.getNombreRol();

        if (rol.equalsIgnoreCase("Administrador")) {
            btnUsuarios.setEnabled(true);
            btnParticipantes.setEnabled(true);
            btnJurados.setEnabled(true);
            btnEventos.setEnabled(true);
            btnBatallas.setEnabled(true);
            btnPuntajes.setEnabled(true);
            btnHistorial.setEnabled(true);
        } else if (rol.equalsIgnoreCase("Organizador")) {
            btnUsuarios.setEnabled(false);
            btnParticipantes.setEnabled(true);
            btnJurados.setEnabled(true);
            btnEventos.setEnabled(true);
            btnBatallas.setEnabled(true);
            btnPuntajes.setEnabled(false);
            btnHistorial.setEnabled(true);
        } else if (rol.equalsIgnoreCase("Jurado")) {
            btnUsuarios.setEnabled(false);
            btnParticipantes.setEnabled(false);
            btnJurados.setEnabled(false);
            btnEventos.setEnabled(false);
            btnBatallas.setEnabled(false);
            btnPuntajes.setEnabled(true);
            btnHistorial.setEnabled(true);
        }
    }
}