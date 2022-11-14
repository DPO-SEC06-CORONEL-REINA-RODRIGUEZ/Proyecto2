package interfaz;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import model.Propietario;

@SuppressWarnings("serial")
public class PanelCrearEquipo extends PlantillaPanel{
	
	
	Propietario propietarioActual;
	
	JButton btnCerrarSesion;
	protected Color bGrndColor = new java.awt.Color(200, 200, 200);
	
	
	public PanelCrearEquipo(Propietario aUsuario) 
	{	
		
		this.propietarioActual = aUsuario;
		
		
		
		SpringLayout grid = new SpringLayout();
		setLayout(grid);
		setSize(new Dimension(size_x,size_y));
		setBackground(bGrndColor);
		
		
		// btnCerrarSesion
		btnCerrarSesion = new JButton("Cerrar sesión");
		grid.putConstraint(SpringLayout.NORTH, btnCerrarSesion, 46, SpringLayout.NORTH, this);
		grid.putConstraint(SpringLayout.WEST, btnCerrarSesion, 10, SpringLayout.WEST, this);
		add(btnCerrarSesion);
		
		btnCerrarSesion.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
			   ventanaPrincipal.volverLogin();
			} 
		});
		
		// btnVolverPropietario
		JButton btnVolverPropietario = new JButton("Menú propietario");
		grid.putConstraint(SpringLayout.NORTH, btnVolverPropietario, espacio_pequeño, SpringLayout.SOUTH, btnCerrarSesion);
		grid.putConstraint(SpringLayout.WEST, btnVolverPropietario, 0, SpringLayout.WEST, btnCerrarSesion);
		grid.putConstraint(SpringLayout.EAST, btnCerrarSesion, 0, SpringLayout.EAST, btnVolverPropietario);
		add(btnVolverPropietario);
		
		btnVolverPropietario.addActionListener(new ActionListener() 
		{ 
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
			   ventanaPrincipal.volverPropietario(propietarioActual);
			} 
		});
		
		
		//lblTitulo
		JLabel lblTitulo = new JLabel("Fútbol de fantasía");
		lblTitulo.setFont(fuenteT);
		grid.putConstraint(SpringLayout.NORTH, lblTitulo, 4, SpringLayout.NORTH, btnCerrarSesion);
		grid.putConstraint(SpringLayout.WEST, lblTitulo, espacio_grande, SpringLayout.EAST, btnVolverPropietario);
		
		add(lblTitulo);
		
		//lblBienvenida
		JLabel lblBienvenida = new JLabel("Bienvenido propietario");
		lblBienvenida.setFont(fuenteBold);
		grid.putConstraint(SpringLayout.NORTH, lblBienvenida, espacio_pequeño, SpringLayout.SOUTH, lblTitulo);
		grid.putConstraint(SpringLayout.WEST, lblBienvenida, 0, SpringLayout.WEST, lblTitulo);
		add(lblBienvenida);
		
		//lblUsername
		String username = propietarioActual.getNombre();
		JLabel lblUsername = new JLabel(username);
		lblUsername.setFont(fuenteBold);
		grid.putConstraint(SpringLayout.NORTH, lblUsername, 0, SpringLayout.NORTH, lblBienvenida);
		grid.putConstraint(SpringLayout.WEST, lblUsername, 6, SpringLayout.EAST, lblBienvenida);
		add(lblUsername);
		
		
		
		
		//lblMensaje1
		JLabel lblMensaje1 = new JLabel("Seleccione la temporada planeada en la que creará el equipo de fantasía");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje1, espacio_grande/2, SpringLayout.SOUTH, lblBienvenida);
		grid.putConstraint(SpringLayout.WEST, lblMensaje1, 0, SpringLayout.WEST, lblBienvenida);
		add(lblMensaje1);
		
		//jcbTemporadas
		String[] tempToSelect = facadeGUI.getKeysTemporadasPlaneadas();
		JComboBox<String> jcbTemporadas = new JComboBox<String>(tempToSelect);
		jcbTemporadas.setEditable(false);
		jcbTemporadas.setPreferredSize(new Dimension(100, 20));
		jcbTemporadas.setSelectedIndex(0);
		add(jcbTemporadas);
		grid.putConstraint(SpringLayout.EAST, jcbTemporadas,0,SpringLayout.EAST, lblMensaje1); 
		grid.putConstraint(SpringLayout.NORTH, jcbTemporadas,10,SpringLayout.SOUTH, lblMensaje1); 
		
		
		
		//lblMensaje2
		JLabel lblMensaje2 = new JLabel("Seleccione el nombre de su equipo");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje2, espacio_pequeño, SpringLayout.SOUTH, jcbTemporadas);
		grid.putConstraint(SpringLayout.WEST, lblMensaje2, 0, SpringLayout.WEST, lblBienvenida);
		add(lblMensaje2);
		
		//txtKey
		JTextField txtKey = new JTextField();
		grid.putConstraint(SpringLayout.NORTH, txtKey, espacio_pequeño, SpringLayout.SOUTH, lblMensaje2);
		grid.putConstraint(SpringLayout.WEST, txtKey, 0, SpringLayout.WEST, lblMensaje2);
		txtKey.setColumns(10);
		add(txtKey);
		
		//lblMensajeA
		JLabel lblMensajeA = new JLabel("Seleccione la alineación de su equipo:");
		grid.putConstraint(SpringLayout.NORTH, lblMensajeA, espacio_pequeño, SpringLayout.SOUTH, txtKey);
		grid.putConstraint(SpringLayout.WEST, lblMensajeA, 0, SpringLayout.WEST, txtKey);
		add(lblMensajeA);
		
		
		
		
		
		//Jugadores
		ArrayList<String> iDelanteroT = new ArrayList<>();
		//btnDelanteros
		JButton btnDelanteros = new JButton("delanteros titulares (2)");
		grid.putConstraint(SpringLayout.WEST, btnDelanteros, 0, SpringLayout.WEST, lblMensajeA);
		grid.putConstraint(SpringLayout.NORTH, btnDelanteros, espacio_pequeño, SpringLayout.SOUTH, lblMensajeA);
		add(btnDelanteros);
		btnDelanteros.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("FW");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iDelanteroT.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		//btnDelanteroSuplente
		ArrayList<String> iDelantero = new ArrayList<>();
		JButton btnDelanteroSuplente = new JButton("delantero suplente");
		grid.putConstraint(SpringLayout.WEST, btnDelanteroSuplente, espacio_pequeño, SpringLayout.EAST, btnDelanteros);
		grid.putConstraint(SpringLayout.NORTH, btnDelanteroSuplente, 0, SpringLayout.NORTH, btnDelanteros);
		add(btnDelanteroSuplente);
		btnDelanteroSuplente.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("FW");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iDelantero.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		
		//btnMediocampistas
		ArrayList<String> iMediocampistasT = new ArrayList<>();
		JButton btnMediocampistas = new JButton("mediocampistas titulares (4)");
		grid.putConstraint(SpringLayout.WEST, btnMediocampistas, 0, SpringLayout.WEST, btnDelanteros);
		grid.putConstraint(SpringLayout.NORTH, btnMediocampistas, espacio_pequeño, SpringLayout.SOUTH, btnDelanteros);
		add(btnMediocampistas);
		btnMediocampistas.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("MF");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iMediocampistasT.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		
		//btnMediocampistaSuplente
		ArrayList<String> iMediocampistas = new ArrayList<>();
		JButton btnMediocampistaSuplente = new JButton("mediocampista suplente");
		grid.putConstraint(SpringLayout.WEST, btnMediocampistaSuplente, espacio_pequeño, SpringLayout.EAST, btnMediocampistas);
		grid.putConstraint(SpringLayout.NORTH, btnMediocampistaSuplente, 0, SpringLayout.NORTH, btnMediocampistas);
		add(btnMediocampistaSuplente);
		btnMediocampistaSuplente.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("MF");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iMediocampistas.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		
		
		//btnDefensas
		ArrayList<String> iDefensasT = new ArrayList<>();
		JButton btnDefensas = new JButton("defensas titulares (4)");
		grid.putConstraint(SpringLayout.WEST, btnDefensas, 0, SpringLayout.WEST, btnMediocampistas);
		grid.putConstraint(SpringLayout.NORTH, btnDefensas, espacio_pequeño, SpringLayout.SOUTH, btnMediocampistas);
		add(btnDefensas);
		btnDefensas.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("DF");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iDefensasT.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		
		//btnDefensaSuplente
		ArrayList<String> iDefensas = new ArrayList<>();
		JButton btnDefensaSuplente = new JButton("defensa suplente");
		grid.putConstraint(SpringLayout.WEST, btnDefensaSuplente, espacio_pequeño, SpringLayout.EAST, btnDefensas);
		grid.putConstraint(SpringLayout.NORTH, btnDefensaSuplente, 0, SpringLayout.NORTH, btnDefensas);
		add(btnDefensaSuplente);
		btnDefensaSuplente.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("DF");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iDefensas.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		
		//btnArquero
		ArrayList<String> iArqueroT = new ArrayList<>();
		JButton btnArquero = new JButton("arquero titular");
		grid.putConstraint(SpringLayout.WEST, btnArquero, 0, SpringLayout.WEST, btnDefensas);
		grid.putConstraint(SpringLayout.NORTH, btnArquero, espacio_pequeño, SpringLayout.SOUTH, btnDefensas);
		add(btnArquero);
		btnArquero.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("GK");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iArqueroT.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});
		
		//btnArqueroSuplente
		ArrayList<String> iArquero = new ArrayList<>();
		JButton btnArqueroSuplente = new JButton("arquero suplente");
		grid.putConstraint(SpringLayout.WEST, btnArqueroSuplente, espacio_pequeño, SpringLayout.EAST, btnArquero);
		grid.putConstraint(SpringLayout.NORTH, btnArqueroSuplente, 0, SpringLayout.NORTH, btnArquero);
		add(btnArqueroSuplente);
		btnArqueroSuplente.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadoresPosicion("GK");
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iArquero.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});		
		
		grid.putConstraint(SpringLayout.EAST, btnDefensas, 0, SpringLayout.EAST, btnMediocampistas);
		grid.putConstraint(SpringLayout.EAST, btnDelanteros, 0, SpringLayout.EAST, btnMediocampistas);
		grid.putConstraint(SpringLayout.EAST, btnArquero, 0, SpringLayout.EAST, btnMediocampistas);
		
		grid.putConstraint(SpringLayout.EAST, btnDelanteroSuplente, 0, SpringLayout.EAST, btnMediocampistaSuplente);
		grid.putConstraint(SpringLayout.EAST, btnDefensaSuplente, 0, SpringLayout.EAST, btnMediocampistaSuplente);
		grid.putConstraint(SpringLayout.EAST, btnArqueroSuplente, 0, SpringLayout.EAST, btnMediocampistaSuplente);
		
		
		//btnCapitan
		ArrayList<String> iCapitan = new ArrayList<>();
		JButton btnCapitan = new JButton("capitan del equipo");
		grid.putConstraint(SpringLayout.WEST, btnCapitan, 0, SpringLayout.WEST, btnArquero);
		grid.putConstraint(SpringLayout.NORTH, btnCapitan, espacio_pequeño, SpringLayout.SOUTH, btnArquero);
		add(btnCapitan);
		btnCapitan.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] subject = facadeGUI.getJugadores();
				JFrame f = new JFrame();
				JList<String> list = new JList<String>(subject);
				JScrollPane s = new JScrollPane(list);
				s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				f.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
				f.add(s);
				f.setSize(300, 300);
				f.setVisible(true);
				
				MouseListener mouseListener = new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				    	String selectedItem = list.getSelectedValue();
				    	iCapitan.add(selectedItem);
				    }
				};
				
				list.addMouseListener(mouseListener);	
				
			}
		});	
		
		
		
		//btnCrearEquipo
		JButton btnCrearEquipo = new JButton("Crear equipo");
		grid.putConstraint(SpringLayout.EAST, btnCrearEquipo, 0, SpringLayout.EAST, btnArqueroSuplente);
		grid.putConstraint(SpringLayout.NORTH, btnCrearEquipo, espacio_pequeño, SpringLayout.SOUTH, btnArqueroSuplente);
		add(btnCrearEquipo);
		btnCrearEquipo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				HashMap<String,ArrayList<String>> confJugadores = new HashMap<>();
				confJugadores.put("arqueroT", iArqueroT);
				confJugadores.put("arqueroS", iArquero);
				confJugadores.put("defensasT", iDefensasT);
				confJugadores.put("defensaS", iDefensas);
				confJugadores.put("mediocampistasT", iMediocampistasT);
				confJugadores.put("mediocampistaS", iMediocampistas);
				confJugadores.put("delanterosT", iDelanteroT);
				confJugadores.put("delanteroS", iDelantero);
				confJugadores.put("capitan", iCapitan);
				
				String keyTemporada = (String) jcbTemporadas.getSelectedItem();
				String keyEquipo = txtKey.getText();
				facadeGUI.crearEquipoFantasia(propietarioActual, keyEquipo, keyTemporada, confJugadores);
			
			}});
		
		//lblMensaje3
		JLabel lblMensaje3 = new JLabel("Con un click sobre el nombre,");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje3, espacio_pequeño, SpringLayout.SOUTH, btnCapitan);
		grid.putConstraint(SpringLayout.WEST, lblMensaje3, 0, SpringLayout.WEST, btnCapitan);
		add(lblMensaje3);
		
		 
		
		//lblMensaje4
		JLabel lblMensaje4 = new JLabel("ya vinculas este jugador a tu equipo");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje4, 0, SpringLayout.SOUTH, lblMensaje3);
		grid.putConstraint(SpringLayout.WEST, lblMensaje4, 0, SpringLayout.WEST, lblMensaje3);
		add(lblMensaje4);
		
		//lblMensaje5
		JLabel lblMensaje5 = new JLabel("Presupuesto inicial:");
		grid.putConstraint(SpringLayout.NORTH, lblMensaje5, 0, SpringLayout.NORTH, lblMensaje2);
		grid.putConstraint(SpringLayout.EAST, lblMensaje5, 0, SpringLayout.EAST, btnArqueroSuplente);
		add(lblMensaje5);
		
		//txtPresupuesto
	    Integer p = (Integer) facadeGUI.getPresupuestoInicial();
	    String ps = p.toString();
	    JTextField txtPresupuesto = new JTextField(ps);
	    grid.putConstraint(SpringLayout.NORTH, txtPresupuesto, espacio_pequeño, SpringLayout.SOUTH, lblMensaje5);
	    
	    grid.putConstraint(SpringLayout.EAST, txtPresupuesto, 0, SpringLayout.EAST, lblMensaje5);
	    txtPresupuesto.setColumns(10);
	    txtPresupuesto.setEditable(false);
	    add(txtPresupuesto);
	    
	    
	    //btnClear
	    JButton btnClear = new JButton("limpiar seleccion");
	    grid.putConstraint(SpringLayout.WEST, btnClear, 0, SpringLayout.WEST, lblMensaje4);
	    grid.putConstraint(SpringLayout.NORTH, btnClear, espacio_pequeño, SpringLayout.SOUTH, lblMensaje4);
	    add(btnClear);
	    btnClear.addActionListener(new ActionListener() 
	    {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			
	    		iCapitan.clear();
	    		iArqueroT.clear();
	    		iDefensasT.clear();
	    		iDefensas.clear();
	    		iMediocampistasT.clear();
	    		iMediocampistas.clear();
	    		iDelanteroT.clear();
	    		iDelantero.clear();
	    		iArquero.clear();

	    		
	    	}
	    });
	  	
	  	
	}
	
	
	
}
