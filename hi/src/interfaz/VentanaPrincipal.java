package interfaz;

//import java.awt.*;
import javax.swing.*;

import model.Propietario;
import model.Usuario;

import java.awt.*;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{
	
	final static int size_x = 700;
	final static int size_y = 600;
	private JPanel panelActual;	
	private FacadeGUI facadeGUI = FacadeGUI.getInstance();
	
	
	
	public VentanaPrincipal()
	{	
		ImageIcon img = new ImageIcon(".\\Entrega 2\\data\\window\\football.png");
		setIconImage(img.getImage());
		setSize(size_x,size_y);
		setResizable(false);
		setTitle("Fútbol de Fantasía");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        facadeGUI.persistenciaSerializar();
		    }
		});
		initiateComponents();
	}
	
	public void initiateComponents()
	{	
		facadeGUI.vincularVentanaPrincipal(this);
		setLayout( new BorderLayout());
		PanelInicial panelInicial = new PanelInicial();
		panelInicial.vincularVentanaPrincipal(this);
		panelActual = panelInicial;
		this.setContentPane(panelActual);
	}
	
	private void actualizarPanel() {
		this.getContentPane().removeAll();
		this.setContentPane(panelActual);
	}
	
	public void aPanelPropietario(Propietario aUsuario)
	{	
		PanelPropietario panelPropietario = new PanelPropietario(aUsuario);
		panelPropietario.vincularVentanaPrincipal(this);
		panelActual = panelPropietario;
		actualizarPanel();
	}
	
	public void aPanelAdministrador(Usuario iUsuario)
	{	
		PanelAdministrador panelAdministrador = new PanelAdministrador(iUsuario);
		panelAdministrador.vincularVentanaPrincipal(this);
		panelActual = panelAdministrador;
		actualizarPanel();
	}
	
	public void aPanelRegistrarPropietario()
	{	
		PanelRegistrarPropietario panelRegistrarPropietario = new PanelRegistrarPropietario();
		panelRegistrarPropietario.vincularVentanaPrincipal(this);
		panelActual = panelRegistrarPropietario;
		actualizarPanel();
	}
	public void aPanelRegistrarAdministrador()
	{	
		PanelRegistrarAdministrador panelRegistrarAdministrador = new PanelRegistrarAdministrador();
		panelRegistrarAdministrador.vincularVentanaPrincipal(this);
		panelActual = panelRegistrarAdministrador;
		actualizarPanel();
	}
	

	
	public void volverLogin() 
	{
		PanelInicial panelInicial = new PanelInicial();
		panelInicial.vincularVentanaPrincipal(this);
		panelActual = panelInicial;
		actualizarPanel();
	}

	public void aPanelCrearEquipo(Propietario aUsuario) {
		PanelCrearEquipo panelCrear = new PanelCrearEquipo(aUsuario);
		panelCrear.vincularVentanaPrincipal(this);
		panelActual = panelCrear;
		actualizarPanel();
		
	}
	
	public void aPanelEspera(Propietario aUsuario) {
		PanelEspera panelEspera = new PanelEspera(aUsuario);
		panelEspera.vincularVentanaPrincipal(this);
		panelActual = panelEspera;
		actualizarPanel();
	}
	
	public void volverPropietario(Propietario aUsuario) 
	{
		PanelPropietario panelProp = new PanelPropietario(aUsuario);
		panelProp.vincularVentanaPrincipal(this);
		panelActual = panelProp;
		actualizarPanel();
	}
	
	public void aPanelEquipo(Propietario aUsuario, String equipo) 
	{
		PanelEquipo panelProp = new PanelEquipo(aUsuario, equipo);
		panelProp.vincularVentanaPrincipal(this);
		panelActual = panelProp;
		actualizarPanel();
	}
	
	


}
