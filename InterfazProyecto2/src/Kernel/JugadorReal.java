package Kernel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Model.ControladorTemporada;

public class JugadorReal {

	private String name;
	private Posicion posicion;
	private int valorCompra;
	private int valorVender;
	private int valorPleno;
	private int puntos;
	private boolean shop=true;
	private ReporteJugador reporte;//Como agrego esto al constructor
	private HashMap<String, JugadorReal> jugadoresReales = new HashMap<>();
	
	HashMap<String, Temporada> temporadas = ControladorTemporada.getTemporadasActuales();
	HashMap<String, JugadorReal> JugReals = ControladorTemporada.JugadoresMap;
	
	
	
	
	
	//Cambios UML; jugadores reales contiene a todos los jugadores reales
	
	//Constructor for admin
	public JugadorReal(String nombre, Posicion posicion, int vCompra, int vVenta, int vPleno)
	{
		this.name = nombre;
		this.posicion = posicion;
		this.valorCompra = vCompra;
		this.valorVender = vVenta;
		this.valorPleno = vPleno;
		jugadoresReales.put(nombre, this);
	}
	//Constructor for controladorFantasia. Jugadores Fantasia constructor
	public JugadorReal(String nombre, String temp) throws Exception
	{
		if (temporadas.get(temp)==null) {
			throw new Exception("La temporada "+temp+" no existe o no es actual(solo planeada).\n");
		}
		else if (JugReals.containsKey(nombre)==false) {
			throw new Exception("El jugador "+nombre+" No existe en esta temporada\n");
			}
		JugadorReal jug = JugReals.get(nombre);
		this.name = nombre;
		this.posicion = jug.getPosicionObj();
		this.valorCompra = jug.getValorCompra();
		this.reporte = jug.getReporte();
		this.valorVender = jug.getValorVenta();
		this.valorPleno = jug.getValorPleno();}
			
	
	public int getValorCompra()//UML cambios; new
	{
		return this.valorCompra;
	}
	
	public int getValorVenta()//UML cambios; new
	{
		return this.valorVender;
	}
	
	public int getValorPleno() 
	{
		return this.valorPleno;
	}
	
	public String getName()//UML cambios; new
	{
		return this.name;
	}
	
	public Posicion getPosicionObj() 
	{
		return this.posicion;
	}
	
	public String getPosicion() 
	{
		return this.posicion.name();
	}
	
	public ReporteJugador getReporte() {
		return reporte;
	}
	public boolean existeJugadorReal(String nombre)
	{
		boolean b = false;
		if (jugadoresReales.get(nombre)!=null) {b=true;}
		return b;
	}
	public JugadorReal getJugadorReal(String nombre)
	{
		return jugadoresReales.get(nombre);
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public boolean isShop() {
		return this.shop;
	}
	public void setShop(boolean shop) {
		this.shop = shop;
	}
}