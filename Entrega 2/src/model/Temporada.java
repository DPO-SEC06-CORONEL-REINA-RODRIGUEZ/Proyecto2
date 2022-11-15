/*
 *  Temporada, es la unidad que agrupa jugadores, partidos, equipos y fechas en una entidad unitaria.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;


public class Temporada implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7714006077458043185L;
	private Date fechaInicio;
	private Fecha fechaActual;
	private ArrayList<Fecha> fechas = new ArrayList<>();
	//private HashMap<String,Fecha> fechas = new HashMap<>(); 
	private ArrayList<EquipoFantasia> equipos = new ArrayList<>();
	private HashMap<String,PartidoReal> partidos = new HashMap<>(); 
	private String key;
	private static final int presupuesto_equipos = 2000;
	
	
	/**
	 * Instantiates a new temporada.
	 *
	 * @param lista_fechas the lista fechas
	 * @param inicio the inicio
	 * @param key the key
	 * @param pPartidos the partidos
	 */
	public Temporada(ArrayList<Fecha> lista_fechas, Date inicio, String key, HashMap<String,PartidoReal> pPartidos)
	{
		this.fechaInicio = inicio;
		this.fechaActual = lista_fechas.get(0);
		this.fechas = lista_fechas;
		this.key = key;
		this.partidos = pPartidos;
		
	}
	

	
	/**
	 * Partido registrado.
	 *
	 * @param keyPartido the key partido
	 * @throws Exception the exception
	 */
	public void partidoRegistrado(String keyPartido) throws Exception {
		PartidoReal partReal = partidos.get(keyPartido);
		if(partReal.getCarga()==true) {throw new Exception("Para este partido ya se le han cargado resultados");};
	}
	
	/**
	 * Siguiente fecha.
	 *
	 * @throws Exception the exception
	 */
	public void siguienteFecha() throws Exception
	{
		for(int i =0;i<fechas.size();i++)
		{
			if(fechas.get(i).equals(fechaActual)) 
			{
				if(i == fechas.size() -1) {throw new Exception("Esta es la última fecha de la temporada");}
				
				fechaActual = fechas.get(i+1);
				break;
			}
		}
	}
	
	/**
	 * Revisar fin temporada.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean revisarFinTemporada() throws Exception {
		boolean rta = true;
		for (Entry<String, PartidoReal> set :
            partidos.entrySet()) 
		{
           PartidoReal partidos = set.getValue();
          
           if(!partidos.getCarga()) {rta = false; throw new Exception("Hay algún partido al que no se le ha cargado el resultado");}
		}
		return rta;
	}
	
	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public Date getFechaInicio()
	{
		return this.fechaInicio;
	}
	
	/**
	 * Gets the date actual.
	 *
	 * @return the date actual
	 */
	public Date getDateActual()
	{
		return fechaActual.getFechaInicio();
	}
	
	/**
	 * Gets the fecha actual.
	 *
	 * @return the fecha actual
	 */
	public Fecha getFechaActual()
	{
		return fechaActual;
	}
	
	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public ArrayList<Fecha> getFechas()
	{
		return this.fechas;
	}
	
	/**
	 * Gets the equipos.
	 *
	 * @return the equipos
	 */
	public ArrayList<EquipoFantasia> getEquipos()
	{
		return this.equipos;
	}
	
	/**
	 * Gets the partido.
	 *
	 * @param keyPartido the key partido
	 * @return the partido
	 */
	public PartidoReal getPartido(String keyPartido)
	{
		return partidos.get(keyPartido);
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}
	
	/**
	 * Gets the presupuesto.
	 *
	 * @return the presupuesto
	 */
	public static int getPresupuesto() {
		return presupuesto_equipos;
	}

}

