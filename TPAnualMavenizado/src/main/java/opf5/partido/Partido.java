package opf5.partido;
import static java.util.stream.Collectors.*;

import java.time.*;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.estadoPartido.*;
import opf5.excepciones.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.observers.*;

@Entity
@Table(name="Partidos")
public class Partido extends PersistentEntity {
  	private LocalDate fecha;
	private LocalTime horario;
	private String lugar;
	@ManyToMany
	private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	@Transient
	private List<Observador> observadores = new ArrayList<Observador>();
	@ManyToMany
	private List<Inscripcion> posiblesJugadores = new ArrayList<Inscripcion>();
	@OneToMany
	@JoinColumn(name="id")
	private List<Denegacion> denegaciones = new ArrayList<Denegacion>();
	@OneToMany
	@JoinColumn(name="id")
	private List<FormacionPartido> formacionesTentativas = new ArrayList<FormacionPartido>();
	@Transient
	private Estado estado;
	private String nombreEstado;
	@OneToOne
	private FormacionPartido formacionConfirmada;

	
	public Partido(){
		
	}
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
		Denegacion denegacion = new Denegacion(motivo, inscripcion, LocalDate.now());
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
	
	public Estado getEstado() {
		return estado;

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


	public int jugo(Jugador jugador) {
		return estado.jugo(jugador);
	}
	
	@PrePersist
	@PreUpdate
	public void prePersistir(){
	this.nombreEstado=this.estado.getNombre();
	if(this.nombreEstado=="Confirmado"){
	this.formacionConfirmada=this.estado.getFormacionConfirmada();	
	}
	}
	
	@PostLoad
	public void cargarEstado(){
		switch(this.nombreEstado){
		case "Confirmado": this.estado=new Confirmado(this.formacionConfirmada);			
		case "Ordenado": this.estado= new Ordenado();
		case "Sin Ordenar":this.estado= new SinOrdenar();
		}
	}

}
