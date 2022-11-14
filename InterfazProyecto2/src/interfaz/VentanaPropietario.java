package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Kernel.EquipoFantasia;
import Kernel.JugadorReal;
import Model.ControladorTemporada;

@SuppressWarnings("serial")
public class VentanaPropietario extends JFrame {
	private String user;
	private String equipo;
	private EquipoFantasia eqObject;
	private int presupuesto=0;
	private int puntosTotales=0;
	private FacadeGUI facade;
	private boolean error=false;
	private int precioJug=0;
	// ===========================CONSTRUCTOR VENTANA DE PROPIETARIO================================///
	
	@SuppressWarnings("unchecked")
	public VentanaPropietario(String usuario, String team, EquipoFantasia eq, FacadeGUI fac)
	{
				this.eqObject = eq;
				this.user = usuario;
				this.equipo = team;
				this.presupuesto =eq.getPresupuestoDisponible();
				setTitle("Ventana Propietario");
				setSize(new Dimension(1000,600));
				setPreferredSize(new Dimension(1000,600));
				ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/balon.png");
				setIconImage(iconoFrame.getImage());
				Color color0 = new Color(255,210,210);
				getContentPane().setBackground(color0);
		
				
		// Layout principal del frame
				setLayout(new GridLayout(2,2,20,20));

				
		//** despliegue del Titulo "EQUIPOS DE FANTASIA"
				JPanel TituloFantasia = new JPanel();
				TituloFantasia.setLayout(new GridBagLayout());
				TituloFantasia.setBackground(color0);
				JPanel panelTit = new JPanel();
				panelTit.setLayout(new GridBagLayout());
				panelTit.setBackground(color0);
				JLabel labelTitulo = new JLabel();
				ImageIcon iconoLabel = new ImageIcon("./InterfazProyecto2/data/propietarioFinal.png");
				labelTitulo.setIcon(iconoLabel);
				panelTit.add(labelTitulo, new GridBagConstraints());
				Box box3 = Box.createVerticalBox();
				
				JTextArea pres = new JTextArea("Presupuesto actual__________"+presupuesto);
				pres.setEditable(false);pres.setBackground(color0);pres.setFont(new Font("Monaco", Font.BOLD, 14));
				
				
				JTextArea prop = new JTextArea("Propietario_________________"+user);
				prop.setEditable(false);prop.setBackground(color0);prop.setFont(new Font("Monaco", Font.BOLD, 14));
				
				JTextArea puntos = new JTextArea("Puntos totales______________"+puntosTotales);
				puntos.setEditable(false);puntos.setBackground(color0);puntos.setFont(new Font("Monaco", Font.BOLD, 14));
				
				if (eqObject!=null)
				{
					presupuesto = eqObject.getPresupuestoDisponible();
					puntosTotales = eqObject.getTotalPuntos();
				}
				
				JTextArea empty = new JTextArea();
				empty.setEditable(false);
				empty.setBackground(color0);
				
				JTextArea empty2 = new JTextArea();
				empty2.setEditable(false);
				empty2.setBackground(color0);
				
				box3.add(panelTit);
				box3.add(empty);
				box3.add(empty2);
				box3.add(pres);
				box3.add(prop);
				box3.add(puntos);
				
				
				TituloFantasia.add(box3, new GridBagConstraints());
				
				
				
		//**  -------------PANELES para las FUNCIONALIDADES del propietario --------------
			
			// PANEL DESPLIEGUE DEL EQUIPO
			JPanel despliegueEquipo = new JPanel();
			despliegueEquipo.setBackground(new Color(102,0,255));
			despliegueEquipo.setLayout(new GridBagLayout());
			
			Box boxTeam = Box.createVerticalBox();

			
			JTextArea tituloEquipo = new JTextArea(equipo);
			tituloEquipo.setFont(new Font("Monaco", Font.BOLD, 30));
			tituloEquipo.setEditable(false);
			tituloEquipo.setForeground(Color.WHITE);
			tituloEquipo.setBackground(new Color(102,0,255));
			Font font = new Font("Monaco", Font.BOLD, 25);
			tituloEquipo.setFont(font);
			@SuppressWarnings("rawtypes")
			Map attributes = font.getAttributes();
		    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//Underline text constructor # Ignore
		    tituloEquipo.setFont(font.deriveFont(attributes));
		    
			boxTeam.add(tituloEquipo);
			
			if (eq!=null)
			{
				
				for (JugadorReal jug: eq.getPlantilla().values())
				{
					JTextArea nombreJugador = new JTextArea(jug.getPosicion().toUpperCase()+"		"+jug.getName()+", Puntos: "+jug.getPuntos());
					nombreJugador.setFont(new Font("Monaco", Font.ITALIC, 11));
					nombreJugador.setEditable(false);
					nombreJugador.setForeground(Color.WHITE);
					nombreJugador.setBackground(new Color(102,0,255));
					boxTeam.add(nombreJugador);
				}
			}
	
			despliegueEquipo.add(boxTeam);
				
			
			
			//PANEAL PARA LA ALINEACION DEL EQUIPO
			JPanel alineacion = new JPanel();
			alineacion.setBackground(new Color(240,80,80));
			alineacion.setLayout(new GridBagLayout());
			
			Box boxAlign = Box.createVerticalBox();
			
			JTextArea tituloAlineacion = new JTextArea("Alineacion");
			tituloAlineacion.setFont(new Font("Monaco", Font.BOLD, 30));
			tituloAlineacion.setEditable(false);
			tituloAlineacion.setForeground(Color.CYAN);
			tituloAlineacion.setBackground(new Color(240,80,80));
			Font font2 = new Font("Monaco", Font.BOLD, 30);
			tituloAlineacion.setFont(font2);
			@SuppressWarnings("rawtypes")
			Map attributes2 = font2.getAttributes();
		    attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//Underline text constructor # Ignore
		    tituloAlineacion.setFont(font2.deriveFont(attributes2));
			
			boxAlign.add(tituloAlineacion);
			
			JTextArea tituloFecha = new JTextArea(ControladorTemporada.inicio.toString());
			tituloFecha.setFont(new Font("Monaco", Font.BOLD, 15));
			tituloFecha.setEditable(false);
			tituloFecha.setForeground(Color.CYAN);
			tituloFecha.setBackground(new Color(240,80,80));
			
			boxAlign.add(tituloFecha);
			
			//Seleccionar arquero suplente---
			Box boxArq = Box.createHorizontalBox();
			JTextArea arqTitular = new JTextArea("Nombre Arquero Suplente: ");
			arqTitular.setFont(new Font("Monaco", Font.ITALIC, 16));
			arqTitular.setEditable(false);
			arqTitular.setForeground(Color.WHITE);
			arqTitular.setBackground(new Color(240,80,80));
			JComboBox<String> selectionArq = new JComboBox<String>();
			selectionArq.addItem("--SELECCIONA--");
			boxArq.add(arqTitular);
			boxArq.add(selectionArq);
			if (eq!=null)
			{
				
				for (JugadorReal jug: eq.getPlantilla().values())
				{
					if (jug.getPosicion()=="Arquero")
					{
					selectionArq.addItem(jug.getName());
					}
			    }
			}
			boxAlign.add(boxArq);
			
			//Seleccionar defensa suplente---
			Box boxDef = Box.createHorizontalBox();
			JTextArea DefTitular = new JTextArea("Nombre Defensa Suplente: ");
			DefTitular.setFont(new Font("Monaco", Font.ITALIC, 16));
			DefTitular.setEditable(false);
			DefTitular.setForeground(Color.WHITE);
			DefTitular.setBackground(new Color(240,80,80));
			JComboBox<String> selectionDef= new JComboBox<String>();
			selectionDef.addItem("--SELECCIONA--");
			boxDef.add(DefTitular);
			boxDef.add(selectionDef);
			if (eq!=null)
			{
				
				for (JugadorReal jug: eq.getPlantilla().values())
				{
					if (jug.getPosicion()=="Defensa")
					{
					selectionDef.addItem(jug.getName());
					}
			    }
			}
			boxAlign.add(boxDef);
			
			//Seleccionar MedioCampista suplente---
			Box boxMC = Box.createHorizontalBox();
			JTextArea MCTitular = new JTextArea("Nombre MedioCampista Suplente: ");
			MCTitular.setFont(new Font("Monaco", Font.ITALIC, 16));
			MCTitular.setEditable(false);
			MCTitular.setForeground(Color.WHITE);
			MCTitular.setBackground(new Color(240,80,80));
			JComboBox<String> selectionMC= new JComboBox<String>();
			selectionMC.addItem("--SELECCIONA--");
			boxMC.add(MCTitular);
			boxMC.add(selectionMC);
			if (eq!=null)
			{
				
				for (JugadorReal jug: eq.getPlantilla().values())
				{
					if (jug.getPosicion()=="MedioCampista")
					{
					selectionMC.addItem(jug.getName());
					}
			    }
			}
			boxAlign.add(boxMC);
			
			//Seleccionar Delantero suplente---
			Box boxDF = Box.createHorizontalBox();
			JTextArea DFTitular = new JTextArea("Nombre Delantero Suplente: ");
			DFTitular.setFont(new Font("Monaco", Font.ITALIC, 16));
			DFTitular.setEditable(false);
			DFTitular.setForeground(Color.WHITE);
			DFTitular.setBackground(new Color(240,80,80));
			JComboBox<String> selectionDF= new JComboBox<String>();
			selectionDF.addItem("--SELECCIONA--");
			boxDF.add(DFTitular);
			boxDF.add(selectionDF);
			if (eq!=null)
			{
				
				for (JugadorReal jug: eq.getPlantilla().values())
				{
					if (jug.getPosicion()=="Delantero")
					{
					selectionDF.addItem(jug.getName());
					}
			    }
			}
			boxAlign.add(boxDF);
			
			
			//BOTON para guardar la informacion de la alineacion
			Color color1 = new Color(250,110,110);
			JButton guardarAlineacion = new JButton("Guardar Alineacion");
			guardarAlineacion.setFont(new Font("Monaco", Font.BOLD, 14));
			guardarAlineacion.setHorizontalAlignment(SwingConstants.CENTER);
			guardarAlineacion.setBackground(Color.CYAN);

			
			boxAlign.add(guardarAlineacion);
			alineacion.add(boxAlign, new GridBagConstraints());
			
			guardarAlineacion.addActionListener(new ActionListener()
					{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						ArrayList<String> mapAling = new ArrayList<String>();
						mapAling.add(selectionArq.getSelectedItem().toString());
						mapAling.add(selectionDef.getSelectedItem().toString());
						mapAling.add(selectionMC.getSelectedItem().toString());
						mapAling.add(selectionDF.getSelectedItem().toString());
						
						
						for (String jug: mapAling)
						{if (jug=="--SELECCIONA--")
							{error=true;}
						}
						if (error==false)
						{
							fac.configurarAlineacion(mapAling, eq);
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
							error = false;
						}
					}
				
				
				
				
					});
			
			
			//------PANEL PARA LA COMPRA Y VENTA DE JUGADORES-------
			JPanel compraVenta = new JPanel();
			compraVenta.setBackground(new Color(102,0,200));
			
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
			if (eq!=null)
			{
				
				for (JugadorReal jug: eq.getPlantilla().values())
				{
					{
					selectionVenta.addItem(jug.getName());
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
					precioJug =ControladorTemporada.JugadoresMap.get(selectionVenta.getSelectedItem().toString()).getValorCompra();
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
			if (eq!=null)
			{
				
				for (JugadorReal jug: ControladorTemporada.JugadoresMap.values())
				{
					{
					selectionCompra.addItem(jug.getName());
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
					precioJug =ControladorTemporada.JugadoresMap.get(selectionCompra.getSelectedItem().toString()).getValorCompra();
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
							fac.comprarJugador(JugCompra, eq);
							fac.venderJugador(JugVenta, eq);
							pres.setText("Presupuesto Disponible: "+eq.getPresupuestoDisponible());
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
			compraVenta.add(mainBox, new GridBagConstraints());

		  

		    
			
			
			
			
			
			
			
			
			
			
		//** Adicion de los componentes al pane del JFrame
				add(TituloFantasia);
				add(despliegueEquipo);
				add( alineacion);
				add(compraVenta);
				

				
		
	}


}
