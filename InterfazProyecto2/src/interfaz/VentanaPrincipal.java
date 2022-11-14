package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Kernel.EquipoFantasia;
import Kernel.JugadorReal;



@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	private FacadeGUI facade = new FacadeGUI();
	private VentanaRegUsuario regUser;
	private VentanaRegAdmin regAdmin;
	private String user;
	private String pass;
	int c = 0;
	int Pos = 0;
	private ArrayList<String> respuestas  =  new ArrayList<>();
	private ArrayList<String> messages = new ArrayList<>();
	private ArrayList<String> jugadores = new ArrayList<>();
	private EquipoFantasia equipoSelected;
	
	//private tituloEquipos login;
	 
	
	// ===========================CONSTRUCTOR VENTANA PRINCIPAL================================///
	
			@SuppressWarnings("unchecked")
			public VentanaPrincipal(){
				
	setTitle("Equipos de Fantasia");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(600,600);
	ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/balon.png");
	setIconImage(iconoFrame.getImage());
	setLocationRelativeTo(null);
	Color color0 = new Color(255,210,210);
	getContentPane().setBackground(color0);
	
	// Layout principal del frame
	setLayout( new GridBagLayout() );

	//** Caja de texto donde el usuario ingresa su nombre
	JTextField usuarioField = new JTextField("Usuario");
	usuarioField.setPreferredSize(new Dimension(200,25));
	usuarioField.setFont(new Font("Monaco", Font.PLAIN, 20));
	usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
	

	
	//** Caja de texto donde el usuario ingresa su contrasena
	JTextField passField = new JTextField("password");
	passField.setPreferredSize(new Dimension(200,25));
	passField.setFont(new Font("Monaco", Font.PLAIN, 20));
	passField.setHorizontalAlignment(SwingConstants.CENTER);
	
	//** Boton para iniciar sesion comprobando los datos ingresados
	JButton iniciarSesion = new JButton("Iniciar Sesion");
	iniciarSesion.setPreferredSize(new Dimension(2,25));
	iniciarSesion.setFont(new Font("Monaco", Font.PLAIN, 20));
	iniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
	iniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
	Color color1 = new Color(250,110,110);
	iniciarSesion.setBackground(color1);
	iniciarSesion.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) { 
			
		    user = usuarioField.getText();
		    pass = passField.getText();
		    c = facade.initUserSesion(user, pass); //**Inicio de sesion del usuario
		    if (c==1)
			{
		    JFrame crearEquipo = new JFrame("Crear Equipo Fantasia");
		    crearEquipo.setSize(new Dimension(400,95));
		    crearEquipo.setVisible(true);
		    crearEquipo.setLocationRelativeTo(null);
		    crearEquipo.getContentPane().setBackground(color0);
			crearEquipo.setIconImage(iconoFrame.getImage());
			JLabel crearTeam = new JLabel("¿Deseas crear un equipo de fantasia?");
			crearTeam.setFont(new Font("Monaco", Font.BOLD, 14));
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(new GridBagLayout());
			JButton Si = new JButton("SI");
			
			JButton No = new JButton("NO");
			Box sino = Box.createHorizontalBox();
			
			sino.add(Si);
			sino.add(No);
			Box big = Box.createVerticalBox();
			big.add(crearTeam,BorderLayout.CENTER);
			big.add(sino,BorderLayout.CENTER);
			big.setAlignmentX(CENTER_ALIGNMENT);
			contentPanel.setBackground(color1);
			contentPanel.add(big, new GridBagConstraints());
			
			crearEquipo.add(contentPanel);
			
			Si.addActionListener(new ActionListener(){ 
				
				//Action listeners en caso de que el usuario SI dese crear un equipo de fantasia
				
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					messages = new ArrayList<>();
					respuestas = new ArrayList<>();
					jugadores = new ArrayList<>();
					crearEquipo.setVisible(false);
					JFrame creacion = new JFrame("Crear Equipo Fantasia");
					creacion.setSize(new Dimension(500,240));
					creacion.setLocationRelativeTo(null);
					creacion.getContentPane().setBackground(color0);
					creacion.setIconImage(iconoFrame.getImage());
					creacion.setLayout(new GridBagLayout());
					creacion.setVisible(true);
					
					JLabel balon = new JLabel();
					balon.setSize(new Dimension(100,96));
					ImageIcon iconoBalon = new ImageIcon("./InterfazProyecto2/data/balonResized.png");
					balon.setIcon(iconoBalon);
					balon.setAlignmentX(CENTER_ALIGNMENT);
					
					
					JTextArea pregunta = new JTextArea("Ingrese el nombre de su EQUIPO de FANTASIA a crear:");
					pregunta.setEditable(false);
					pregunta.setBackground(color0);
					pregunta.setFont(new Font("Monaco", Font.BOLD, 16));
					
					JTextField cajaRespuesta = new JTextField();
					cajaRespuesta.setFont(new Font("Monaco", Font.BOLD, 16));
					cajaRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
					
					JButton answer = new JButton("Ingresar");
					answer.setFont(new Font("Monaco", Font.BOLD, 16));
					answer.setHorizontalAlignment(SwingConstants.CENTER);
					answer.setBackground(color1);
					answer.setAlignmentX(CENTER_ALIGNMENT);
					
					JComboBox<String> combo4 = new JComboBox<String>();
					combo4.setVisible(false);
					
					Box questionsBox = Box.createVerticalBox();
					questionsBox.add(balon);
					questionsBox.add(pregunta);
					questionsBox.add(cajaRespuesta);
					questionsBox.add(combo4);
					questionsBox.add(answer);
					
					creacion.add(questionsBox, new GridBagConstraints());
					
					//Recibimos las respuestas que vaya ingresando en la casilla y las guardamos en una lista
					
					messages.add("Ingrese la temporada (e.g mundial2018):");
					messages.add("Seleccione el Arquero 1");
					messages.add("Seleccione el Arquero 2");
					messages.add("Seleccione el Defensor 1");
					messages.add("Seleccione el Defensor 2");
					messages.add("Seleccione el Defensor 3");
					messages.add("Seleccione el Defensor 4");
					messages.add("Seleccione el Defensor 5");
					messages.add("Seleccione el MedioCampista 1");
					messages.add("Seleccione el MedioCampista 2");
					messages.add("Seleccione el MedioCampista 3");
					messages.add("Seleccione el MedioCampista 4");
					messages.add("Seleccione el MedioCampista 5");
					messages.add("Seleccione el Delantero 1");
					messages.add("Seleccione el Delantero 2");
					messages.add("Seleccione el Delantero 3");
					messages.add("CrHashMap<K, V>l equipo...");
					HashMap<String, JugadorReal> jugadoresMap = facade.getJugadoresMap();
					
					answer.addActionListener(new ActionListener(){ 
					
				    @Override
				    public void actionPerformed(ActionEvent e)
					{
				    	if (Pos<=1) {
				    	respuestas.add(cajaRespuesta.getText());
				    	cajaRespuesta.setText("");
				    	pregunta.setText(messages.get(Pos));
				    	Pos++;
				    	}
				    	
				    	if (Pos>1 && Pos<=3)
				    	{

				    		cajaRespuesta.setVisible(false);
				    		combo4.setVisible(true);
				    		combo4.addItem("--SELECCIONA--");
				    		
				    		
				    		
						    		
						    		for (JugadorReal jug: jugadoresMap.values())
						    		{
						    			if (jug.getPosicion()=="Arquero")
						    			{
						    				combo4.addItem(jug.getName());
						    			}
						    	
						    		}
						    		
						    		if (combo4.getSelectedItem()!="--SELECCIONA--")
							    	{
							    		respuestas.add(combo4.getSelectedItem().toString());
							    		pregunta.setText(messages.get(Pos));
							    		Pos++;
							    		combo4.removeAllItems();
							    		answer.doClick();
							    		
							    	}
						    		
				    	}
				    	
				    	if (Pos>3 && Pos<=8)
				    	{
				    		
				    		combo4.addItem("--SELECCIONA--");
				    		
						    		
						    		for (JugadorReal jug: jugadoresMap.values())
						    		{
						    			if (jug.getPosicion()=="Defensa")
						    			{
						    				combo4.addItem(jug.getName());
						    			}
						    	
				    		}
						    		if (combo4.getSelectedItem()!="--SELECCIONA--")
							    	{
							    		respuestas.add(combo4.getSelectedItem().toString());
							    		pregunta.setText(messages.get(Pos));
							    		Pos++;
							    		combo4.removeAllItems();
							    		answer.doClick();
							    	}
				    	}
				    	
				    	if (Pos>8 && Pos<=13)
				    	{
				    		
				    		combo4.addItem("--SELECCIONA--");
				    		
						    		
						    		for (JugadorReal jug: jugadoresMap.values())
						    		{
						    			if (jug.getPosicion()=="MedioCampista")
						    			{
						    				combo4.addItem(jug.getName());
						    			}
						    	
				    		}
						    		if (combo4.getSelectedItem()!="--SELECCIONA--")
							    	{
							    		respuestas.add(combo4.getSelectedItem().toString());
							    		pregunta.setText(messages.get(Pos));
							    		Pos++;
							    		combo4.removeAllItems();
							    		answer.doClick();
							    	}
				    	}
				    	
				    	if (Pos>13 && Pos<=16)
				    	{
				    		
				    		combo4.addItem("--SELECCIONA--");
				    		
						    		
						    		for (JugadorReal jug: jugadoresMap.values())
						    		{
						    			if (jug.getPosicion()=="Delantero")
						    			{
						    				combo4.addItem(jug.getName());
						    				
						    			}
						    	
				    		}
						    		if (combo4.getSelectedItem()!="--SELECCIONA--")
							    	{
							    		respuestas.add(combo4.getSelectedItem().toString());
							    		pregunta.setText(messages.get(Pos));
							    		Pos++;
							    		combo4.removeAllItems();
							    		answer.doClick();
							    	}
				    	}
				    	
				    	
				    	if (Pos==17)
				    	{
				    		for (int indice=2;indice<respuestas.size();indice++)
				    		{
				    			jugadores.add(respuestas.get(indice));
				    		}
				    		
				    		//=============================CREACION DEL EQUIPO DE FANTASIA======================================//
				    		
				    		cajaRespuesta.setVisible(false);
				    		answer.setVisible(false);
				    		facade.crearEquipoFantasia(respuestas.get(0), user, jugadores, respuestas.get(1));
				    		Pos=0;	
				    		
				    		creacion.dispatchEvent(new WindowEvent(crearEquipo, WindowEvent.WINDOW_CLOSING));
					    		
					    		//Creacion de ventana para la seleccion de los equipos creados
					    		JFrame choseTeam = new JFrame("Equipos Creados");
					    		choseTeam.setSize(new Dimension(370,180));
					    		choseTeam.setIconImage(iconoFrame.getImage());
								Color color2 = new Color(255,210,168);
								choseTeam.getContentPane().setBackground(color2);
								choseTeam.setLocationRelativeTo(null);
								choseTeam.setLayout(new GridBagLayout());
								
								JPanel panelLista = new JPanel();
								panelLista.setBackground(color2);
								
								JTextArea texto = new JTextArea("< Selecciona tu Equipo de Fantasia >");
								texto.setBackground(color2);
								texto.setFont(new Font("Monaco", Font.BOLD, 14));
								texto.setEditable(false);
					
								JComboBox<String> combo1=new JComboBox<String>();
						        combo1.setBounds(10,10,300,20);
						        combo1.addItem("--SELECCIONA--");
						        ArrayList<EquipoFantasia>ListaEquiposDelPropietario = facade.getEquiposFant().get(user);
						        
						        JButton confirmarEq = new JButton("confirmar");
						        confirmarEq.setFont(new Font("Monaco", Font.BOLD, 16));
						        confirmarEq.setHorizontalAlignment(SwingConstants.CENTER);
						        confirmarEq.setBackground(color1);
						        confirmarEq.setAlignmentX(CENTER_ALIGNMENT);
						        
						        Box caja2 = Box.createVerticalBox();
						        caja2.add(texto);
						        
						        
						        if (ListaEquiposDelPropietario!=null)
								        {
								        for (EquipoFantasia eq: ListaEquiposDelPropietario)
								        {
								        	combo1.addItem(eq.getNombre());
								        }
								        
								        panelLista.add(combo1);
								        caja2.add(panelLista);
								       
								        }
						        
						        else 
								        {
								        	texto.setText("Aun no has creado ningun Equipo De Fantasia :(");
								        }
						        
						        caja2.add(confirmarEq);
						        choseTeam.add(caja2, new GridBagConstraints());
						        choseTeam.setVisible(true);
						        confirmarEq.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent e)
									{
								            String seleccionado=(String)combo1.getSelectedItem();
								            
										            if (seleccionado != "--SELECCIONA--") {
										            	ArrayList<EquipoFantasia>ListaEquipos = facade.getEquiposFant().get(user);
												    	if (ListaEquipos!=null)
												    		{for (EquipoFantasia eq: ListaEquipos)
																    	{
																    		if (eq.getNombre()==seleccionado)
																    		{equipoSelected = eq;}
																    	}
												    		
											         VentanaPropietario ventanaProp  =new VentanaPropietario(user, seleccionado, equipoSelected,facade);
													ventanaProp.setVisible(true);
													choseTeam.dispatchEvent(new WindowEvent(crearEquipo, WindowEvent.WINDOW_CLOSING));
										            }
								        }	
									}
						        });
					    	
					    	
						}				
				}}
					);
				}	
			});
			
			No.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e)
				{
					crearEquipo.setVisible(false);
					//Creacion de ventana para la seleccion de los equipos creados
		    		JFrame choseTeam = new JFrame("Equipos Creados");
		    		choseTeam.setSize(new Dimension(370,180));
		    		choseTeam.setIconImage(iconoFrame.getImage());
					Color color2 = new Color(255,210,168);
					choseTeam.getContentPane().setBackground(color2);
					choseTeam.setLocationRelativeTo(null);
					choseTeam.setLayout(new GridBagLayout());
					
					JPanel panelLista = new JPanel();
					panelLista.setBounds(10,10,300,20);
					panelLista.setBackground(color2);
					
					JTextArea texto = new JTextArea("< Selecciona tu Equipo de Fantasia >");
					texto.setBackground(color2);
					texto.setFont(new Font("Monaco", Font.BOLD, 14));
					texto.setEditable(false);
		
					JComboBox<String> combo1=new JComboBox<String>();
			        combo1.setBounds(10,10,300,20);
			        combo1.addItem("--SELECCIONA--");
			        ArrayList<EquipoFantasia>ListaEquiposDelPropietario = facade.getEquiposFant().get(user);
			        
			        JButton confirmarEq = new JButton("confirmar");
			        confirmarEq.setFont(new Font("Monaco", Font.BOLD, 16));
			        confirmarEq.setHorizontalAlignment(SwingConstants.CENTER);
			        confirmarEq.setBackground(color1);
			        confirmarEq.setAlignmentX(CENTER_ALIGNMENT);
			        
			        Box caja2 = Box.createVerticalBox();
			        caja2.add(texto);
			        
			        if (ListaEquiposDelPropietario!=null)
					        {
					        for (EquipoFantasia eq: ListaEquiposDelPropietario)
					        {
					        	combo1.addItem(eq.getNombre());
					        }
					        
					        panelLista.add(combo1);
					        caja2.add(panelLista);
					       
					        }
			        
			        else 
					        {
					        	texto.setText("Aun no has creado ningun Equipo De Fantasia :(");
					        }
			        caja2.add(confirmarEq);
			        choseTeam.add(caja2, new GridBagConstraints());
			        choseTeam.setVisible(true);
			        
			        confirmarEq.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e)
						{
					            String seleccionado=(String)combo1.getSelectedItem();
					            
							            if (seleccionado != "--SELECCIONA--") {
							            	ArrayList<EquipoFantasia>ListaEquipos = facade.getEquiposFant().get(user);
									    	if (ListaEquipos!=null)
									    		{for (EquipoFantasia eq: ListaEquipos)
													    	{
													    		if (eq.getNombre()==seleccionado)
													    		{equipoSelected = eq;}
													    	}
									    		
								         VentanaPropietario ventanaProp  =new VentanaPropietario(user, seleccionado, equipoSelected, facade);
										ventanaProp.setVisible(true);
										choseTeam.dispatchEvent(new WindowEvent(crearEquipo, WindowEvent.WINDOW_CLOSING));
							            }
					        }	
						}
			        });
				}
			        
			});
		}
		
		    if (c==1)
		    {
		    }
		    else if(c==2)
		    {
		    	VentanaAdmin ventanaAdmin  =new VentanaAdmin();
				ventanaAdmin.setVisible(true);
				ventanaAdmin.pack();
		    }
		  } 
		} );
	

	//** "containerBox" es el contenedor que tiene los componentes donde el usuario ingresa su nombre y contrasena
	Box containerBox = Box.createVerticalBox();
	containerBox.add(usuarioField,BorderLayout.CENTER);
	containerBox.add(passField,BorderLayout.CENTER);
	containerBox.add(iniciarSesion, BorderLayout.CENTER);
	GridBagConstraints grillaBox = new GridBagConstraints();
	grillaBox.anchor = GridBagConstraints.CENTER;
	grillaBox.fill = GridBagConstraints.NONE;
	add(containerBox, grillaBox);
	
	//** despliegue del Titulo "EQUIPOS DE FANTASIA"
	JLabel labelTitulo = new JLabel();
	ImageIcon iconoLabel = new ImageIcon("./InterfazProyecto2/data/titulo.png");
	JPanel iconoPanel = new JPanel();
	labelTitulo.setIcon(iconoLabel);
	iconoPanel.add(labelTitulo);
	iconoPanel.setBackground(color0);
	GridBagConstraints grilla = new GridBagConstraints();
	grilla.gridx = 0;
	grilla.gridy = 0;
	grilla.gridwidth = 1;
	grilla.gridheight = 0;
	grilla.weighty = 1.0;
	grilla.anchor = GridBagConstraints.NORTH;
	grilla.fill = GridBagConstraints.NONE;
	add(iconoPanel, grilla);
	
	
	//** BOTON/LABEL "o"
	JLabel o = new JLabel("o");
	o.setPreferredSize(new Dimension(200,100));
	o.setFont(new Font("Monaco", Font.BOLD, 16));
	o.setHorizontalAlignment(SwingConstants.CENTER);
	o.setAlignmentX(CENTER_ALIGNMENT);
	
	// ** BOTON/LABEL "Registrarse Como Usuario"
	JLabel lblReg = new JLabel("Registrarse como usuario."); //LISTENER DEL LABEL: action performed -> new VentanaRegistroUsuario
	boolean ventanaOpen = false;
	lblReg.addMouseListener(new MouseAdapter() {   
        @Override
        public void mousePressed(MouseEvent e) {
        	{
            regUser = new VentanaRegUsuario();
            regUser.setVisible(true);
            facade.registrarPropietario(regUser.getName(), regUser.getPass());
            
            }
        }
    });
	
	
	Font font = new Font("Monaco", Font.PLAIN, 18);
	lblReg.setFont(font);
	@SuppressWarnings("rawtypes")
	Map attributes = font.getAttributes();
    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);//Underline text constructor #Ignore
    lblReg.setFont(font.deriveFont(attributes));
	lblReg.setHorizontalAlignment(SwingConstants.CENTER);
	lblReg.setAlignmentX(CENTER_ALIGNMENT);
	
	// BOTON/LABEL "Registrarese Como Administrador"
	JLabel lblRegAdm = new JLabel("Registrarse como administrador.");
	lblRegAdm.setFont(font.deriveFont(attributes));
	lblRegAdm.setHorizontalAlignment(SwingConstants.CENTER);
	lblRegAdm.setAlignmentX(CENTER_ALIGNMENT);
	lblRegAdm.addMouseListener(new MouseAdapter() {   
        @Override
        public void mousePressed(MouseEvent e) {
        	if(ventanaOpen==false) {
            regAdmin = new VentanaRegAdmin();
            regAdmin.setVisible(true);
            facade.registrarAdmin(regAdmin.getName(), regAdmin.getPass(),regAdmin.getKey());
        	}
        }
    });
	
	
	
	Box lblBox = Box.createVerticalBox();
	lblBox.add(o,BorderLayout.CENTER);
	lblBox.add(lblReg,BorderLayout.CENTER);
	lblBox.add(lblRegAdm,BorderLayout.CENTER);
	lblBox.setAlignmentX(CENTER_ALIGNMENT);
	GridBagConstraints grillaBox2 = new GridBagConstraints();
	grillaBox2.gridx = 0;
	grillaBox2.gridy = 0;
	grillaBox2.gridwidth = 2;
	grillaBox2.gridheight = 1;
	grillaBox2.weighty = 1.0;
	grillaBox2.anchor = GridBagConstraints.SOUTH;
	grillaBox2.fill = GridBagConstraints.NONE;
	add(lblBox, grillaBox2);
	
	}

}
