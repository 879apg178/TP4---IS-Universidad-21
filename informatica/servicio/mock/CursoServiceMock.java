package informatica.servicio.mock;

import informatica.modelo.Actividad;
import informatica.modelo.Curso;
import informatica.servicio.ICursoService;
import java.util.ArrayList;
import java.util.List;

public class CursoServiceMock implements ICursoService {

    @Override
    public List<Curso> getCursosPorUsuario(int idUsuario) {
        List<Curso> cursosFalsos = new ArrayList<>();
        if (idUsuario == 1) {
            cursosFalsos.add(new Curso(101, "INSTALADOR DE FIBERHOME E DESPLEGADOR DE FIBRA...", 49));
            cursosFalsos.add(new Curso(102, "Redes y Telecomunicaciones I", 15));
            cursosFalsos.add(new Curso(103, "Programación Orientada a Objetos", 95));
        }
        return cursosFalsos;
    }

    // Este método también debe coincidir con la interfaz (con idUsuario)
    @Override
    public List<Actividad> getActividadesPorCurso(int idCurso, int idUsuario) {
        List<Actividad> actividadesFalsas = new ArrayList<>();
        if (idCurso == 101) {
            actividadesFalsas.add(new Actividad(201, "CLASE 1", "seccion", false));
            actividadesFalsas.add(new Actividad(202, "ACTIVIDAD 1 INSTALADOR", "actividad", true));
            actividadesFalsas.add(new Actividad(203, "CLASE 2", "seccion", false));
            actividadesFalsas.add(new Actividad(204, "DESPLEGADOR", "seccion", false));
            actividadesFalsas.add(new Actividad(205, "Actividad N°2 Desplegador", "actividad", false));
            actividadesFalsas.add(new Actividad(206, "Arquitectura FTTH", "recurso", false));
            actividadesFalsas.add(new Actividad(207, "video instalador", "video", false));
        }
        return actividadesFalsas;
    }


    @Override
    public void marcarActividad(int idUsuario, int idActividad, boolean completada) {
        // En un mock (simulación)
        // Solo imprimimos un mensaje para saber que se llamó
        System.out.println("MOCK: Marcando actividad " + idActividad + " como " + completada);
    }
}