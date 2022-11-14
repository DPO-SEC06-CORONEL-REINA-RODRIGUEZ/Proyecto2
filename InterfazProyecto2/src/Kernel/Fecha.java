package Kernel;
import java.util.ArrayList;
import java.util.Date;

public class Fecha {
	
	private Date fechaInicio;
	private boolean yaInicio = false;
	private ArrayList<PartidoReal>partidos = new ArrayList<>();
	
	public Fecha(ArrayList<PartidoReal> partidos, Date fecha)
	{
		this.partidos = partidos;
		this.fechaInicio = fecha;
	}
	
	public Fecha() {
		// TODO Auto-generated constructor stub
	}

	public Date getFechaInicio()
	{
		return this.fechaInicio;
	}
	public ArrayList<PartidoReal> getPartidos()
	{
		return this.partidos;
	}

	public boolean isYaInicio() {
		return yaInicio;
	}

	public void setYaInicio(boolean yaInicio) {
		this.yaInicio = yaInicio;
	}
}
