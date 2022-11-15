/*
 * Panel inicial, panel de log-in
 */
package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelInicial extends PlantillaPanel {

	FacadeGUI facadeGUI = FacadeGUI.getInstance();

	private Color bGrndColor = new java.awt.Color(100, 200, 254);

	JLabel lblTitulo;
	JLabel lblAccion;
	JLabel lblNombre;
	JTextField txtNombre;
	JLabel lblContraseña;
	JTextField txtContraseña;
	JButton btnEntrar;
	JLabel lblRegistrarseUsuario;
	JLabel lblRegistrarseAdmin;
	SpringLayout layout;

	JLabel lblError;

	public PanelInicial() {
		super();
		/* De arriba para abajo */
		SpringLayout layout = new SpringLayout();
		this.layout = layout;
		setLayout(layout);
		setSize(new Dimension(size_x, size_y));
		setBackground(bGrndColor);

		// lblTitulo
		lblTitulo = new JLabel("Fútbol de Fantasía");
		lblTitulo.setFont(fuenteT);
		add(lblTitulo);
		layout.putConstraint(SpringLayout.WEST, lblTitulo, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblTitulo, espacio_grande, SpringLayout.NORTH, this);

		// lblAccion
		lblAccion = new JLabel("Iniciar sesión");
		add(lblAccion);
		layout.putConstraint(SpringLayout.WEST, lblAccion, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblAccion, espacio_grande, SpringLayout.NORTH, lblTitulo);

		// lblNombre
		lblNombre = new JLabel("Ingrese su nombre de usuario");
		add(lblNombre);
		layout.putConstraint(SpringLayout.WEST, lblNombre, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblNombre, espacio_grande, SpringLayout.NORTH, lblAccion);

		// txtNombre
		txtNombre = new JTextField(20);
		add(txtNombre);
		layout.putConstraint(SpringLayout.WEST, txtNombre, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, txtNombre, 20, SpringLayout.NORTH, lblNombre);

		// lblContraseña
		lblContraseña = new JLabel("Ingrese su contraseña");
		add(lblContraseña);
		layout.putConstraint(SpringLayout.WEST, lblContraseña, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblContraseña, 30, SpringLayout.NORTH, txtNombre);

		// txtContraseña
		txtContraseña = new JTextField(20);
		add(txtContraseña);
		layout.putConstraint(SpringLayout.WEST, txtContraseña, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, txtContraseña, 20, SpringLayout.NORTH, lblContraseña);

		// btnEntrar
		btnEntrar = new JButton("Ingresar");
		add(btnEntrar);
		layout.putConstraint(SpringLayout.WEST, btnEntrar, margen_west + 140, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, btnEntrar, espacio_grande, SpringLayout.NORTH, txtContraseña);
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkPassword();
			}
		});

		// lblRegistrarseUsuario
		lblRegistrarseUsuario = new JLabel(">> Registrarse como usuario <<");
		add(lblRegistrarseUsuario);
		layout.putConstraint(SpringLayout.WEST, lblRegistrarseUsuario, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRegistrarseUsuario, espacio_grande * 2, SpringLayout.NORTH,
				btnEntrar);
		lblRegistrarseUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				ventanaPrincipal.aPanelRegistrarPropietario();
			}
		});

		// lblRegistrarseAdmin
		lblRegistrarseAdmin = new JLabel(">> Registrarse como administrador <<");
		add(lblRegistrarseAdmin);
		layout.putConstraint(SpringLayout.WEST, lblRegistrarseAdmin, margen_west, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lblRegistrarseAdmin, 30, SpringLayout.NORTH, lblRegistrarseUsuario);
		lblRegistrarseAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				ventanaPrincipal.aPanelRegistrarAdministrador();
			}
		});

	}

	public void checkPassword() {
		String nUsuario = txtNombre.getText();
		String contraseña = txtContraseña.getText();
		facadeGUI.verificarLogin(nUsuario, contraseña);

	}

}
