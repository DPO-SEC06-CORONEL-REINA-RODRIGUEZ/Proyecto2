package Console;
import java.util.Scanner;

import Kernel.EquipoFantasia;
import Kernel.JugadorReal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Aplicacion {
		private Registrador reg = new Registrador();
		private Model.ControladorTemporada con = new Model.ControladorTemporada();
		private Model.ControladorVentas cVentas = new Model.ControladorVentas();
		private Model.ControladorFantasia cFantas = new Model.ControladorFantasia();
		
		
		
		public void desplegarConsola() {

	        Scanner sn = new Scanner(System.in);
	        boolean salir = false;
	        int opcion; 
	 
	        while (!salir) {
	            System.out.println("MENU PRINCIPAL\n1. Registrarse en la aplicacion");
	            System.out.println("2. Iniciar Sesion");
	            System.out.println("3. Salir de la aplicacion");

	 
	            try { 
	 
	                opcion = sn.nextInt();
	 
	                switch (opcion) {
	                
	                    case 1: //Despligue para la opcion 1: REGISTRARSE EN LA APP ->
	                        Scanner sn2 = new Scanner(System.in);
	            	        boolean salir2 = false;
	            	        int opcion2; 
	            	 
	            	        while (!salir2) {
	            	 
	            	            System.out.println("MENU DEL REGISTRO\n1. Registrarse como administrador");
	            	            System.out.println("2. Registrarse como propietario");
	            	            System.out.println("3. Regresar al menu principal");

	            	            try {
	            	 
	            	                opcion2 = sn2.nextInt();
	            	 
	            	                switch (opcion2) {
	            	                    case 1:
	            	                    	ejecutarOpcion(1);
	            	                    	salir2 = true;
	            	                        break;
	            	                    case 2:
	            	                    	ejecutarOpcion(2);
	            	                    	salir2 = true;
	            	                        break;
	            	                    case 3:
	            	                        salir2 = true;
	            	                        break;
	            	                    default:
	            	                        System.out.println("\nSyntax Error: Ingrese números entre 1 y 3");
	            	                }
	            	            } catch (InputMismatchException e) {
	            	                System.out.println("Debes insertar un número");
	            	                sn2.next();
	            	            }}
	                        break;
	                        
	                    case 2: //Despligue para la opcion 2: INICIAR SESION ->
	                    	ejecutarOpcion(3);
	                        break;
	                    case 3:
	                        salir = true;
	                        break;
	                    default:
	                        System.out.println("\nSyntax Error: Ingrese números entre 1 y 3");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Debes insertar un número");
	                sn.next();
	            }
	        }
	 
	    }
		
		
		
		private void ejecutarOpcion(int Opcion) {
			
			if (Opcion==1)
			{
				Scanner scanner = new Scanner(System.in);
				//Pedimos los datos del administrador a registrar
				System.out.print("Ingresa el nombre del administrador:\n ");
				String name = scanner.nextLine();
				System.out.print("Ingresa la contrasena del administrador:\n ");
				String pass = scanner.nextLine();
				System.out.print("Ingresa la key del administrador: ");
				String key = scanner.nextLine();
				try {
				reg.registrarAdmin(name, pass, key);
				System.out.println("Registro Exitoso!\n");
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
};

			if (Opcion==2) 
			{
				Scanner scanner = new Scanner(System.in);
				//Pedimos los datos del propietario a registrar
				System.out.print("Ingresa el nombre del usuario a registrar:\n ");
				String name = scanner.nextLine();
				System.out.print("Ingresa la contrasena del usuario a registrar:\n ");
				String pass = scanner.nextLine();
				try {
				reg.registrarPropietario(name, pass);
				System.out.println("Registro Exitoso!\n");
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
;};

			if (Opcion==3)
			{
				int c = 0;
				String message="";
				Scanner scanner = new Scanner(System.in);
				//Pedimos los datos del usuario que iniciara sesion
				System.out.print("Ingresa el nombre del usuario:\n ");
				String name = scanner.nextLine();
				System.out.print("Ingresa la contrasena del usuario:\n ");
				String pass = scanner.nextLine();
				try {
				c = reg.iniciarSesionUsuario(name, pass); 
				
				//----------------->ENTRADA DE USUARIO AL SISTEMA<-----------------
				if (c==1) {message="Propietario ";};
				if (c==2) {message="Administrador ";};
				System.out.println("Inicio de sesion Exitoso! Bienvenido, "+message+name+"\n");
				
				//----------------->ENTRADA DE PROPIETARIO AL SISTEMA<-----------------
				if (c==1) {
					Scanner sn4 = new Scanner(System.in);
        	        boolean salir4 = false;
        	        int opcion4; 
        	 
        	        while (!salir4) {
        	 
        	            System.out.println("MENU PROPIETARIO\n1. Crear equipo de fantasia!");
        	            System.out.println("2. Ver Estadisticas");
        	            System.out.println("3. Configurar Alineacion");
        	            System.out.println("4. Ver mis Equipos");//CAMBIOS UML, responsabilidad nueva
        	            System.out.println("5. Vender Jugador");
        	            System.out.println("6. Cerrar Sesion");

        	            try {
        	 
        	                opcion4 = sn4.nextInt();
        	 
        	                switch (opcion4) {
        	                    case 1:
        	        				Scanner scanner2 = new Scanner(System.in);
        	        				ArrayList<String> lista = new ArrayList<>();
        	        				
        	        				
        	        				//---> Despliegue de creacion de la plantilla del equipo de fantasia <----
        	        				System.out.print("Ingresa el nombre de tu Equipo de Fantasia!:\n ");
        	        				
        	        				
        	        				String nombreEquipo = scanner2.nextLine();
        	        				
        	        				System.out.print("Ingresa la Temporada (e.g mundial2018):\n ");
        	        				String temporada = scanner2.nextLine();

        	        				
        	        				System.out.print("Ingresa el nombre del Arquero 1!:\n ");
        	        				String arq1 = scanner2.nextLine();
        	        				lista.add(arq1);
        	        				
        	        				System.out.print("Ingresa el nombre del Arquero 2!:\n ");
        	        				String arq2= scanner2.nextLine();
        	        				lista.add(arq2);
        	        				
        	        				System.out.print("Ingresa el nombre del Defensor 1!:\n ");
        	        				String def1= scanner2.nextLine();
        	        				lista.add(def1);
        	        				
        	        				System.out.print("Ingresa el nombre del Defensor 2!:\n ");
        	        				String def2 = scanner2.nextLine();
        	        				lista.add(def2);
        	        				
        	        				System.out.print("Ingresa el nombre del Defensor 3!:\n ");
        	        				String def3 = scanner2.nextLine();
        	        				lista.add(def3);
        	        				
        	        				System.out.print("Ingresa el nombre del Defensor 4!:\n ");
        	        				String def4 = scanner2.nextLine();
        	        				lista.add(def4);
        	        				
        	        				System.out.print("Ingresa el nombre del Defensor 5!:\n ");
        	        				String def5 = scanner2.nextLine();
        	        				lista.add(def5);
        	        				
        	        				System.out.print("Ingresa el nombre del Mediocampista 1!:\n ");
        	        				String med1= scanner2.nextLine();
        	        				lista.add(med1);
        	        				
        	        				System.out.print("Ingresa el nombre del Mediocampista 2!:\n ");
        	        				String med2= scanner2.nextLine();
        	        				lista.add(med2);
        	        				
        	        				System.out.print("Ingresa el nombre del Mediocampista 3!:\n ");
        	        				String med3 = scanner2.nextLine();
        	        				lista.add(med3);
        	        				
        	        				System.out.print("Ingresa el nombre del Mediocampista 4!:\n ");
        	        				String med4 = scanner2.nextLine();
        	        				lista.add(med4);
        	        				
        	        				System.out.print("Ingresa el nombre del Mediocampista 5!:\n ");
        	        				String med5 = scanner2.nextLine();
        	        				lista.add(med5);
        	        				
        	        				System.out.print("Ingresa el nombre del Delantero 1!:\n ");
        	        				String del1= scanner2.nextLine();
        	        				lista.add(del1);
        	        				
        	        				System.out.print("Ingresa el nombre del Delantero 2!:\n ");
        	        				String del2= scanner2.nextLine();
        	        				lista.add(del2);
        	        				
        	        				System.out.print("Ingresa el nombre del Delantero 3!:\n ");
        	        				String del3 = scanner2.nextLine();
        	        				lista.add(del3);
        	        		
        	        				
        	        				
        	        				
        	        				try {
        	                    	cFantas.crearEquipoFantasia(nombreEquipo, name, lista, temporada);
        	                    	System.out.println("\nTu equipo, "+nombreEquipo+" Fue creado con exito.\n");
        	        				}
        	        				catch (Exception ex)
        	        				{
        	        					System.out.println(ex.getMessage());
        	        				}
        	        				
        	                        break;
        	                        
        	                    case 2:
        	                    	cFantas.controladorEquipos(name);
        	                        break;
        	                        
        	                    case 3://FALTA CONSIDERAR QUE SEAN 15 JUGADORES SIEMPRE PARA ALINEAR
        	                    	
        	                    	try {
            	                    	cFantas.configurarAlineacion(name);
            	                    	}
            	                    	catch(Exception ex) {System.out.println(ex.getMessage());}
            	                    	System.out.println("Alineacion configurada correctamente!\n");
            	                        break;
        	                        
        	                    /*   
        	                    case x://FALTA AGREGAR EL DINERO AL DUEnO DEL JUGADOR
        	                    	Scanner case4 = new Scanner(System.in);
        	                    	System.out.println("Ingresa el nombre del jugador que quieres comprar:\n");
        	                    	String jug = case4.nextLine();
        	                    	HashMap<String, ArrayList<EquipoFantasia>> EquiposDeFantasia = cFantas.getEquiposFant();

        	                    	if (EquiposDeFantasia.get(name)==null) {System.out.println(("Error: Aun no has creado ningun equipo\n"));}
    
        	                    	
        	                    	ArrayList<EquipoFantasia>ListaEquiposDelPropietario = EquiposDeFantasia.get(name);
        	                		System.out.println("Estos son los equipos que has creado:\n");
        	                		for (EquipoFantasia eq:ListaEquiposDelPropietario)
        	                		{
        	                			 
        	                			 System.out.println("EQUIPO: "+eq.getNombre());}
        	                		
        	                    	System.out.println("\nIngresa el nombre del equipo de fantasia al que lo quieres anadir:\n");
        	                    	String equipo = case4.nextLine();
        	                    	try {
        	                    	cVentas.comprarJugador(name, jug, equipo, EquiposDeFantasia);
        	                    	}
        	                    	catch(Exception e) {System.out.println(e.getMessage());}
        	                        break;
        	                    */    
        	                    case 4:
        	                    	try {
        	                    	cFantas.controladorEquipos(name);}
        	                    	catch(Exception e)
        	                    	{
        	                    		System.out.println(e.getMessage());
        	                    	}
        	                        break;
        	                        
        	                    case 5://FALTA AGREGAR EL DINERO AL DUEnO DEL JUGADOR - Miguel: técnicamente es al equipo el que tiene el dinero
        	                    
        	                    	Scanner case6 = new Scanner(System.in);
        	                    	HashMap<String, ArrayList<EquipoFantasia>> EquiposDeFantasia6 = cFantas.getEquiposFant();
        	                    	ArrayList<EquipoFantasia>ListaEquipos = EquiposDeFantasia6.get(name);
        	                   
        	                    	if (EquiposDeFantasia6.get(name)==null) {System.out.println(("Error: Aun no has creado ningun equipo\n"));}

        	                		System.out.println("Estos son los equipos que has creado:\n");
        	                		for (EquipoFantasia eq:ListaEquipos)
        	                		{
        	                			 
        	                			 System.out.println("EQUIPO: "+eq.getNombre());}
        	                		
        	                    	System.out.println("\nIngresa el nombre del equipo de donde quieres vender el jugador:\n");
        	                    	String equipo6 = case6.nextLine();
        	                    	System.out.println("Estos son tus jugadores disponibles:\n");
	    	                    	try {
	    	                    		cVentas.venderJugador(name, equipo6, EquiposDeFantasia6);
	    	                    	}
	    	                    	catch(Exception e) {System.out.println(e.getMessage());}
	    	                        break;

        	                    case 6:
        	                    	System.out.println("\n...Su sesion ha sido cerrada exitosamente!\n");
        	                        salir4 = true;
        	                        break;
        	                    default:
        	                        System.out.println("\nSyntax Error: Ingrese números entre 1 y 7");
        	                }
        	            } catch (InputMismatchException e) {
        	                System.out.println("Debes insertar un número");
        	                sn4.next();
        	            }}
					;}
				
				//----------------->ENTRADA DE ADMINISTRADOR AL SISTEMA<-----------------
			else {
				Scanner sn4 = new Scanner(System.in);
    	        boolean salir4 = false;
    	        int opcion4; 
    	 
    	        while (!salir4) {
    	 
    	            System.out.println("MENU ADMINISTRADOR\n1. - - - Planear Temporada - - -");
    	            System.out.println("2. Cambiar de Temporada planeada a Temporada actual");
    	            System.out.println("3. Ver temporadas actuales");
    	            System.out.println("4. Registrar resultados de jugadores");
    	            System.out.println("5. Cerrar Sesion");
    	            //CAMBIOS UML; CAMBIAR TIPO DE USUARIOS EN EL CUADRO DE RESPONSABILIDADES

    	            try {
    	 
    	                opcion4 = sn4.nextInt();
    	 
    	                switch (opcion4) {
    	                    case 1:
    	                    	System.out.print("Para iniciar, tenga en cuenta que "
    	                    			+ "\nlos archivos que serán solicitados deberá"
    	                    			+ "\nguardarlos en la carpeta /data \n");
    	                    	System.out.print("\nCargue el archivo .txt con los partidos"
    	                    			+ "\n con el formato dates-[key]. "
    	                    			+ "\n Por ejemplo dates-mundial2018\n\n");
    	                    	System.out.print("Cargue el archivo.txt con los jugadores"
    	                    			+ "\n con el formato players-[key] (misma key anterior). "
    	                    			+ "\n Por ejemplo players-mundial2018\n\n");
    	                    	System.out.print("Ahora, escoja un 'key' con el que "
    	                    			+ "\nidentificó la temporada (e.g. mundial2018): \n");
    	                    	String key_temporada = scanner.nextLine();
    	                    	
    	                    	
    	                    	
    	                    	con.planearTemp(key_temporada);
    	                        break;
    	                    case 2:
    	                    	System.out.print("Ingrese el key la temporada (e.g. mundial2018): \n");
    	                    	String key_temporada1 = scanner.nextLine();
    	                    	con.establecerTemporadaActual(key_temporada1);
    	                    	break;
    	                    	
    	                    case 3:
    	                    	con.verTemporadasActuales();
    	                    	break;
    	                        
    	                    case 4:
    	                    	System.out.print("Ingrese el key de la temporada del partido (e.g. mundial2018): \n");
    	                    	String key_temporada11 = scanner.nextLine();
    	                    	System.out.print("Ingrese el key del partido (e.g. russia-arabia): \n");
    	                    	String key_partido = scanner.nextLine();
    	                    	con.registrarDesempenos(key_partido, key_temporada11);
    	                        break;
    	                        
    	                    case 5:
    	                    	System.out.println("\n...Su sesion ha sido cerrada exitosamente!\n");
    	                        salir4 = true;
    	                        break;
    	                        
    	                        
    	                    default:
    	                        System.out.println("\nSyntax Error: Ingrese números entre 1 y 3");
    	                }
    	            } catch (InputMismatchException e) {
    	                System.out.println("Debes insertar un número");
    	                sn4.next();
    	            }}
				;}
				
				
				
				
				
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());}
				;}
			
		}
		
		
		public static void main(String[] args) { //MODIFICACION UML, PUBLIC NOT PRIVATE
			
		Aplicacion app = new Aplicacion();
		
		System.out.print("Bienvenido a la aplicacion /Equipos de fantasia/\nSelecciona una opcion por favor: \n\n");
		
		app.desplegarConsola();
		}
		
		


	}


