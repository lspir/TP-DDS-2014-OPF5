package refactor;

import refactor.excepciones.BusinessException;

public class Lleno implements EstadoDelPartido {

	public void inscribirEnPartido(Jugador jugador, Partido partido) {
		if (partido.hayAlgunJugadorQueCedaLugar()) {
			partido.getInscriptos().remove(partido.jugadorQueCedeLugar());
			partido.getInscriptos().add(jugador);
		} else {
			throw new BusinessException("No hay mas lugar");
		}

	}

	public void dividirEnEquipos(Partido partido) {
		partido.distribuirEquipos(partido.ordenarEquipos());
		partido.setEstado(new Distribuido());
	}

}
