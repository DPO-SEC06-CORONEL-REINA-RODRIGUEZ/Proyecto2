/*
 *  Panel para informarle al usuario que no hay temporadas planeadas
 */
package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import model.Propietario;

@SuppressWarnings("serial")
public class PanelEspera extends PlantillaPanel{

	private Color bGrndColor = new java.awt.Color(100, 200, 100);
	Propietario iPropietario;
	JButton btnCerrarSesion;
	JLabel  lblTitulo;
	JLabel  lblMensaje;
	
	public PanelEspera(Propietario prop)
	{
		iPropietario = prop;
		
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
		
		
		//lblMensaje
		
		lblMensaje = new JLabel("Hasta la fecha, no se tienen planeado ninguna temporada");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje, espacio_grande, SpringLayout.SOUTH, lblTitulo);
		grid.putConstraint(SpringLayout.WEST, lblMensaje, 0, SpringLayout.WEST, lblTitulo);
		
		add(lblMensaje);
		
		//lblMensaje
		
		JLabel lblMensaje2 = new JLabel("vuelve cuando el administrador haya planeado alguna");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje2, 5, SpringLayout.SOUTH, lblMensaje);
		grid.putConstraint(SpringLayout.WEST, lblMensaje2, 0, SpringLayout.WEST, lblMensaje);
		
		add(lblMensaje2);
	}
}
