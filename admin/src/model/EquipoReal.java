package model;

import java.io.Serializable;
import java.util.HashMap;


public class EquipoReal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3388149540146352553L;
	private String nombre;
	private HashMap<String, JugadorReal> equipoReal = new HashMap<>();
	
	public EquipoReal(String nombre, HashMap<String, JugadorReal> equipo)
	{
		this.nombre = nombre;
		this.equipoReal = equipo;
	}
	@Override
	public String toString() {
		return "EquipoReal [nombre=" + nombre + ", equipoReal=" + equipoReal + "]";
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public HashMap<String, JugadorReal> getEquipoReal() {
		return equipoReal;
	}
}

