package ConsultorioOdontologico.edu.co.model;
import ConsultorioOdontologico.edu.co.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Consultorio {
    private List<Odontologo> odontologos;
    private List<Paciente> pacientes;

    public Consultorio() {
        odontologos = new ArrayList<>();
        pacientes = new ArrayList<>();
    }

    public void agregarOdontologo(int idMedico, String nombre, String licencia, String especialidad) {
        Odontologo nuevoOdontologo = new Odontologo(idMedico, nombre, licencia, especialidad);
        odontologos.add(nuevoOdontologo);
    }

    public void agregarPaciente(String numeroHistoria, String nombre, int edad, String telefono, String direccion, String fechaUltimaConsulta, int tratamientosRealizados) {
        Paciente nuevoPaciente = new Paciente(numeroHistoria, nombre, edad, telefono, direccion, fechaUltimaConsulta, tratamientosRealizados);
        pacientes.add(nuevoPaciente);
    }

    public List<Paciente> listarPacientesConTratamientos() {
        List<Paciente> pacientesConRevisiones = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (paciente.getTratamientosRealizados() > 5) {
                pacientesConRevisiones.add(paciente);
            }
        }
        return pacientesConRevisiones;
    }
}
