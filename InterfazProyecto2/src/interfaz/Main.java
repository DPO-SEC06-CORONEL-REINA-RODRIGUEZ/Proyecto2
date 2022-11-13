package interfaz;

import Console.Aplicacion;

public class Main {

	public static void main(String[] args)
	{
		VentanaPrincipal ventana  =new VentanaPrincipal();
		ventana.setVisible(true);
		Aplicacion app = new Aplicacion();
		System.out.print("Bienvenido a la aplicacion /Equipos de fantasia/\nSelecciona una opcion por favor: \n\n");
		app.desplegarConsola();
	}
}
