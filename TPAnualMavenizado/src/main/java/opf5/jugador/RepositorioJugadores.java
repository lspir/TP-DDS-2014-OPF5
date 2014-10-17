package opf5.jugador;

import static java.util.stream.Collectors.toList;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

import opf5.jugador.*;
import opf5.partido.HomePartidos;

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
		List<Jugador> resultados = new ArrayList<Jugador>();
		resultados.addAll(this.cumpleEdadMinima(edad,this.getJugadores()));
		resultados.removeAll(this.nombreNoEmpiezacon(nombre,resultados));
		resultados.removeAll(this.noCumpleCriterioPromedio(criterioPromedio,promedio,resultados));
		resultados.removeAll(this.noCumpleCriterioHandicap(criterioBusquedaHandicap,handicap,resultados));
		resultados.removeAll(this.noCumpleCriterioInfractores(criterioInfractoresSeleccionado,resultados));
		return resultados;
	}

	private List<Jugador> noCumpleCriterioInfractores(
			CriterioBusquedaInfractoresAbstracta criterioInfractoresSeleccionado,
			List<Jugador> jugadores) {
		return this.filtrarJugadoresConCondicion(jugador->!(criterioInfractoresSeleccionado.teCumple(jugador)), jugadores);
	}

	private List<Jugador> noCumpleCriterioHandicap(
			CriterioBusqueda criterioBusquedaHandicap, double handicap,
			List<Jugador> jugadores) {
		return this.filtrarJugadoresConCondicion(jugador->!(criterioBusquedaHandicap.teCumpleHandicap(handicap, jugador)), jugadores);
	}

	private List<Jugador> noCumpleCriterioPromedio(
			CriterioBusqueda criterioPromedio, double promedio,
			List<Jugador> jugadores) {
		return this.filtrarJugadoresConCondicion((jugador)->!(criterioPromedio.teCumple(promedio, jugador)), jugadores);
		}
	

	private List<Jugador> nombreNoEmpiezacon(String nombre,
			List<Jugador> jugadores) {
		return this.filtrarJugadoresConCondicion(jugador-> !(jugador.nombre().startsWith(nombre)), jugadores);
	}

	private List<Jugador> cumpleEdadMinima(double edad, List<Jugador> jugadores ) {
		return this.filtrarJugadoresConCondicion((jugador)->jugador.edad()>=edad,jugadores);
		}

	private List<Jugador> filtrarJugadoresConCondicion(Predicate<Jugador> condicion, List<Jugador> jugadores) {
		return jugadores.stream().filter((jugador)->condicion.test(jugador) ).collect(toList());
	}

	public List<Jugador> getJugadores() {
		return this.jugadores;
	}
}
