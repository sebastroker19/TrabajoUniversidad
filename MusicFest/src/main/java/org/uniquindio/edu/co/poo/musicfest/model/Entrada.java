package org.uniquindio.edu.co.poo.musicfest.model;

import java.util.List;

public class Entrada {
    public String tipo;
    public double precio;
    public List<String> beneficios;

    public Entrada(String tipo, double precio, List<String> beneficios) {
        this.tipo = tipo;
        this.precio = precio;
        this.beneficios = beneficios;
    }
}
