package org.uniquindio.edu.co.poo.universidad.app;

public class MainApp {
    public static void main(String[] args) {
        GestorNota gestor = new GestorNota();

        gestor.registrarEstudiante("Carlos", "001");
        gestor.registrarEstudiante("Ana", "002");

        gestor.agregarNota("001", "Nota1", 2.0);
        gestor.agregarNota("001", "Nota2", 2.5);
        gestor.agregarNota("001", "Nota3", 3.0);

        gestor.agregarNota("002", "Nota1", 4.5);
        gestor.agregarNota("002", "Nota2", 3.5);
        gestor.agregarNota("002", "Nota3", 4.0);

        System.out.println("\n-- Promedios --");
        gestor.mostrarEstudiantesConPromedio();

        System.out.println("\n-- Mejor estudiante --");
        System.out.println("Nombre: " + gestor.obtenerMejorPromedio().getNombre());

        System.out.println("\n-- Predicción de Aprobación --");
        gestor.prediccionAprobacion();

        System.out.println("\n-- Mejor Estudiante por Nota Específica (2) --");
        gestor.mejorEstudiantePorNotaEspecifica(2);
    }
}
