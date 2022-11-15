/*
 *  Ventana para informarle al usuario sobre el éxito de una operación
 */
package interfaz;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class VentanaExito extends JFrame{
	
	final static int size_x = 500;
	final static int size_y = 150;
	
	JPanel panelExito = new JPanel();
	
	VentanaExito(String mensaje)
	{
		setSize(size_x,size_y);
		setTitle("Fútbol de Fantasía");
		setLayout(new BorderLayout());
		SpringLayout layout = new SpringLayout();
		
		panelExito.setLayout(layout);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        JLabel lblTituloExito = new JLabel("¡Felicidades!");
        panelExito.add(lblTituloExito);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTituloExito,0,SpringLayout.HORIZONTAL_CENTER, panelExito); 
		layout.putConstraint(SpringLayout.NORTH, lblTituloExito,30,SpringLayout.NORTH, this);
		
        JLabel lblExito = new JLabel(mensaje);
        panelExito.add(lblExito);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblExito,0,SpringLayout.HORIZONTAL_CENTER, panelExito); 
		layout.putConstraint(SpringLayout.NORTH, lblExito,20,SpringLayout.SOUTH, lblTituloExito);
        
        this.setContentPane(panelExito);
	}
}
