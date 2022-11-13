package Kernel;

public class JugadorFantasia extends JugadorReal{
	
	//Cambios UML; no deben ser statics ya que no son modificables para crear el objeto
	private int puntosObtenidos = 0;
	private int puntosPenaltiAtajado=0;
	private int puntosSinGolesRecibidosDefyAr=0;
	private int puntosGolDef=0;
	private int puntosGolDelanyMeCa=0;
	private int puntosAutogol=0;
	private int puntosTarjetaRoja=0;
	private int puntosTarjetaAmarilla=0;
	private int puntosErrarPenalti=0;
	private int puntosAsistencia=0;
	private int puntosMas60min=0;
	private int puntosHasta60min=0;
	
	public JugadorFantasia(String nombre, Posicion posicion, int vCompra, int vVenta, int vPleno
			,int ppa, int psgr, int pgd, int pgdmc, int ag, int tr, int ta, int ep, int pa, int ptm60, int pt60,
			ReporteJugador reporte)
	{
		super(nombre, posicion, vCompra, vVenta, vPleno);
		this.puntosPenaltiAtajado = ppa;
		this.puntosSinGolesRecibidosDefyAr = psgr;
		this.puntosGolDef = pgd;
		this.puntosGolDelanyMeCa = pgdmc;
		this.puntosAutogol = ag;
		this.puntosTarjetaRoja = tr;
		this.puntosTarjetaAmarilla = ta;
		this.puntosErrarPenalti = ep;
		this.puntosAsistencia = pa;
		this.puntosMas60min = ptm60;
		this.puntosHasta60min = pt60;
	}
	
	
	public int getPuntosObtenidos()
	{
		return this.puntosObtenidos;
	}
}
