package org.uniquindio.edu.co.poo.model;
import java.util.ArrayList;


public class Estudiante {
    private String nombre;
    private String id;
    private ArrayList<Nota> notas;

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

    public void agregarNota(String nombreNota, double valor) {
        if (notas.size() < 5) {
            notas.add(new Nota(nombreNota, valor));
        } else {
            System.out.println("El estudiante ya tiene 5 notas registradas.");
        }
    }

    public void actualizarNota(int index, double nuevoValor) {
        if (index >= 0 && index < notas.size()) {
            notas.get(index).setValor(nuevoValor);
        } else {
            System.out.println("Índice de nota inválido.");
        }
    }

    public double calcularPromedio() {
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getValor();
        }
        return notas.isEmpty() ? 0 : suma / notas.size();
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}

