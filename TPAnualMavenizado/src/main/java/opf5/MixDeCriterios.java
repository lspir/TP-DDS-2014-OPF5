package opf5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class MixDeCriterios implements Criterio {

	private List<Criterio> criterios = new ArrayList<Criterio>();

	//FIXME para qué hacerlo mutable al criterio? No podrían simplemente inyectar las dependencias
	//por constructor?
	public void agregarCriterio(Criterio criterio) {
		criterios.add(criterio);
	}

	public double funcion(Jugador jugador) {
	  //FIXME nuevamente, ¿por qué la List intermedia?
		List<Double> lista = criterios.stream()
				.map(criterio -> criterio.funcion(jugador)).collect(toList());
		return lista.stream().mapToDouble(i -> i).average().orElse(0);
	}
}
