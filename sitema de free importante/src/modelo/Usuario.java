package modelo;

public class Usuario {

    private int idUsuario;
    private int idRol;
    private String nombre;
    private String correo;
    private String password;
    private String estado;
    private String nombreRol;

    public Usuario() {
    }

    public Usuario(int idUsuario, int idRol, String nombre, String correo, String password, String estado, String nombreRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.nombreRol = nombreRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}