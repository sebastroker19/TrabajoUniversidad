package org.uniquindio.edu.co.poo.musicfest.model;

import java.util.ArrayList;
import java.util.List;

public class Concierto {
    public enum Estado { PROGRAMADO, CANCELADO, FINALIZADO }

    public int idConcierto;
    public String fecha;
    public String lugar;
    public Estado estado;
    public List<Artista> artistas;
    public List<PersonalTecnico> personalTecnico;
    public List<Proveedor> proveedores;
    public List<Entrada> entradasVendidas;

    public Concierto(int idConcierto, String fecha, String lugar) {
        this.idConcierto = idConcierto;
        this.fecha = fecha;
        this.lugar = lugar;
        this.estado = Estado.PROGRAMADO;
        this.artistas = new ArrayList<>();
        this.personalTecnico = new ArrayList<>();
        this.proveedores = new ArrayList<>();
        this.entradasVendidas = new ArrayList<>();
    }

    public void agregarArtista(Artista artista) {
        artistas.add(artista);
    }

    public void agregarPersonalTecnico(PersonalTecnico persona) {
        personalTecnico.add(persona);
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public void venderEntrada(Entrada entrada, Asistente asistente) {
        entradasVendidas.add(entrada);
        asistente.agregarEntrada(entrada);
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
