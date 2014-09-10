package ui;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import opf5.AlgoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.jugador.*;

public class UnViewModelGeneracion {

	private AlgoritmoDivisionDeEquipos algoritmoDivision;
	private CriterioOrdenamientoEquipos criterioOrdenamiento;
	private List<AlgoritmoDivisionDeEquipos> algoritmosDivision = new ArrayList<AlgoritmoDivisionDeEquipos>();
	private List<CriterioOrdenamientoEquipos> criteriosOrdenamiento = new ArrayList<CriterioOrdenamientoEquipos>();
	private List <Jugador> equipo1 = new ArrayList<Jugador>();
	private List <Jugador> equipo2 = new ArrayList<Jugador>();
	private Jugador jugadorSeleccionado;
	
	public Jugador getJugadorSeleccionado() {
		return jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
		this.jugadorSeleccionado = jugadorSeleccionado;
	}

	public List<Jugador> getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(List<Jugador> equipo1) {
		this.equipo1 = equipo1;
	}

	public List<Jugador> getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(List<Jugador> equipo2) {
		this.equipo2 = equipo2;
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
}
