package org.uniquindio.edu.co.poo.musicfest.model;

import java.util.ArrayList;
import java.util.List;

public class Artista {
    public int idArtista;
    public String nombreArtistico;
    public String genero;
    public List<String> riderTecnico;
    public boolean esBanda;
    public List<String> integrantes;

    public Artista(int idArtista, String nombreArtistico, String genero, List<String> riderTecnico, boolean esBanda) {
        this.idArtista = idArtista;
        this.nombreArtistico = nombreArtistico;
        this.genero = genero;
        this.riderTecnico = riderTecnico;
        this.esBanda = esBanda;
        this.integrantes = esBanda ? new ArrayList<>() : null;
    }

    public void agregarIntegrante(String nombre) {
        if (esBanda && integrantes != null) {
            integrantes.add(nombre);
        }
    }
}
