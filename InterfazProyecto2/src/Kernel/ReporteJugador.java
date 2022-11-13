package Kernel;

public class ReporteJugador {
	private int minutosJugados;
	private int minutoIngreso;
	private int minutoSalida;
	private int cantidadGolesAnotados;
	private int penaltisAnotados;
	private int autogoles;
	private int asistencias;
	private int golesRecibidos;
	private int penaltisDetenidos;
	private int penaltisErrados;
	private int numTarjetasAmarillas;
	private boolean tarjetaRoja;
	private boolean equipoGano; //Cambio UML; boolean en vez de int 
	
	public ReporteJugador(int mj, int mi, int ms, int cga, int pa, int ag, int asis, int gr, int pd,
			int pe, int nta, boolean tr, boolean eg){
		this.minutosJugados = mj;
		this.minutoIngreso = mi;
		this.minutoSalida = ms;
		this.cantidadGolesAnotados = cga;
		this.penaltisAnotados = pa;
		this.autogoles = ag;
		this.asistencias = asis;
		this.golesRecibidos = gr;
		this.penaltisDetenidos = pd;
		this.penaltisErrados = pe;
		this.numTarjetasAmarillas = nta;
		this.tarjetaRoja =tr;
		this.equipoGano = eg;
	}

	public int getMinutosJugados() {
		return minutosJugados;
	}

	public int getMinutoSalida() {
		return minutoSalida;
	}

	public int getMinutoIngreso() {
		return minutoIngreso;
	}

	public int getCantidadGolesAnotados() {
		return cantidadGolesAnotados;
	}

	public int getPenaltisAnotados() {
		return penaltisAnotados;
	}

	public int getAutogoles() {
		return autogoles;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public int getGolesRecibidos() {
		return golesRecibidos;
	}

	public int getPenaltisDetenidos() {
		return penaltisDetenidos;
	}

	public int getPenaltisErrados() {
		return penaltisErrados;
	}

	public int getNumTarjetasAmarillas() {
		return numTarjetasAmarillas;
	}

	public boolean isTarjetaRoja() {
		return tarjetaRoja;
	}

	public boolean isEquipoGano() {
		return equipoGano;
	}
}
