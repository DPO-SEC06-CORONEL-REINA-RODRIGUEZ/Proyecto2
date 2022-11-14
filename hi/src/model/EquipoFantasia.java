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
	HashMap<String, ArrayList<String>> hJugadores;
	private int presupuesto_restante;
	
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


	public int getTotalPuntos() {
		return totalPuntos;
	}


	public void setTotalPuntos(int totalPuntos) {
		this.totalPuntos = totalPuntos;
	}


	public int getPresupuesto_restante() {
		return presupuesto_restante;
	}


	public void setPresupuesto_restante(int presupuesto_restante) {
		this.presupuesto_restante = presupuesto_restante;
	}


	public void agregarPuntos(JugadorReal jugador, String won, Integer[] save_ints) {
		
		String n = jugador.getName();
		String r = buscarConfiguracionJ(n);
		if(r.equals("arqueroT") || r.equals("defensasT") || r.equals("mediocampistasT") || r.equals("delanterosT")) 
		{
			int puntos = 0;
			if(save_ints[0] <= 60) {puntos +=puntosHasta60min;}
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
			
			
			totalPuntos += puntos;
			jugador.puntosAcumulados(puntos);
		}
		
		
		
		
	}
	
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
	

}
