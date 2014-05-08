package OPF5;

public class Inscripcion {
	Jugador jugador;
	TipoDeInscripcion tipoDeInscripcion;

	public Inscripcion(Jugador jugadorI, TipoDeInscripcion tipoI) {
		jugador = jugadorI;
		tipoDeInscripcion = tipoI;
	}

	public String nuevaInscripcionA(Partido partido) {
		return partido.tenesNuevaInscripcion(this);
	}

	public Boolean dejasPasarTuTipo() {
		return this.tipoDeInscripcion.dejasPasar();
	}

	public String inscribiteSiPodesA(Partido partido) {
		if (this.tipoDeInscripcion.tePodesInscribirA(partido)) {
			this.inscribiteA(partido);
			return "inscripcion correcta";
		} else {
			return "no te podes inscribir";
		}
	}

	public void inscribiteA(Partido partido) {
		partido.inscribiA(this);
	}

	public Boolean tipoDePrioridadMinima() {
		return tipoDeInscripcion.prioridadMinima();
	}

}
