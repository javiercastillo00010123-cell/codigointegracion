package dao;

import modelo.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipanteDAO {

    public boolean registrarParticipante(Participante participante) {
        String sql = "INSERT INTO participante (nombre_artistico, nombre_real, edad, ciudad) VALUES (?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, participante.getNombreArtistico());
            ps.setString(2, participante.getNombreReal());
            ps.setInt(3, participante.getEdad());
            ps.setString(4, participante.getCiudad());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar participante.");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Participante> listarParticipantes() {
        ArrayList<Participante> lista = new ArrayList<>();

        String sql = "SELECT * FROM participante";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Participante participante = new Participante();

                participante.setIdParticipante(rs.getInt("id_participante"));
                participante.setNombreArtistico(rs.getString("nombre_artistico"));
                participante.setNombreReal(rs.getString("nombre_real"));
                participante.setEdad(rs.getInt("edad"));
                participante.setCiudad(rs.getString("ciudad"));

                lista.add(participante);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar participantes.");
            e.printStackTrace();
        }

        return lista;
    }
}