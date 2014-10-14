package opf5.criteriosDeOrdenamientoDeEquipos;


import org.uqbar.commons.utils.Observable;

import opf5.jugador.*;
@Observable
public class CriterioHandicap extends CriterioOrdenamientoEquipos {
	
	
	public CriterioHandicap() {
		super("Segun Handicap");
		
	}

	public double ponderate(Jugador jugador) {
		return jugador.getHandicap();
	}

}
