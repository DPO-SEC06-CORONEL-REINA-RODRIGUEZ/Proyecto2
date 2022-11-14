package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;





public class Registrador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3312771379783406920L;

	private static Registrador uniqueObject;
	
	// <nombreUsuario, contraseña>
	private  HashMap<String,String> usersInfo = new HashMap<String, String>();
	// <nombreUsuario, Usuario>
	private  HashMap<String, Usuario> usersMap = new HashMap<String,Usuario>(); 
	
	private Registrador(){}
	
	public static Registrador getInstance()
	{
		if (uniqueObject == null) {uniqueObject = new Registrador();}
		return uniqueObject;
	}
	
	
	
	public Usuario iniciarSesionUsuario(String nombre, String contraseña) throws Exception
	{ 
		Usuario iUsuario;
    	
    	if (nombre.isEmpty()) {throw new Exception("Ingrese un nombre de usuario");}
    	else if (contraseña.isEmpty()) {throw new Exception("Ingrese una contraseña");}
    	else if (usersInfo.containsKey(nombre)==false) {throw new Exception("Error al iniciar sesion: Usuario no registrado");}
    	else if (contraseñaCorrecta(nombre, contraseña)==false) {throw new Exception("Error al iniciar sesión: Contraseña Incorrecta");}
    	else 
    	{
    		iUsuario = usersMap.get(nombre);
    	}
    	
    	
    	
		return iUsuario;
    	
    }
	
	private boolean contraseñaCorrecta(String nombre, String contraseña) 
	{
    	boolean c = false;
    	if (usersInfo.get(nombre).equals(contraseña)) {c=true;};
        return c;
    }
	public boolean registrarPropietario(String nombre,  String contraseña) throws Exception 
	{
    	boolean out = false;
		
    	if (nombre.isEmpty()) {throw new Exception("Ingrese un nombre de usuario");}
    	else if (contraseña.isEmpty()) {throw new Exception("Ingrese una contraseña");}
    	else if (nombreYaExiste(nombre) == true) {throw new Exception("Error al registrarse: El nombre ya existe\n");}
    	else 
    	{
    		out=true;
    		usersInfo.put(nombre, contraseña);
    		Propietario propietario = new Propietario(nombre, contraseña);
    		usersMap.put(nombre, propietario);
    		FileWriter fw = new FileWriter(".\\data\\usuarios.txt",true);
        	fw.write("\npropietario,"+nombre+","+contraseña);//appends the string to the file
            fw.close();
    	}
    	
    	
    	
    	return out;
    }
	public boolean registrarAdmin(String nombre,  String contraseña, String key) throws Exception 
	{
    	boolean out = false;
		
    	if (nombre.isEmpty()) {throw new Exception("Ingrese un nombre de usuario");}
    	else if (contraseña.isEmpty()) {throw new Exception("Ingrese una contraseña");}
    	else if (key.isEmpty()) {throw new Exception("Ingrese un Key");}
    	else if (nombreYaExiste(nombre) == true) {throw new Exception("Error al registrarse: El nombre ya existe\n");}
		
    	else if (Administrador.keyValido(key)==false) {throw new Exception("Error al registrarse como administrador: Key Incorrecta\n");}
    	else 
    	{	
    		out=true; 
    		usersInfo.put(nombre, contraseña);
    		Administrador admin = new Administrador(nombre, contraseña);
    		usersMap.put(nombre, admin);
    		FileWriter fw = new FileWriter(".\\data\\usuarios.txt",true);
        	fw.write("\nadmin,"+nombre+","+contraseña);//appends the string to the file
            fw.close();
    	}
    	
    	
    
    	return out;
    }
	private boolean nombreYaExiste(String nombre)
    {
    	boolean c =false;
    	if (usersInfo.containsKey(nombre)==true) {c=true;};
    	return c;
    }
	
	 public  void cargarUsuarios(String path) throws IOException
	 {
		
     	BufferedReader br = new BufferedReader(new FileReader(path));
     	String linea = br.readLine();
     	while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
     	{
     		
     		String[] partes = linea.split(",");
     		String tipoUsuario = partes[0];
     		String nombreUsuario = partes[1];
     		String contrasenaUsuario = partes[2];
     		
     		if(tipoUsuario.equals("admin")) 
     	    {
     	    	Administrador admin = new Administrador(nombreUsuario,contrasenaUsuario);
     	    	usersInfo.put(nombreUsuario,contrasenaUsuario);
     	    	usersMap.put(nombreUsuario,admin);
     	    }
     		
     	    else if (tipoUsuario.equals("propietario")) 
     	    {
     	    	Propietario propietario = new Propietario(nombreUsuario,contrasenaUsuario);
     	    	usersInfo.put(nombreUsuario,contrasenaUsuario);
     	    	usersMap.put(nombreUsuario, propietario);
     	    }
     	    	
     		
     		linea = br.readLine();
     	}
     
     	br.close();
     	
     	
     
     }

	public static void setObject(Registrador dataRegistrador) {
		uniqueObject = dataRegistrador;
		
	}

}
