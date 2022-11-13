package Kernel;
import java.util.ArrayList;
import java.util.Date;

public class PartidoReal {

	private Date dia;
	private String hora;
	private String marcador;
	private EquipoReal visitante;
	private EquipoReal local;
	private ArrayList<ReporteJugador> reportes =new ArrayList<>();
	
	public PartidoReal(Date date1, String hora, EquipoReal vis, EquipoReal lcl)
	{
		this.dia = date1;
		this.hora = hora;
		this.visitante = vis;
		this.local = lcl;
		
	}
	
	public Date getDia()
	{
		return this.dia;
	}
	
	
	public EquipoReal getLocal()
	{
		return this.local;
	}
	
	public EquipoReal getVisitante()
	{
		return this.visitante;
	}
	
	public ArrayList<ReporteJugador> getReportes()
	{
		return this.reportes;
	}

	public String getHora() {
		return hora;
	}

	public String getMarcador() {
		return marcador;
	}
}
