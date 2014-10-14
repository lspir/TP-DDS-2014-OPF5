package opf5.criteriosDeOrdenamientoDeEquipos;

import org.uqbar.commons.utils.Observable;

import opf5.jugador.*;
@Observable
public abstract class CriterioOrdenamientoEquipos {
	private String nombre;
	public CriterioOrdenamientoEquipos(String nombre){
		this.nombre=nombre;
	}
	
  	abstract public double ponderate(Jugador jugador);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
  	
  	

}

