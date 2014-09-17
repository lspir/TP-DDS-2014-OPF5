package ui;

import java.time.LocalDate;

import static java.util.stream.Collectors.toList;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.commons.model.ObservableUtils;

import opf5.FormacionPartido;
import opf5.HomePartidos;
import opf5.Partido;
import opf5.AlgoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.estadoPartido.Estado;
import opf5.estadoPartido.SinOrdenar;
import opf5.inscripcion.Estandar;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.*;

public class UnViewModelGeneracion {

	private AlgoritmoDivisionDeEquipos algoritmoDivision;
	private CriterioOrdenamientoEquipos criterioOrdenamiento;
	private List<AlgoritmoDivisionDeEquipos> algoritmosDivision = new ArrayList<AlgoritmoDivisionDeEquipos>();
	private List<CriterioOrdenamientoEquipos> criteriosOrdenamiento = new ArrayList<CriterioOrdenamientoEquipos>();
	private Partido partido;
	private Jugador jugadorSeleccionado;
	private List<Jugador> equipo1;
	private List <Jugador> equipo2;
	
	public UnViewModelGeneracion(){
		partido=HomePartidos.getInstance().getData().get(0);
//	partido.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
//	this.formacion=partido.getFormacionesTentativas().get(0);
//	partido.aceptarEquipos(formacion);
	}
	
	public Jugador getJugadorSeleccionado() {
		return jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
		this.jugadorSeleccionado = jugadorSeleccionado;
	}

	public List<Jugador> getEquipo1() {
		if(partido.getFormacionesTentativas().size()==0){
			return new ArrayList<Jugador>();
		}
		return this.formacion().getEquipoA().stream().map(inscrip -> inscrip.jugador()).collect(toList());
}

	public void setEquipo1(List<Jugador> equipo1) {
//		this.equipo1 = equipo1;
	}

	public List<Jugador> getEquipo2() {
		if(partido.getFormacionesTentativas().size()==0){
			return new ArrayList<Jugador>();
		}
		return this.formacion().getEquipoB().stream().map(inscrip -> inscrip.jugador()).collect(toList());
	}

	public void setEquipo2(List<Jugador> equipo2) {
//		this.equipo2 = equipo2;
	}

	public ArrayList<CriterioOrdenamientoEquipos> getCriteriosOrdenamiento() {
		ArrayList<CriterioOrdenamientoEquipos> asd = new ArrayList<CriterioOrdenamientoEquipos>();
		asd.add(new PromedioDeUltimoPartido());
		asd.add(new UltimasNCalificaciones(3));
		return asd;
	}

	public ArrayList<AlgoritmoDivisionDeEquipos> getAlgoritmosDivision() {
		ArrayList<AlgoritmoDivisionDeEquipos> asd = new ArrayList<AlgoritmoDivisionDeEquipos>();
		asd.add(new DivisionPorPares());
		asd.add(new EquipoA03478EquipoB12569());
		return asd;
	}

	public AlgoritmoDivisionDeEquipos getAlgoritmoDivision() {
		return algoritmoDivision;
	}

	public void setAlgoritmoDivision(AlgoritmoDivisionDeEquipos nom) {
		this.algoritmoDivision = nom;
	}

	public void setAlgoritmosDivision(
			ArrayList<AlgoritmoDivisionDeEquipos> algoritmos) {
		this.algoritmosDivision = algoritmos;
	}
	
	public CriterioOrdenamientoEquipos getCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}

	public void setCriterioOrdenamiento (CriterioOrdenamientoEquipos nom) {
		this.criterioOrdenamiento= nom;
	}

	public void setCriteriosOrdenamiento(
			ArrayList<CriterioOrdenamientoEquipos> criterios) {
		this.criteriosOrdenamiento= criterios;
	}
	public void generacionEquipos(){
		System.out.println("asd");
//		partido.armarEquipos(criterioOrdenamiento, algoritmoDivision);
//		
//		ObservableUtils.firePropertyChanged(this, "getEquipo1", this.formacion().getEquipoA().stream().map(inscrip -> inscrip.jugador()).collect(toList()));
//		ObservableUtils.firePropertyChanged(this, "getEquipo2", this.formacion().getEquipoA().stream().map(inscrip -> inscrip.jugador()).collect(toList()));
//		
	}
	public void confirmarEquipos(){
		partido.aceptarEquipos(this.formacion());
	}

	private FormacionPartido formacion() {
		return partido.getFormacionesTentativas().get(0);
		}
}
