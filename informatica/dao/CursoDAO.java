package informatica.dao;

import informatica.db.ConexionDB;
import informatica.modelo.Actividad;
import informatica.modelo.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public List<Curso> getCursosPorUsuario(int idUsuario) {
        // 1. La lista se crea aquí, al inicio
        List<Curso> cursos = new ArrayList<>();

        String sql = "SELECT c.id_curso, c.nombre_curso, i.progreso " +
                "FROM cursos c " +
                "JOIN inscripciones i ON c.id_curso = i.id_curso " +
                "WHERE i.id_usuario = ?";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // 2. La lista se llena dentro del 'try'
                    cursos.add(new Curso(rs.getInt("id_curso"), rs.getString("nombre_curso"), rs.getInt("progreso")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los cursos del usuario: " + e.getMessage());
            e.printStackTrace();
            // 3. Si hay un error, el 'catch' se ejecuta, pero NO retorna nada
        }


        return cursos;
    }

    public List<Actividad> getActividadesPorCurso(int idCurso, int idUsuario) {
        // 1. La lista se crea aquí, al inicio
        List<Actividad> actividades = new ArrayList<>();

        String sql = "SELECT a.id_actividad, a.nombre_actividad, a.tipo, " +
                "COALESCE(pa.completado, FALSE) AS completado " +
                "FROM actividades a " +
                "LEFT JOIN progreso_actividades pa ON a.id_actividad = pa.id_actividad AND pa.id_usuario = ? " +
                "WHERE a.id_curso = ?";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idCurso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // 2. La lista se llena dentro del 'try'
                    actividades.add(new Actividad(
                            rs.getInt("id_actividad"),
                            rs.getString("nombre_actividad"),
                            rs.getString("tipo"),
                            rs.getBoolean("completado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las actividades del curso: " + e.getMessage());
            e.printStackTrace();
            // 3. Si hay un error, el 'catch' se ejecuta
        }

        return actividades;
    }

    public void actualizarProgresoActividad(int idUsuario, int idActividad, boolean completada) {

        String sql = "INSERT INTO progreso_actividades (id_usuario, id_actividad, completado) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE completado = ?";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idActividad);
            ps.setBoolean(3, completada);
            ps.setBoolean(4, completada);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar el progreso de la actividad: " + e.getMessage());
            e.printStackTrace();
        }
        // (Este método es 'void', por lo que NO necesita return)
    }
}