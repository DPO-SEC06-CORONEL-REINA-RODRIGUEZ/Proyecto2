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
	
	public PartidoReal(String ID,Date date1, String hora, EquipoReal vis, EquipoReal lcl)
	{
		this.id = ID;
		this.dia = date1;
		this.hora = hora;
		this.visitante = vis;
		this.local = lcl;
		cargado = false;
		
	}
	
	public String getID()
	{
		return id;
	}
	
	public void resultadoCargado() throws Exception
	{	
		if(cargado == false) {cargado = true;}
		else {throw new Exception("Este partido ya se le han registrado resultados");}
	}
	
	public boolean getCarga() 
	{
		return this.cargado;
	}
	
	
}
