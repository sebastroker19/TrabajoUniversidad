package ConsultorioOdontologico.edu.co.model;

public class Odontologo {
    private int idMedico;
    private String nombre;
    private String licencia;
    private String especialidad;

    public Odontologo(int idMedico, String nombre, String licencia, String especialidad) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.licencia = licencia;
        this.especialidad = especialidad;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getTratamientosRealizados() {
        return 0; // Este método puede ser modificado para devolver el número de tratamientos
    }
}

