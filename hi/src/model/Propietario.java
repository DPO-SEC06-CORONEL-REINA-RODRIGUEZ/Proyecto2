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
	
	public Propietario(String nombre, String contraseña) {
		this.nombreUsuario = nombre;
		this.contraseña = contraseña;
	}
	
	
	public boolean tieneAlgunEquipo() {
		boolean rta = true;
		if(hKey_Equipo.isEmpty()) {rta = false;}
		return rta;
	}

	public boolean vacioEquipoEnTemporada(String keyTemporada) throws Exception {
		boolean rta = false;
		EquipoFantasia equipo = hTemporada_Equipo.get(keyTemporada);
		if(equipo != null) {rta = true; throw new Exception("Ya se tiene un equipo para esta temporada");}
		
		return rta;
	}
	
	public void vincularEquipo(EquipoFantasia ef) {
		String keyEf = ef.getNombre();
		hKey_Equipo.put(keyEf, ef);
	}
	
	public String getNombre()
	{
		return nombreUsuario;
	}
	
	
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
	
	
	public EquipoFantasia getEquipo(String key) {
		return hKey_Equipo.get(key);
	}


	public String getContraseña() {
		return contraseña;
	}
}
	