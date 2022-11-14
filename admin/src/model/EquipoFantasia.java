package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class EquipoFantasia implements Serializable{
	
	private String nombre;
	private Propietario propietario;
	private int totalPuntos;
	private Temporada temporada;
	HashMap<String, ArrayList<String>> hJugadores;
	private int presupuesto_restante;
	
	private static final long serialVersionUID = -670034583416353181L;

	public EquipoFantasia(String nombre, Propietario propietario, HashMap<String, ArrayList<String>> hJugadores, Temporada t, int presupuesto_restante) {
		this.nombre = nombre;
		this.totalPuntos = 0;
		this.propietario = propietario;
		this.hJugadores = hJugadores;
		this.temporada = t;
		this.presupuesto_restante = presupuesto_restante;

	}
	
	
	public String getNombre() {
		return this.nombre;
	}

}
