/*
 *  Panel para cumplir el requerimiento de compra
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

import model.EquipoFantasia;
import model.Propietario;

public class PanelEquipoCompra extends PlantillaPanel{

	
	
	private static final long serialVersionUID = -6471744318368568129L;
	Propietario propActual;
	private Color bGrndColor = new Color(102,0,200);
	private int precioJug=0;
	
	
	
	@SuppressWarnings("unchecked")
	public PanelEquipoCompra(Propietario aUsuario, EquipoFantasia equipoActual){
		this.propActual = aUsuario;
		setLayout(new GridBagLayout());
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		JTextArea tituloCompra = new JTextArea("Compra/Venta Jugadores");
		tituloCompra.setFont(new Font("Monaco", Font.BOLD, 30));
		tituloCompra.setEditable(false);
		tituloCompra.setForeground(Color.yellow);
		tituloCompra.setBackground(new Color(102,0,200));
		Font font3 = new Font("Monaco", Font.BOLD, 30);
		tituloCompra.setFont(font3);
		@SuppressWarnings("rawtypes")
		Map attributes3 = font3.getAttributes();
	    attributes3.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//Underline text constructor # Ignore
	    tituloCompra.setFont(font3.deriveFont(attributes3));
		
	    Box mainBox = Box.createVerticalBox();
	    mainBox.add(tituloCompra);
	    
	  //Seleccionar Jugador a vender ---
		Box boxVenta = Box.createHorizontalBox();
		JTextArea txtVenta= new JTextArea("Selecciona el jugador a vender: ");
		txtVenta.setFont(new Font("Monaco", Font.ITALIC, 16));
		txtVenta.setEditable(false);
		txtVenta.setForeground(Color.WHITE);
		txtVenta.setBackground(new Color(102,0,200));
		JComboBox<String> selectionVenta= new JComboBox<String>();
		selectionVenta.addItem("--SELECCIONA--");
		boxVenta.add(txtVenta);
		boxVenta.add(selectionVenta);
		if (equipoActual!=null)
		{
			
			for (ArrayList<String> lista: equipoActual.gethJugadores().values())
			{
				{
					for (String jug:lista)
				selectionVenta.addItem(jug);
				}
		    }
		}
		mainBox.add(boxVenta);
		
		
		//Valor de la venta del jugador -- 
		JTextArea valorVenta= new JTextArea("Valor Venta: "+precioJug);
		valorVenta.setFont(new Font("Monaco", Font.BOLD, 12));
		valorVenta.setEditable(false);
		valorVenta.setForeground(Color.WHITE);
		valorVenta.setBackground(new Color(102,0,200));
		
		mainBox.add(valorVenta);
		
		selectionVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (selectionVenta.getSelectedItem().toString()!="--SELECCIONA--")
				{
				precioJug =(facadeGUI.getJugador(selectionVenta.getSelectedItem().toString()).getPrecioCompra());
				valorVenta.setText("Valor Venta: "+precioJug);
				}
			}		
		});
		
		//Relleno #ignorar
		JTextArea relleno1= new JTextArea();
		relleno1.setFont(new Font("Monaco", Font.BOLD, 30));
		relleno1.setEditable(false);
		relleno1.setForeground(Color.WHITE);
		relleno1.setBackground(new Color(102,0,200));
		mainBox.add(relleno1);
		
		
		
	    //Seleccionar Jugador a comprar ---
		Box boxCompra = Box.createHorizontalBox();
		JTextArea txtCompra = new JTextArea("Selecciona el jugador a comprar: ");
		txtCompra.setFont(new Font("Monaco", Font.ITALIC, 16));
		txtCompra.setEditable(false);
		txtCompra.setForeground(Color.WHITE);
		txtCompra.setBackground(new Color(102,0,200));
		JComboBox<String> selectionCompra= new JComboBox<String>();
		selectionCompra.addItem("--SELECCIONA--");
		boxCompra.add(txtCompra);
		boxCompra.add(selectionCompra);
		if (equipoActual!=null)
		{
			
			for (String jug: facadeGUI.getJugadores())
			{
				{
				selectionCompra.addItem(jug);
				}
		    }
		}
		mainBox.add(boxCompra);
		
		//Valor de la compra del jugador -- 
		JTextArea valorCompra= new JTextArea("Valor Compra: "+precioJug);
		valorCompra.setFont(new Font("Monaco", Font.BOLD, 12));
		valorCompra.setEditable(false);
		valorCompra.setForeground(Color.WHITE);
		valorCompra.setBackground(new Color(102,0,200));
		
		mainBox.add(valorCompra);
		
		selectionCompra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (selectionCompra.getSelectedItem().toString()!="--SELECCIONA--")
				{
				precioJug =(facadeGUI.getJugador(selectionCompra.getSelectedItem().toString()).getPrecioCompra());
				valorCompra.setText("Valor Compra: "+precioJug);
				}
			}		
		});
		
		
		//Boton para realizar la transaccion de compra y venta ---
		JButton guardarTransaccion = new JButton("Aceptar Transaccion");
		guardarTransaccion.setFont(new Font("Monaco", Font.BOLD, 14));
		guardarTransaccion.setHorizontalAlignment(SwingConstants.CENTER);
		guardarTransaccion.setBackground(Color.YELLOW);
		
		guardarTransaccion.addActionListener(new ActionListener()
				{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					String JugCompra = selectionCompra.getSelectedItem().toString();
					String JugVenta = selectionVenta.getSelectedItem().toString();
					
					if (JugCompra=="--SELECCIONA--" || JugVenta=="--SELECCIONA--")
							{
								JFrame errorFrame = new JFrame("Mensaje de Sistema");
								ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/warning.png");
								errorFrame.getContentPane().setBackground(Color.red);
								errorFrame.setIconImage(iconoFrame.getImage());
								errorFrame.setSize(new Dimension(400,80));
								errorFrame.setPreferredSize(new Dimension(300,300));
								errorFrame.setLayout(new GridBagLayout());
								errorFrame.setLocationRelativeTo(null);
								JLabel errorLabel = new JLabel("Si compras o vendes un jugador, debes reponerlo!");
								errorLabel.setForeground(Color.white);
								errorFrame.add(errorLabel, new GridBagConstraints());
								errorFrame.setVisible(true);	
							}
					else
					{
						facadeGUI.comprarVenta(equipoActual, JugVenta, JugCompra);
						
						selectionCompra.removeItem(selectionCompra.getSelectedItem());
						selectionVenta.removeItem(selectionVenta.getSelectedItem());
						precioJug=0;
						valorVenta.setText("Valor Venta: "+precioJug);
						valorCompra.setText("Valor Venta: "+precioJug);
						
						JFrame errorFrame = new JFrame("Mensaje de Sistema");
						ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/warning.png");
						errorFrame.getContentPane().setBackground(Color.green);
						errorFrame.setIconImage(iconoFrame.getImage());
						errorFrame.setSize(new Dimension(400,80));
						errorFrame.setPreferredSize(new Dimension(300,300));
						errorFrame.setLayout(new GridBagLayout());
						errorFrame.setLocationRelativeTo(null);
						JLabel errorLabel = new JLabel("La transaccion fue realizada con exito! :)");
						errorFrame.add(errorLabel, new GridBagConstraints());
						errorFrame.setVisible(true);
					}
					
					}
			
			
				});
		
		
		

		
		mainBox.add(guardarTransaccion);
		add(mainBox, new GridBagConstraints());
		
	
	}
}
