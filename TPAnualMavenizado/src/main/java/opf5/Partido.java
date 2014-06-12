package opf5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
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
	public List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	public List<Observador> observadores = new ArrayList<Observador>();
	private List<Inscripcion> posiblesJugadores = new ArrayList<Inscripcion>();
	private List<Denegacion> denegaciones = new ArrayList<Denegacion>();
	public List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	public List<Inscripcion> equipoB = new ArrayList<Inscripcion>();
	private Estado estado;

	public Partido(String dia, String hora, String lugar) {
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		this.estado = new SinOrdenar();
	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion)
			throws ElPartidoYaEstaConfirmadoException {
		estado.intentarInscribirA(inscripcion, this);
	}

	public boolean noEstaLlenoDeEstandares() {
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

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion)
			throws ElPartidoYaEstaConfirmadoException {
		estado.seDioDeBajaSinReemplazante(inscripcion, this);

	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo)
			throws ElPartidoYaEstaConfirmadoException {
		estado.seDioDeBajaConReemplazante(inscripcion, jugador, tipo, this);

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

	public void administradorAcepto(Inscripcion inscripcion)
			throws ElPartidoYaEstaConfirmadoException {
		posiblesJugadores.remove(inscripcion);
		this.intentarInscribirA(inscripcion);
	}

	public void seRechazoInscripcion(Inscripcion inscripcion, String motivo) {
		// FIXME no usen Date, usen LocalDate o LocalDateTime o LocalTime segun
		// su necesidad, ya que tienen interfaces mas ricas
		posiblesJugadores.remove(inscripcion);
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

	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo) throws ElPartidoYaEstaConfirmadoException,ElPartidoNoEstaCompleto{
		estado.armarEquipos(criterio, algoritmo, this);
	}
	

	public void aceptarEquipos()throws NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException,ElPartidoNoEstaCompleto {
		estado.aceptarEquipos(this);
	}

	public void tuEstadoEs(Estado unEstado) {
		estado= unEstado;
		
	}

	public void tenes10Jugadores() throws ElPartidoNoEstaCompleto{
		if (inscripciones.size()<10){
			throw new ElPartidoNoEstaCompleto();
		}
	}
}
