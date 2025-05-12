package org.uniquindio.edu.co.poo.veterinaria.model;

import java.time.LocalDate;

public class Paciente {
    private String numeroHistoriaClinica;
    private String nombre;
    private int edad;
    private String telefono;
    private String direccion;
    private LocalDate fechaUltimaConsulta;
    private int tratamientosRealizados;

    public Paciente(String numeroHistoriaClinica, String nombre, int edad, String telefono, String direccion, LocalDate fechaUltimaConsulta, int tratamientosRealizados) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaUltimaConsulta = fechaUltimaConsulta;
        this.tratamientosRealizados = tratamientosRealizados;
    }

    public String getNumeroHistoriaClinica() { return numeroHistoriaClinica; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public LocalDate getFechaUltimaConsulta() { return fechaUltimaConsulta; }
    public int getTratamientosRealizados() { return tratamientosRealizados; }
}
