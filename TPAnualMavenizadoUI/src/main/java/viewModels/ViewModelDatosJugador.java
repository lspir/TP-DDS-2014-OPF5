package viewModels;

import static java.util.stream.Collectors.toList;

import java.util.*;

import opf5.*;
import opf5.jugador.*;

import org.uqbar.commons.utils.Observable;

@Observable
public class ViewModelDatosJugador {

	private String nombre;
	private int handicap;
	private double promedio, ultimoPromedio;
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	private List<String> amigos = new ArrayList<String>();
	private long cantidadPartidos;
	
	public long getCantidadPartidos() {
		return cantidadPartidos;
	}

	public void setCantidadPartidos(long cantidadPartidos) {
		this.cantidadPartidos = cantidadPartidos;
	}

	public ViewModelDatosJugador(Jugador jugador) {
		this.nombre = jugador.nombre();
		this.handicap = jugador.getHandicap();
		this.infracciones = jugador.infracciones();
		this.cantidadPartidos = HomePartidos.getInstance().consultarCantidadDePartidosJugados(jugador);
		this.promedio= jugador.getPromedio();
		this.ultimoPromedio = jugador.getPromedioUltimoPartido();
		this.amigos=jugador.amigos().stream().map((amigo)->amigo.direccion()).collect(toList());
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

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public double getUltimoPromedio() {
		return ultimoPromedio;
	}

	public void setUltimoPromedio(double ultimoPromedio) {
		this.ultimoPromedio = ultimoPromedio;
	}

	public List<String> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<String> amigos) {
		this.amigos = amigos;
	}

}
