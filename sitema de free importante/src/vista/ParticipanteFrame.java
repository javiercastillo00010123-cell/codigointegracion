package vista;

import dao.ParticipanteDAO;
import modelo.Participante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ParticipanteFrame extends JFrame {

    private JTextField txtNombreArtistico;
    private JTextField txtNombreReal;
    private JTextField txtEdad;
    private JTextField txtCiudad;
    private JTable tablaParticipantes;
    private DefaultTableModel modeloTabla;

    public ParticipanteFrame() {
        setTitle("Gestión de participantes");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registro de participantes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtNombreArtistico = new JTextField();
        txtNombreReal = new JTextField();
        txtEdad = new JTextField();
        txtCiudad = new JTextField();

        panelFormulario.add(new JLabel("Nombre artístico:"));
        panelFormulario.add(txtNombreArtistico);

        panelFormulario.add(new JLabel("Nombre real:"));
        panelFormulario.add(txtNombreReal);

        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Ciudad:"));
        panelFormulario.add(txtCiudad);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnLimpiar);

        add(panelFormulario, BorderLayout.WEST);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre artístico");
        modeloTabla.addColumn("Nombre real");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Ciudad");

        tablaParticipantes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaParticipantes);
        add(scroll, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarParticipante());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void guardarParticipante() {
        String nombreArtistico = txtNombreArtistico.getText().trim();
        String nombreReal = txtNombreReal.getText().trim();
        String edadTexto = txtEdad.getText().trim();
        String ciudad = txtCiudad.getText().trim();

        if (nombreArtistico.isEmpty() || nombreReal.isEmpty() || edadTexto.isEmpty() || ciudad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
            return;
        }

        int edad;

        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número.");
            return;
        }

        Participante participante = new Participante();
        participante.setNombreArtistico(nombreArtistico);
        participante.setNombreReal(nombreReal);
        participante.setEdad(edad);
        participante.setCiudad(ciudad);

        ParticipanteDAO dao = new ParticipanteDAO();
        boolean registrado = dao.registrarParticipante(participante);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Participante registrado correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar participante.");
        }
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);

        ParticipanteDAO dao = new ParticipanteDAO();
        ArrayList<Participante> lista = dao.listarParticipantes();

        for (Participante p : lista) {
            Object[] fila = {
                    p.getIdParticipante(),
                    p.getNombreArtistico(),
                    p.getNombreReal(),
                    p.getEdad(),
                    p.getCiudad()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void limpiarCampos() {
        txtNombreArtistico.setText("");
        txtNombreReal.setText("");
        txtEdad.setText("");
        txtCiudad.setText("");
        txtNombreArtistico.requestFocus();
    }
}