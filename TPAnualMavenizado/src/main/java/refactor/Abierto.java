package refactor;

import refactor.excepciones.BusinessException;

public class Abierto implements EstadoDelPartido {

	public void inscribirEnPartido(Jugador jugador, Partido partido) {
		partido.getInscriptos().add(jugador);
		if (partido.getInscriptos().size() == 10) {
			partido.setEstado(new Lleno());
		}
	}

	public void dividirEnEquipos(Partido partido) {
		throw new BusinessException("Todavia no hay 10 jugadores");

	}

}
