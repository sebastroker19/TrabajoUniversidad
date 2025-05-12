package org.uniquindio.edu.co.poo.universidad.app;
import java.util.*;
import org.uniquindio.edu.co.poo.universidad.model.Estudiante;
import org.uniquindio.edu.co.poo.universidad.model.Nota;

public class GestorNota {
    private List<Estudiante> estudiantes = new ArrayList<>();

    public void registrarEstudiante(String nombre, String id) {
        estudiantes.add(new Estudiante(nombre, id));
    }

    public boolean agregarNota(String idEstudiante, String nombreNota, double valor) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(idEstudiante)) {
                return e.agregarNota(new Nota(nombreNota, valor));
            }
        }
        return false;
    }

    public Estudiante obtenerMejorPromedio() {
        Estudiante mejor = null;
        double max = 0;
        for (Estudiante e : estudiantes) {
            double promedio = e.calcularPromedio();
            if (promedio > max) {
                max = promedio;
                mejor = e;
            }
        }
        return mejor;
    }

    public void actualizarNota(String idEstudiante, int indiceNota, double nuevoValor) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(idEstudiante)) {
                e.actualizarNota(indiceNota, nuevoValor);
            }
        }
    }

    public void mostrarNotasYPromedio(String idEstudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(idEstudiante)) {
                System.out.println("Estudiante: " + e.getNombre());
                int i = 1;
                for (Nota nota : e.getNotas()) {
                    System.out.println("Nota " + i++ + ": " + nota.getNombre() + " - " + nota.getValor());
                }
                System.out.println("Promedio: " + e.calcularPromedio());
            }
        }
    }

    public void mostrarEstudiantesConPromedio() {
        for (Estudiante e : estudiantes) {
            System.out.println("Estudiante: " + e.getNombre() + ", Promedio: " + e.calcularPromedio());
        }
    }

    // Ejercicio 1
    public void prediccionAprobacion() {
        for (Estudiante e : estudiantes) {
            double promedioActual = e.calcularPromedio();
            if (promedioActual < 3.0 && !e.getNotas().isEmpty()) {
                int n = e.getNotas().size();
                double sumaActual = promedioActual * n;
                for (int i = 0; i < n; i++) {
                    double sumaSinNota = sumaActual - e.getNotas().get(i).getValor();
                    double nuevaNotaNecesaria = 3.0 * n - sumaSinNota;
                    if (nuevaNotaNecesaria <= 5.0) {
                        System.out.println("Estudiante: " + e.getNombre() + " puede aprobar si mejora la nota " + (i+1) +
                                " a " + nuevaNotaNecesaria);
                        break;
                    }
                }
            }
        }
    }

    // Ejercicio 2
    public void mejorEstudiantePorNotaEspecifica(int indiceNota) {
        double max = -1;
        Estudiante mejor = null;
        for (Estudiante e : estudiantes) {
            if (e.getNotas().size() >= indiceNota) {
                double valor = e.getNotas().get(indiceNota - 1).getValor();
                if (valor > max) {
                    max = valor;
                    mejor = e;
                }
            }
        }
        if (mejor != null) {
            System.out.println("Mejor estudiante en la nota " + indiceNota + ": " +
                    mejor.getNombre() + " con " + max);
        } else {
            System.out.println("No hay suficiente información para la nota " + indiceNota);
        }
    }
}
