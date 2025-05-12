package org.uniquindio.edu.co.poo.veterinaria.app;
import org.uniquindio.edu.co.poo.veterinaria.model.Odontologo;
import org.uniquindio.edu.co.poo.veterinaria.model.Paciente;

import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;

public class ClinicaDentalApp {
    private static final List<Paciente> pacientes = new ArrayList<>();

    public static void main(String[] args) {
        registrarPaciente();
        mostrarPacientesConMasDeCincoTratamientos();
    }

    private static void registrarPaciente() {
        String numeroHistoria = JOptionPane.showInputDialog("Número de historia clínica:");
        String nombre = JOptionPane.showInputDialog("Nombre completo del paciente:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del paciente:"));
        String telefono = JOptionPane.showInputDialog("Teléfono:");
        String direccion = JOptionPane.showInputDialog("Dirección:");
        int tratamientos = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de tratamientos realizados:"));

        Paciente nuevoPaciente = new Paciente(
                numeroHistoria,
                nombre,
                edad,
                telefono,
                direccion,
                LocalDate.now(),
                tratamientos
        );

        pacientes.add(nuevoPaciente);
    }

    private static void mostrarPacientesConMasDeCincoTratamientos() {
        StringBuilder mensaje = new StringBuilder("Pacientes con más de 5 tratamientos:\n");

        for (Paciente p : pacientes) {
            if (p.getTratamientosRealizados() > 5) {
                mensaje.append("- ").append(p.getNombre()).append(" (")
                        .append(p.getTratamientosRealizados()).append(" tratamientos)\n");
            }
        }

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Listado de Pacientes", JOptionPane.INFORMATION_MESSAGE);
    }
}