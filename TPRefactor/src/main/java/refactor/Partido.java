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
	// private int distribucionEquipos; // 5 es par/impar, 16 = 1,4,5,8,9 vs.
	// 2,3,6,7,10

	private DistribucionEquipos distribucionDeEquipos;

	public Partido() {
		this.distribucionDeEquipos = distribucionDeEquipos;
		inscriptos = new ArrayList<Jugador>();
		estado = new Abierto();
		distribucionDeEquipos =new ParImpar();
		criterioOrdenamiento = new OrdenamientoPorHandicap();
	}

	public void generarEquipos() {

		estado.dividirEnEquipos(this);
	}

	public void distribuirEquipos(List<Jugador> jugadores) {

		equipo1 = new Equipo();
		equipo2 = new Equipo();
		distribucionDeEquipos.distribuirEquipos(jugadores, equipo1, equipo2);
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

	public void setDistribucionEquipos(DistribucionEquipos distribucionDeEquipos) {
		this.distribucionDeEquipos = distribucionDeEquipos;
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
