package opf5.jugador;

import java.io.*;
import java.util.*;

import opf5.HomePartidos;
import opf5.jugador.*;

import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioJugadores implements Serializable {

	private List<Jugador> jugadores = new ArrayList<Jugador>();
	private static final RepositorioJugadores instance=new RepositorioJugadores();

	public static synchronized RepositorioJugadores getInstance() {
		return instance;
	}

	public void create(Jugador jugador) {
		this.jugadores.add(jugador);
	}

	public List<Jugador> search(int numero, String nombre, int handicapDesde,
			int handicapHasta) {
		//FIXME no usen fors e ifs!!!!!!!!!
		//No estamos en C!!
		List<Jugador> resultados = new ArrayList<Jugador>();
		if (handicapHasta == 0) {
			for (Jugador jugador : this.jugadores) {

				if (jugador.edad() > numero
						&& (jugador.getHandicap() >= handicapDesde)
						&& (jugador.nombre().startsWith(nombre))) {
					resultados.add(jugador);
				}
			}
		} else {
			if (handicapDesde == 0) {
				for (Jugador jugador : this.jugadores) {

					if (jugador.edad() > numero
							&& (jugador.getHandicap() <= handicapHasta)
							&& (jugador.nombre().startsWith(nombre))) {
						resultados.add(jugador);
					}
				}
			}
		}
		return resultados;
	}


	public List<Jugador> getJugadores() {
		return this.jugadores;
	}
}
