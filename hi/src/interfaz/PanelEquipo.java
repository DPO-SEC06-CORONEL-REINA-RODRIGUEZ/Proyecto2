package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.EquipoFantasia;
import model.Propietario;

public class PanelEquipo extends PlantillaPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5199936097655053405L;
	
	private int currentCard = 1;
	private Color bGrndColor = new java.awt.Color(0, 250, 250);
    private CardLayout cl;

	private Propietario propietario;
	private EquipoFantasia equipoActual;
	PanelEquipo(Propietario aUsuario, String kEquipo)
	{
		 this.propietario = aUsuario;
		 this.equipoActual = aUsuario.getEquipo(kEquipo);
		 
		 setBackground(bGrndColor);
		 BorderLayout layout = new BorderLayout();
		 setLayout(layout);
		 
	     JPanel cardPanel = new JPanel();
	    
	     cl = new CardLayout();
	    
	     cardPanel.setLayout(cl);
	    
	     PanelEquipoDatos jp1 = new PanelEquipoDatos(propietario, equipoActual);
	    
	     PanelEquipoAlineacion jp2 = new PanelEquipoAlineacion(propietario, equipoActual);
	    
	     PanelEquipoCompra jp3 = new PanelEquipoCompra(propietario, equipoActual);
	    
	     JPanel jp4 = new JPanel();

	    
	     cardPanel.add(jp1, "1");
	    
	     cardPanel.add(jp2, "2");
	    
	     cardPanel.add(jp3, "3");
	    
	     cardPanel.add(jp4, "4");
	    
	     JPanel buttonPanel = new JPanel();
	    
	     JButton btnDatos = new JButton("Equipo");
	    
	     JButton btnAlinear = new JButton("Alinear");
	    
	     JButton btnVender = new JButton("Vender/Comprar");
	    
	     JButton btnHistorico = new JButton("Histórico");
	     JButton btnVolver = new JButton("Volver");
	    
	     buttonPanel.add(btnDatos);
	     buttonPanel.add(btnAlinear);
	     buttonPanel.add(btnVender);
	     buttonPanel.add(btnHistorico);
	     buttonPanel.add(btnVolver);
	    
	     btnDatos.addActionListener(new ActionListener()
	     {
	         public void actionPerformed(ActionEvent arg0)
	         {
	              
	             cl.show(cardPanel, "1");
	         }
	     });
	     
	     btnAlinear.addActionListener(new ActionListener()
	     {
	         public void actionPerformed(ActionEvent arg0)
	         {
	              
	        	 cl.show(cardPanel, "2");
	         }
	     });
	     
	     btnVender.addActionListener(new ActionListener()
	     {
	         public void actionPerformed(ActionEvent arg0)
	         {
	              
	        	 cl.show(cardPanel, "3");
	         }
	     });
	    
	    
	     // add lastbtn in ActionListener
	     btnHistorico.addActionListener(new ActionListener()
	     {
	         public void actionPerformed(ActionEvent arg0)
	         {
	    
	        	 cl.show(cardPanel, "4");
	         }
	     });
	     
	     
	     btnVolver.addActionListener(new ActionListener()
	     {
	         public void actionPerformed(ActionEvent arg0)
	         {
	              
	             ventanaPrincipal.aPanelPropietario(aUsuario);
	         }
	     });
	    
	   
	    
	     // used to get content pane
	     add(cardPanel, BorderLayout.CENTER);
	    
	     // used to get content pane
	     add(buttonPanel, BorderLayout.NORTH);
	    
	}
	

}
