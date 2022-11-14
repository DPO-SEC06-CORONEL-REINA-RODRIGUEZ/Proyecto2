package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ControladorFantasia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3556801142513982952L;
	private static ControladorFantasia uniqueObject;
	// <key(String), equipoFantasia>
	private HashMap<String,HashMap<String, EquipoFantasia>> equiposFantasia = new HashMap<>();
	// 
	private HashMap<String, ArrayList<EquipoFantasia>> hJ_equipos = new HashMap<>();
	private static ControladorTemporada ctrlTemporada = ControladorTemporada.getInstance();
	
	private ControladorFantasia() 
	{
	}
	
	
	public static ControladorFantasia getInstance()
	{
		if (uniqueObject == null) {uniqueObject = new ControladorFantasia();}
		return uniqueObject;
	}


	public boolean revisarNombreEquipo(String keyEquipo, String keyTemporada) throws Exception {
		boolean rta = false;
		if(equiposFantasia.get(keyTemporada).containsKey(keyEquipo)) {rta=true;throw new Exception("Este nombre de equipo ya existe, escoje otro");}
		return rta;
	}


	public boolean revisarAlineacion(HashMap<String, ArrayList<String>> itemsSeleccionados) throws Exception {
		boolean rta = true;
		
		if(itemsSeleccionados.get("arqueroT").size() != 1)
		{	
			rta = false;
			throw new Exception("No hay un único arquero titular");
		}
		else if(itemsSeleccionados.get("arqueroS").size() != 1)
		{	
			rta = false;
			throw new Exception("No hay un único arquero suplente");
		}
		else if(itemsSeleccionados.get("defensasT").size() != 4)
		{
			throw new Exception("No hay 4 defensas titulares");
		}
		else if(itemsSeleccionados.get("defensaS").size() != 1)
		{
			throw new Exception("No hay un único defensa suplente");
		}
		else if(itemsSeleccionados.get("mediocampistasT").size() != 4)
		{
			throw new Exception("No hay 4 mediocampistas titulares");
		}
		else if(itemsSeleccionados.get("mediocampistaS").size() != 1)
		{
			throw new Exception("No hay un único mediocampista suplente");
		}
		else if(itemsSeleccionados.get("delanterosT").size() != 2)
		{
			throw new Exception("No hay 4 delanteros titulares");
		}
		else if(itemsSeleccionados.get("delanteroS").size() != 1)
		{
			throw new Exception("No hay un único delantero suplente");
		}
		else if(itemsSeleccionados.get("capitan").size() != 1)
		{
			throw new Exception("No hay un único capitán");
		}
		
		ArrayList<String> arrayCapitan = itemsSeleccionados.get("capitan");
		String nombreCapitan = arrayCapitan.get(0);
		boolean c1 = itemsSeleccionados.get("arqueroT").contains(nombreCapitan);
		boolean c2 = itemsSeleccionados.get("defensasT").contains(nombreCapitan);
		boolean c3 = itemsSeleccionados.get("mediocampistasT").contains(nombreCapitan);
		boolean c4 = itemsSeleccionados.get("delanterosT").contains(nombreCapitan);
		
		if(!(c1 || c2 || c3 || c4)) {rta = false; throw new Exception("El capitán no está dentro de los 11 titulares");}
		
		
		HashMap<String, ArrayList<String>> copia = new HashMap<>();
		copia.put("arqueroT", itemsSeleccionados.get("arqueroT"));
		copia.put("defensasT", itemsSeleccionados.get("defensasT"));
		copia.put("mediocampistasT", itemsSeleccionados.get("mediocampistasT"));
		copia.put("delanterosT", itemsSeleccionados.get("delanterosT"));
		copia.put("delanteroS", itemsSeleccionados.get("delanteroS"));
		copia.put("mediocampistaS", itemsSeleccionados.get("mediocampistaS"));
		copia.put("defensaS", itemsSeleccionados.get("defensaS"));
		copia.put("arqueroS", itemsSeleccionados.get("arqueroS"));
		HashMap<String, Integer> cuenta = new HashMap<>();
		for (Entry<String, ArrayList<String>> set :
			copia.entrySet()) 
		{

          ArrayList<String> nombres = set.getValue();
          for(String nombre : nombres)
          {
        	  Integer n = cuenta.get(nombre);
              if (n == null) {n=0;}
              n +=1;
              cuenta.put(nombre, n);
          }
          
		}
		
		for(Entry<String,Integer> set : cuenta.entrySet())
		{
			if(set.getValue() != 1) {rta = false; throw new Exception("No se permite varias veces el mismo jugador");}
		}
		
		
		return rta;
	}


	public void nuevaTemporada(String key) {
		HashMap<String, EquipoFantasia> hashmap = new HashMap<>();
		this.equiposFantasia.put(key, hashmap);
		
	}




	public static void setObject(ControladorFantasia dataFantasia) {
		uniqueObject = dataFantasia;
		
	}


	public int revisarPresupuesto(HashMap<String, ArrayList<String>> itemsSeleccionados, String kTemporada) throws Exception {
		
		int total_precio = 0;
		ArrayList<String> jugadores = new ArrayList<>();
		for(Entry<String, ArrayList<String>> set: itemsSeleccionados.entrySet())
		{	
			ArrayList<String> values = set.getValue();
			jugadores.addAll(values);

		}
		
		for(String j : jugadores)
		{
			JugadorReal jugador = ctrlTemporada.obtenerJugador(j);
			
			total_precio += jugador.getPrecioCompra();
		}
		
		total_precio -= ctrlTemporada.obtenerJugador(itemsSeleccionados.get("capitan").get(0)).getPrecioCompra();
		
		if(total_precio > getPresupuesto(kTemporada)) { throw new Exception("Se supera el presupuesto del equipo");}
		
		
		return getPresupuesto(kTemporada) -total_precio;
	}
	
	public static int getPresupuesto(String keyTemporada) {
		
		return Temporada.getPresupuesto();
		
	}


	public void vincularCtrlTemporada(ControladorTemporada ctrlTemporada2) {
		ctrlTemporada = ctrlTemporada2;
	}


	public void crearEquipoFantasia(Propietario propietarioActual, String keyEquipo, String keyTemporada,
			HashMap<String, ArrayList<String>> itemsSeleccionados, int presupuesto_restante) 
	{
		Temporada t = ctrlTemporada.getTemporada(keyTemporada);
		EquipoFantasia ef = new EquipoFantasia(keyEquipo, propietarioActual, itemsSeleccionados, t, presupuesto_restante);
		propietarioActual.vincularEquipo(ef);
		GenericAlgorithms.addHashHash(equiposFantasia, keyTemporada, keyEquipo, ef);
		
		for (Entry<String, ArrayList<String>> set: itemsSeleccionados.entrySet())
		{
			 ArrayList<String> lista = set.getValue();
			 for(String nombre : lista)
			 {
				GenericAlgorithms.addToHashList(hJ_equipos, nombre, ef);
			 }
		}
		
		
		
	}
	
	
	
}
