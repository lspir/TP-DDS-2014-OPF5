package opf5;

public class Handicap implements Criterio {

	public int funcion(Jugador jugador) {
		return jugador.handicap();
	}

}
