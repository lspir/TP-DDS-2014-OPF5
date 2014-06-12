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
	private List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	private List<Inscripcion> equipoB = new ArrayList<Inscripcion>();
	private Estado estado;

	public Partido(String dia, String hora, String lugar) {
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		this.estado = new SinConfirmar();
	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion)
			throws ElPartidoYaEstaConfirmadoException {
		estado.intentarInscribirA(inscripcion, this);
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

	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo) {
		this.inscripciones().stream().forEach(inscrip->inscrip.settearValorCriterio(criterio.funcion(inscrip.jugador())));
		List<Inscripcion> listaOrdenada = this.inscripciones().stream().sorted(comparing(x -> x.valorDeCriterio())).collect(toList());
		this.inscripciones= algoritmo.dameLista(listaOrdenada);
	}
	

	public void aceptarEquipos() {
		equipoA = inscripciones.stream().limit(5).collect(toList());
		equipoB = inscripciones.stream().skip(5).collect(toList());
		this.confirmar();
	}

	public void confirmar() {
		estado = new Confirmado();
	}

	public void asd(Ordenado ordenado) {
		estado= ordenado;
		
	}
}
