package refactor.ordenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import refactor.Jugador;
import refactor.Partido;

public class OrdenamientoPorHandicap extends CriterioOrdenamiento {
		
	public Double calcularValor(Jugador jugador) {
		return jugador.getCalificacion();
	}
	
}
