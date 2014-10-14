package opf5.jugador;

import java.io.*;
import java.util.*;

import opf5.HomePartidos;
import opf5.jugador.*;

import org.uqbar.commons.utils.Observable;

import utilitarios.CriterioBusqueda;
import utilitarios.CriterioBusquedaInfractoresAbstracta;

@Observable
public class RepositorioJugadores implements Serializable {

	private List<Jugador> jugadores = new ArrayList<Jugador>();
	private static final RepositorioJugadores instance = new RepositorioJugadores();

	public static synchronized RepositorioJugadores getInstance() {
		return instance;
	}

	public void create(Jugador jugador) {
		this.jugadores.add(jugador);
	}

	public List<Jugador> search(double edad, String nombre, double handicap,
			CriterioBusqueda criterioBusquedaHandicap, double promedio,
			CriterioBusqueda criterioPromedio,
			CriterioBusquedaInfractoresAbstracta criterioInfractoresSeleccionado) {
		// FIXME no usen fors e ifs!!!!!!!!!
		// No estamos en C!!
		List<Jugador> resultados = new ArrayList<Jugador>();
		for (Jugador jugador : this.jugadores) {

			if (jugador.edad() > edad
					&& (jugador.nombre().startsWith(nombre))
					&& ((criterioPromedio != null ? criterioPromedio.teCumple(
							promedio, jugador) : true))
					&& (criterioBusquedaHandicap != null ? criterioBusquedaHandicap
							.teCumple(handicap, jugador) : true)
							&& (criterioInfractoresSeleccionado!=null?criterioInfractoresSeleccionado.teCumple(jugador):true)) {
				resultados.add(jugador);
			}
		}
		return resultados;
	}

	public List<Jugador> getJugadores() {
		return this.jugadores;
	}
}
