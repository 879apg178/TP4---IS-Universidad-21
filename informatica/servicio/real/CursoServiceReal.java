package informatica.servicio.real;

import informatica.dao.CursoDAO;
import informatica.modelo.Actividad;
import informatica.modelo.Curso;
import informatica.servicio.ICursoService;
import java.util.ArrayList;
import java.util.List;

public class CursoServiceReal implements ICursoService {

    private final CursoDAO cursoDAO;

    public CursoServiceReal() {
        this.cursoDAO = new CursoDAO();
    }

    @Override
    public List<Curso> getCursosPorUsuario(int idUsuario) {
        try {
            return cursoDAO.getCursosPorUsuario(idUsuario);
        } catch (Exception e) {
            System.err.println("Error en CursoServiceReal (getCursos): " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Actividad> getActividadesPorCurso(int idCurso, int idUsuario) {
        try {
            return cursoDAO.getActividadesPorCurso(idCurso, idUsuario);
        } catch (Exception e) {
            System.err.println("Error en CursoServiceReal (getActividades): " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void marcarActividad(int idUsuario, int idActividad, boolean completada) {
        try {
            // Simplemente llama al DAO para que haga el trabajo de SQL
            cursoDAO.actualizarProgresoActividad(idUsuario, idActividad, completada);
        } catch (Exception e) {
            System.err.println("Error en CursoServiceReal (marcarActividad): " + e.getMessage());
            e.printStackTrace();
        }
    }
}