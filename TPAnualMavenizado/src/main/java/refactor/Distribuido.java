package refactor;

import refactor.excepciones.BusinessException;

public class Distribuido implements EstadoDelPartido {

	public void inscribirEnPartido(Jugador jugador, Partido partido) {
		throw new BusinessException("La inscripcion ya cerro");
	}

	public void dividirEnEquipos(Partido partido) {
		throw new BusinessException("Los equipos ya fueron divididos");
	}
}
