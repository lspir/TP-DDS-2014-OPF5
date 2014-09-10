package opf5.criteriosDeOrdenamientoDeEquipos;


import opf5.*;
import opf5.jugador.*;

public class CriterioHandicap extends CriterioOrdenamientoEquipos {
	
	
	public CriterioHandicap() {
		super("Segun Handicap");
		
	}

	public double ponderate(Jugador jugador) {
		return jugador.getHandicap();
	}

}
