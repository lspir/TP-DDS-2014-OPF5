package refactor;

import refactor.inscripcion.CriterioInscripcion;
import refactor.inscripcion.ModoSolidario;

import java.util.ArrayList;
import java.util.List;

import refactor.inscripcion.ModoEstandar;

public class Jugador {

	private String nombre;
	private Double calificacion;
	private List<Double> puntajes;
	CriterioInscripcion criterioInscripcion;

	public Jugador() {
		this.puntajes = new ArrayList<Double>();
		this.calificacion = null;
		this.criterioInscripcion = new ModoEstandar();
		this.nombre = "";
	}

	public Jugador(String nombre, double calificacion, List<Double> puntajes) {
		this.calificacion = calificacion;
		this.puntajes = puntajes;
		this.criterioInscripcion = new ModoEstandar();
		this.nombre = nombre;
	}

	public void modoSolidario() {
		criterioInscripcion = new ModoSolidario();
	}

	boolean dejaLugarAOtro() {
		return (criterioInscripcion.dejaLugarAOtro());
	}

	public String toString() {
		// "Jugador (" + calificacion + ") - modo " +
		// criterioInscripcion.toString()
		return nombre;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public List<Double> getPuntajes() {
		return puntajes;
	}
}
