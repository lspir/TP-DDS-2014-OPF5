package opf5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class MixDeCriterios implements Criterio {

	private List<Criterio> criterios = new ArrayList<Criterio>();

	public void agregarCriterio(Criterio criterio) {
		criterios.add(criterio);
	}

	public int funcion(Jugador jugador) {
		List<Integer> lista = criterios.stream()
				.map(criterio -> criterio.funcion(jugador)).collect(toList());
		return (lista.sum() / lista.size());
	}
}
