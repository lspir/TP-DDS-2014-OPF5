package refactor;
//FIXME yo les diria que no pongan el codigo del refactor en este mismo proyecto, porque
//van a tener dos clases con igual nombre, y si bien Java se lo banca (porque tienen paquetes diferentes)
//complica el uso de búsqueda por nombre en el IDE
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
