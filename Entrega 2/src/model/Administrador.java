/*
 * Administrador representa a la persona administradora de la aplicación
 */
package model;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable{
	private static final long serialVersionUID = 4517748305219345589L;

	private static final String Key = "ADMONKEY";
											
	private String nombreUsuario;
	private String contraseña;
	

	/**
	 * Instantiates a new administrador.
	 *
	 * @param nombre the nombre
	 * @param contraseña the contraseña
	 */
	public Administrador(String nombre, String contraseña) {
		this.nombreUsuario = nombre;
		this.contraseña = contraseña;
	}

	/**
	 * Key valido.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	public static boolean keyValido(String key)
	{
		boolean c = false;
		if (Key.equals(key)) {c=true;};
		return c;
	}
}
