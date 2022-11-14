package model;

public abstract class Usuario {

	private String nombreUsuario;
	private String contraseña;

	
	public String getNombre()
	{
		return nombreUsuario;
	}
	
	
	@SuppressWarnings("unused")
	private String getContraseña()
	{
		return contraseña;
	}
	
	public boolean revisarContraseña(String nUsuario, String contraseña)
	{
		return true;
	}
}