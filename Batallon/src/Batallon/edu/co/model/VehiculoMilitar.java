package Batallon.edu.co.model;

import java.util.ArrayList;
import java.util.Optional;

public abstract class VehiculoMilitar {
    protected String id;
    protected String modelo;
    protected int anioFabricacion;
    protected int kilometraje;
    protected int cantidadMisiones;
    protected EstadoOperativo estadoOperativo;
    protected ArrayList<Mision> listaMisiones;

    public VehiculoMilitar(String id, String modelo, int anioFabricacion, int kilometraje, int cantidadMisiones,
                           EstadoOperativo estadoOperativo, ArrayList<Mision> listaMisiones) {
        this.id = id;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.kilometraje = kilometraje;
        this.cantidadMisiones = cantidadMisiones;
        this.estadoOperativo = estadoOperativo;
        this.listaMisiones = new ArrayList<>();
    }
    //Metodo abstracto
    public abstract String mostrarInformacion();

    public void actualizarInformacion(String nuevoModelo, int nuevoAnio, int nuevoKilometraje, EstadoOperativo nuevoEstado) {
        this.modelo = nuevoModelo;
        this.anioFabricacion = nuevoAnio;
        this.kilometraje = nuevoKilometraje;
        this.estadoOperativo = nuevoEstado;
    }

    public void registrarMision(Mision mision) throws MisionException {
        //Verificar si la misión ya existe
        Optional<Mision> optionalMision = buscarMision(mision.codigo());
        if (optionalMision.isPresent()){
            throw new MisionException("Mision Existe");
        }
        listaMisiones.add(mision);
        cantidadMisiones ++;
    }

    public Optional<Mision> buscarMision(String codigoMision) {
        return listaMisiones.stream()
                .filter(mision -> codigoMision.equals(mision.codigo()))
                .findFirst();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAnioFabricacion() {
        return anioFabricacion;
    }
    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }
    public int getKilometraje() {
        return kilometraje;
    }
    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }
    public int getCantidadMisiones() {
        return cantidadMisiones;
    }
    public void setCantidadMisiones(int cantidadMisiones) {
        this.cantidadMisiones = cantidadMisiones;
    }
    public EstadoOperativo getEstadoOperativo() {
        return estadoOperativo;
    }
    public void setEstadoOperativo(EstadoOperativo estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }
    public ArrayList<Mision> getListaMisiones() {
        return listaMisiones;
    }
    public void setListaMisiones(ArrayList<Mision> listaMisiones) {
        this.listaMisiones = listaMisiones;
    }

}
