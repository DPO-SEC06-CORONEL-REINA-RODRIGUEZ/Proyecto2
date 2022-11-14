package interfaz;


import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	private FacadeGUI()
	{
		try 
		{	
			
			//Registrador.cargarUsuarios(pathPersistenciaRegistrador);
			persistenciaDeSerializar();
			ctrlFantasia.vincularCtrlTemporada(ctrlTemporada);
			
		} 
		catch (Exception e) 
		{
			new VentanaError(e.getMessage());
		}
	}
	
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

	public static FacadeGUI getInstance()
	{
		if (uniqueObject == null) {uniqueObject = new FacadeGUI();}
		return uniqueObject;
	}
	
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
	
	
	
	
	public void vincularVentanaPrincipal(VentanaPrincipal iVentana) {
		this.ventanaPrincipal = iVentana;
	}
	
	public String[] getKeysTemporadasPlaneadas() {
		return ctrlTemporada.getKeyTemporadasPlaneadas();
		
	}
	public String getFechaActual() {
		return ctrlTemporada.getStringFechaActual();
	}
	public String getKeyTemporadaActual() {
		return ctrlTemporada.getKeyTemporadaActual();
	}

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

	public String[] getIDPartidosReales() {
		return ctrlTemporada.getIDPartidosReales();
	}

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
	
	
	
	
	
	
	
	
	public String[] getJugadoresPosicion(String pos) {
		return ctrlTemporada.obtenerJugadoresPosicion(pos);
	}
	
	public String[] getJugadores() 
	{
		return ctrlTemporada.obtenerJugadores();
	}
	


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
	
	public int getPresupuestoInicial() {
		return Temporada.getPresupuesto();
	}

	public String[] getEquiposUsuario(Propietario prop) {
		return prop.getIDEquipos();
	}
	
	
	
	
	
	
	
	
}
