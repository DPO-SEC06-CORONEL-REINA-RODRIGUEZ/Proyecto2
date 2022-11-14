package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaAdmin extends JFrame {
	
	
	// ===========================CONSTRUCTOR VENTANA DE ADMIN================================///
	
	public VentanaAdmin()
	{

				setTitle("Ventana Administrador");
				setPreferredSize(new Dimension(1000,600));
				ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/balon.png");
				setIconImage(iconoFrame.getImage());
				Color color0 = new Color(255,210,210);
				getContentPane().setBackground(color0);
		
				
		// Layout principal del frame
				setLayout(new GridLayout(3,2));

				
		//** despliegue del Titulo "EQUIPOS DE FANTASIA"
				JLabel labelTitulo = new JLabel();
				ImageIcon iconoLabel = new ImageIcon("./InterfazProyecto2/data/tituloAdmin.png");
				labelTitulo.setIcon(iconoLabel);
				
				
		//**  -------------Panel para la PLANEACION de una TEMPORADA--------------
				
				JPanel planTemp = new JPanel();
				JPanel tituloTemp = new JPanel();
				
				planTemp.setLayout(new GridBagLayout());
				planTemp.setBackground(color0);
				tituloTemp.setLayout(new GridBagLayout());
				tituloTemp.setBackground(color0);
				
				//** despliegue del Titulo "PLANEAR TEMPORADA"
				JLabel lblTitulo = new JLabel();
				ImageIcon iconoTitulo = new ImageIcon("./InterfazProyecto2/data/temp2.png");
				lblTitulo.setIcon(iconoTitulo);
				tituloTemp.add(lblTitulo, new GridBagConstraints());

				
				
				
							//** Caja de texto donde el usuario ingresa la key de la temporada
							JTextField key = new JTextField("key (e.g mundial 2018)");
							key.setPreferredSize(new Dimension(200,25));
							key.setFont(new Font("Monaco", Font.PLAIN, 18));
							key.setHorizontalAlignment(SwingConstants.CENTER);
				
										//** Caja de texto donde el usuario ingresa la data [key]
										JTextField dataKey = new JTextField("datos (data_{key})");
										dataKey.setPreferredSize(new Dimension(200,25));
										dataKey.setFont(new Font("Monaco", Font.PLAIN, 18));
										dataKey.setHorizontalAlignment(SwingConstants.CENTER);

													//** Boton para planear temporada comprobando los datos ingresados
													JButton iniciarTemp = new JButton("Planear");
													iniciarTemp.setPreferredSize(new Dimension(2,25));
													iniciarTemp.setFont(new Font("Monaco", Font.PLAIN, 20));
													iniciarTemp.setHorizontalAlignment(SwingConstants.CENTER);
													iniciarTemp.setAlignmentX(CENTER_ALIGNMENT);
													Color color1 = new Color(250,110,110);
													iniciarTemp.setBackground(color1);
													
			//**  -------------Panel para REGISTRAR DESEMPENOS--------------
			
			JPanel planDsp = new JPanel();
			JPanel tituloDsp= new JPanel();
			
			planDsp.setLayout(new GridBagLayout());
			planDsp.setBackground(color0);
			tituloDsp.setLayout(new GridBagLayout());
			tituloDsp.setBackground(color0);
			
			
			//** despliegue del Titulo "PLANEAR TEMPORADA"
			JLabel lblDsp = new JLabel();
			ImageIcon iconoDsp= new ImageIcon("./InterfazProyecto2/data/FLECHADSP.png");
			lblDsp.setIcon(iconoDsp);
			tituloDsp.add(lblDsp, new GridBagConstraints());

													
													
													
			//** Caja de texto donde el usuario ingresa el nomrbre del archivo con los desempenos
			JTextField Dsp = new JTextField(".txt desempeños");
			Dsp.setPreferredSize(new Dimension(200,25));
			Dsp.setFont(new Font("Monaco", Font.PLAIN, 18));
			Dsp.setHorizontalAlignment(SwingConstants.CENTER);

						//** Boton para REGISTRAR los desempenos comprobando los datos ingresados
						JButton iniciarDsp = new JButton("Registrar");
						iniciarDsp.setPreferredSize(new Dimension(2,25));
						iniciarDsp.setFont(new Font("Monaco", Font.PLAIN, 20));
						iniciarDsp.setHorizontalAlignment(SwingConstants.CENTER);
						iniciarDsp.setAlignmentX(CENTER_ALIGNMENT);
						iniciarDsp.setBackground(color1);
					
				//** Box que contiene los componentes del panel de registrar desempenos
				Box box2 = Box.createVerticalBox();
				box2.add(Dsp);
				box2.add(iniciarDsp);
				planDsp.add(box2, new GridBagConstraints());
				
				//** Box que contiene los componentes del panel de planear temporada
				Box box = 	Box.createVerticalBox();
				box.add(tituloTemp);
				box.add(key);
				box.add(dataKey);
				box.add(iniciarTemp);
				planTemp.add(box, new GridBagConstraints());
				
				
		//** PANELES DE RELLENO: su unica funcion es completar la grilla con el color 0
				JPanel panelRelleno = new JPanel();
				panelRelleno.setBackground(color0);
				JPanel panelRelleno2 = new JPanel();
				panelRelleno2.setBackground(color0);
				JPanel panelRelleno3 = new JPanel();
				panelRelleno3.setBackground(color0);
				JPanel panelRelleno4 = new JPanel();
				panelRelleno4.setBackground(color0);
				JPanel panelRelleno5 = new JPanel();
				panelRelleno5.setBackground(color0);
				JPanel panelRelleno6 = new JPanel();
				panelRelleno6.setBackground(color0);
				JPanel panelRelleno7 = new JPanel();
				panelRelleno7.setBackground(color0);
				
		//** Adicion de los componentes al pane del JFrame
				add(labelTitulo);
				add(tituloTemp);
				add(panelRelleno);
				add( planTemp);
				add( tituloDsp);
				add(planDsp);
				

				

				
		
	}
}
