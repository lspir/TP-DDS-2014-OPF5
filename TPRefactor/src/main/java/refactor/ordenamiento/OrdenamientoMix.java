package refactor.ordenamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import refactor.Jugador;
import refactor.Partido;

public class OrdenamientoMix extends CriterioOrdenamiento {
	
	List<CriterioOrdenamiento> criterios;
	
	public OrdenamientoMix() {
		criterios = new ArrayList<CriterioOrdenamiento>();
	}
	
	public void addCriterio(CriterioOrdenamiento criterio) {
		criterios.add(criterio);
	}
	
	public Double calcularValor(Jugador jugador) {
		Double acumulador=0d;
		for (CriterioOrdenamiento criterio : criterios) {
			acumulador+=criterio.calcularValor(jugador);
		}
		
		return acumulador;
	}
	
}
