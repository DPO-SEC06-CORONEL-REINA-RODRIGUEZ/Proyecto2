package Kernel;
import java.util.HashMap;

public class EquipoFantasia {

	private String nombre;
	private String propietario;
	private int presupuestoDisponible;
	private int totalPuntos;
	private final static int presupuestoInicial = 2000; //UML CAMBIOS; private final static int
	private final static int puntosCapitanSiGanan = 2;
	private HashMap<String, JugadorReal> plantilla;//UML cambios; new.
	private HashMap<String, JugadorReal> alineacion;
	//PLANTILLLA Y MAPA TIENEN COMO LLAVE NOMBRE DEL JUGADOR
	
	
	
	
	
	//Constructor
	public EquipoFantasia(String nombre, String propietario, HashMap<String, JugadorReal> hm) {
		this.nombre = nombre;
		this.presupuestoDisponible = presupuestoInicial;
		this.totalPuntos = 0;
		this.propietario = propietario;
		this.plantilla = hm;

	}
	
	public int getPresupuestoInicial() {
		
		return presupuestoInicial;
	}
	public String getPropietario()
	{
		return this.propietario;
	}
	
	public int getPresupuestoDisponible() {
		
		return this.presupuestoDisponible;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public HashMap<String, JugadorReal> getPlantilla()
	{
		return this.plantilla;
	}
	public void setAlineacion(HashMap<String, JugadorReal> alineacion)
	{
		this.alineacion = alineacion;
	}

	public int getTotalPuntos() {
		return totalPuntos;
	}
	public void setPresupuestoDisponible(int pres) {
		this.presupuestoDisponible = pres;
	}

}
