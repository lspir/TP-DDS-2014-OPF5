package ui;

import opf5.jugador.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

import org.uqbar.commons.utils.Observable;

@Observable
public class UnViewModelDatosJugador {

	private String nombre;
	private int handicap;
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	
	public UnViewModelDatosJugador(Jugador jugador) {
		this.nombre = jugador.nombre();
		this.handicap = jugador.getHandicap();
		this.infracciones = jugador.infracciones();
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

}
