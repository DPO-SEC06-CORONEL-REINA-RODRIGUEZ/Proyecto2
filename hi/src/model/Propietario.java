/*
 *  Usuario que no es administrador y puede tener equipos de fantasía
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Propietario extends Usuario implements Serializable{
	
	/**
	 * 
	 */
	private String nombreUsuario;
	private String contraseña;
	private static final long serialVersionUID = -4567774723691544076L;
	private HashMap<String, EquipoFantasia> hTemporada_Equipo = new HashMap<>();
	private HashMap<String, EquipoFantasia> hKey_Equipo = new HashMap<>();
	
	/**
	 * Instantiates a new propietario.
	 *
	 * @param nombre the nombre
	 * @param contraseña the contraseña
	 */
	public Propietario(String nombre, String contraseña) {
		this.nombreUsuario = nombre;
		this.contraseña = contraseña;
	}
	
	
	/**
	 * Tiene algun equipo.
	 *
	 * @return true, if successful
	 */
	public boolean tieneAlgunEquipo() {
		boolean rta = true;
		if(hKey_Equipo.isEmpty()) {rta = false;}
		return rta;
	}

	/**
	 * Vacio equipo en temporada.
	 *
	 * @param keyTemporada the key temporada
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean vacioEquipoEnTemporada(String keyTemporada) throws Exception {
		boolean rta = false;
		EquipoFantasia equipo = hTemporada_Equipo.get(keyTemporada);
		if(equipo != null) {rta = true; throw new Exception("Ya se tiene un equipo para esta temporada");}
		
		return rta;
	}
	
	/**
	 * Vincular equipo.
	 *
	 * @param ef the ef
	 */
	public void vincularEquipo(EquipoFantasia ef) {
		String keyEf = ef.getNombre();
		hKey_Equipo.put(keyEf, ef);
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre()
	{
		return nombreUsuario;
	}
	
	
	/**
	 * Gets the ID equipos.
	 *
	 * @return the ID equipos
	 */
	public String[] getIDEquipos()
	{	
		ArrayList<String> temp = new ArrayList<>();
		for(Entry<String, EquipoFantasia> set: hKey_Equipo.entrySet())
		{
			temp.add(set.getKey());
		}
		int s = temp.size();
		if ( s == 0) {s = 1;}
		 String[] str = new String[s];
		 
	        for (int i = 0; i < temp.size(); i++) 
	        {
	            str[i] = temp.get(i);
	        }
		return str;
	}
	
	
	/**
	 * Gets the equipo.
	 *
	 * @param key the key
	 * @return the equipo
	 */
	public EquipoFantasia getEquipo(String key) {
		return hKey_Equipo.get(key);
	}


	/**
	 * Gets the contraseña.
	 *
	 * @return the contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}
}
	