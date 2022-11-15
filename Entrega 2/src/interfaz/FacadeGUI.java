package interfaz;


import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.*;

public class FacadeGUI {
	
	private static FacadeGUI    uniqueObject;
	private Registrador registrador = Registrador.getInstance();
	private ControladorTemporada ctrlTemporada = ControladorTemporada.getInstance();
	private ControladorFantasia ctrlFantasia = ControladorFantasia.getInstance();
	private VentanaPrincipal ventanaPrincipal;
	private Usuario usuarioActual;
	private String pathPersistenciactrlTemporada = "./data/persistencia/ctrlTemporada.ser";
	private String pathPersistenciactrlFantasia = "./data/persistencia/ctrlFantasia.ser";
	private String pathPersistenciaRegistrador = "./data/persistencia/ctrlRegistrador.ser";
	
	/**
	 * Instantiates a new facade GUI.
	 * El facadeGUI estará encargado de lidiar entre la interfaz y la consola
	 */
	private FacadeGUI()
	{
		try 
		{	
			
			persistenciaDeSerializar();
			ctrlFantasia.vincularCtrlTemporada(ctrlTemporada);
			
		} 
		catch (Exception e) 
		{
			new VentanaError(e.getMessage());
		}
	}
	
	/**
	 * En el constructor de la fachada serializamos los controladores garantizando la persistencia<br>
	 * <b>pre: Los paths de los serializables se encuentran disponibles (no se ha eliminado el directorio)</b> 
	 * <b>post: guardamos los archivos binarios de registrador, ctrlTemporada y ctrl Fantasía</b>
	 */
	
	public void persistenciaSerializar()  {
		
		try 
		{	
			FileOutputStream bsRegistrador = new FileOutputStream(pathPersistenciaRegistrador);
			ObjectOutputStream osRegistrador = new ObjectOutputStream (bsRegistrador);
			
			osRegistrador.reset();
			osRegistrador.writeObject(registrador);  
			osRegistrador.close();
			osRegistrador.close();
			
			FileOutputStream bs = new FileOutputStream(pathPersistenciactrlTemporada);
			ObjectOutputStream os = new ObjectOutputStream (bs);
			
			os.reset();
			os.writeObject(ctrlTemporada);  
			bs.close();
			os.close();
			
			FileOutputStream bsFantasia = new FileOutputStream(pathPersistenciactrlFantasia);
			ObjectOutputStream osFantasia = new ObjectOutputStream (bsFantasia);
			
			osFantasia.reset();
			osFantasia.writeObject(ctrlFantasia);  
			osFantasia.close();
			osFantasia.close();
			
			
			
		} 
		catch (Exception e) 
		{
			new VentanaError(e.getMessage());
		}
		
	}
	
	/**
	 * En el constructor de la fachada deserializamos los controladores garantizando la persistencia<br>
	 * <b>pre: Los paths de los serializables se encuentran disponibles (no se ha eliminado el directorio)</b> 
	 * <b>post: Carga de registrador, ctrlTemporada y ctrl Fantasía</b>
	 * @throws Exception si los archivos .ser no existen.
	 */
	private void persistenciaDeSerializar() {
		// Opening the input stream
		try {
			
			FileInputStream finRegistrador = new FileInputStream(pathPersistenciaRegistrador);
			ObjectInputStream inRegistrador = new ObjectInputStream(finRegistrador) ;
			// Reading the object
			Registrador dataRegistrador = (Registrador)inRegistrador.readObject() ;
			registrador = dataRegistrador;
			Registrador.setObject(registrador);
			// Closing the stream
			finRegistrador.close();
			inRegistrador.close() ;
			
			FileInputStream fin = new FileInputStream(pathPersistenciactrlTemporada);
			ObjectInputStream in = new ObjectInputStream(fin) ;
			// Reading the object
			ControladorTemporada data = (ControladorTemporada)in.readObject() ;
			ctrlTemporada = data;
			ControladorTemporada.setObject(data);
			// Closing the stream
			fin.close();
			in.close() ; 
			
			FileInputStream finFantasia = new FileInputStream(pathPersistenciactrlFantasia);
			ObjectInputStream inFantasia = new ObjectInputStream(finFantasia) ;
			// Reading the object
			ControladorFantasia dataFantasia = (ControladorFantasia)inFantasia.readObject() ;
			ctrlFantasia = dataFantasia;
			ControladorFantasia.setObject(dataFantasia);
			// Closing the stream
			finFantasia.close();
			inFantasia.close() ; 
			
			
			
			
		} catch (Exception e) {
			new VentanaError(e.getMessage());
		}
		
	}

	/**
	 * Gets the single instance of FacadeGUI.
	 * Util para el patrón de Singleton
	 * @return single instance of FacadeGUI
	 */
	public static FacadeGUI getInstance()
	{
		if (uniqueObject == null) {uniqueObject = new FacadeGUI();}
		return uniqueObject;
	}
	
	/**
	 * Verificar login.
	 * <b>pre: nombreUsuario y contraseña no son vacías</b>
	 * <b>post: redirección a la ventana adecuada para cada tipo de usuario, retorna una ventana de error si no es posible</b>
	 * @param nombreUsuario el nombre usuario ingresado
	 * @param contraseña la contraseña ingresada
	 */

	public void verificarLogin(String nombreUsuario, String contraseña)
	{
		
		try
		{
			Usuario iUsuario = registrador.iniciarSesionUsuario(nombreUsuario, contraseña);
			this.usuarioActual = iUsuario;
			HashMap<String,Temporada> temporadasPlaneadas = ctrlTemporada.getTemporadasPlaneadas();
			if(iUsuario.getClass().equals(Administrador.class)) 
			{
				ventanaPrincipal.aPanelAdministrador(iUsuario);
			}
			else if(iUsuario.getClass().equals(Propietario.class)) 
			{
				Propietario aUsuario = (Propietario) iUsuario;
				if (temporadasPlaneadas.isEmpty()) {ventanaPrincipal.aPanelEspera(aUsuario);}
				else if(aUsuario.tieneAlgunEquipo() == false) {ventanaPrincipal.aPanelCrearEquipo(aUsuario);}
				else
				{
					ventanaPrincipal.aPanelPropietario(aUsuario);
				}
			}
		}
		catch(Exception e)
		{
			new VentanaError(e.getMessage());
		}
	}
	
	/**
	 * Registrar propietario.
	 * <b>pre: nombre y contraseña se encuentran inicializados != null</b>
	 * <b>post: guarda al propietario, hace que persista la información y genera una ventana de éxito, en caso contrario, muestra una ventana error</b>
	 * @param nombre the nombre
	 * @param contraseña the contraseña
	 * @return true, if successful
	 */

	public boolean registrarPropietario(String nombre,  String contraseña) 
	{	
		try
		{
			registrador.registrarPropietario(nombre, contraseña);
			new VentanaExito("Registro exitoso");
			return true;
		}
		catch(Exception e)
		{
			new VentanaError(e.getMessage());
			return false;
		}
    }
	
	/**
	 * Registrar administrador.
	 * <b>pre: nombre, contraseña, key se encuentran inicializados != null</b>
	 * <b>post: guarda al administrador, hace que persista su información y genera una ventana de éxito o de ventana error dependiendo si hubo excepción dentro de la función</b>
	 *
	 * @param nombre the nombre
	 * @param contraseña the contraseña
	 * @param key the key
	 * @return true, if successful
	 */
	public boolean registrarAdministrador(String nombre,  String contraseña, String key) 
	{	
		try
		{
			registrador.registrarAdmin(nombre, contraseña, key);
			new VentanaExito("Registro exitoso");
			return true;
		}
		catch(Exception e)
		{
			new VentanaError(e.getMessage());
			return false;
		}
    }
	
	/**
	 * Planear temporada.
	 * <b>pre: key, fechas, jugadores no son nulos</b>
	 * <b>post: Asegura los parámetros, carga la temporada al sistema y quede dentro de temporadas planeadas</b>
	 * @param key the key
	 * @param fechas the fechas
	 * @param jugadores the jugadores
	 */
	public void planearTemporada(String key, File fechas, File jugadores)
	{
		try
		{	
			GenericAlgorithms.cadenaNoVacia(key);
			GenericAlgorithms.revisarFile(fechas, "csv");
			GenericAlgorithms.revisarFile(jugadores, "csv");
			ctrlTemporada.planearTemporada(key,fechas,jugadores);
			new VentanaExito("Temporada planeada con éxito");
			ventanaPrincipal.aPanelAdministrador(usuarioActual);
		}
		catch(IndexOutOfBoundsException e)
		{
			new VentanaError("El archivo .csv no sigue la plantilla de la aplicación");
		}
		catch(IOException e)
		{
			new VentanaError("El archivo .csv no sigue la plantilla de la aplicación");
		}
		catch(ParseException e)
		{
			new VentanaError("El formato de las fechas del .csv no es dd/MM/yyyy");
		}
		catch(Exception e)
		{
			new VentanaError(e.getMessage());
			
		}
    
	}
	
	
	
	
	/**
	 * Vincular ventana principal.
	 * Permite el paso entre páneles entre una misma ventana principal
	 * <b>pre: iVentana es un parámetro válido</b>
	 * <b>post: returnar ventana princiap</b>
	 * @param iVentana the ventana
	 */
	public void vincularVentanaPrincipal(VentanaPrincipal iVentana) {
		this.ventanaPrincipal = iVentana;
	}
	
	/**
	 * Gets the keys temporadas planeadas.
	 * Devuelve las key de todas las temporadas planeadas que se tienen en el sistema
	 * <b>pre: ctrlTemproada se encuentra inicializado</b>
	 * <b>post: retorna las keys de las temporadas</b>
	 * @return the keys temporadas planeadas
	 */
	public String[] getKeysTemporadasPlaneadas() {
		return ctrlTemporada.getKeyTemporadasPlaneadas();
		
	}
	
	/**
	 * Gets the fecha actual.
	 * Devuelve la fecha actual en la que se encuentra el sistema
	 * <b>pre: ctrlTemporada se encuentra inicializado junto con su temporada actual</b>
	 * <b>post: dar fecha actual</b>
	 * @return the fecha actual
	 */
	public String getFechaActual() {
		return ctrlTemporada.getStringFechaActual();
	}
	
	/**
	 * Gets the key temporada actual.
	 * Retorna el key de la temporada actual
	 * <b>pre: ctrlTemporada se encuentra inicializado, a la par que temporada actual</b>
	 * <b>post: devuelve el key de la temporada actual</b>
	 * @return the key temporada actual
	 */
	public String getKeyTemporadaActual() {
		return ctrlTemporada.getKeyTemporadaActual();
	}

	/**
	 * Sets the temporada actual.
	 * Establece una temporada para que sea la actual
	 * <b>pre: ctrlTemporada, temporadasPlaneadas se encuentran inicializadas, key es una key de temporada válida</b>
	 * <b>post: establece una temporadaActual en la que se podrán cargar resultados</b>
	 * @param key the new temporada actual
	 */
	public void setTemporadaActual(Object key) {
		
		try {
			
			
			ctrlTemporada.setTemporadaActual(key);
				ventanaPrincipal.aPanelAdministrador(usuarioActual);
				new VentanaExito("Se estableció una nueva temporada actual");
			
			
		}
		
		catch (Exception e) {
			new VentanaError(e.getMessage());
		}
	}

	/**
	 * Gets the ID partidos reales.
	 * Devuelve los ID de los partidos reales
	 * <b>pre: Ya hay una temporadaActual inicializada y válida</b>
	 * <b>post: Se devuelve los ID de los partidos reales de la temporada actual</b>
	 * @return the ID partidos reales
	 */
	public String[] getIDPartidosReales() {
		return ctrlTemporada.getIDPartidosReales();
	}

	/**
	 * Registrar partido.
	 * <b>pre: keyPartido y resultadoPartido son archivos inicializados, keyPartido es un key válido</b>
	 * <b>post: se carga los resultados del partido y se suman los puntos de fantasía </b>
	 * @param keyPartido the key partido
	 * @param resultadoPartido the resultado partido
	 */
	public void registrarPartido(String keyPartido, File resultadoPartido) {
		try 
		{	
			GenericAlgorithms.cadenaNoVacia(keyPartido);
			GenericAlgorithms.revisarFile(resultadoPartido, "csv");
			ctrlTemporada.getTemporadaActual().partidoRegistrado(keyPartido);
			ctrlTemporada.registrarPartido(keyPartido, resultadoPartido);
			new VentanaExito("Se cargaron los resultados con éxito");
		} 
		catch(IndexOutOfBoundsException e)
		{
			new VentanaError("El archivo .csv no sigue la plantilla de la aplicación");
		}
		catch(IOException e)
		{
			new VentanaError("El archivo .csv no sigue la plantilla de la aplicación");
		}
		catch (Exception e) 
		{
			new VentanaError(e.getMessage());
		}
	}

	/**
	 * Siguiente fecha.
	 * <b>pre: Ya hay una temporada actual inicializada</b>
	 * <b>post: Se continua a la siguiente fecha, haciendo disponible nuevos partidos para cargar resultados</b>
	 */
	public void siguienteFecha() {
		try
		{	
			
			boolean todosCargados =  ctrlTemporada.getTemporadaActual().getFechaActual().checkPartidosCargados();
			if(todosCargados) 
			{
				ctrlTemporada.getTemporadaActual().siguienteFecha();
				ventanaPrincipal.aPanelAdministrador(usuarioActual);
			}
			else {throw new Exception("No todos los partidos para esta fecha se encuentran cargados");}
		}
		catch(Exception e)
		{
			new VentanaError(e.getMessage());
		}
		
	}

	/**
	 * Terminar temporada.
	 * <b>pre: Ya hay una temporada actual y es el administrador quién llama a la función</b>
	 * <b>post: Se elimina la temporada actual y se guarda a temporada pasadas abriendo el espacio para otra temporada </b>
	 */
	public void terminarTemporada() {
		try
		{
			ctrlTemporada.getTemporadaActual().revisarFinTemporada();
			ctrlTemporada.resetTemporadaActual();
			new VentanaExito("Se ha terminado la temporada con éxito");
			ventanaPrincipal.aPanelAdministrador(usuarioActual);
		}
		catch(Exception e)
		{
			new VentanaError(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Gets the jugadores posicion.
	 * <b>pre: Asumimos que pos es una posición válida, que ya hay una temporada actual</b>
	 * <b>post: retornar los String nombres jugadores en esa posición</b>
	 * @param pos the pos
	 * @return the jugadores posicion
	 */
	public String[] getJugadoresPosicion(String pos) {
		return ctrlTemporada.obtenerJugadoresPosicion(pos);
	}
	
	/**
	 * Gets the jugadores.
	 * <b>pre: Ya hay una temporada actual cargada (y por tanto sus jugadores)</b>
	 * <b>post: Retorna un array de Strings con los nombres de todos los jugadores de la temporada actual</b>
	 * @return the jugadores
	 */
	public String[] getJugadores() 
	{
		return ctrlTemporada.obtenerJugadores();
	}
	


	/**
	 * Crear equipo fantasia.
	 * <b>pre:Asumimos que propietario actual es válido, que hay al menos una temporada planeada que corresponde con keyTemporada
	 * e itemsSeleccionados conlleva la información de alineación válida para un equipo de fantasía </b>
	 * <b>post: Se crea un equipo de fantasía con total_puntos = 0, los jugadores asociados</b>
	 * @param propietarioActual the propietario actual
	 * @param keyEquipo the key equipo
	 * @param keyTemporada the key temporada
	 * @param itemsSeleccionados the items seleccionados
	 */
	public void crearEquipoFantasia(Propietario propietarioActual, String keyEquipo, String keyTemporada, HashMap<String,ArrayList<String>> itemsSeleccionados) {
		try 
		{	
			GenericAlgorithms.cadenaNoVacia(keyEquipo);
			ctrlFantasia.revisarNombreEquipo(keyEquipo, keyTemporada);
			propietarioActual.vacioEquipoEnTemporada(keyTemporada);
			ctrlFantasia.revisarAlineacion(itemsSeleccionados);
			int presupuesto_restante = ctrlFantasia.revisarPresupuesto(itemsSeleccionados, keyTemporada);
			ctrlFantasia.crearEquipoFantasia(propietarioActual, keyEquipo, keyTemporada,itemsSeleccionados, presupuesto_restante);
			new VentanaExito("Se ha creado el equipo de fantasía con éxito");
			
			
			
			
			
		} 
		catch (Exception e) 
		{
			new VentanaError(e.getMessage());
		}
		
	}
	
	
	/**
	 * Comprar venta.
	 * <b>pre: eq es un equipo de fantasía válido, al igual que jugVenta y jugCompra</b>
	 * <b>post: se actualiza la configuración del equipo de fantasía eq incluyendo el jugador jugVenta y eliminando el jugador jugCompra </b>
	 * @param eq the eq
	 * @param jugVenta the jug venta
	 * @param jugCompra the jug compra
	 */
	public void comprarVenta(EquipoFantasia eq, String jugVenta, String jugCompra )
	{
		String key="";
		for (Entry<String, ArrayList<String>> set :
            eq.gethJugadores().entrySet()) 
        {
			for (String nombre: set.getValue())
			{
				if (nombre==jugVenta)
				{
					key = set.getKey();
				}
			}
        }
		eq.gethJugadores().get(key).remove(jugVenta);
		eq.gethJugadores().get(key).add(jugCompra);
	}
	
	/**
	 * Alinear equipo.
	 * <b>pre: lista es una lista de jugadores suplentes válidos los cuales pertenecen al equipo de fantasía
	 * eq es un equipo de fantasía creado apropiadamente</b>
	 * <b>post: cambia la configuración del equipo</b>
	 * @param lista the lista
	 * @param eq the eq
	 */
	public void alinearEquipo(ArrayList<String> lista, EquipoFantasia eq)
	{
		if (eq.gethJugadores().get("arqueroT").contains(lista.get(0))==true)
		{
			eq.gethJugadores().get("arqueroT").remove(lista.get(0));
			eq.gethJugadores().get("arqueroS").add(lista.get(0));
		}
		if (eq.gethJugadores().get("defensasT").contains(lista.get(1))==true)
		{
			eq.gethJugadores().get("defensasT").remove(lista.get(1));
			eq.gethJugadores().get("defensaS").add(lista.get(1));
		}
		if (eq.gethJugadores().get("mediocampistasT").contains(lista.get(2))==true)
		{
			eq.gethJugadores().get("mediocampistasT").remove(lista.get(2));
			eq.gethJugadores().get("mediocampistaS").add(lista.get(2));
		}
		if (eq.gethJugadores().get("delanterosT").contains(lista.get(3))==true)
		{
			eq.gethJugadores().get("delanterosT").remove(lista.get(3));
			eq.gethJugadores().get("delanteroS").add(lista.get(3));
		}
	
		
		
	}

	/**
	 * Gets the presupuesto inicial.
	 * <b>pre: Existe una temporada actual a la que extraer el presupuesto inicial </b>
	 * <b>post:Devuelve el presupuesto inicial establecido para la temporada </b>
	 * @return the presupuesto inicial
	 */
	public Integer getPresupuestoInicial() {
			return Temporada.getPresupuesto();
	}
	
	/**
	 * Gets the equipos usuario.
	 * <b>pre: Asumimos que prop es un propietario actual válido y que está inicializado</b>
	 * <b>post: </b>
	 * @param prop the prop
	 * @return the equipos usuario
	 */
	public String[] getEquiposUsuario(Propietario prop) {
		return prop.getIDEquipos();
	}
	
	/**
	 * Gets the jugador.
	 * <b>pre: Asumimos que el key es un nombre válido para algún jugador de la temporada actual</b>
	 * <b>post: Devolvemos el objeto jugadorreal que corresponde con ese nombre de key</b>
	 * @param key the key
	 * @return the jugador
	 */
	public JugadorReal getJugador(String key)
	{
		return ctrlTemporada.obtenerJugador(key);
	}
	
	
	
}
