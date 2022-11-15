/**
 *  Este es un controlador con responsabilidades al mantenimiento de la temporada
 *  
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;






public class ControladorTemporada implements Serializable{
	
	
	private static final long serialVersionUID = 2842626090867959496L;
	private static ControladorTemporada uniqueObject;
	private static final double porcentajeVenta =  0.97;
	private ControladorTemporada(){}
	
	private  Temporada temporadaActual;
	// <key, Temporada (Planeada)>
	private  HashMap<String,Temporada> temporadasPlaneadas = new HashMap<>();
	// <key, Temporada (Finalizada)>
	private  HashMap<String, Temporada> temporadasFinalizadas = new HashMap<>();
	// <key, JugadorReal>
	private  HashMap<String, JugadorReal> jugadoresMap = new HashMap<>();
	// <posicion, keyJugador>
	private  HashMap<String, ArrayList<String>> hPosicion_Jugadores = new HashMap<>();
	
	private  ControladorFantasia ctrlFantasia = ControladorFantasia.getInstance();
	
	
	
	

	/**
	 * Planear temporada.
	 * <b>pre: jugadores y fechas son archivos .csv</b>
	 * <b>post: carga a los controladores los jugadores, crea una temporada por lo que establece
	 * sus fechas, sus partidos reales, sus equipos reales y sus jugadores reales</b>
	 * @param key the key
	 * @param fechas the fechas
	 * @param jugadores the jugadores
	 * @throws Exception 
	 * 1. si key es nulo
	 * 2. si el key de temporada ya existe
	 * 3. si la fecha del .csv no sigue el formato
	 * 4. si los parámetros del .csv como cantidad de columnas no corresponden con el formato  
	 */
	public void planearTemporada(String key, File fechas, File jugadores) throws Exception
	{	
		if(key == null) {throw new Exception("Por favor ingrese un key no vacío");}
		else if(temporadasPlaneadas.containsKey(key)==true) {throw new Exception("Este key de temporada ya existe");}
		
		BufferedReader brJugadores = new BufferedReader(new FileReader(jugadores));
		
		
		
		
		HashMap<String, ArrayList<JugadorReal>> hEquipo_Jugadores = new HashMap<>(); 
		HashMap<String, EquipoReal> hashNombreequipo = new HashMap<>();
		
		
		
		//Recorremos jugadores creando los JugadoresReales
		
		String linea = brJugadores.readLine();
			   linea = brJugadores.readLine(); //header
		while (linea != null) 
		{
			String[] partes = linea.split(",");
			
			String equipo = partes[0];
			String pos    = partes[2];
			
			Posicion position;
			if(pos.equals("DF")) { position = Posicion.Defensa;}
			else if(pos.equals("MF")) { position = Posicion.MedioCampista;}
			else if(pos.equals("FW")) { position = Posicion.Delantero;}
			else { position = Posicion.Arquero;}
			
			
			
			String nombreJugador = partes[3];
			Integer precio = Integer.decode(partes[9]);
			Integer vCompra = precio;
			Integer vVenta  = (int) (precio*porcentajeVenta);
			
			
			
			
			JugadorReal jugadorR = new JugadorReal(nombreJugador, position, vCompra, vVenta);
			jugadoresMap.put(nombreJugador, jugadorR);
			
			GenericAlgorithms.addToHashList(hEquipo_Jugadores, equipo,jugadorR);
			GenericAlgorithms.addToHashList(hPosicion_Jugadores, pos,nombreJugador);
			
			
			linea = brJugadores.readLine();
		}
		brJugadores.close();
		
		for (Entry<String, ArrayList<JugadorReal>> set :hEquipo_Jugadores.entrySet()) 
		{

           String nombre_equipo = set.getKey();
           ArrayList<JugadorReal> jugadoresA = set.getValue();
           HashMap<String, JugadorReal> objective = new HashMap<>();
           
           for(JugadorReal i:jugadoresA) 
           {
        	   String nombre = i.getName();
        	   objective.put(nombre, i);
           }
           EquipoReal equipo = new EquipoReal(nombre_equipo, objective);
           
           hashNombreequipo.put(nombre_equipo, equipo);
           
       }
		HashMap<Date, ArrayList<PartidoReal>> fecha_partidos = new HashMap<Date, ArrayList<PartidoReal>>(); 
		
		BufferedReader brFechas = new BufferedReader(new FileReader(fechas));
		linea = brFechas.readLine();
		linea = brFechas.readLine();
		boolean first_time = true;
		
		HashMap<String,PartidoReal> hashPartidosReales = new HashMap<>();
		ArrayList<Fecha> lista_fechas = new ArrayList<>();
		Date inicio = new Date();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá
		{
			String[] partes = linea.split(",");
			String decodificar = partes[1];
			String[] partes_datetime = decodificar.split(" ");
			
			
			Date date1 =new SimpleDateFormat("dd/MM/yyyy").parse(partes_datetime[0]);
			if (first_time == true) {inicio = date1;first_time = false;}
			
			String hora = partes_datetime[1];
			
			String nombre_local = partes[3];
			String nombre_away  = partes[4];
			String matchID      = partes[6];
			
			
			EquipoReal local = hashNombreequipo.get(nombre_local);
			
			EquipoReal away = hashNombreequipo.get(nombre_away);
			PartidoReal pr = new PartidoReal(matchID,date1, hora,away,local);
			
			GenericAlgorithms.addToHashList(fecha_partidos,date1,pr);
			hashPartidosReales.put(matchID, pr);
			
			
			
			
			linea = brFechas.readLine();
		}
		
		for (Entry<Date, ArrayList<PartidoReal>> set :
            fecha_partidos.entrySet()) 
		{

           Date fecha = set.getKey();
           ArrayList<PartidoReal> partidos = set.getValue();
          
           Fecha fechaObjeto = new Fecha(partidos, fecha);
           lista_fechas.add(fechaObjeto);
           
		}
		
		lista_fechas.sort(new Comparator<Fecha>() 
		{
		    @Override
		    public int compare(Fecha o1, Fecha o2) {
		        return o1.compareTo(o2);
		    }
		});
		brFechas.close();
		Temporada temporada = new Temporada(lista_fechas, inicio, key, hashPartidosReales);
		temporadasPlaneadas.put(key,temporada);
		ctrlFantasia.nuevaTemporada(key);
		
		
		
		
	}
	
	/**
	 * Sets the temporada actual.
	 * <b>pre: keyTemporada es un key válido</b>
	 * <b>post: establece una temporada actual dentro del controlador</b>
	 * @param keyTemporada the new temporada actual
	 * @throws Exception 
	 * 1. keyTemporada es vacío
	 * 2. ya hay una temporada en progreso
	 */
	public void setTemporadaActual(Object keyTemporada) throws Exception
	{
		if(keyTemporada == null) {throw new Exception("Seleccione un key no vacío por favor");}
		else
		{
			String key = keyTemporada.toString();
			Temporada pTemporada = temporadasPlaneadas.get(key);
			if(temporadaActual == null) {temporadaActual = pTemporada;}
			else {throw new Exception("Ya hay una temporada actual en progreso");}
			
			
		}
	}
	
	/**
	 * Gets the string fecha actual.
	 * <b>pre: existe una temporada actual no nula</b>
	 * <b>post: devuelve la fecha actual de la temporada actual </b>
	 * @return the string fecha actual
	 */
	public String getStringFechaActual()
	{	
	
		String stringDate;
		
		if(temporadaActual == null) {stringDate = "";}
		else
		{	
			Date fecha = temporadaActual.getDateActual();
			stringDate = GenericAlgorithms.formatDate(fecha); 
			
		}
		return stringDate;
	}
	
	
	
	/**
	 * Gets the key temporada actual.
	 * <b>pre: hay una temporada actual no vacía</b>
	 * <b>post: devuelve el key de la temporada actual </b>
	 * @return the key temporada actual
	 */
	public String getKeyTemporadaActual()
	{
		String key;
		if(temporadaActual == null) {key = "";}
		else{key = temporadaActual.getKey();}
		return key;
	}
	
	/**
	 * Gets the temporada actual.
	 * <b>pre: - </b>
	 * <b>post: devuelve la temporada actual</b>
	 * @return the temporada actual
	 * @throws Exception 
	 * 1. no hay una temporada actual no vacía
	 */
	public Temporada getTemporadaActual() throws Exception
	{
		if(temporadaActual == null) {throw new Exception("Por favor inicialice una temporada actual primero");}
		
		return temporadaActual;
	}

	/**
	 * Gets the temporada.
	 * <b>pre: Existe una temporada actual no vacía</b>
	 * <b>post: devuelve el obj. de temporada actual</b>
	 * @param key the key
	 * @return the temporada
	 */
	public Temporada getTemporada(String key) 
	{
		return temporadasPlaneadas.get(key);
	}
	
	
	/**
	 * Gets the key temporadas planeadas.
	 * <b>pre: Existe al menos una temporada planeada no vacía</b>
	 * <b>post: Devuelve un String[] con las keys de las temporadasPlaneadas</b>
	 * @return the key temporadas planeadas
	 */
	public String[] getKeyTemporadasPlaneadas()
	{
		ArrayList<String> result = new ArrayList<>();
		for (Entry<String, Temporada> set: temporadasPlaneadas.entrySet()) 
		{
			
           String key = set.getKey();
           result.add(key);
		}
		int s = result.size();
		if ( s == 0) {s = 1;}
		 String[] str = new String[s];
		 
	        for (int i = 0; i < result.size(); i++) 
	        {
	            str[i] = result.get(i);
	        }
		return str;
	}
	
	/**
	 * Gets the temporadas planeadas.
	 * <b>pre: Existe alguna temporada planeada no vacía</b>
	 * <b>post: Devuelve el hashMap de nombre temporada, Temporada</b>
	 * @return the temporadas planeadas
	 */
	public HashMap<String,Temporada> getTemporadasPlaneadas()
	{
		return temporadasPlaneadas;
	}

	/**
	 * Gets the single instance of ControladorTemporada.
	 * Patrón singleton
	 * @return single instance of ControladorTemporada
	 */
	public static ControladorTemporada getInstance()
	{
		if (uniqueObject == null) {uniqueObject = new ControladorTemporada();}
		return uniqueObject;
	}

	/**
	 * Gets the ID partidos reales.
	 * <b>pre: Existe una temporada actual válida</b>
	 * <b>post: devuelve los id de los partidos reales de la temporada actual</b>
	 * @return the ID partidos reales
	 */
	public String[] getIDPartidosReales() {
		String[] rta = new String[1];
		if(temporadaActual == null) {rta[0] = "";}
		else {rta = temporadaActual.getFechaActual().getIDPartidos();}
		return rta;
	}

	/**
	 * Registrar partido.
	 * <b>pre:  keyPartido es un key válido, resultadoPartido es de extensión .csv</b>
	 * <b>post: revisa el formato del .csv, carga los puntos y resultado del partido en la aplicación</b>
	 * @param keyPartido the key partido
	 * @param resultadoPartido the resultado partido
	 * @throws Exception 
	 * 1. El .csv no sigue el formato esperado por la aplicación
	 */
	public void registrarPartido(String keyPartido, File resultadoPartido) throws Exception {
		PartidoReal partido = temporadaActual.getPartido(keyPartido);
		
		
		BufferedReader br = new BufferedReader(new FileReader(resultadoPartido));
		String linea = br.readLine();
		linea = br.readLine();
		
		while (linea != null) 
		{
			Integer[] save_ints = new Integer[10];
			String[] partes = linea.split(",");
			
			String nombre = partes[6];
			String won = partes[2];
			
			int minutos = Integer.parseInt(partes[7]);
			save_ints[0] = minutos;
			int golesRecibidos = Integer.parseInt(partes[8]);
			save_ints[1] = golesRecibidos;
			int penaltisAnotados = Integer.parseInt(partes[9]);
			save_ints[2] = penaltisAnotados;
			int autoGoles = Integer.parseInt(partes[10]);
			save_ints[3] = autoGoles;
			int golesAnotados = Integer.parseInt(partes[12]);
			save_ints[4] = golesAnotados;
			int penaltisDetenidos = Integer.parseInt(partes[13]);
			save_ints[5] = penaltisDetenidos;
			int penaltisErrados = Integer.parseInt(partes[14]);
			save_ints[6] = penaltisErrados;
			int tarjetasAmarillas = Integer.parseInt(partes[15]);
			save_ints[7] = tarjetasAmarillas;
			int tarjetasRojas = Integer.parseInt(partes[16]);
			save_ints[8] = tarjetasRojas;
			int asistencias = Integer.parseInt(partes[11]);
			save_ints[9] = asistencias;
			
			
			
			JugadorReal jugador = jugadoresMap.get(nombre);
			/*
			 * Llamar a controladorFantasía que tendrá un HashMap con los 
			 * <jugadores, lista_equiposFantasía> se recorrerá esa lista, 
			 * actualizando los puntos de cada equipoFantasía
			 * 
			 * ControladorTemporada(jugador, won, save_ints);
			 */
			ctrlFantasia.agregarPuntos(jugador, won, save_ints);
			
	
			jugador.actualizarDatos(won, save_ints);
			
			
			linea = br.readLine();
		}
		
		
		br.close();
		ctrlFantasia.agregarPuntos();
		partido.resultadoCargado();
		
	}

	/**
	 * Reset temporada actual.
	 * <b>pre: Asumimos que ya hay una temporada actual </b>
	 * <b>post: elimina y termina la temporada actual y la guarda en temporadasFinalizadas</b>
	 */
	public void resetTemporadaActual() {
		String key = temporadaActual.getKey();
		temporadasFinalizadas.put(key, temporadaActual);
		temporadasPlaneadas.remove(key, temporadaActual);
		temporadaActual = null;
		
	}

	/**
	 * utilizado para la persistencia de los datos
	 * 
	 * @param data the new object
	 */
	public static void setObject(ControladorTemporada data) {
		uniqueObject = data;
		
	}

	/**
	 * Obtener jugadores posicion.
	 * <b>pre: existe una temporada actual que cargo hPosicion_jugadores</b>
	 * <b>post: devuelve un arreglo de Strings con todos los jugadores de una posición posicion</b>
	 * @param posicion the posicion
	 * @return the string[]
	 */
	public String[] obtenerJugadoresPosicion(String posicion) {
		ArrayList<String> nombJugadores = hPosicion_Jugadores.get(posicion);
		
		int s = nombJugadores.size();
		if ( s == 0) {s = 1;}
		 String[] str = new String[s];
		 
	        for (int i = 0; i < nombJugadores.size(); i++) 
	        {
	            str[i] = nombJugadores.get(i);
	        }
		return str;
	}
	
	/**
	 * Obtener jugadores.
	 * <b>pre: jugadoresMap se encuentra inicializado </b>
	 * <b>post: devuelve un arreglo de cadena de caracteres de todos los jugadores de la temporada </b>
	 * @return the string[]
	 */
	public String[] obtenerJugadores()
	{
		ArrayList<String> result = new ArrayList<>();
		for (Entry<String, JugadorReal> set: jugadoresMap.entrySet()) 
		{
			
           String key = set.getKey();
           result.add(key);
		}
		int s = result.size();
		if ( s == 0) {s = 1;}
		 String[] str = new String[s];
		 
	        for (int i = 0; i < result.size(); i++) 
	        {
	            str[i] = result.get(i);
	        }
		return str;
	}
	

	/**
	 * Obtener jugador.
	 * <b>pre: Hay una temporada actual cargada, jugadoresMap por tanto está cargado, keyJ es válido</b>
	 * <b>post: devuelve el objeto JugadorReal  bajo el nombre keyJ</b>
	 * @param keyJ the key J
	 * @return the jugador real
	 */
	public JugadorReal obtenerJugador(String keyJ)
	{
		return jugadoresMap.get(keyJ);
	}

	
	
	
	
}
