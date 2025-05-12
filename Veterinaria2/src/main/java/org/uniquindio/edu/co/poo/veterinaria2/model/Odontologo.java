package org.uniquindio.edu.co.poo.veterinaria2.model;

public class Odontologo {
    private int id;
    private String nombreCompleto;
    private String numeroLicencia;
    private String especialidad;

    public Odontologo(int id, String nombreCompleto, String numeroLicencia, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.numeroLicencia = numeroLicencia;
        this.especialidad = especialidad;
    }

    public int getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getNumeroLicencia() { return numeroLicencia; }
    public String getEspecialidad() { return especialidad; }
}
