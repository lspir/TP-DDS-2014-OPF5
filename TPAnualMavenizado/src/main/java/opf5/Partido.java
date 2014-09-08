package opf5;
import opf5.excepciones.*;
import opf5.jugador.*;
import opf5.inscripcion.*;
import opf5.observers.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.estadoPartido.*;
import opf5.AlgoritmosDivisionDeEquipos.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;


public class Partido {
  	private LocalDate fecha;
	private LocalTime horario;
	private String lugar;
	private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	private List<Observador> observadores = new ArrayList<Observador>();
	private List<Inscripcion> posiblesJugadores = new ArrayList<Inscripcion>();
	private List<Denegacion> denegaciones = new ArrayList<Denegacion>();
	private List<FormacionPartido> formacionesTentativas = new ArrayList<FormacionPartido>();
	private Estado estado;

	public Partido(LocalDate fecha, LocalTime horario, String lugar) {
		this.fecha = fecha;
		this.horario = horario;
		this.lugar = lugar;
		this.estado = new SinOrdenar();
}

	
	public void intentarInscribirA(Inscripcion inscripcion)
			{
		estado.intentarInscribirA(inscripcion, this);
	}

	public boolean noEstaLlenoDeEstandares() {
		return (this.getInscripciones().stream()
				.filter(ins -> !ins.tuTipoDejaPasar()).count() != 10);
	}

	public void inscribiA(Inscripcion inscripcion) {
		this.reemplazarInscripcion(this.obtenerInscripcionesQuePuedenBorrarse().get(0),inscripcion);
	}

	private List<Inscripcion> obtenerInscripcionesQuePuedenBorrarse() {
		if (this.hayCondicionales()) {
			return this.condicional();
		} else {
			return  this.solidarios();

		}
	}

	private List<Inscripcion> condicional() {
		return ((getInscripciones().stream().filter(
				ins -> ins.tipoDePrioridadMinima()).collect(toList())));
	}
	
	private List<Inscripcion> solidarios() {
		return (getInscripciones().stream()
				.filter(ins -> ins.tuTipoDejaPasar()).collect(toList()));
	}


	private boolean hayCondicionales() {
		return (this.getInscripciones().stream().filter(i -> i.tipoDePrioridadMinima())
				.count() > 0);
	}
	


	private void reemplazarInscripcion(Inscripcion inscripcionAEliminar,
			Inscripcion nuevaInscripcion) {
		this.getInscripciones().remove(inscripcionAEliminar);
		this.getInscripciones().add(nuevaInscripcion);
		this.revisarCondicionales();
		this.revisarSiEstaLlenoEInformar();
		getObservadores().forEach(observador -> observador
				.notificarJugadorInscripto(nuevaInscripcion.jugador()));
		
	}


	public void revisarCondicionales() {
		getInscripciones().removeIf(inscripcion -> !inscripcion.teCumple(this));
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion)
			{
		estado.seDioDeBajaSinReemplazante(inscripcion, this);

	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo)
			{
		estado.seDioDeBajaConReemplazante(inscripcion, jugador, tipo, this);

	}

	public void revisarSiEstaLlenoEInformar() {
		if (this.getInscripciones().size() == 10) {
			getObservadores().forEach(obs -> obs.notificarPartidoLleno());
		}
	}

	public void tePropusieron(Jugador jugador, TipoDeInscripcion tipo) {
		Inscripcion inscripcion = new Inscripcion(jugador, tipo);
		posiblesJugadores.add(inscripcion);
	}

	public void administradorAcepto(Inscripcion inscripcion)
			{
		posiblesJugadores.remove(inscripcion);
		this.intentarInscribirA(inscripcion);
	}

	public void seRechazoInscripcion(Inscripcion inscripcion, String motivo) {

		posiblesJugadores.remove(inscripcion);
		Denegacion denegacion = new Denegacion(motivo, inscripcion, LocalDate.now(),
				inscripcion.jugador());
		denegaciones.add(denegacion);
	}

	public boolean verificarSiJugo(Jugador jugador) {
		return (getInscripciones().stream().anyMatch(inscripcion -> inscripcion
				.jugador().equals(jugador)));
	}

	public List<Denegacion> denegaciones() {
		return denegaciones;
	}

	public List<Inscripcion> posiblesJugadores() {
		return posiblesJugadores;
	}

	public void agregarObservador(Observador observador) {
		getObservadores().add(observador);
	}

	public void armarEquipos(CriterioOrdenamientoEquipos criterio, AlgoritmoDivisionDeEquipos algoritmo)
			{
		estado.armarEquipos(criterio, algoritmo, this);

	}

	public void aceptarEquipos(FormacionPartido formacion)
			{
		estado.aceptarEquipos(this,formacion);
	}

	
	public void setEstado(Estado unEstado) {
		estado = unEstado;

	}

	public void tenes10Jugadores() {
		if (getInscripciones().size() < 10) {
			throw new ElPartidoNoEstaCompleto();
		}
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public List<Observador> getObservadores() {
		return observadores;
	}

	public void setObservadores(List<Observador> observadores) {
		this.observadores = observadores;
	}


	public List<FormacionPartido> getFormacionesTentativas() {
		return formacionesTentativas;
	}


	public void setFormacionesTentativas(List<FormacionPartido> formacionesTentativas) {
		this.formacionesTentativas = formacionesTentativas;
	}


	public void limpiarFormaciones() {
		this.formacionesTentativas.clear();
		
	}


	public void agregarFormacion(FormacionPartido formacionPartido) {
		this.formacionesTentativas.add(formacionPartido);
		
	}

}
