package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Kernel.EquipoFantasia;
import Kernel.JugadorReal;

public class ControladorVentas {
	private HashMap<String, JugadorReal> JugReals = ControladorTemporada.JugadoresMap;

	
	
	public boolean comprarJugador(String prop, String jug, String equipo, HashMap<String, ArrayList<EquipoFantasia>> EquiposDeFantasia, String ideal) throws Exception {
		boolean desea = false;
		
		int saldo=0; 
		boolean c=false;
		String equipoName="";
		EquipoFantasia equipoProp=null;
		ArrayList<EquipoFantasia>ListaEquiposDelPropietario = EquiposDeFantasia.get(prop);
		
		if (ListaEquiposDelPropietario==null) {throw new Exception("Error: Aun no has creado ningun equipo de fantasia\n");}
    	for (EquipoFantasia eq: ListaEquiposDelPropietario) {
			if (eq.getNombre().equals(equipo)) {
				equipoName = eq.getNombre();
				equipoProp = eq;
				c=true;
				saldo = eq.getPresupuestoDisponible();
				if (eq.getPlantilla().containsKey(jug)==true) {
					throw new Exception("\nNo puedes comprar un jugador que ya te pertenezca\n");}}
			}
    	
    	if (c=false) {throw new Exception("Error: El equipo de fantasia que ingresaste no existe.");}
    	else if (JugReals.containsKey(jug)==false) {throw new Exception("Error: El jugador no existe.\n");}
		JugadorReal jugador = JugReals.get(jug);
		if (jugador.isShop()==false) {throw new Exception("Error: El jugador no esta a la venta.\n");}
		System.out.println("INFORMACION DEL JUGADOR:\n");
		System.out.println("Nombre: "+jugador.getName());
		System.out.println("Posicion: "+jugador.getPosicion());
		System.out.println("Puntos Actuales: "+jugador.getPuntos());
		System.out.println("Precio: "+jugador.getValorVenta());
		System.out.println("Reporte: "+jugador.getReporte());
    	
		
		System.out.println("\nTu saldo disponible es:"+saldo);
		System.out.println("Tu nuevo saldo sera: "+(saldo-jugador.getValorVenta()));
		
		Scanner scan = new Scanner(System.in);
		System.out.println("¿Deseas comprarlo?: (si/no)");
		String answer = scan.nextLine();
		if (answer.equals("si")) {
			if (!(ideal.equals(jugador.getPosicion()))) {throw new Exception("Error: Posición no coincide");}
			desea = true;
			if ((saldo-jugador.getPuntos())<0) {throw new Exception("Error: No tienes saldo suficiente");}
			System.out.println("Felicidades, el jugador "+jugador.getName()+" ahora pertenece a la plantilla de tu Equipo de fantasia: "+equipoName+"!\n");
			equipoProp.getPlantilla().put(jugador.getName(), jugador);
			equipoProp.setPresupuestoDisponible(saldo-jugador.getValorVenta());
			;}
			jugador.setShop(false);
	
		return desea;
	}

	public void venderJugador(String prop, String equipo,HashMap<String, ArrayList<EquipoFantasia>> EquiposDeFantasia) throws Exception{
		boolean c=false;
		EquipoFantasia equipoF=null;
		ArrayList<EquipoFantasia>ListaEquiposDelPropietario = EquiposDeFantasia.get(prop);
		if (ListaEquiposDelPropietario==null) {throw new Exception("Error: Aun no has creado ningun equipo de fantasia\n");}
    	for (EquipoFantasia eq: ListaEquiposDelPropietario) {
			if (eq.getNombre().equals(equipo)) {
				equipoF = eq; 
				HashMap<String, JugadorReal> jugadores = eq.getPlantilla();
				c = true;
				for (String key: jugadores.keySet()) {
					System.out.println("- "+jugadores.get(key).getName());
				}
			}
			}
    	if (c==false) {throw new Exception("Error: el equipo ingresado no existe");}
    	Scanner scan = new Scanner(System.in);
    	System.out.println("\nIngresa por favor el nombre del jugador que vas a vender: ");
    	String jugadorS = scan.nextLine();
    	if (equipoF.getPlantilla().get(jugadorS)==null) {throw new Exception("Error: El jugador no existe en tu plantilla");}
    	JugadorReal jugador = equipoF.getPlantilla().get(jugadorS);
    	
    	System.out.println("\nINFORMACION DEL JUGADOR:\n");
		System.out.println("Nombre: "+jugador.getName());
		System.out.println("Posicion: "+jugador.getPosicion());
		System.out.println("Puntos Actuales: "+jugador.getPuntos());
		System.out.println("Precio de venta: "+jugador.getValorVenta());
		System.out.println("Reporte: "+jugador.getReporte());
		
		Scanner scan1 = new Scanner(System.in);
		System.out.println("¿Deseas venderlo?: (si/no)");
		String answer = scan1.nextLine();
		if (answer.equals("si")) {
			
			
			jugador.setShop(true);
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("Ahora, deberías comprar un jugador en la posición " +jugador.getPosicion());
			System.out.println("\nIngresa por favor el nombre del jugador que vas a comprar: ");
			String jugX = scanner1.nextLine();
			String ideal = jugador.getPosicion();
			boolean resultado = comprarJugador(prop, jugX, equipo, EquiposDeFantasia, ideal);
			if(resultado == true) {equipoF.getPlantilla().remove(jugadorS);System.out.println("Felicidades, el jugador "+jugador.getName()+" ha sido removido de tu plantilla y ahora esta a la venta y ha sido reemplazado por "+jugX+"!\n");}
			
			;}
	}
	
}
