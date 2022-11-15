/*
 *  Ventana predeterminada para mostrar las excepciones esperadas en el código
 */
package interfaz;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class VentanaError extends JFrame {
	
	private final static int size_x = 500;
	private final static int size_y = 150;
	
	JPanel panelError = new JPanel();
	protected Color bGrndColor = new java.awt.Color(200, 200, 200);
	
	VentanaError(String mensaje)
	{
		
		setSize(size_x,size_y);
		setTitle("Fútbol de Fantasía");
		setLayout(new BorderLayout());
		SpringLayout layout = new SpringLayout();
		panelError.setLayout(layout);
		setBackground(bGrndColor);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        JLabel lblTituloError = new JLabel("Error");
        panelError.add(lblTituloError);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTituloError,0,SpringLayout.HORIZONTAL_CENTER, panelError); 
		layout.putConstraint(SpringLayout.NORTH, lblTituloError,30,SpringLayout.NORTH, this);
		
        JLabel lblError = new JLabel(mensaje);
        panelError.add(lblError);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblError,0,SpringLayout.HORIZONTAL_CENTER, panelError); 
		layout.putConstraint(SpringLayout.NORTH, lblError,20,SpringLayout.SOUTH, lblTituloError);
        
        this.setContentPane(panelError);
	}
	
}
