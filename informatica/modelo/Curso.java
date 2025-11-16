package informatica.modelo;

public class Curso {
    private int idCurso;
    private String nombre;
    private int progreso;

    // Constructor
    public Curso(int idCurso, String nombre, int progreso) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.progreso = progreso;
    }

    // Getters
    public int getIdCurso() { return idCurso; }
    public String getNombre() { return nombre; }
    public int getProgreso() { return progreso; }
}