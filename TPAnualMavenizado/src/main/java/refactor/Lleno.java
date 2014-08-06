package refactor;
import refactor.excepciones.BusinessException;

public class Lleno implements EstadoDelPartido {
		
		public void inscribirEnPartido(Jugador jugador, Partido partido){
			if (partido.hayAlgunJugadorQueCedaLugar()) {
				partido.getInscriptos().remove(partido.jugadorQueCedeLugar());
				partido.getInscriptos().add(jugador);
			} else {
				throw new BusinessException("No hay m�s lugar");
		}
		

	}
//FIXME indentación
		public void dividirEnEquipos(Partido partido){
		partido.distribuirEquipos(partido.ordenarEquipos());
		partido.setEstado(new Distribuido());
		}

	}

