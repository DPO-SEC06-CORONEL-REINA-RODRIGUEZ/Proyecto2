/*
 *  EquipoReal modela el equipo de fútbol que va a ligas y compite en la realidad
 */
package model;

import java.io.Serializable;
import java.util.HashMap;


public class EquipoReal implements Serializable{
	

	private static final long serialVersionUID = 3388149540146352553L;
	private String nombre;
	private HashMap<String, JugadorReal> equipoReal = new HashMap<>();
	
	/**
	 * Instantiates a new equipo real.
	 *
	 * @param nombre the nombre
	 * @param equipo the equipo
	 */
	public EquipoReal(String nombre, HashMap<String, JugadorReal> equipo)
	{
		this.nombre = nombre;
		this.equipoReal = equipo;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EquipoReal [nombre=" + nombre + ", equipoReal=" + equipoReal + "]";
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre()
	{
		return this.nombre;
	}
	
	/**
	 * Gets the equipo real.
	 *
	 * @return the equipo real
	 */
	public HashMap<String, JugadorReal> getEquipoReal() {
		return equipoReal;
	}
}

