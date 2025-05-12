package org.uniquindio.edu.co.poo.musicfest.model;

import java.util.ArrayList;
import java.util.List;

public class Asistente {
    public int idAsistente;
    public String nombre;
    public String apellidos;
    public String correo;
    public String telefono;
    public List<Entrada> entradas;

    public Asistente(int idAsistente, String nombre, String apellidos, String correo, String telefono) {
        this.idAsistente = idAsistente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.entradas = new ArrayList<>();
    }

    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }
}
