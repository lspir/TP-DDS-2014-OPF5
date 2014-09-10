package opf5.criteriosDeOrdenamientoDeEquipos;

import java.util.ArrayList;
import opf5.jugador.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import opf5.*;
import static java.util.stream.Collectors.toList;

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

