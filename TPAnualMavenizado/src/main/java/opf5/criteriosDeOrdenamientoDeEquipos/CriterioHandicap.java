package opf5.criteriosDeOrdenamientoDeEquipos;


import opf5.*;

public class CriterioHandicap implements CriterioOrdenamientoEquipos {

	public double ponderate(Jugador jugador) {
		return jugador.handicap();
	}

}
