package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Fecha implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6605144169894336082L;
	private Date fechaInicio;
	private boolean yaInicio = false;
	private ArrayList<PartidoReal>partidos = new ArrayList<>();
	
	public Fecha(ArrayList<PartidoReal> partidos, Date fecha)
	{
		this.partidos = partidos;
		this.fechaInicio = fecha;
	}
	
	public Fecha() {
	}

	public Date getFechaInicio()
	{
		return this.fechaInicio;
	}
	public String[] getIDPartidos()
	{	
		int s = partidos.size();
		if ( s == 0) {s = 1;}
		 String[] str = new String[s];
		 
	        for (int i = 0; i < partidos.size(); i++) 
	        {
	            PartidoReal partido = partidos.get(i);
	            str[i] = partido.getID();
	        }
		return str;
	}
	public boolean checkPartidosCargados() throws Exception {
		boolean rta = true;
		for(PartidoReal pr : partidos) {
			if (pr.getCarga()==false) 
			{rta = false;
			throw new Exception("Hay un partido al que todavía no se le han registrado resultados");}
			
			
		}
		return rta;
	}
	

	public boolean isYaInicio() {
		return yaInicio;
	}

	public void setYaInicio(boolean yaInicio) {
		this.yaInicio = yaInicio;
	}

	public int compareTo(Fecha o2) {
		if(this.getFechaInicio().before(o2.getFechaInicio())) {return -1;}
		else if(this.getFechaInicio().after(o2.getFechaInicio())) {return 1;}
		return 0;
	}
}
