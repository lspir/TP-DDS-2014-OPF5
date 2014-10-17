package opf5.inscripcion;
import opf5.jugador.*;
import opf5.partido.*;


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
	
	public Inscripcion conJugador(Jugador jugador) {
		return new Inscripcion(jugador, tipoDeInscripcion);
	}

	public void teReemplaza (Jugador jugador, TipoDeInscripcion tipo){
		this.jugador= jugador;
		this.tipoDeInscripcion= tipo;
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
	
	public Boolean teCumple (Partido partido){
		return tipoDeInscripcion.teCumple(partido);
	}

	public Boolean tipoDePrioridadMinima() {
		return tipoDeInscripcion.prioridadMinima();
	}

}
