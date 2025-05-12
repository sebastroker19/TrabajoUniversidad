package org.uniquindio.edu.co.poo.app;

import org.uniquindio.edu.co.poo.model.Estudiante;
import java.util.ArrayList;

public class Universidad {
    private ArrayList<Estudiante> estudiantes;

    public Universidad() {
        estudiantes = new ArrayList<>();
    }

    public void registrarEstudiante(String nombre, String id) {
        estudiantes.add(new Estudiante(nombre, id));
    }

    public Estudiante obtenerEstudianteConMayorPromedio() {
        Estudiante mejorEstudiante = null;
        double maxPromedio = -1;

        for (Estudiante estudiante : estudiantes) {
            double promedio = estudiante.calcularPromedio();
            if (promedio > maxPromedio) {
                maxPromedio = promedio;
                mejorEstudiante = estudiante;
            }
        }
        return mejorEstudiante;
    }

    public void mostrarEstudiantes() {
        StringBuilder sb = new StringBuilder();
        for (Estudiante estudiante : estudiantes) {
            sb.append(estudiante.getNombre())
                    .append(" (ID: ").append(estudiante.getId())
                    .append(") - Promedio: ").append(estudiante.calcularPromedio())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Estudiantes", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método principal para ejecutar el sistema
    public static void main(String[] args) {
        Universidad universidad = new Universidad();
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog("1. Registrar Estudiante\n2. Agregar Nota\n3. Mostrar Estudiantes\n4. Estudiante con Mayor Promedio\n5. Salir");
            switch (opcion) {
                case "1":
                    String nombre = JOptionPane.showInputDialog("Nombre del estudiante:");
                    String id = JOptionPane.showInputDialog("ID del estudiante:");
                    universidad.registrarEstudiante(nombre, id);
                    break;
                case "2":
                    // Lógica para agregar nota
                    break;
                case "3":
                    universidad.mostrarEstudiantes();
                    break;
                case "4":
                    Estudiante mejorEstudiante = universidad.obtenerEstudianteConMayorPromedio();
                    if (mejorEstudiante != null) {
                        JOptionPane.showMessageDialog(null, "Estudiante con mayor promedio: " + mejorEstudiante.getNombre() + " - Promedio: " + mejorEstudiante.calcularPromedio());
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
                    }
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (!opcion.equals("5"));
    }
}

