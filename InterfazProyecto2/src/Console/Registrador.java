package Console;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Model.Usuario;
import Model.Administrador;
import Model.Propietario;


public class Registrador {
	
    private static HashMap<String, Usuario> usersMap = new HashMap<String,Usuario>(); //MODIFICACION UML
    private static HashMap<String,String> usersInfo = new HashMap<String, String>();
//MODIFIACION UML; AnADIMOS UN HASHMAP DE NOMBRES Y CONTRASEnAS

	
	public Registrador() {
		try {cargarUsuarios();} catch (IOException e) {e.printStackTrace();}
		
    }

    public void registrarPropietario(String nombre,  String contrasena) throws Exception {//MODIFICACION UML; public void method.
    	Usuario prop = new Propietario(nombre, contrasena);
    	if (nombreYaExiste(prop) == true) {throw new Exception("Error al registrarse: El nombre ya existe\n");};
    	usersInfo.put(nombre, contrasena);
    	usersMap.put(contrasena, prop);
    	FileWriter fw = new FileWriter("./InterfazProyecto2/data/Usuarios/usuarios.txt",true);
    	fw.write("\nusuario,"+nombre+","+contrasena);//appends the string to the file
        fw.close();
    	}
    
    

    public void registrarAdmin(String nombre,  String contrasena, String key) throws Exception {
    	//MODIFICACION UML; public void method.
    	Administrador admin = new Administrador(nombre, contrasena, key);
    	if (admin.keyValido(key)==false) {throw new Exception("Error al registrarse como administrador: Key Incorrecta\n");}
    	usersInfo.put(nombre, contrasena);
    	usersMap.put(contrasena, admin);
    	FileWriter fw = new FileWriter("./InterfazProyecto2/data/Usuarios/usuarios.txt",true);
    	fw.write("\nadmin,"+nombre+","+contrasena);//appends the string to the file
        fw.close();
    }

    public int iniciarSesionUsuario(String nombre, String contrasena) throws Exception{ //MODIFICACION UML; public INT method.
    	Usuario prop = new Propietario(nombre, contrasena);
    	int c=2; //inicio de sesion default como ADMIN;
    	
    	if (usersInfo.containsKey(nombre)==false) {throw new Exception("Error al iniciar sesion: Usuario no registrado\n");};
    	//CAMBIOS UML; FALTA PONER LA EXCEPCION DE SI LA CUENTA ESTA REGISTRADA O NO
    	if (contrasenaCorrecta(nombre, contrasena)==false) {throw new Exception("Error al iniciar sesion: Contrasena Incorrecta\n");};
    	//informar ingreso exitoso
    	if (usersMap.get(contrasena).getClass().equals(prop.getClass())) {c=1;};//Inicio sesion como PROP.
    	{
    	return c;
    	}
    	
    }
    

    private boolean contrasenaCorrecta(String nombre, String contrasena) {
    	boolean c = false;
    	if (usersInfo.get(nombre).equals(contrasena)) {c=true;};
        return c;
    }
    
    private boolean nombreYaExiste(Usuario user)
    {
    	boolean c =false;
    	if (usersInfo.containsKey(user.getNombre())==true) {c=true;};
    	return c;
    }
    
    private static void cargarUsuarios() throws IOException{
		

		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		
		BufferedReader br = new BufferedReader(new FileReader("./InterfazProyecto2/data/Usuarios/usuarios.txt"));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			
			String[] partes = linea.split(",");
			String tipoUsuario = partes[0];
			String nombreUsuario = partes[1];
			String contrasenaUsuario = partes[2];
			
			if(tipoUsuario.equals("admin")) 
				{
					Administrador admin = new Administrador(nombreUsuario,contrasenaUsuario,"ADMONKEY");
					usersInfo.put(nombreUsuario,contrasenaUsuario);
					usersMap.put(contrasenaUsuario,admin);
				}
			else if (tipoUsuario.equals("usuario")) 
				{
					Propietario prop = new Propietario(nombreUsuario,contrasenaUsuario);
					usersInfo.put(nombreUsuario,contrasenaUsuario);
					usersMap.put(contrasenaUsuario, prop);
				}
			
			
			linea = br.readLine();
		}

		br.close();
		
		
	
	}

}
