package ui;

import opf5.HomePartidos;
import opf5.jugador.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

import org.uqbar.commons.utils.Observable;

@Observable
public class UnViewModelDatosJugador {

	private String nombre;
	private int handicap, promedio, ultimoPromedio;
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	private long cantidadPartidos;
	
	public long getCantidadPartidos() {
		return cantidadPartidos;
	}

	public void setCantidadPartidos(long cantidadPartidos) {
		this.cantidadPartidos = cantidadPartidos;
	}

	public UnViewModelDatosJugador(Jugador jugador) {
		this.nombre = jugador.nombre();
		this.handicap = jugador.getHandicap();
		this.infracciones = jugador.infracciones();
		this.cantidadPartidos = HomePartidos.getInstance().consultarCantidadDePartidosJugados(jugador);
	}
	
	public String getNombre(){
		return this.nombre;
	}

	public int getHandicap(){
		return this.handicap;
	}
	
	
	public List<Infraccion> getInfracciones()
	{
		return (this.infracciones);
	}

	public int getPromedio() {
		return promedio;
	}

	public void setPromedio(int promedio) {
		this.promedio = promedio;
	}

	public int getUltimoPromedio() {
		return ultimoPromedio;
	}

	public void setUltimoPromedio(int ultimoPromedio) {
		this.ultimoPromedio = ultimoPromedio;
	}

}
