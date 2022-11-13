package Model;

public class Administrador extends Usuario{
	private static final String Key = "ADMONKEY";
	//ASUMIMOS LA EXISTENCIA DE UN ADMIN y QUE SE TIENE QUE REGISTRAR
											

	//Constructor
	public Administrador(String nombre, String contrasena, String key) {
		super(nombre, contrasena);
	}
	
	public boolean keyValido(String key)
	{
		boolean c = false;
		if (Key.equals(key)) {c=true;};
		return c;
	}

}
