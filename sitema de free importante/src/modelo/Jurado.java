package modelo;

public class Jurado {

    private int idJurado;
    private String nombre;
    private String alias;
    private String experiencia;

    public Jurado() {
    }

    public Jurado(int idJurado, String nombre, String alias, String experiencia) {
        this.idJurado = idJurado;
        this.nombre = nombre;
        this.alias = alias;
        this.experiencia = experiencia;
    }

    public int getIdJurado() {
        return idJurado;
    }

    public void setIdJurado(int idJurado) {
        this.idJurado = idJurado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}