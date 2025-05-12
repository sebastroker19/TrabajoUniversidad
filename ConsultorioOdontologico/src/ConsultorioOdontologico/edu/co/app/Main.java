package ConsultorioOdontologico.edu.co.app;
import ConsultorioOdontologico.edu.co.model.Consultorio;
import ConsultorioOdontologico.edu.co.model.Paciente;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Consultorio consultorio = new Consultorio();

        // Agregar odontólogos
        consultorio.agregarOdontologo(1, "Dr. Juan Pérez", "123456", "Odontología General");
        consultorio.agregarOdontologo(2, "Dra. Ana Gómez", "654321", "Ortodoncia");

        // Agregar pacientes
        consultorio.agregarPaciente("001", "Carlos Ruiz", 30, "555-1234", "Calle Falsa 123", "2023-01-15", 6);
        consultorio.agregarPaciente("002", "María López", 25, "555-5678", "Avenida Siempre Viva 742", "2023-02-10", 3);

        // Listar pacientes con más de 5 tratamientos
        List<Paciente> pacientesRevisiones = consultorio.listarPacientesConTratamientos();
        for (Paciente paciente : pacientesRevisiones) {
            System.out.println("Paciente: " + paciente.getNombre() + ", Tratamientos realizados: " + paciente.getTratamientosRealizados());
        }
    }
}



