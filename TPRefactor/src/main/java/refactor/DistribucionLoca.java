package refactor;

import java.util.List;

import refactor.utilitarios.Lists;

public class DistribucionLoca implements DistribucionEquipos {

	public void distribuirEquipos(List<Jugador> jugadores, List<Jugador>  equipo1,
			List<Jugador>  equipo2) {

				 equipo1.addAll(Lists.newArrayList(jugadores.get(0),
				 jugadores.get(3), jugadores.get(4), jugadores.get(7),
				 jugadores.get(8)));
				
				 equipo2.addAll(Lists.newArrayList(jugadores.get(1),
				 jugadores.get(2), jugadores.get(5), jugadores.get(6),
				 jugadores.get(9)));
		
	
	}
}
