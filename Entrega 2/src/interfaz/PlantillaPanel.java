/*
 * Plantilla no instanciable para tener en todas los otros paneles que hereden de este
 */
package interfaz;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public abstract class PlantillaPanel extends JPanel{
	
	protected final static int size_x = 700;
	protected final static int size_y = 600;
	protected final static int margen_west = 200;
	protected final static int espacio_grande = 40;
	protected final static int espacio_pequeño = 5;
	protected final static Dimension buttonDimension = new Dimension(300,100);
	
	protected Color bGrndColor = new java.awt.Color(0, 0, 200);
	protected Font fuenteT = new Font("SansSerif", Font.BOLD, 18);
	protected Font fuenteBold = new Font("Arial", Font.BOLD, 12);
	@SuppressWarnings("unused")
	private SpringLayout layout;
	protected FacadeGUI facadeGUI = FacadeGUI.getInstance();
	protected VentanaPrincipal ventanaPrincipal;
	
	public PlantillaPanel()
	{
		SpringLayout layout = new SpringLayout();
		this.layout = layout;
		setLayout(layout);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
	}
	public void vincularVentanaPrincipal(VentanaPrincipal iVentana) {
		ventanaPrincipal = iVentana;
	}
	
	
}
