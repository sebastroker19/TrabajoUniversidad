package org.uniquindio.edu.co.poo.universidad.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private String id;
    private List<Nota> notas;

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.notas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public boolean agregarNota(Nota nota) {
        if (notas.size() < 5) {
            notas.add(nota);
            return true;
        }
        return false;
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getValor();
        }
        return suma / notas.size();
    }

    public void actualizarNota(int indice, double nuevoValor) {
        if (indice >= 0 && indice < notas.size()) {
            notas.get(indice).setValor(nuevoValor);
        }
    }
}
