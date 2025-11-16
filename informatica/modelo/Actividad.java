
package informatica.modelo;

public class Actividad {
    private int idActividad;
    private String nombre;
    private String tipo; // "seccion", "actividad", "video", "recurso"
    private boolean completado;

    // Constructor
    public Actividad(int idActividad, String nombre, String tipo, boolean completado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.completado = completado;
    }

    // Getters
    public int getIdActividad() { return idActividad; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public boolean isCompletado() { return completado; }
}