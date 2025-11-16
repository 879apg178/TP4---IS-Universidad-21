package informatica.modelo;

public class Usuario {
    private int idUsuario;
    private String username;
    private String nombreCompleto;

    // Constructor
    public Usuario(int idUsuario, String username, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
    }

    // Getters
    public int getIdUsuario() { return idUsuario; }
    public String getUsername() { return username; }
    public String getNombreCompleto() { return nombreCompleto; }
}