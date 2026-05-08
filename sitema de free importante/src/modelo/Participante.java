package modelo;

public class Participante {

    private int idParticipante;
    private String nombreArtistico;
    private String nombreReal;
    private int edad;
    private String ciudad;

    public Participante() {
    }

    public Participante(int idParticipante, String nombreArtistico, String nombreReal, int edad, String ciudad) {
        this.idParticipante = idParticipante;
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}