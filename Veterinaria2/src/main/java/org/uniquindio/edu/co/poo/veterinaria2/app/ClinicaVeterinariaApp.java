package org.uniquindio.edu.co.poo.veterinaria2.app;
import org.uniquindio.edu.co.poo.veterinaria2.model.Mascota;
import org.uniquindio.edu.co.poo.veterinaria2.model.Paciente;

import java.util.*;
import javax.swing.JOptionPane;

public class ClinicaVeterinariaApp {
    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final List<Mascota> mascotas = new ArrayList<>();

    public static void main(String[] args) {
        registrarMascota();
        mostrarMascotasMayoresA10();
    }

    private static void registrarMascota() {
        String id = JOptionPane.showInputDialog("ID de la mascota:");
        String nombre = JOptionPane.showInputDialog("Nombre de la mascota:");
        String especie = JOptionPane.showInputDialog("Especie:");
        String raza = JOptionPane.showInputDialog("Raza:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String genero = JOptionPane.showInputDialog("Género:");
        String color = JOptionPane.showInputDialog("Color:");
        double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso (kg):"));

        boolean idExiste = mascotas.stream().anyMatch(m -> m.getId().equals(id));
        if (idExiste) {
            JOptionPane.showMessageDialog(null, "El ID de la mascota ya existe.");
            return;
        }

        Mascota nuevaMascota = new Mascota(id, nombre, especie, raza, edad, genero, color, peso);
        mascotas.add(nuevaMascota);
    }

    private static void mostrarMascotasMayoresA10() {
        StringBuilder mensaje = new StringBuilder("Mascotas mayores a 10 años:\n");

        for (Mascota m : mascotas) {
            if (m.getEdad() > 10) {
                mensaje.append("- ").append(m.getNombre()).append(" (" + m.getEdad() + " años)\n");
            }
        }

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Listado de Mascotas Mayores", JOptionPane.INFORMATION_MESSAGE);
    }
}
