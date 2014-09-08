package refactor;

import java.util.List;
import refactor.utilitarios.Lists;

public class ParImpar implements DistribucionEquipos {

	public void distribuirEquipos(List<Jugador> jugadores, Equipo equipo1,
			Equipo equipo2) {

		 equipo1.setJugadores(Lists.newArrayList(jugadores.get(0),
		 jugadores.get(2), jugadores.get(4), jugadores.get(6),
		 jugadores.get(8)));
		
		 equipo2.setJugadores(Lists.newArrayList(jugadores.get(1),
		 jugadores.get(3), jugadores.get(5), jugadores.get(7),
		 jugadores.get(9)));

	}
	
}
