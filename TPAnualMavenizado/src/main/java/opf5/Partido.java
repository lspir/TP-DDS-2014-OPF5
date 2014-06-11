package opf5;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import java.util.Date;

public class Partido {
	private String dia;
	private String hora;
	private String lugar;
	// quizas aun no es tan evidente, pero no ven que de a poco el partido
	// empieza a crecer en cosas que hace?
	// Es decir, lentamente y con cada entrega el partido se está volviendo
	// menos cohesivo.
	// No es que tengan que hacer algo ya, pero tenganlo en cuenta en proximas
	// entregas
	private List<Inscripcion> inscripciones;
	private List<Observador> observadores;
	private List<Inscripcion> posiblesJugadores;
	private List<Denegacion> denegaciones;

	public Partido(String dia, String hora, String lugar) {
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		inscripciones = new ArrayList<Inscripcion>();
		observadores = new ArrayList<Observador>();
		posiblesJugadores = new ArrayList<Inscripcion>();
		denegaciones = new ArrayList<Denegacion>();

	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion) {
		if (this.inscripciones.size() < 10) {
			inscripciones.add(inscripcion);
			this.revisarSiEstaLlenoEInformar();
			observadores.forEach(observador -> observador
					.notificarJugadorInscripto(inscripcion.jugador()));

		} else {
			if (this.estaLlenoDeEstandares()) {
				inscripcion.inscribiteSiPodesA(this);
			}
		}
	}

	public boolean estaLlenoDeEstandares() {
		return (this.inscripciones.stream()
				.filter(ins -> !ins.tuTipoDejaPasar()).count() != 10);
	}

	public void inscribiA(Inscripcion inscripcion) {
		Inscripcion inscriptoAEliminar;
		List<Inscripcion> genteAEliminar;

		if ((this.inscripciones.stream().filter(i -> i.tipoDePrioridadMinima()))
				.count() == 0) {
			genteAEliminar = inscripciones.stream()
					.filter(ins -> ins.tuTipoDejaPasar()).collect(toList());
		} else {
			genteAEliminar = (inscripciones.stream().filter(
					ins -> ins.tipoDePrioridadMinima()).collect(toList()));

		}

		inscriptoAEliminar = genteAEliminar.get(0);
		this.inscripciones.remove(inscriptoAEliminar);
		this.inscripciones.add(inscripcion);
		this.revisarCondicionales();
		this.revisarSiEstaLlenoEInformar();
		observadores.forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));

	}

	public void revisarCondicionales() {
		inscripciones.removeIf(inscripcion -> !inscripcion.teCumple(this));
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion) {
		this.inscripciones.remove(inscripcion);
		Infraccion infraccion = new Infraccion();
		inscripcion.jugador().tePenalizaron(infraccion);
		if (this.inscripciones().size() == 9) {
			observadores.forEach(obs -> obs.notificarPartidoIncompleto());
		}
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo) {
		inscripcion.teReemplaza(jugador, tipo);
		observadores.forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));
	}

	public void revisarSiEstaLlenoEInformar() {
		if (this.inscripciones.size() == 10) {
			observadores.forEach(obs -> obs.notificarPartidoLleno());
		}
	}

	public void tePropusieron(Jugador jugador, TipoDeInscripcion tipo) {
		Inscripcion inscripcion = new Inscripcion(jugador, tipo);
		posiblesJugadores.add(inscripcion);
	}

	public void administradorAcepto(Inscripcion inscripcion) {
		posiblesJugadores.remove(inscripcion);
		this.intentarInscribirA(inscripcion);
	}

	public void seRechazoInscripcion(Inscripcion inscripcion, String motivo) {
		// FIXME no usen Date, usen LocalDate o LocalDateTime o LocalTime segun
		// su necesidad, ya que tienen interfaces mas ricas
		posiblesJugadores.remove(inscripcion);
		// sugerencia: eviten las variables locales, hacen que el codigo
		// sea mas largo, se vea mas imperativo,
		// y sea por tanto mas complejo de seguir
		Denegacion denegacion = new Denegacion(motivo, inscripcion, new Date(),
				inscripcion.jugador());
		denegaciones.add(denegacion);
	}

	public boolean verificarSiJugo(Jugador jugador) {
		return (inscripciones.stream().anyMatch(inscripcion -> inscripcion
				.jugador() == jugador));
	}

	public List<Denegacion> denegaciones() {
		return denegaciones;
	}

	public List<Inscripcion> posiblesJugadores() {
		return posiblesJugadores;
	}

	public void agregarObservador(Observador observador) {
		observadores.add(observador);
	}

	public void ordenarSegun(Criterio criterio, AlgoritmoDivision algoritmo) {
		
		}
}
