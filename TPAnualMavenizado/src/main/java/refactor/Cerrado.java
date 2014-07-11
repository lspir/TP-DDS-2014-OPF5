package refactor;

import refactor.excepciones.BusinessException;

public class Cerrado implements EstadoDelPartido {

	public void inscribirEnPartido(Jugador jugador, Partido partido) {
		throw new BusinessException("El partido ya esta cerrado");
	}

	public void dividirEnEquipos(Partido partido) {
		partido.distribuirEquipos(partido.ordenarEquipos());
		partido.setEstado(new Distribuido());
	}
}