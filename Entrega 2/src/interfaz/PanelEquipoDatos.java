/*
 *  Panel para cumplir con el requerimiento de mostrar info. equipo
 */
package interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.EquipoFantasia;
import model.Propietario;

public class PanelEquipoDatos extends PlantillaPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7873769007936418556L;
	Propietario propActual;
	private Color bGrndColor = new java.awt.Color(220, 220, 220);
	
	
	public PanelEquipoDatos(Propietario aUsuario, EquipoFantasia equipoActual){
		this.propActual = aUsuario;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		
		// lblTitulo
		JLabel lblTitulo = new JLabel("Datos equipo");
		lblTitulo.setFont(fuenteT);
		add(lblTitulo);
		layout.putConstraint(SpringLayout.WEST, lblTitulo,margen_west,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, lblTitulo,espacio_grande,SpringLayout.NORTH, this); 
		
		
		// lblNombreEquipo
		JLabel lblNombreEquipo = new JLabel("Nombre");
		add(lblNombreEquipo);
		layout.putConstraint(SpringLayout.WEST, lblNombreEquipo,0,SpringLayout.WEST, lblTitulo); 
		layout.putConstraint(SpringLayout.NORTH, lblNombreEquipo,espacio_grande,SpringLayout.SOUTH, lblTitulo); 
		
		//txtNombreEquipo
		String pString = equipoActual.getNombre();
		JTextField txtNombreEquipo = new JTextField(pString);
		layout.putConstraint(SpringLayout.NORTH, txtNombreEquipo, 0, SpringLayout.NORTH, lblNombreEquipo);
		
		
		txtNombreEquipo.setColumns(10);
		txtNombreEquipo.setEditable(false);
		add(txtNombreEquipo);
		
		
		// lblPresupuesto
		JLabel lblPresupuesto = new JLabel("Presupuesto actual");
		add(lblPresupuesto);
		layout.putConstraint(SpringLayout.WEST, lblPresupuesto,0,SpringLayout.WEST, lblNombreEquipo); 
		layout.putConstraint(SpringLayout.NORTH, lblPresupuesto,espacio_pequeño,SpringLayout.SOUTH, lblNombreEquipo); 
		
		//txtPresupuesto
		Integer pString1 = (Integer) equipoActual.getPresupuesto_restante();
		JTextField txtPresupuesto = new JTextField(pString1.toString());
		layout.putConstraint(SpringLayout.NORTH, txtPresupuesto, 0, SpringLayout.NORTH, lblPresupuesto);
		
		
		txtPresupuesto.setColumns(10);
		txtPresupuesto.setEditable(false);
		add(txtPresupuesto);
		
		// lblPuntos
		JLabel lblPuntos = new JLabel("Puntos acumulados");
		add(lblPuntos);
		layout.putConstraint(SpringLayout.WEST, lblPuntos,0,SpringLayout.WEST, lblPresupuesto); 
		layout.putConstraint(SpringLayout.NORTH, lblPuntos,espacio_pequeño,SpringLayout.SOUTH, lblPresupuesto); 
		
		//txtPuntos
		Integer pString11 = (Integer) equipoActual.getTotalPuntos();
		JTextField txtPuntos = new JTextField(pString11.toString());
		layout.putConstraint(SpringLayout.NORTH, txtPuntos, 0, SpringLayout.NORTH, lblPuntos);
		
		layout.putConstraint(SpringLayout.WEST, txtPuntos, espacio_pequeño, SpringLayout.EAST, lblPuntos);
		txtPuntos.setColumns(10);
		txtPuntos.setEditable(false);
		add(txtPuntos);
		
		
		layout.putConstraint(SpringLayout.WEST, txtPresupuesto, 0, SpringLayout.WEST, txtPuntos);
		layout.putConstraint(SpringLayout.WEST, txtNombreEquipo, 0, SpringLayout.WEST, txtPuntos);
		
		
		
		
		
		
		
						
	}
}
