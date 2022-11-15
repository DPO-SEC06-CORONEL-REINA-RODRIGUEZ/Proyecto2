/*
 * Partido que ocurrió/ocurrirá en la temporada en cuestión
 */
package model;

import java.io.Serializable;
import java.util.Date;

public class PartidoReal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1872921066019251620L;
	private String id;
	private Date dia;
	private String hora;
	private String marcador;
	private EquipoReal visitante;
	private EquipoReal local;
	private boolean cargado;
	
	/**
	 * Instantiates a new partido real.
	 *
	 * @param ID the id
	 * @param date1 the date 1
	 * @param hora the hora
	 * @param vis the vis
	 * @param lcl the lcl
	 */
	public PartidoReal(String ID,Date date1, String hora, EquipoReal vis, EquipoReal lcl)
	{
		this.id = ID;
		this.dia = date1;
		this.hora = hora;
		this.visitante = vis;
		this.local = lcl;
		cargado = false;
		
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID()
	{
		return id;
	}
	
	/**
	 * Resultado cargado.
	 *
	 * @throws Exception the exception
	 */
	public void resultadoCargado() throws Exception
	{	
		if(cargado == false) {cargado = true;}
		else {throw new Exception("Este partido ya se le han registrado resultados");}
	}
	
	/**
	 * Gets the carga.
	 *
	 * @return the carga
	 */
	public boolean getCarga() 
	{
		return this.cargado;
	}
	
	
}
