package opf5;

public class Inscripcion {
	Jugador jugador;
	TipoDeInscripcion tipoDeInscripcion;

	public Inscripcion(Jugador jugador, TipoDeInscripcion tipoDeInscripcion) {
		this.jugador = jugador;
		this.tipoDeInscripcion = tipoDeInscripcion;
	}
	
	public Jugador jugador(){
		return this.jugador;
	}

	public void teReemplaza (Jugador jugador){
		this.jugador= jugador;
	}
	public Boolean tuTipoDejaPasar() {
		return this.tipoDeInscripcion.dejasPasar();
	}

	public void inscribiteSiPodesA(Partido partido) {
		if (this.tipoDeInscripcion.tePodesInscribirA(partido)) 
			this.inscribiteA(partido);
		
	}

	public void inscribiteA(Partido partido) {
		partido.inscribiA(this);
	}

	public Boolean tipoDePrioridadMinima() {
		return tipoDeInscripcion.prioridadMinima();
	}

	public void avisarATusAmigos()
	{
		this.jugador.avisarATusAmigos();
	}
}
