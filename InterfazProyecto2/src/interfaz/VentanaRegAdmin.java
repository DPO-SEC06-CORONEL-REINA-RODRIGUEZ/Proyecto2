package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaRegAdmin extends JFrame{
	
	private String name;
	private String pass;
	private String key;
	
	// ===========================CONSTRUCTOR VENTANA DE REGISTRO ADMIN================================///
	
	public VentanaRegAdmin(){
		setTitle("Registro Administrador");
		setSize(600,600);
		ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/balon.png");
		setIconImage(iconoFrame.getImage());
		setLocationRelativeTo(null);
		Color color0 = new Color(255,210,210);
		getContentPane().setBackground(color0);
		
		// Layout principal del frame
		setLayout( new GridBagLayout() );
		
		
		//** Caja de texto donde el usuario ingresa su nombre
		JLabel lblignore  = new JLabel();
		lblignore.setPreferredSize(new Dimension(200,75));
		JTextField usuarioField = new JTextField("Usuario");
		usuarioField.setPreferredSize(new Dimension(200,25));
		usuarioField.setFont(new Font("Monaco", Font.PLAIN, 20));
		usuarioField.setHorizontalAlignment(SwingConstants.CENTER);
		

		
		//** Caja de texto donde el usuario ingresa su contrasena
		JTextField passField = new JTextField("password");
		passField.setPreferredSize(new Dimension(200,25));
		passField.setFont(new Font("Monaco", Font.PLAIN, 20));
		passField.setHorizontalAlignment(SwingConstants.CENTER);
		
		//** Caja de texto donde el usuario ingresa su contrasena
		JTextField keyField = new JTextField("Key");
		keyField.setPreferredSize(new Dimension(200,25));
		keyField.setFont(new Font("Monaco", Font.PLAIN, 20));
		keyField.setHorizontalAlignment(SwingConstants.CENTER);
		
		//** Boton para iniciar sesion comprobando los datos ingresados
		JButton iniciarSesion = new JButton("Registrarse");
		iniciarSesion.setPreferredSize(new Dimension(2,25));
		iniciarSesion.setFont(new Font("Monaco", Font.PLAIN, 20));
		iniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		iniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
		Color color1 = new Color(250,110,110);
		iniciarSesion.setBackground(color1);
		
		//** "containerBox" es el contenedor que tiene los componentes donde el usuario ingresa su nombre y contrasena
		Box containerBox = Box.createVerticalBox();
		containerBox.add(lblignore,BorderLayout.CENTER);
		containerBox.add(usuarioField,BorderLayout.CENTER);
		containerBox.add(passField,BorderLayout.CENTER);
		containerBox.add(keyField, BorderLayout.CENTER);
		containerBox.add(iniciarSesion, BorderLayout.CENTER);
		GridBagConstraints grillaBox = new GridBagConstraints();
		grillaBox.gridx = 0;
		grillaBox.gridy = 1;
		grillaBox.gridwidth = 1;
		grillaBox.gridheight = 0;
		grillaBox.anchor = GridBagConstraints.CENTER;
		grillaBox.fill=GridBagConstraints.HORIZONTAL;
		add(containerBox, grillaBox);
		
		//** despliegue del Titulo "EQUIPOS DE FANTASIA"
		JLabel labelTitulo = new JLabel();
		ImageIcon iconoLabel = new ImageIcon("./InterfazProyecto2/data/tituloRegAdmin.png");
		JPanel iconoPanel = new JPanel();
		labelTitulo.setIcon(iconoLabel);
		iconoPanel.add(labelTitulo);
		iconoPanel.setBackground(color0);
		GridBagConstraints grilla = new GridBagConstraints();
		grilla.gridx = 0;
		grilla.gridy = 0;
		grilla.gridwidth = 0;
		grilla.gridheight = 0;
		grilla.weighty = 1.0;
		grilla.anchor = GridBagConstraints.NORTH;
		grilla.fill = GridBagConstraints.NONE;
		add(iconoPanel, grilla);
	}
	
	public String getNombre()
	{
		return this.name;
	}
	
	public String getPass()
	{
		return this.pass;
	}
	
	public String getKey()
	{
		return this.key;
	}
}
