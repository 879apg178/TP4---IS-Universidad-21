package informatica.servicio;

import informatica.modelo.Curso;
import informatica.modelo.Actividad;
import java.util.List;

public interface ICursoService {

    List<Curso> getCursosPorUsuario(int idUsuario);

    List<Actividad> getActividadesPorCurso(int idCurso, int idUsuario);


    /**
     * Actualiza el estado de completado de una actividad para un usuario.
     */
    void marcarActividad(int idUsuario, int idActividad, boolean completada);
}