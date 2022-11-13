package Model;


public class Usuario {

	private String nombreUsuario;
	private String contrasena;

	public Usuario(String nombre, String contrasena)
	{
		this.nombreUsuario = nombre;
		this.contrasena = contrasena;
		
	}
	
	public String getNombre()
	{
		return this.nombreUsuario;
	}
	private String getContrasena()
	{
		return this.contrasena;
	}
}
