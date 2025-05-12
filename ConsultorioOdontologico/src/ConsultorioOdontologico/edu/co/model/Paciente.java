package ConsultorioOdontologico.edu.co.model;

public class Paciente {
        private String numeroHistoria;
        private String nombre;
        private int edad;
        private String telefono;
        private String direccion;
        private String fechaUltimaConsulta;
        private int tratamientosRealizados;

        public Paciente(String numeroHistoria, String nombre, int edad, String telefono, String direccion, String fechaUltimaConsulta, int tratamientosRealizados) {
            this.numeroHistoria = numeroHistoria;
            this.nombre = nombre;
            this.edad = edad;
            this.telefono = telefono;
            this.direccion = direccion;
            this.fechaUltimaConsulta = fechaUltimaConsulta;
            this.tratamientosRealizados = tratamientosRealizados;
        }

        public String getNombre() {
            return nombre;
        }

        public int getTratamientosRealizados() {
            return tratamientosRealizados;
        }

}
