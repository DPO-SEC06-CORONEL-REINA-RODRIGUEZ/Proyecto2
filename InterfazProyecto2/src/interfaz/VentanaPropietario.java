package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Kernel.EquipoFantasia;
import Kernel.JugadorReal;

@SuppressWarnings("serial")
public class VentanaPropietario extends JFrame {
	private String user;
	private String equipo;
	private EquipoFantasia eqObject;
	private int presupuesto=0;
	private int puntosTotales=0;
	// ===========================CONSTRUCTOR VENTANA DE PROPIETARIO================================///
	
	@SuppressWarnings("unchecked")
	public VentanaPropietario(String usuario, String team, EquipoFantasia eq)
	{
				this.eqObject = eq;
				this.user = usuario;
				this.equipo = team;
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
		    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//Underline text constructor #Ignore
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
			
			
			
			
			
			
			
			JPanel compraVenta = new JPanel();
			compraVenta.setBackground(new Color(102,0,200));
			JPanel alineacion = new JPanel();
			alineacion.setBackground(new Color(240,80,80));

		//** Adicion de los componentes al pane del JFrame
				add(TituloFantasia);
				add(despliegueEquipo);
				add( alineacion);
				add(compraVenta);
				

				
		
	}


}
