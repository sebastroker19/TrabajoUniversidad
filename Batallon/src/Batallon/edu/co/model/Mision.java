package Batallon.edu.co.model;

import java.time.LocalDate;
import java.util.LinkedList;

public record Mision(String codigo, LocalDate fecha, String ubicacion, LinkedList<Personal> listaPersonalAsignado) {



}
