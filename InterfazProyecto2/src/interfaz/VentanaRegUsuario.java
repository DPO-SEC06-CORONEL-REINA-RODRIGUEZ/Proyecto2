package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class VentanaRegUsuario extends JFrame {

		private String name="";
		private String pass="";
	
	// ===========================CONSTRUCTOR VENTANA DE REGISTRO USUARIO================================///
	
			public VentanaRegUsuario ()
	
	{
		setTitle("Registro Usuario");
		setSize(600,600);
		ImageIcon iconoFrame = new ImageIcon("./InterfazProyecto2/data/balon.png");
		setIconImage(iconoFrame.getImage());
		setLocationRelativeTo(null);
		Color color0 = new Color(255,210,210);
		getContentPane().setBackground(color0);
		
		// Grilla principal del frame
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
		JButton iniciarSesion = new JButton("Registrarse");
		iniciarSesion.setPreferredSize(new Dimension(2,25));
		iniciarSesion.setFont(new Font("Monaco", Font.PLAIN, 20));
		iniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		iniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
		Color color1 = new Color(250,110,110);
		iniciarSesion.setBackground(color1);
		iniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
			    name = usuarioField.getText();
			    pass = passField.getText();
			    
			  } 
			} );
		
		
		//** "containerBox" es el contenedor que tiene los componentes donde el usuario ingresa su nombre y contrasena
		Box containerBox = Box.createVerticalBox();
		containerBox.add(usuarioField,BorderLayout.CENTER);
		containerBox.add(passField,BorderLayout.CENTER);
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
		ImageIcon iconoLabel = new ImageIcon("./InterfazProyecto2/data/tituloRegUsuario.png");
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
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPass()
	{
		return this.pass;
	}
	
}
