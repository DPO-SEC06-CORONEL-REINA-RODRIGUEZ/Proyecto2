package interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import model.EquipoFantasia;
import model.Propietario;

public class PanelEquipoAlineacion extends PlantillaPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6471744318368568129L;
	Propietario propActual;
	private Color bGrndColor = new java.awt.Color(220, 220, 220);
	
	
	public PanelEquipoAlineacion(Propietario aUsuario, EquipoFantasia equipoActual){
		this.propActual = aUsuario;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		
		// lblTitulo
		JLabel lblTitulo = new JLabel("Configurar alineación");
		lblTitulo.setFont(fuenteT);
		add(lblTitulo);
		layout.putConstraint(SpringLayout.WEST, lblTitulo,margen_west,SpringLayout.WEST, this); 
		layout.putConstraint(SpringLayout.NORTH, lblTitulo,espacio_grande,SpringLayout.NORTH, this);
	}
}
