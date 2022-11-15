/*
 *  Panel para registrar un nuevo propietario
 */
package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PanelRegistrarPropietario extends PlantillaPanel{

	
	private Color bGrndColor = new java.awt.Color(100, 200, 200);
	JLabel lblTitulo;
	JLabel lblAccion;
	JLabel lblNombre;
	JTextField txtNombre;
	JLabel lblContraseña;
	JTextField txtContraseña;
	JButton btnEntrar;
	JLabel lblRegistrarseUsuario;
	JLabel lblRegistrarseAdmin;
	SpringLayout layout;
	JButton btnVolver;
	
	JLabel lblError;
	 
	
	PanelRegistrarPropietario()
	{
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		// lblTitulo
	    lblTitulo = new JLabel("Fútbol de Fantasía");
	    add(lblTitulo);
	    layout.putConstraint(SpringLayout.WEST, lblTitulo,margen_west,SpringLayout.WEST, this); 
	    layout.putConstraint(SpringLayout.NORTH, lblTitulo,espacio_grande,SpringLayout.NORTH, this); 
	    
	    // lblAccion
	    lblAccion = new JLabel("Registrar un propietario");
	    add(lblAccion);
	    layout.putConstraint(SpringLayout.WEST, lblAccion,margen_west,SpringLayout.WEST, this); 
	    layout.putConstraint(SpringLayout.NORTH, lblAccion,espacio_grande,SpringLayout.NORTH, lblTitulo);
	
	    // lblNombre
	    lblNombre = new JLabel("Determine su nombre de usuario"); 
	    add(lblNombre);
	    layout.putConstraint(SpringLayout.WEST, lblNombre,margen_west,SpringLayout.WEST, this); 
	    layout.putConstraint(SpringLayout.NORTH, lblNombre,espacio_grande,SpringLayout.NORTH, lblAccion);
		
		// txtNombre
		txtNombre = new JTextField(20);
		add(txtNombre);
		layout.putConstraint(SpringLayout.WEST, txtNombre,margen_west,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, txtNombre,20,SpringLayout.NORTH, lblNombre);
		
		// lblContraseña
	    lblContraseña = new JLabel("Determine su contraseña"); 
	    add(lblContraseña);
	    layout.putConstraint(SpringLayout.WEST, lblContraseña,margen_west,SpringLayout.WEST, this); 
	    layout.putConstraint(SpringLayout.NORTH, lblContraseña,30,SpringLayout.NORTH, txtNombre);
		
		// txtContraseña
		txtContraseña = new JTextField(20);
		add(txtContraseña);
		layout.putConstraint(SpringLayout.WEST, txtContraseña,margen_west,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, txtContraseña,20,SpringLayout.NORTH, lblContraseña);
		
		// btnVolver
		btnVolver = new JButton("Volver");
		add(btnVolver);
		layout.putConstraint(SpringLayout.WEST, btnVolver,30,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, btnVolver,espacio_grande,SpringLayout.NORTH, this);
		btnVolver.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
			   ventanaPrincipal.volverLogin();
			} 
		});
		
		// btnEntrar
		btnEntrar = new JButton("Registrarse");
		add(btnEntrar);
		layout.putConstraint(SpringLayout.WEST, btnEntrar,margen_west+100,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, btnEntrar,espacio_grande,SpringLayout.NORTH, txtContraseña);
		btnEntrar.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				String nombre = txtNombre.getText();
				String contraseña = txtContraseña.getText();
				boolean result = facadeGUI.registrarPropietario(nombre, contraseña);
				if(result) {txtWhipe();}
				
				
			}
		});
		
		
	}
	private void txtWhipe()
	{	
	   txtNombre.setText("");
	   txtContraseña.setText("");
	}
	
	
}
