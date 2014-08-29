package opf5.criteriosDeOrdenamientoDeEquipos;

import java.util.ArrayList;
import opf5.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class PromedioDeUltimoPartido implements Criterio {

  //FIXME no ven lógica repetida entre PromedioUltimoPartido y UltimasNCalificaciones?
	public double funcion(Jugador jugador) {
		Partido ultimoPartidoJugado = jugador.criticas().get(jugador.criticas().size()-1).partido();
		//FIXME ojo, == compara por identidad, equals compara por equivalencia
		List<Integer> lista = jugador.criticas().stream().filter(critica-> critica.partido() == ultimoPartidoJugado).map(critica->critica.nota()).collect(toList());
		//FIXME para que construyen un List<Integer> intermedio?
		return lista.stream().mapToInt(i -> i).average().orElse(0);
	}

}
