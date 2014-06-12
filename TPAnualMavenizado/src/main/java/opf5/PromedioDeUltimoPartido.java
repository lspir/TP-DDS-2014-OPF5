package opf5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class PromedioDeUltimoPartido implements Criterio {

	public double funcion(Jugador jugador) {
		Partido ultimoPartidoJugado = jugador.criticas().get(jugador.criticas().size()).partido();
		List<Integer> lista = jugador.criticas().stream().filter(critica-> critica.partido() == ultimoPartidoJugado).map(critica->critica.nota()).collect(toList());
		return lista.stream().mapToInt(i -> i).average().orElse(0);
	}

}
