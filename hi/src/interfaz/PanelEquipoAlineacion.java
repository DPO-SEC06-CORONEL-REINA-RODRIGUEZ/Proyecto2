/*
 *  Panel para cumplir el requerimiento de alinemiento
 */
package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import model.ControladorTemporada;
import model.EquipoFantasia;
import model.JugadorReal;
import model.Propietario;

public class PanelEquipoAlineacion extends PlantillaPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6471744318368568129L;
	Propietario propActual;
	private Color bGrndColor = new Color(240,80,80);
	
	
	@SuppressWarnings("unchecked")
	public PanelEquipoAlineacion(Propietario aUsuario, EquipoFantasia equipoActual){
		this.propActual = aUsuario;
		setLayout(new GridBagLayout());
		setSize(new Dimension(size_x,size_y));
		setBackground(new Color(240,80,80));
		
		Box boxAlign = Box.createVerticalBox();
		
		JTextArea tituloAlineacion = new JTextArea("Alineacion");
		tituloAlineacion.setFont(new Font("Monaco", Font.BOLD, 60));
		tituloAlineacion.setEditable(false);
		tituloAlineacion.setForeground(Color.CYAN);
		tituloAlineacion.setBackground(new Color(240,80,80));
		Font font2 = new Font("Monaco", Font.BOLD, 60);
		tituloAlineacion.setFont(font2);
		@SuppressWarnings("rawtypes")
		Map attributes2 = font2.getAttributes();
	    attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//Underline text constructor # Ignore
	    tituloAlineacion.setFont(font2.deriveFont(attributes2));
		
		boxAlign.add(tituloAlineacion);

		
		//Seleccionar arquero suplente---
		Box boxArq = Box.createHorizontalBox();
		JTextArea arqTitular = new JTextArea("Nombre Arquero Suplente: ");
		arqTitular.setFont(new Font("Monaco", Font.ITALIC, 20));
		arqTitular.setEditable(false);
		arqTitular.setForeground(Color.WHITE);
		arqTitular.setBackground(new Color(240,80,80));
		JComboBox<String> selectionArq = new JComboBox<String>();
		selectionArq.addItem("--SELECCIONA--");
		boxArq.add(arqTitular);
		boxArq.add(selectionArq);
		if (equipoActual!=null)
		{
			
			for (String jug: equipoActual.gethJugadores().get("arqueroT"))
			{

				selectionArq.addItem(jug);
		    
		    }
		}
		boxAlign.add(boxArq);
		
		//Seleccionar defensa suplente---
		Box boxDef = Box.createHorizontalBox();
		JTextArea DefTitular = new JTextArea("Nombre Defensa Suplente: ");
		DefTitular.setFont(new Font("Monaco", Font.ITALIC, 20));
		DefTitular.setEditable(false);
		DefTitular.setForeground(Color.WHITE);
		DefTitular.setBackground(new Color(240,80,80));
		JComboBox<String> selectionDef= new JComboBox<String>();
		selectionDef.addItem("--SELECCIONA--");
		boxDef.add(DefTitular);
		boxDef.add(selectionDef);
		if (equipoActual!=null)
		{
			
			for (String jug: equipoActual.gethJugadores().get("defensasT"))
			{
				selectionDef.addItem(jug);
		    }
		}
		boxAlign.add(boxDef);
		
		//Seleccionar MedioCampista suplente---
		Box boxMC = Box.createHorizontalBox();
		JTextArea MCTitular = new JTextArea("Nombre MedioCampista Suplente: ");
		MCTitular.setFont(new Font("Monaco", Font.ITALIC, 20));
		MCTitular.setEditable(false);
		MCTitular.setForeground(Color.WHITE);
		MCTitular.setBackground(new Color(240,80,80));
		JComboBox<String> selectionMC= new JComboBox<String>();
		selectionMC.addItem("--SELECCIONA--");
		boxMC.add(MCTitular);
		boxMC.add(selectionMC);
		if (equipoActual!=null)
		{
			
			for (String jug: equipoActual.gethJugadores().get("mediocampistasT"))
			{

				selectionMC.addItem(jug);
		    }
		}
		boxAlign.add(boxMC);
		
		//Seleccionar Delantero suplente---
		Box boxDF = Box.createHorizontalBox();
		JTextArea DFTitular = new JTextArea("Nombre Delantero Suplente: ");
		DFTitular.setFont(new Font("Monaco", Font.ITALIC, 20));
		DFTitular.setEditable(false);
		DFTitular.setForeground(Color.WHITE);
		DFTitular.setBackground(new Color(240,80,80));
		JComboBox<String> selectionDF= new JComboBox<String>();
		selectionDF.addItem("--SELECCIONA--");
		boxDF.add(DFTitular);
		boxDF.add(selectionDF);
		if (equipoActual!=null)
		{
			
			for (String jug: equipoActual.gethJugadores().get("delanterosT"))
			{

				selectionDF.addItem(jug);
		    }
		}
		boxAlign.add(boxDF);
		
		
		//BOTON para guardar la informacion de la alineacion
		Color color1 = new Color(250,110,110);
		JButton guardarAlineacion = new JButton("Guardar Alineacion");
		guardarAlineacion.setFont(new Font("Monaco", Font.BOLD, 16));
		guardarAlineacion.setHorizontalAlignment(SwingConstants.CENTER);
		guardarAlineacion.setBackground(Color.CYAN);

		
		boxAlign.add(guardarAlineacion);
		add(boxAlign, new GridBagConstraints());
	
	guardarAlineacion.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (selectionArq.getSelectedItem().toString()!="--SELECCIONA--"&&
						selectionDef.getSelectedItem().toString()!="--SELECCIONA--"&&
						selectionMC.getSelectedItem().toString()!="--SELECCIONA--"&&
						selectionDF.getSelectedItem().toString()!="--SELECCIONA--")
				{
					ArrayList<String> mapAling = new ArrayList<String>();
					mapAling.add(selectionArq.getSelectedItem().toString());
					mapAling.add(selectionDef.getSelectedItem().toString());
					mapAling.add(selectionMC.getSelectedItem().toString());
					mapAling.add(selectionDF.getSelectedItem().toString());
					
					facadeGUI.alinearEquipo(mapAling, equipoActual);
					JFrame errorFrame = new JFrame("Mensaje de Sistema");
					ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/warning.png");
					errorFrame.getContentPane().setBackground(Color.green);
					errorFrame.setIconImage(iconoFrame.getImage());
					errorFrame.setSize(new Dimension(400,80));
					errorFrame.setPreferredSize(new Dimension(300,300));
					errorFrame.setLayout(new GridBagLayout());
					errorFrame.setLocationRelativeTo(null);
					JLabel errorLabel = new JLabel("Alineacion configurada correctamente :)");
					errorFrame.add(errorLabel, new GridBagConstraints());
					errorFrame.setVisible(true);
				}
				else
				{
					JFrame errorFrame = new JFrame("Mensaje de Sistema");
					ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/warning.png");
					errorFrame.getContentPane().setBackground(Color.red);
					errorFrame.setIconImage(iconoFrame.getImage());
					errorFrame.setSize(new Dimension(400,80));
					errorFrame.setPreferredSize(new Dimension(300,300));
					errorFrame.setLayout(new GridBagLayout());
					errorFrame.setLocationRelativeTo(null);
					JLabel errorLabel = new JLabel("No debes dejar selecciones vacias!");
					errorLabel.setForeground(Color.white);
					errorFrame.add(errorLabel, new GridBagConstraints());
					errorFrame.setVisible(true);	
				}
			}
			});
	}
}
