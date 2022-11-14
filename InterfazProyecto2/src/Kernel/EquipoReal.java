package Kernel;
import java.util.HashMap;
public class EquipoReal {

		private String nombre;
		private HashMap<String, JugadorReal> equipoReal = new HashMap<>();
		//Cambios UML; equipo real tiene un hashmap de <nombre, JugadorReal>
		
		public EquipoReal(String nombre, HashMap<String, JugadorReal> equipo)
		{
			this.nombre = nombre;
			this.equipoReal = equipo;
		}
		public String getNombre()
		{
			return this.nombre;
		}
		public HashMap<String, JugadorReal> getEquipoReal() {
			return equipoReal;
		}
}
