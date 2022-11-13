package interfaz;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Console.Registrador;
import Kernel.EquipoFantasia;
import Kernel.JugadorReal;
import Model.ControladorFantasia;
import Model.ControladorTemporada;


public class FacadeGUI {
	private static FacadeGUI uniqueObject;
	private JFrame errorFrame;
	private Registrador reg =  new Registrador();
	private ControladorFantasia conF = new ControladorFantasia();
	private ControladorTemporada conT = new ControladorTemporada();
	private String error = "";
	private boolean recentError = false;

	

	
	public static FacadeGUI getInstance()
	{
		if (uniqueObject == null)
		{
			uniqueObject = new FacadeGUI();
		}
		return uniqueObject;
	}
	
	private void windowError(String msg)
	{
		errorFrame = new JFrame("Mensaje de Sistema");
		ImageIcon iconoFrame = new ImageIcon("./data/warning.png");
		Color color0 = new Color(255, 255, 0);
		errorFrame.getContentPane().setBackground(color0);
		errorFrame.setIconImage(iconoFrame.getImage());
		errorFrame.setSize(new Dimension(400,80));
		errorFrame.setPreferredSize(new Dimension(300,300));
		errorFrame.setLayout(new GridBagLayout());
		errorFrame.setLocationRelativeTo(null);
		JLabel errorLabel = new JLabel(msg);
		errorFrame.add(errorLabel, new GridBagConstraints());
		errorFrame.setVisible(true);
		error ="";
	}
	
	public int initUserSesion(String user, String pass)
	{
		int c=0;
		try {
		c = reg.iniciarSesionUsuario(user, pass);
		}
		catch(Exception e)
		{error = e.getMessage();}
		if (error!="")
		{
			windowError(error);
		}
		return c;
	}
	
	 public void registrarPropietario(String nombre,  String contrasena)
	 {
		 try {
		 reg.registrarPropietario(nombre, contrasena);
		 }
		 catch(Exception e)
			{error = e.getMessage();}
			if (error!="")
			{
				windowError(error);
			}
			
	 }
	 
	 public void registrarAdmin(String nombre,  String contrasena, String key)
		{
		 try {
			 reg.registrarAdmin(nombre, contrasena, key);
			 }
			 catch(Exception e)
				{error = e.getMessage();}
			if (error!="")
				{
					windowError(error);
				}	
		}
	 
	 public void crearEquipoFantasia(String nombre, String propietario, ArrayList<String> jugadores, String temporada)
	 {
		 try {
			 conF.crearEquipoFantasia(nombre, propietario, jugadores, temporada);
			 }
			 catch(Exception e)
				{error = e.getMessage();}
			if (error!="")
				{
					windowError(error);
					recentError = true;
				}	
			else {
				windowError("El equipo se ha creado con exito! :)");
			}
	 }
	
	 public HashMap<String, ArrayList<EquipoFantasia>> getEquiposFant()
	 
	 {
		 return conF.getEquiposFant();
	 }

	public boolean getRecentError() {
		return recentError;
	}
	
	public HashMap<String, JugadorReal> getJugadoresMap()
	{
		return ControladorTemporada.JugadoresMap;
	}
}
