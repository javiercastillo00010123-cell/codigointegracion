package vista;

import dao.JuradoDAO;
import modelo.Jurado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JuradoFrame extends JFrame {

    private JTextField txtNombre;
    private JTextField txtAlias;
    private JTextField txtExperiencia;
    private JTable tablaJurados;
    private DefaultTableModel modeloTabla;

    public JuradoFrame() {
        setTitle("Gestión de jurados");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registro de jurados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtNombre = new JTextField();
        txtAlias = new JTextField();
        txtExperiencia = new JTextField();

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Alias:"));
        panelFormulario.add(txtAlias);

        panelFormulario.add(new JLabel("Experiencia:"));
        panelFormulario.add(txtExperiencia);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnLimpiar);

        add(panelFormulario, BorderLayout.WEST);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Alias");
        modeloTabla.addColumn("Experiencia");

        tablaJurados = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaJurados);
        add(scroll, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarJurado());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarTabla();
    }

    private void guardarJurado() {
        String nombre = txtNombre.getText().trim();
        String alias = txtAlias.getText().trim();
        String experiencia = txtExperiencia.getText().trim();

        if (nombre.isEmpty() || alias.isEmpty() || experiencia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
            return;
        }

        Jurado jurado = new Jurado();
        jurado.setNombre(nombre);
        jurado.setAlias(alias);
        jurado.setExperiencia(experiencia);

        JuradoDAO dao = new JuradoDAO();
        boolean registrado = dao.registrarJurado(jurado);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Jurado registrado correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar jurado.");
        }
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);

        JuradoDAO dao = new JuradoDAO();
        ArrayList<Jurado> lista = dao.listarJurados();

        for (Jurado j : lista) {
            Object[] fila = {
                    j.getIdJurado(),
                    j.getNombre(),
                    j.getAlias(),
                    j.getExperiencia()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtAlias.setText("");
        txtExperiencia.setText("");
        txtNombre.requestFocus();
    }
}