package Model;
import java.util.ArrayList;
import java.util.Collection;

import Kernel.EquipoFantasia;
import Kernel.Fecha;
import Kernel.JugadorReal;
import Kernel.Posicion;
import Kernel.Temporada;
import Kernel.JugadorReal;

import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;

public class ControladorFantasia {
	private Fecha fecha = new Fecha();
	private HashMap<String, ArrayList<EquipoFantasia>> EquiposFantasia = new HashMap<>();
	private ControladorTemporada conT = new ControladorTemporada();
	HashMap<String, Temporada> temp = ControladorTemporada.getTemporadasActuales();
	HashMap<String, JugadorReal> JugReals = ControladorTemporada.JugadoresMap;
	

	public void crearEquipoFantasia(String nombre, String propietario, ArrayList<String> jugadores, String temporada) throws Exception {
		
	
		int total = 0;
		HashMap<String, JugadorReal> hm = new HashMap<>();

		JugadorReal jug = new JugadorReal(jugadores.get(0), temporada);
		Posicion arq = Posicion.Arquero;
		if (jug.getPosicion().equals(arq.name())==false) {throw new Exception("La posicion de "+jugadores.get(0)+" es incorrecta\n");}
		hm.put(jugadores.get(0), jug);
		total += jug.getValorCompra();
		
		JugadorReal jug2 = new JugadorReal(jugadores.get(1), temporada);
		if (jug2.getPosicion().equals(arq.name())==false) {throw new Exception("La posicion de "+jugadores.get(1)+" es incorrecta\n");}
		hm.put(jugadores.get(1), jug2);
		total += jug2.getValorCompra();
		
		JugadorReal jug3 = new JugadorReal(jugadores.get(2), temporada);
		Posicion def = Posicion.Defensa;
		if (jug3.getPosicion().equals(def.name())==false) {throw new Exception("La posicion de "+jugadores.get(2)+" es incorrecta\n");}
		hm.put(jugadores.get(2), jug3);
		total += jug3.getValorCompra();
		
		JugadorReal jug4 = new JugadorReal(jugadores.get(3), temporada);
		if (jug4.getPosicion().equals(def.name())==false) {throw new Exception("La posicion de "+jugadores.get(3)+" es incorrecta\n");}
		hm.put(jugadores.get(3), jug4);
		total += jug4.getValorCompra();
		
		JugadorReal jug5 = new JugadorReal(jugadores.get(4), temporada);
		if (jug5.getPosicion().equals(def.name())==false) {throw new Exception("La posicion de "+jugadores.get(4)+" es incorrecta\n");}
		hm.put(jugadores.get(4), jug5);
		total += jug5.getValorCompra();
		
		JugadorReal jug6 = new JugadorReal(jugadores.get(5), temporada);
		if (jug6.getPosicion().equals(def.name())==false) {throw new Exception("La posicion de "+jugadores.get(5)+" es incorrecta\n");}
		hm.put(jugadores.get(5), jug6);
		total += jug6.getValorCompra();
		
		JugadorReal jug7 = new JugadorReal(jugadores.get(6), temporada);
		if (jug7.getPosicion().equals(def.name())==false) {throw new Exception("La posicion de "+jugadores.get(6)+" es incorrecta\n");}
		hm.put(jugadores.get(6), jug7);
		total += jug7.getValorCompra();
		
		JugadorReal jug8 = new JugadorReal(jugadores.get(7), temporada);
		Posicion mc = Posicion.MedioCampista;
		if (jug8.getPosicion().equals(mc.name())==false) {throw new Exception("La posicion de "+jugadores.get(7)+" es incorrecta\n");}
		hm.put(jugadores.get(7), jug8);
		total += jug8.getValorCompra();
		
		JugadorReal jug9 = new JugadorReal(jugadores.get(8), temporada);
		if (jug9.getPosicion().equals(mc.name())==false) {throw new Exception("La posicion de "+jugadores.get(8)+" es incorrecta\n");}
		hm.put(jugadores.get(8), jug9);
		total += jug9.getValorCompra();
		
		JugadorReal jug10 = new JugadorReal(jugadores.get(9), temporada);
		if (jug10.getPosicion().equals(mc.name())==false) {throw new Exception("La posicion de "+jugadores.get(9)+" es incorrecta\n");}
		hm.put(jugadores.get(9), jug10);
		total += jug10.getValorCompra();
		
		JugadorReal jug11 = new JugadorReal(jugadores.get(10), temporada);
		if (jug11.getPosicion().equals(mc.name())==false) {throw new Exception("La posicion de "+jugadores.get(10)+" es incorrecta\n");}
		hm.put(jugadores.get(10), jug11);
		total += jug11.getValorCompra();
		
		JugadorReal jug12 = new JugadorReal(jugadores.get(11), temporada);
		if (jug12.getPosicion().equals(mc.name())==false) {throw new Exception("La posicion de "+jugadores.get(11)+" es incorrecta\n");}
		hm.put(jugadores.get(11), jug12);
		total += jug12.getValorCompra();
		
		JugadorReal jug13 = new JugadorReal(jugadores.get(12), temporada);
		Posicion del = Posicion.Delantero;
		if (jug13.getPosicion().equals(del.name())==false) {throw new Exception("La posicion de "+jugadores.get(12)+" es incorrecta\n");}
		hm.put(jugadores.get(12), jug13);
		total += jug13.getValorCompra();
		
		JugadorReal jug14 = new JugadorReal(jugadores.get(13), temporada);
		if (jug14.getPosicion().equals(del.name())==false) {throw new Exception("La posicion de "+jugadores.get(13)+" es incorrecta\n");}
		hm.put(jugadores.get(13), jug14);
		total += jug14.getValorCompra();
		
		JugadorReal jug15 = new JugadorReal(jugadores.get(14), temporada);
		if (jug15.getPosicion().equals(del.name())==false) {throw new Exception("La posicion de "+jugadores.get(14)+" es incorrecta\n");}
		hm.put(jugadores.get(14), jug15);
		total += jug15.getValorCompra();
		
		
		
		EquipoFantasia equipo = new EquipoFantasia(nombre, propietario, hm);
		int disponible = equipo.getPresupuestoDisponible() - total;
		if(disponible < 0) {throw new Exception("no te alcanza el presupuesto");}
		equipo.setPresupuestoDisponible(disponible);
		
		//Si el jugador aun no ha creado equipos; creamos su llave en el hashmap
		if (EquiposFantasia.containsKey(propietario)==false) {
			ArrayList<EquipoFantasia> equipos = new ArrayList<>();
			EquiposFantasia.put(propietario, equipos);
		}
		
		//Ahora ponemos el equipo creado a el hashmap de equipos de fantasia para ese propietario
		EquiposFantasia.get(propietario).add(equipo);
		
		Temporada estaTemporada = temp.get(temporada);
		ArrayList<EquipoFantasia> equipos = estaTemporada.getEquipos();
		equipos.add(equipo);
	}

	public void controladorEquipos(String propietario) throws Exception{
		if (fecha.isYaInicio()==true) {throw new Exception("Error: La fecha ya ha pasado\n");}
		if (EquiposFantasia.get(propietario)==null) {throw new Exception("Error: Aun no has creado ningun equipo de fantasia\n");}
		ArrayList<EquipoFantasia>ListaEquiposDelPropietario = EquiposFantasia.get(propietario);
		System.out.println("Estos son los equipos que has creado:\n");
		for (EquipoFantasia eq:ListaEquiposDelPropietario)
		{
			 HashMap<String, JugadorReal> plantilla = eq.getPlantilla();
			 System.out.println("EQUIPO: "+eq.getNombre());
			 System.out.println("Propietario: "+eq.getPropietario());
			 System.out.println("Presupuesto Disponible: "+eq.getPresupuestoDisponible());
			 System.out.println("Puntos Totales: "+eq.getTotalPuntos()+"\n");
			 System.out.println("--------- PLANTILLA DEL EQUIPO ---------");
			 for (String key: plantilla.keySet())
			 {
				 System.out.println("JUGADOR: "+plantilla.get(key).getName());
				 System.out.println("Posicion: "+plantilla.get(key).getPosicion());
				 System.out.println("Valor compra: "+plantilla.get(key).getValorCompra());
				 System.out.println("Valor venta: "+plantilla.get(key).getValorVenta());
				 // El valor pleno es el mismo de compra
				 System.out.println("Reporte Jugador: "+plantilla.get(key).getReporte());
				 //FALTA LO DE LOS PUNTOS DEL JUGADOR
				 System.out.println("Puntos actuales: "+plantilla.get(key).getPuntos()+"\n");
			 }
			 
		}
	}
		
		
	
	public void configurarAlineacion(ArrayList<String> jugadores, EquipoFantasia eq) {

		HashMap<String, JugadorReal> alineacion = new HashMap<>();//Mapa donde se guardara la alineacion
		for (String jug: jugadores)
			{
			alineacion.put(jug, JugReals.get(jug));
			}
				
		eq.setAlineacion(alineacion);//CREAMOS LA ALINEACION

	}

	
	public boolean alineacionValida() {
		
		return false;
	}
	
	public boolean fechaIniciada() {
		
		return false;
	}
	
	public HashMap<String, ArrayList<EquipoFantasia>> getEquiposFant()
	{
		return EquiposFantasia;
	}

	
}
