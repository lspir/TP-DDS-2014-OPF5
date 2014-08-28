package opf5;
import opf5.excepciones.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import java.util.Date;

public class Partido {
  //FIXME ¿por qué querrían modelar a una fecha con tres campos string?
	private String dia;
	private String hora;
	private String lugar;
	public List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	public List<Observador> observadores = new ArrayList<Observador>();
	private List<Inscripcion> posiblesJugadores = new ArrayList<Inscripcion>();
	private List<Denegacion> denegaciones = new ArrayList<Denegacion>();
	public List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	//FIXME mantengan siempre a sus atributos privados, para favorecer al encapsulamiento
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
			{
		estado.intentarInscribirA(inscripcion, this);
	}

	public boolean noEstaLlenoDeEstandares() {
		return (this.inscripciones.stream()
				.filter(ins -> !ins.tuTipoDejaPasar()).count() != 10);
	}

	public void inscribiA(Inscripcion inscripcion) {
	  //FIXME Long Method. Si me dicen que después de tres meses pueden seguir entendiendo este código
	  //tengo dos opciones: o darles un premio o no creerles:P
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
			{
		estado.seDioDeBajaSinReemplazante(inscripcion, this);

	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo)
			{
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
			{
		posiblesJugadores.remove(inscripcion);
		this.intentarInscribirA(inscripcion);
	}

	public void seRechazoInscripcion(Inscripcion inscripcion, String motivo) {

		posiblesJugadores.remove(inscripcion);
		//FIXME no usen Date, usen LocalDate, que tienen una interfaz más simple de usar y 
		//presentan mejores cualidade de diseño
		Denegacion denegacion = new Denegacion(motivo, inscripcion, new Date(),
				inscripcion.jugador());
		denegaciones.add(denegacion);
	}

	public boolean verificarSiJugo(Jugador jugador) {
	  //FIXME usar equals (equivalencia) y no == (identidad)
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

	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo)
			{
		estado.armarEquipos(criterio, algoritmo, this);

	}

	public void aceptarEquipos()
			{
		estado.aceptarEquipos(this);
	}

	//FIXME la convención de Java para los setters es setXYZ, y no 
	//"tuXyzEs"
	public void tuEstadoEs(Estado unEstado) {
		estado = unEstado;

	}

	public void tenes10Jugadores() {
		if (inscripciones.size() < 10) {
			throw new ElPartidoNoEstaCompleto();
		}
	}
}
