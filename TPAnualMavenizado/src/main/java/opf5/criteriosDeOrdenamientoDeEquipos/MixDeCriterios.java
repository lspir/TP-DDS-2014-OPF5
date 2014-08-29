package opf5.criteriosDeOrdenamientoDeEquipos;

import java.util.ArrayList;
import opf5.jugador.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import opf5.*;
import static java.util.stream.Collectors.toList;

public class MixDeCriterios implements CriterioOrdenamientoEquipos {

	private List<CriterioOrdenamientoEquipos> criterios = new ArrayList<CriterioOrdenamientoEquipos>();

	//FIXME para qué hacerlo mutable al criterio? No podrían simplemente inyectar las dependencias
	//por constructor?
	public void agregarCriterio(CriterioOrdenamientoEquipos criterio) {
		criterios.add(criterio);
	}

	public double ponderate(Jugador jugador) {
	  //FIXME nuevamente, ¿por qué la List intermedia?
		List<Double> lista = criterios.stream()
				.map(criterio -> criterio.ponderate(jugador)).collect(toList());
		return lista.stream().mapToDouble(i -> i).average().orElse(0);
	}
}
