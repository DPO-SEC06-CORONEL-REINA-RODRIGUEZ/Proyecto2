/*
 * Panel para seleccionar el equipo a revisar o crear un nuevo equipo 
 */
package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Propietario;

@SuppressWarnings("serial")
public class PanelPropietario extends PlantillaPanel{
	
	
	private Color bGrndColor = new java.awt.Color(150, 100, 250);
	Propietario propActual;
	JButton btnCerrarSesion;
	JLabel  lblTitulo;
	
	
	PanelPropietario(Propietario aUsuario)
	{	
		this.propActual = aUsuario;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		// btnCerrarSesion
		btnCerrarSesion = new JButton("Cerrar sesión");
		add(btnCerrarSesion);
		layout.putConstraint(SpringLayout.WEST, btnCerrarSesion,30,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, btnCerrarSesion,espacio_grande,SpringLayout.NORTH, this);
		btnCerrarSesion.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
			   ventanaPrincipal.volverLogin();
			} 
		});
		
		// btnCrearEquipo
		JButton btnCrearEquipo = new JButton("Crear equipo");
		layout.putConstraint(SpringLayout.NORTH, btnCrearEquipo, espacio_pequeño, SpringLayout.SOUTH, btnCerrarSesion);
		layout.putConstraint(SpringLayout.WEST, btnCrearEquipo, 0, SpringLayout.WEST, btnCerrarSesion);
		layout.putConstraint(SpringLayout.EAST, btnCrearEquipo, 0, SpringLayout.EAST, btnCerrarSesion);
		add(btnCrearEquipo);
		
		btnCrearEquipo.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
			   ventanaPrincipal.aPanelCrearEquipo(propActual);
			} 
		});
		
		// lblTitulo
		lblTitulo = new JLabel("Fútbol de Fantasía");
		lblTitulo.setFont(fuenteT);
		add(lblTitulo);
		layout.putConstraint(SpringLayout.WEST, lblTitulo,margen_west,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, lblTitulo,espacio_grande,SpringLayout.NORTH, this); 
		
		//lblBienvenida
		JLabel lblBienvenida = new JLabel("Bienvenido propietario");
		lblBienvenida.setFont(fuenteBold);
		layout.putConstraint(SpringLayout.NORTH, lblBienvenida, espacio_pequeño, SpringLayout.SOUTH, lblTitulo);
		layout.putConstraint(SpringLayout.WEST, lblBienvenida, 0, SpringLayout.WEST, lblTitulo);
		add(lblBienvenida);
		
		//lblUsername
		String username = propActual.getNombre();
		JLabel lblUsername = new JLabel(username);
		lblUsername.setFont(fuenteBold);
		layout.putConstraint(SpringLayout.NORTH, lblUsername, 0, SpringLayout.NORTH, lblBienvenida);
		layout.putConstraint(SpringLayout.WEST, lblUsername, 6, SpringLayout.EAST, lblBienvenida);
		add(lblUsername);
		
		
		//lblSeleccionarEquipo
		JLabel lblSeleccionarEquipo = new JLabel("Selecciona tu equipo");
		lblSeleccionarEquipo.setFont(fuenteT);
		layout.putConstraint(SpringLayout.NORTH, lblSeleccionarEquipo, espacio_grande, SpringLayout.SOUTH, lblBienvenida);
		layout.putConstraint(SpringLayout.WEST, lblSeleccionarEquipo, 0, SpringLayout.WEST, lblBienvenida);
		add(lblSeleccionarEquipo);
		
		//jcbIDPartidosReales
		String[] idToSelect = facadeGUI.getEquiposUsuario(propActual);
		JComboBox<String> jcbIDPartidosReales = new JComboBox<String>(idToSelect);
		jcbIDPartidosReales.setEditable(false);
		jcbIDPartidosReales.setPreferredSize(new Dimension(400, 25));
		jcbIDPartidosReales.setSelectedIndex(0);
		add(jcbIDPartidosReales);
		layout.putConstraint(SpringLayout.WEST, jcbIDPartidosReales,0,SpringLayout.WEST, lblSeleccionarEquipo); 
		layout.putConstraint(SpringLayout.NORTH, jcbIDPartidosReales,espacio_pequeño,SpringLayout.SOUTH, lblSeleccionarEquipo);
				
		
		//btnRevisar
		JButton btnRevisar = new JButton("Revisar equipo");
		layout.putConstraint(SpringLayout.EAST, btnRevisar, 0, SpringLayout.EAST, jcbIDPartidosReales);
		layout.putConstraint(SpringLayout.WEST, btnRevisar, 0, SpringLayout.WEST, jcbIDPartidosReales);
		layout.putConstraint(SpringLayout.NORTH, btnRevisar, espacio_pequeño, SpringLayout.SOUTH, jcbIDPartidosReales);
		add(btnRevisar);
		btnRevisar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Object a = jcbIDPartidosReales.getSelectedItem();
				if(a == null)
				{
					new VentanaError("Crea un equipo de fantasía primero");
				}
				else
				{
					String equipo = a.toString();
					ventanaPrincipal.aPanelEquipo(aUsuario, equipo);
				}
				
			
			}});
		
	}
	
	
	
}
