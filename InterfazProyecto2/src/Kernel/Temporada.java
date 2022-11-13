package Kernel;
import java.util.ArrayList;
import java.util.Date;

public class Temporada {
	
	private Date fechaInicio;
	private ArrayList<Fecha> fechas = new ArrayList<>();
	private ArrayList<EquipoFantasia> equipos = new ArrayList<>();
	
	public Temporada(ArrayList<Fecha> lista_fechas, Date inicio )
	{
		this.fechaInicio = inicio;
		this.fechas = lista_fechas;
	}
	
	public Date getFechaInicio()
	{
		return this.fechaInicio;
	}
	public ArrayList<Fecha> getFechas()
	{
		return this.fechas;
	}
	public ArrayList<EquipoFantasia> getEquipos()
	{
		return this.equipos;
	}
}
