package refactor;

import refactor.excepciones.BusinessException;
import refactor.ordenamiento.CriterioOrdenamiento;
import refactor.ordenamiento.OrdenamientoPorHandicap;
import refactor.utilitarios.Lists;

import java.util.ArrayList;
import java.util.List;

public class Partido {

	private List<Jugador> inscriptos;
	private Equipo equipo1;
	private Equipo equipo2;
	private EstadoDelPartido estado;
	private CriterioOrdenamiento criterioOrdenamiento;
	private int distribucionEquipos; // 5 es par/impar, 16 = 1,4,5,8,9 vs.
										// 2,3,6,7,10

	public Partido() {
		inscriptos = new ArrayList<Jugador>();
		estado = new Abierto();
		distribucionEquipos = 5; // par/impar
		criterioOrdenamiento = new OrdenamientoPorHandicap();
	}

	public void generarEquipos() {

		estado.dividirEnEquipos(this);
	}

	public void distribuirEquipos(List<Jugador> jugadores) {
		equipo1 = new Equipo();
		equipo2 = new Equipo();
		if (distribucionEquipos == 5) {
			equipo1.setJugadores(Lists.newArrayList(jugadores.get(0),
					jugadores.get(2), jugadores.get(4), jugadores.get(6),
					jugadores.get(8)));

			equipo2.setJugadores(Lists.newArrayList(jugadores.get(1),
					jugadores.get(3), jugadores.get(5), jugadores.get(7),
					jugadores.get(9)));
		} else {
			// distribucionEquipos == 16 que ordena de esta manera
			equipo1.setJugadores(Lists.newArrayList(jugadores.get(0),
					jugadores.get(3), jugadores.get(4), jugadores.get(7),
					jugadores.get(8)));

			equipo2.setJugadores(Lists.newArrayList(jugadores.get(1),
					jugadores.get(2), jugadores.get(5), jugadores.get(6),
					jugadores.get(9)));
		}
	}

	public List<Jugador> ordenarEquipos() {
		return criterioOrdenamiento.ordenar(this);
	}

	public void inscribir(Jugador jugador) {
		estado.inscribirEnPartido(jugador, this);
	}

	public boolean hayAlgunJugadorQueCedaLugar() {
		for (Jugador inscripto : inscriptos) {
			if (inscripto.dejaLugarAOtro()) {
				return true;
			}
		}
		return false;
	}

	public Jugador jugadorQueCedeLugar() {
		if (!hayAlgunJugadorQueCedaLugar()) {
			return null;
		}

		List<Jugador> jugadores = new ArrayList<Jugador>();
		for (Jugador inscripto : inscriptos) {
			if (inscripto.dejaLugarAOtro()) {
				jugadores.add(inscripto);
			}
		}

		return jugadores.get(0);
	}

	public void cerrar() {
		estado = new Cerrado();
	}

	public List<Jugador> getInscriptos() {
		return inscriptos;
	}

	public void setCriterioOrdenamiento(
			CriterioOrdenamiento criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}

	public void setDistribucionEquipos(int distribucionEquipos) {
		this.distribucionEquipos = distribucionEquipos;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEstado(EstadoDelPartido estadoNuevo) {
		this.estado = estadoNuevo;
	}

}
