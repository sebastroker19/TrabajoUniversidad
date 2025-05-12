package Batallon.edu.co.model;

public enum EstadoOperativo {
    DISPONIBLE(0),
    EN_MISION(1),
    EN_MANTENIMIENTO(2);


    @SuppressWarnings("unused")
    private final int valor;

    EstadoOperativo(int valor){
        this.valor = valor;
    }
}
