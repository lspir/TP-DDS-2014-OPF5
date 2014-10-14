package opf5.criteriosDeOrdenamientoDeEquipos;

import static java.util.stream.Collectors.*;

import java.util.*;

import org.uqbar.commons.utils.Observable;

import opf5.jugador.*;
@Observable
public class MixDeCriterios extends CriterioOrdenamientoEquipos {

	private List<CriterioOrdenamientoEquipos> criterios = new ArrayList<CriterioOrdenamientoEquipos>();

	public MixDeCriterios(List<CriterioOrdenamientoEquipos> criterios){
		super("Mix De Criterios");
		this.criterios=criterios;
	}
	
	public double ponderate(Jugador jugador) {
	return obtenerListaCriteriosPonderados(jugador).stream().mapToDouble(i -> i).average().orElse(0);
	}
	
	public List<Double> obtenerListaCriteriosPonderados(Jugador jugador){
		return criterios.stream()
				.map(criterio -> criterio.ponderate(jugador)).collect(toList());
	}
}

