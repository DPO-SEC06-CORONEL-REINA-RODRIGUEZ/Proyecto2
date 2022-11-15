/*
 *  Esta clase representa el jugador real que participa en las ligas de fútbol real
 */
package model;

import java.io.Serializable;

public class JugadorReal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8531701704450670334L;
	private String name;
	private Posicion posicion;
	private Integer vCompra;
	private Integer vVenta;
	
	
	private int minJugados_Total;
	private int golesRecibidos_Total;
	private int penaltisAnotados_Total;
	private int autoGoles_Total;
	private int golesAnotados_Total;
	private int penaltisDetenidos_Total;
	private int penaltisErrados_Total;
	private int tarjetasAmarillas_Total;
	private int tarjetasRojas_Total;
	
	private int nWins;
	private int nTies;
	private int nLosses;
	private int puntosFantasia;
	
	
	/**
	 * Instantiates a new jugador real.
	 *
	 * @param nombreJugador the nombre jugador
	 * @param position the position
	 * @param vCompra the v compra
	 * @param vVenta the v venta
	 */
	public JugadorReal(String nombreJugador, Posicion position, Integer vCompra, Integer vVenta) {
		name = nombreJugador;
		posicion = position;
		this.vCompra = vCompra;
		this.vVenta = vVenta;
		
	}
	
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {return this.name;}
	
	/**
	 * Actualizar datos.
	 *
	 * @param results the results
	 * @param save the save
	 */
	public void actualizarDatos(String results, Integer[] save) {
		
		if(results.equals("won")){nWins +=1;}
		else if(results.equals("tie")){nTies +=1;}
		else if(results.equals("lost")){nLosses +=1;}
		
		minJugados_Total += save[0];
		golesRecibidos_Total += save[1];
		penaltisAnotados_Total += save[2];
		autoGoles_Total += save[3];
		golesAnotados_Total += save[4];
		penaltisDetenidos_Total += save[5];
		penaltisErrados_Total += save[6];
		tarjetasAmarillas_Total += save[7];
		tarjetasRojas_Total += save[8];
	}
	
	/**
	 * Gets the precio compra.
	 *
	 * @return the precio compra
	 */
	public int getPrecioCompra()
	{
		return this.vCompra;
	}
	
	/**
	 * Gets the posicion.
	 *
	 * @return the posicion
	 */
	public Posicion getPosicion()
	{
		return this.posicion;
	}



	/**
	 * Puntos acumulados.
	 *
	 * @param puntos the puntos
	 */
	public void puntosAcumulados(int puntos) {
		this.puntosFantasia += puntos;
	}



	
	
}
