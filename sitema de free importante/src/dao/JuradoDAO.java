package dao;

import modelo.Jurado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JuradoDAO {

    public boolean registrarJurado(Jurado jurado) {
        String sql = "INSERT INTO jurado (nombre, alias, experiencia) VALUES (?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, jurado.getNombre());
            ps.setString(2, jurado.getAlias());
            ps.setString(3, jurado.getExperiencia());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar jurado.");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Jurado> listarJurados() {
        ArrayList<Jurado> lista = new ArrayList<>();

        String sql = "SELECT * FROM jurado";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Jurado jurado = new Jurado();

                jurado.setIdJurado(rs.getInt("id_jurado"));
                jurado.setNombre(rs.getString("nombre"));
                jurado.setAlias(rs.getString("alias"));
                jurado.setExperiencia(rs.getString("experiencia"));

                lista.add(jurado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar jurados.");
            e.printStackTrace();
        }

        return lista;
    }
}