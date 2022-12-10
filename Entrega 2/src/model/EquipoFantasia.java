/*
 * EquipoFantasia representa el equipo que el propietario conforma y gana puntos
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class EquipoFantasia implements Serializable{
	
	private String nombre;
	private Propietario propietario;
	private int totalPuntos;
	private Temporada temporada;
	private HashMap<String, ArrayList<String>> hJugadores;
	private int presupuesto_restante;
	private HashMap<String, Integer[]> temp_Puntos = new HashMap<>();
	
	private static final int puntosPenaltiAtajadoAr=5;
	private static final int puntosSinGolesRecibidosDefyAr=4;
	private static final int puntosGolDefyArquero=5;
	private static final int puntosGolDelan=4;
	private static final int puntosGolMediocampista=5;
	private static final int puntosAutogol=-2;
	private static final int puntosTarjetaRoja=-3;
	private static final int puntosTarjetaAmarilla=-1;
	private static final int puntosErrarPenalti=-2;
	private static final int puntosAsistencia=3;
	private static final int puntosMas60min=2;
	private static final int puntosHasta60min=1;
	private static final int puntosCapitanVictoria = 5;
		
		
	 private static final int puntosPorHacerMano = -1;
	 private static final int puntosTiroLibre = 1;
	 private static final int puntosGolTiroLibre = 2;
	 private static final int puntosGolTresPartidosSeguidos = 10;
	 private static final int puntosMas60minTresPartidosSeguidos = 5;
	 private static final int puntosCampeon = 10;
	 private static final int puntosSubcampeon = 7;
	 private static final int puntosTercero = 5;
	 private static final int puntosNoPerdioNunca = 10;
	 private static final int puntosGanaronPartidoReal = 15;
	 private static final int puntosJugadoresTitulares60min = 5;
	 private static final int puntosMejorEquipoFantasiaPorFecha = 10;

	                                 
	
	
	private static final long serialVersionUID = -670034583416353181L;
	
	private int fechaPuntosArqueros;
	private int fechaPuntosDefensas;
	private int fechaPuntosMediocampistas;
	private int fechaPuntosDelanteros;

	/**
	 * Instantiates a new equipo fantasia.
	 *
	 * @param nombre the nombre
	 * @param propietario the propietario
	 * @param hJugadores the h jugadores
	 * @param t the temporada
	 * @param presupuesto_restante the presupuesto restante
	 */
	public EquipoFantasia(String nombre, Propietario propietario, HashMap<String, ArrayList<String>> hJugadores, Temporada t, int presupuesto_restante) {
		this.nombre = nombre;
		this.totalPuntos = 0;
		this.propietario = propietario;
		this.hJugadores = hJugadores;
		this.temporada = t;
		this.presupuesto_restante = presupuesto_restante;
		
		this.fechaPuntosArqueros = 0;
	}
	
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}


	/**
	 * Gets the total puntos.
	 *
	 * @return the total puntos
	 */
	public int getTotalPuntos() {
		return totalPuntos;
	}


	/**
	 * Sets the total puntos.
	 *
	 * @param totalPuntos the new total puntos
	 */
	public void setTotalPuntos(int totalPuntos) {
		this.totalPuntos = totalPuntos;
	}


	/**
	 * Gets the presupuesto restante.
	 *
	 * @return the presupuesto restante
	 */
	public int getPresupuesto_restante() {
		return presupuesto_restante;
	}


	/**
	 * Sets the presupuesto restante.
	 *
	 * @param presupuesto_restante the new presupuesto restante
	 */
	public void setPresupuesto_restante(int presupuesto_restante) {
		this.presupuesto_restante = presupuesto_restante;
	}


	/**
	 * Preparar puntos.
	 *
	 * @param jugador the jugador
	 * @param won the won
	 * @param save_ints the save ints
	 */
	public void prepararPuntos(JugadorReal jugador, String won, Integer[] save_ints) {
		
		String n = jugador.getName();
		
		int valido = 1;
		int puntos = 0;
		if(save_ints[0] < 1) {valido = 0;return;}
		else if(save_ints[0] <= 60) {puntos +=puntosHasta60min;}
		else if(save_ints[0] > 60) {puntos +=puntosMas60min;}
		for(int i = save_ints[3]; i > 0; i--) 
		{
			puntos += puntosAutogol;
		}
		for(int i = save_ints[7]; i > 0; i--) 
		{
			puntos += puntosTarjetaAmarilla;
		}
		for(int i = save_ints[8]; i > 0; i--) 
		{
			puntos += puntosTarjetaRoja;
		}
		for(int i = save_ints[6]; i > 0; i--) 
		{
			puntos += puntosErrarPenalti;
		}
		for(int i = save_ints[9]; i > 0; i--) 
		{
			puntos += puntosAsistencia;
		}
		
		
		Posicion x = jugador.getPosicion();
		if(x.equals(Posicion.Arquero))
		{
			for(int i = save_ints[5]; i > 0; i--) 
			{
				puntos += puntosPenaltiAtajadoAr;
			}
			
			if(save_ints[4] == 0) {
				puntos += puntosSinGolesRecibidosDefyAr;
			}
			
			for(int i = save_ints[2]; i > 0; i--) 
			{
				puntos += puntosGolDefyArquero;
			}
			
		}
		else if(x.equals(Posicion.Defensa))
		{
			
			if(save_ints[1] == 0) {
				puntos += puntosSinGolesRecibidosDefyAr;
			}
			
			for(int i = save_ints[4]; i > 0; i--) 
			{
				puntos += puntosGolDefyArquero;
			}
		}
		
		else if(x.equals(Posicion.MedioCampista))
		{
			
			for(int i = save_ints[4]; i > 0; i--) 
			{
				puntos += puntosGolMediocampista;
			}
		}
		else if(x.equals(Posicion.Delantero))
		{
			
			for(int i = save_ints[4]; i > 0; i--) 
			{
				puntos += puntosGolDelan;
			}
		}
		
		if(hJugadores.get("capitan").get(0).equals(n) && won.equals("won"))
		{
			puntos += puntosCapitanVictoria;
		}
		Integer[] y = new Integer[2];
		y[0] = puntos;
		y[1] = valido;
 		temp_Puntos.put(n, y);
 		
		
		
		
		
			
		
		
		
		
	}
	
	/**
	 * Buscar configuracion J.
	 *
	 * @param name the name
	 * @return the string
	 */
	private String buscarConfiguracionJ(String name)
	{
		String rta = "";
		for(Entry<String, ArrayList<String>> set: hJugadores.entrySet())
		{	
			ArrayList<String> lista = set.getValue();
			for(String i : lista) {
				if(i.equals(name)) 
				{
					rta = set.getKey();
					return rta;
				}
			}
		}
		
		return rta;
	}


	/**
	 * Agregar puntos.
	 */
	public void agregarPuntos() {
		
		boolean first_time = true;
		for(Entry<String, Integer[]> set : temp_Puntos.entrySet())
		{
			String config = buscarConfiguracionJ(set.getKey());
			if(first_time && set.getValue()[1] == 0)
			{
				if(config.equals("arqueroT"))
				{
					String name_suplente = hJugadores.get("arqueroS").get(0);
					int puntos = temp_Puntos.get(name_suplente)[0];
					
					totalPuntos += puntos;
					fechaPuntosArqueros += puntos;
					first_time = false;
					
				}
				else if(config.equals("delanterosT"))
				{
					String name_suplente = hJugadores.get("delanteroS").get(0);
					int puntos = temp_Puntos.get(name_suplente)[0];
					
					totalPuntos += puntos;
					fechaPuntosArqueros += puntos;
					first_time = false;
				}
				else if(config.equals("mediocampistasT"))
				{
					String name_suplente = hJugadores.get("mediocampistaT").get(0);
					int puntos = temp_Puntos.get(name_suplente)[0];
					
					totalPuntos += puntos;
					fechaPuntosArqueros += puntos;
					first_time = false;
				}
				else if(config.equals("defensasT"))
				{
					String name_suplente = hJugadores.get("defensaS").get(0);
					int puntos = temp_Puntos.get(name_suplente)[0];
					
					totalPuntos += puntos;
					fechaPuntosArqueros += puntos;
					first_time = false;
				}
			}
			if(set.getValue()[1] == 1)
			{
				if(config.equals("arqueroT"))
				{
					String name_suplente = hJugadores.get("arqueroT").get(0);
					int puntos = temp_Puntos.get(name_suplente)[0];
					
					totalPuntos += puntos;
					fechaPuntosArqueros += puntos;
					
				}
				else if(config.equals("delanterosT"))
				{	
					
					ArrayList<String> lista = hJugadores.get("delanterosT");
					for(String s : lista) 
					{
						if(s.equals(set.getKey()))
						{
							int puntos = temp_Puntos.get(set.getKey())[0];
							
							totalPuntos += puntos;
							fechaPuntosDelanteros += puntos;
						}
					}
					
				}
				else if(config.equals("mediocampistasT"))
				{
					ArrayList<String> lista = hJugadores.get("mediocampistasT");
					for(String s : lista) 
					{
						if(s.equals(set.getKey()))
						{
							int puntos = temp_Puntos.get(set.getKey())[0];
							
							totalPuntos += puntos;
							fechaPuntosMediocampistas += puntos;
						}
					}
				}
				else if(config.equals("defensasT"))
				{
					ArrayList<String> lista = hJugadores.get("defensasT");
					for(String s : lista) 
					{
						if(s.equals(set.getKey()))
						{
							int puntos = temp_Puntos.get(set.getKey())[0];
							
							totalPuntos += puntos;
							fechaPuntosDefensas += puntos;
						}
					}
				}
			}
			
		}
		
		
	}
	
	/**
	 * Gets the h jugadores.
	 *
	 * @return the h jugadores
	 */
	public HashMap<String, ArrayList<String>> gethJugadores()
	{
		return this.hJugadores;
	}
	

}
