package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Kernel.EquipoReal;
import Kernel.Fecha;
import Kernel.JugadorReal;
import Kernel.PartidoReal;
import Kernel.Posicion;
import Kernel.Temporada;


public class ControladorTemporada {
	
	public static HashMap<String,Temporada> temporadasActuales = new HashMap<>();
	public static HashMap<String,Temporada> temporadasPlaneadas = new HashMap<>();
	public static HashMap<String, JugadorReal> JugadoresMap = new HashMap<>();
	public static Date inicio; // Unicamente sirve para cargar la fecha de inicio para la temporada a cargar
	                     
	private static final int puntosPenaltiAtajadoAr=5;
	private static final int puntosSinGolesRecibidosDefyAr=0;
	private static final int puntosGolDef=0;
	private static final int puntosGolDelanyMeCa=0;
	private static final int puntosAutogol=0;
	private static final int puntosTarjetaRoja=0;
	private static final int puntosTarjetaAmarilla=0;
	private static final int puntosErrarPenalti=0;
	private static final int puntosAsistencia=0;
	private static final int puntosMas60min=0;
	private static final int puntosHasta60min=0;
	
	public ControladorTemporada() {
		try {cargarPersTemporadas();}catch(IOException e) {e.printStackTrace();}
	}

	

	public void planearTemp(String key_temporada) {
		try {
			cargarTemporada(key_temporada,false);
			persistenciaTemporada(key_temporada, false);
			System.out.print("Felicidades, se ha cargado la temporada con éxito\n");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}

	public void establecerTemporadaActual(String key_temporada) 
	{
		Temporada oTemporada = temporadasPlaneadas.get(key_temporada);
		temporadasActuales.put(key_temporada, oTemporada);
		try {persistenciaTemporada(key_temporada, true); System.out.println("Exito estableciendo la temporada como actual\n");} catch (IOException e) {e.printStackTrace();}
		
		
		// quedarían temporadas actuales en el .txt de temporadasPlaneadas, pero eso está bien pues podríamos 
		// ver toda la información asociada a una temporada actual a través de temporadas planeadas
	}
	
	public void verTemporadasActuales() {
		
		for (Entry<String, Temporada> set :
            temporadasActuales.entrySet()) {

           String key = set.getKey();
           Temporada temporada = set.getValue();
           Date fecha_inicio = temporada.getFechaInicio();
           
           if(key.equals("")) {System.out.println("No se encontraron temporadas actuales\n");}
           else {
           System.out.println("key: "+key+" | fecha_inicio: "+fecha_inicio);
           }
       }
	}
	public void registrarEstadisticas() {
		// TODO Auto-generated method stub
		
	}

	
	
	public static HashMap<String, Temporada> getTemporadasActuales() {
		return temporadasActuales;
	}

	public void registrarDesempenos(String key_partido, String key_temporada) {
		
		try {
			cargarPartido(key_partido, key_temporada);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static void cargarPartido(String key_partido, String key_temporada) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("./InterfazProyecto2/data/Temporadas/"+key_temporada+"/"+key_partido+".txt"));
		String linea = br.readLine();
		
		Temporada miTemporada =temporadasActuales.get(key_temporada);
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] partes = linea.split(",");
			//fecha
			String won = partes[2];
			String position = partes[5];
			String nombre = partes[6];
			int minutos = Integer.parseInt(partes[7]);
			int golesRecibidos = Integer.parseInt(partes[8]);
			int penaltisAnotados = Integer.parseInt(partes[9]);
			int autoGoles = Integer.parseInt(partes[10]);
			int golesAnotados = Integer.parseInt(partes[11]);
			int penaltisDetenidos = Integer.parseInt(partes[12]);
			int penaltisErrados = Integer.parseInt(partes[13]);
			int tarjetasAmarillas = Integer.parseInt(partes[14]);
			int tarjetasRojas = Integer.parseInt(partes[15]);
			
			
			
			
			
			
			linea = br.readLine();
		}
		
		
		br.close();
		
	}
	
	private static void addToList(String mapKey, JugadorReal myItem, HashMap<String, ArrayList<JugadorReal>> items) {
		ArrayList<JugadorReal> itemsList = items.get(mapKey);

	    // if list does not exist create it
	    if(itemsList == null) {
	         itemsList = new ArrayList<JugadorReal>();
	         itemsList.add(myItem);
	         items.put(mapKey, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(myItem)) itemsList.add(myItem);
	    }
	}
	
	private static void ad(Date date, PartidoReal pr, HashMap<Date,ArrayList<PartidoReal>> fecha_partidos) {
		ArrayList<PartidoReal> itemsList = fecha_partidos.get(date);

	    // if list does not exist create it
	    if(itemsList == null) {
	         itemsList = new ArrayList<PartidoReal>();
	         itemsList.add(pr);
	         fecha_partidos.put(date, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(pr)) itemsList.add(pr);
	    }
	}
	
	private static void cargarTemporada(String key_temporada, boolean actual) throws IOException 
	
	
	{	if(key_temporada == null) {return;}
		else
		{	
		HashMap<String, EquipoReal> hashNombreequipo = new HashMap<>();
		HashMap<String, ArrayList<JugadorReal>> items = new HashMap<String, ArrayList<JugadorReal>>(); 
		BufferedReader br = new BufferedReader(new FileReader("./InterfazProyecto2/data/Temporadas/"+key_temporada+"/players-"+key_temporada+".csv"));
		String linea = br.readLine();
			   linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
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
			Integer vVenta  = (int) (precio*0.97);
			
			
			
			
			JugadorReal jugadorR = new JugadorReal(nombreJugador, position, vCompra, vVenta, precio);
			JugadoresMap.put(nombreJugador, jugadorR);
			
			addToList(equipo,jugadorR,items);
			
			
			linea = br.readLine();
		}
		br.close();
		
		for (Entry<String, ArrayList<JugadorReal>> set :
            items.entrySet()) {

           String nombre_equipo = set.getKey();
           ArrayList<JugadorReal> jugadores = set.getValue();
           
           HashMap<String, JugadorReal> objective = new HashMap<>();
           
           for(JugadorReal i:jugadores) {
        	   String nombre = i.getName();
        	   objective.put(nombre, i);
           }
           EquipoReal equipo = new EquipoReal(nombre_equipo, objective);
           hashNombreequipo.put(nombre_equipo, equipo);
       }
		HashMap<Date, ArrayList<PartidoReal>> fecha_partidos = new HashMap<Date, ArrayList<PartidoReal>>(); 
		
		br = new BufferedReader(new FileReader("./InterfazProyecto2/data/Temporadas/"+key_temporada+"/dates-"+key_temporada+".csv"));
		linea = br.readLine();
		linea = br.readLine();
		boolean first_time = true;
		
		
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] partes = linea.split(",");
			
			
			
			String decodificar = partes[1];
			String[] partes_datetime = decodificar.split(" ");
			try {
				Date date1 =new SimpleDateFormat("dd/MM/yyyy").parse(partes_datetime[0]);
				if (first_time == true) {inicio = date1;first_time = false;}
				
				String hora = partes_datetime[1];
				
				String nombre_local = partes[4];
				String nombre_away  = partes[5];
				String matchID      = partes[6];
				
				
				EquipoReal local = hashNombreequipo.get(nombre_local);
				EquipoReal away = hashNombreequipo.get(nombre_away);
				
				PartidoReal pr = new PartidoReal(date1, hora,away,local);
				
				ad(date1,pr, fecha_partidos);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			
			
			
			
			
			
			linea = br.readLine();
		}
		
		ArrayList<Fecha> lista_fechas = new ArrayList<>();
		for (Entry<Date, ArrayList<PartidoReal>> set :
            fecha_partidos.entrySet()) 
		{

           Date fecha = set.getKey();
           ArrayList<PartidoReal> partidos = set.getValue();
          
           Fecha fechaObjeto = new Fecha(partidos, fecha);
           lista_fechas.add(fechaObjeto);
		}
		
		Temporada temporada = new Temporada(lista_fechas, inicio);
		
		
		if (actual == true) {temporadasActuales.put(key_temporada,temporada);}
		else if (actual == false) {temporadasPlaneadas.put(key_temporada,temporada);}
		}
	}
	
	private void cargarPersTemporadas() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("./InterfazProyecto2/data/Temporadas/actual/temporadasActuales.txt"));
		String linea = br.readLine();
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			cargarTemporada(linea, true);
			
			
			linea = br.readLine();
		}
		
		
		br.close();
		
		br = new BufferedReader(new FileReader("./InterfazProyecto2/data/Temporadas/actual/temporadasPlaneadas.txt"));
		linea = br.readLine();
		
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			cargarTemporada(linea, false);
			
			
			linea = br.readLine();
		}
		
		
		br.close();
		
	}
	
	private void persistenciaTemporada(String keyTemporada, boolean actual) throws IOException {
		if (keyTemporada == "\n") {}
		else if (actual == true) 
		{
			FileWriter fw = new FileWriter("./InterfazProyecto2/data/Temporadas/actual/temporadasActuales.txt",true);
	    	fw.write(keyTemporada+"\n");//appends the string to the file
	        fw.close();
		}
		else if (actual == false) 
		{
			FileWriter fw = new FileWriter("./InterfazProyecto2/data/Temporadas/actual/temporadasPlaneadas.txt",true);
	    	fw.write(keyTemporada+"\n");//appends the string to the file
	        fw.close();
		}
	}
	
}
