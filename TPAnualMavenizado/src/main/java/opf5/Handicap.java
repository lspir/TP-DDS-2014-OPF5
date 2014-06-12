package opf5;

public class Handicap implements Criterio {

	public double funcion(Jugador jugador) {
		return jugador.handicap();
	}

}
