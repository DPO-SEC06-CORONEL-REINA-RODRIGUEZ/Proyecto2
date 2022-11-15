/*
 * Panel para funciones administrador
 */
package interfaz;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import model.Usuario;

@SuppressWarnings("serial")
public class PanelAdministrador extends PlantillaPanel{
	
	private Color bGrndColor = new java.awt.Color(100, 200, 100);
	
	
	JButton btnCerrarSesion;
	JLabel  lblTitulo;
	Usuario usuarioActual;
	File jugadores;
	File fechas;
	File keyTemporada;
	JTextField txtKey;
	
	
	
	public PanelAdministrador(Usuario iUsuario)
	{	
		usuarioActual = iUsuario;
		
		SpringLayout grid = new SpringLayout();
		setLayout(grid);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		// btnCerrarSesion
		btnCerrarSesion = new JButton("Cerrar sesión");
		grid.putConstraint(SpringLayout.NORTH, btnCerrarSesion, 46, SpringLayout.NORTH, this);
		grid.putConstraint(SpringLayout.WEST, btnCerrarSesion, 10, SpringLayout.WEST, this);
		add(btnCerrarSesion);
		
		btnCerrarSesion.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
			   ventanaPrincipal.volverLogin();
			} 
		});
		
		//lblTitulo
		
		lblTitulo = new JLabel("Fútbol de fantasía");
		lblTitulo.setFont(fuenteT);
		grid.putConstraint(SpringLayout.NORTH, lblTitulo, 4, SpringLayout.NORTH, btnCerrarSesion);
		grid.putConstraint(SpringLayout.WEST, lblTitulo, 6, SpringLayout.EAST, btnCerrarSesion);
		
		add(lblTitulo);
		
		//lblBienvenida
		JLabel lblBienvenida = new JLabel("Bienvenido administrador");
		lblBienvenida.setFont(fuenteBold);
		grid.putConstraint(SpringLayout.NORTH, lblBienvenida, espacio_pequeño, SpringLayout.SOUTH, lblTitulo);
		grid.putConstraint(SpringLayout.WEST, lblBienvenida, 0, SpringLayout.WEST, lblTitulo);
		add(lblBienvenida);
		
		//lblUsername
		String username = usuarioActual.getNombre();
		JLabel lblUsername = new JLabel(username);
		lblUsername.setFont(fuenteBold);
		grid.putConstraint(SpringLayout.NORTH, lblUsername, 0, SpringLayout.NORTH, lblBienvenida);
		grid.putConstraint(SpringLayout.WEST, lblUsername, 6, SpringLayout.EAST, lblBienvenida);
		add(lblUsername);
		
		//lblPlanear
		JLabel lblPlanear = new JLabel("Planear temporada");
		lblPlanear.setFont(fuenteT);
		grid.putConstraint(SpringLayout.NORTH, lblPlanear, 150, SpringLayout.NORTH, this);
		grid.putConstraint(SpringLayout.WEST, lblPlanear, 30, SpringLayout.WEST, this);
		add(lblPlanear);
		
		//lblKey
		JLabel lblKey = new JLabel("Ingrese el key de temporada");
		grid.putConstraint(SpringLayout.WEST, lblKey, 0, SpringLayout.WEST, lblPlanear);
		grid.putConstraint(SpringLayout.NORTH, lblKey, 20, SpringLayout.SOUTH, lblPlanear);
		add(lblKey);
		
		//txtKey
		JTextField txtKey = new JTextField();
		grid.putConstraint(SpringLayout.NORTH, txtKey, espacio_pequeño, SpringLayout.SOUTH, lblKey);
		grid.putConstraint(SpringLayout.WEST, txtKey, 0, SpringLayout.WEST, lblPlanear);
		txtKey.setColumns(17);
		add(txtKey);
		
		
		//btnFechas
		JButton btnFechas = new JButton("Cargar fechas.csv");
		grid.putConstraint(SpringLayout.WEST, btnFechas, 0, SpringLayout.WEST, lblPlanear);
		grid.putConstraint(SpringLayout.NORTH, btnFechas, espacio_pequeño, SpringLayout.SOUTH, txtKey);
		add(btnFechas);
		btnFechas.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//FileChooser
				JFileChooser jfc = new JFileChooser(".//data//");
				jfc.showOpenDialog(PanelAdministrador.this);
				File SelectedFile = jfc.getSelectedFile();
				fechas = SelectedFile;
				
			}
		});
		
		//btnJugadores
		JButton btnJugadores = new JButton("Cargar jugadores.csv ");
		grid.putConstraint(SpringLayout.WEST, btnJugadores, 0, SpringLayout.WEST, lblPlanear);
		grid.putConstraint(SpringLayout.NORTH, btnJugadores, espacio_pequeño, SpringLayout.SOUTH, btnFechas);
		grid.putConstraint(SpringLayout.EAST, btnFechas, 0, SpringLayout.EAST, btnJugadores);
		grid.putConstraint(SpringLayout.EAST, txtKey, 0, SpringLayout.EAST, btnJugadores);
		add(btnJugadores);
		btnJugadores.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//FileChooser
				JFileChooser jfc = new JFileChooser(".//data//");
				jfc.showOpenDialog(PanelAdministrador.this);
				File SelectedFile = jfc.getSelectedFile();
				jugadores = SelectedFile;
				
			}
		});
		
		
		JButton btnPlanear = new JButton("Planear");
		grid.putConstraint(SpringLayout.EAST, btnPlanear, 0, SpringLayout.EAST, btnJugadores);
		grid.putConstraint(SpringLayout.NORTH, btnPlanear, espacio_pequeño*3, SpringLayout.SOUTH, btnJugadores);
		add(btnPlanear);
		btnPlanear.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = txtKey.getText();
				facadeGUI.planearTemporada(key, fechas, jugadores);
			}
		});
		
		
		
		
		
		//lblRegistrar
		JLabel lblRegistrar = new JLabel("Registrar Desempeños");
		lblRegistrar.setFont(fuenteT);
		grid.putConstraint(SpringLayout.WEST, lblRegistrar, 70, SpringLayout.EAST, lblPlanear);
		grid.putConstraint(SpringLayout.NORTH, lblRegistrar, 0, SpringLayout.NORTH, lblPlanear);
		add(lblRegistrar);
		
		//lblPartido
		JLabel lblPartido = new JLabel("Seleccione el partido de la fecha actual a registrar");
		grid.putConstraint(SpringLayout.WEST, lblPartido, 0, SpringLayout.WEST, lblRegistrar);
		grid.putConstraint(SpringLayout.NORTH, lblPartido,0,SpringLayout.NORTH, lblKey); 
		add(lblPartido);
		
		//jcbIDPartidosReales
		String[] idToSelect = facadeGUI.getIDPartidosReales();
		JComboBox<String> jcbIDPartidosReales = new JComboBox<String>(idToSelect);
		jcbIDPartidosReales.setEditable(false);
		jcbIDPartidosReales.setPreferredSize(new Dimension(200, 25));
		jcbIDPartidosReales.setSelectedIndex(0);
		add(jcbIDPartidosReales);
		grid.putConstraint(SpringLayout.WEST, jcbIDPartidosReales,0,SpringLayout.WEST, lblPartido); 
		grid.putConstraint(SpringLayout.NORTH, jcbIDPartidosReales,espacio_pequeño,SpringLayout.SOUTH, lblPartido); 
		
		//btnRegistrar
		JButton btnRegistrar = new JButton("Cargar .csv de resultados");
		grid.putConstraint(SpringLayout.NORTH, btnRegistrar, espacio_pequeño, SpringLayout.SOUTH, jcbIDPartidosReales);
		grid.putConstraint(SpringLayout.WEST, btnRegistrar, 0, SpringLayout.WEST, lblRegistrar);
		add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyPartido = jcbIDPartidosReales.getSelectedItem().toString();
				//FileChooser
				JFileChooser jfc = new JFileChooser(".//data//");
				jfc.showOpenDialog(PanelAdministrador.this);
				File resultadoPartido = jfc.getSelectedFile();
				facadeGUI.registrarPartido(keyPartido, resultadoPartido);
			}
		});
		
		
		
		//lblTempActual
		JLabel lblTempActual = new JLabel("Establecer temporada actual");
		lblTempActual.setFont(fuenteT);
		grid.putConstraint(SpringLayout.NORTH, lblTempActual, 400, SpringLayout.NORTH, this);
		grid.putConstraint(SpringLayout.WEST, lblTempActual, 0, SpringLayout.WEST, lblPlanear);
		add(lblTempActual);
		
		//JComboBox
		String[] tempToSelect = facadeGUI.getKeysTemporadasPlaneadas();
		JComboBox<String> boxSelectTemp = new JComboBox<String>(tempToSelect);
		boxSelectTemp.setEditable(false);
		boxSelectTemp.setPreferredSize(new Dimension(200, 25));
		boxSelectTemp.setSelectedIndex(0);
		add(boxSelectTemp);
		grid.putConstraint(SpringLayout.EAST, boxSelectTemp,0,SpringLayout.EAST, lblTempActual); 
		grid.putConstraint(SpringLayout.NORTH, boxSelectTemp,10,SpringLayout.SOUTH, lblTempActual); 
		
		
		//btnTempActual
		JButton btnTempActual = new JButton("Actualizar");
		grid.putConstraint(SpringLayout.EAST, btnTempActual, 0, SpringLayout.EAST, boxSelectTemp);
		grid.putConstraint(SpringLayout.NORTH, btnTempActual, espacio_pequeño, SpringLayout.SOUTH, boxSelectTemp);
		add(btnTempActual);
		btnTempActual.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object key = boxSelectTemp.getSelectedItem();
				facadeGUI.setTemporadaActual(key);
			}
		});
		
		
		//lblShowActual
		JLabel lblShowActual = new JLabel("Temporada actual:");
		grid.putConstraint(SpringLayout.NORTH, lblShowActual, 0, SpringLayout.NORTH, lblTitulo);
		grid.putConstraint(SpringLayout.WEST, lblShowActual, espacio_grande*2, SpringLayout.EAST, lblTitulo);
		add(lblShowActual);
		
		//txtTemporadaActual
		String pString = facadeGUI.getKeyTemporadaActual();
		JTextField txtTemporadaActual = new JTextField(pString);
		grid.putConstraint(SpringLayout.NORTH, txtTemporadaActual, 0, SpringLayout.NORTH, lblShowActual);
		
		grid.putConstraint(SpringLayout.WEST, txtTemporadaActual, espacio_pequeño, SpringLayout.EAST, lblShowActual);
		txtTemporadaActual.setColumns(10);
		txtTemporadaActual.setEditable(false);
		add(txtTemporadaActual);
		
		
		//lblFechaActual
	    JLabel lblFechaActual = new JLabel("Fecha actual:");
	    grid.putConstraint(SpringLayout.NORTH, lblFechaActual, espacio_pequeño, SpringLayout.SOUTH, lblShowActual);
	    grid.putConstraint(SpringLayout.WEST, lblFechaActual, 0, SpringLayout.WEST, lblShowActual);
	    add(lblFechaActual);
	    
	    //txtFechaActual
	    String fechaActual = facadeGUI.getFechaActual();
	    JTextField txtFechaActual = new JTextField(fechaActual);
	    grid.putConstraint(SpringLayout.NORTH, txtFechaActual, 0, SpringLayout.NORTH, lblFechaActual);
	    
	    grid.putConstraint(SpringLayout.WEST, txtFechaActual, 0, SpringLayout.WEST, txtTemporadaActual);
	    txtFechaActual.setColumns(10);
	    txtFechaActual.setEditable(false);
	    add(txtFechaActual);
	    
	      
	    //lblSiguienteFecha
	    JLabel lblSiguienteFecha = new JLabel("Continuar a la siguiente fecha");
	    lblSiguienteFecha.setFont(fuenteT);
	    grid.putConstraint(SpringLayout.WEST, lblSiguienteFecha, 0, SpringLayout.WEST, lblRegistrar);
	    grid.putConstraint(SpringLayout.NORTH, lblSiguienteFecha, 10, SpringLayout.NORTH, btnJugadores);
	    
	    add(lblSiguienteFecha);
	    
	    //btnSiguienteFecha
	    JButton btnSiguienteFecha = new JButton("Ir");
	    grid.putConstraint(SpringLayout.EAST, btnSiguienteFecha, 0, SpringLayout.EAST, lblSiguienteFecha);
	    grid.putConstraint(SpringLayout.NORTH, btnSiguienteFecha, espacio_pequeño, SpringLayout.SOUTH, lblSiguienteFecha);
	    add(btnSiguienteFecha);
	    btnSiguienteFecha.addActionListener(new ActionListener() 
	    {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		facadeGUI.siguienteFecha();
	    	}
	    });
	    
	    
	    
	    //lblTerminarTemporada
	    JLabel lblTerminarTemporada = new JLabel("Terminar temporada actual");
	    lblTerminarTemporada.setFont(fuenteT);
	    grid.putConstraint(SpringLayout.WEST, lblTerminarTemporada, espacio_grande, SpringLayout.EAST, lblTempActual);
	    grid.putConstraint(SpringLayout.NORTH, lblTerminarTemporada, 0, SpringLayout.NORTH, lblTempActual);
	    
	    add(lblTerminarTemporada);
	    
	    //btnTerminarTemporada
	    JButton btnTerminarTemporada = new JButton("terminar");
		grid.putConstraint(SpringLayout.EAST, btnTerminarTemporada, 0, SpringLayout.EAST, lblTerminarTemporada);
		grid.putConstraint(SpringLayout.NORTH, btnTerminarTemporada, espacio_pequeño, SpringLayout.SOUTH, lblTerminarTemporada);
		add(btnTerminarTemporada);
		btnTerminarTemporada.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				facadeGUI.terminarTemporada();
			}
		});
	    
	    
	   
	}
	
	
	
}
