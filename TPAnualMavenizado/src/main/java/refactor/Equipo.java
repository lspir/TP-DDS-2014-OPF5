package refactor;

import java.util.List;

//FIXME no ven un code smell acá? un data object quizás?
public class Equipo {
	
	private List<Jugador> jugadores;

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
}