package utilitarios;

import opf5.jugador.Jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public abstract class CriterioBusqueda {
	private String nombre;
	public abstract Boolean teCumple(int valor, Jugador jugador);
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public abstract boolean teCumple(double promedio, Jugador jugador);
	

}
