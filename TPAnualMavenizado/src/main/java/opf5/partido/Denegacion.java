package opf5.partido;

import java.time.*;

import opf5.inscripcion.*;
import opf5.jugador.*;

public class Denegacion {
	private String motivo;
	private Inscripcion inscripcion;
	private LocalDate fecha;
	private Jugador jugador;

	public Denegacion(String motivo, Inscripcion inscripcion, LocalDate fecha, Jugador jugador) {
		this.motivo=motivo;
		this. inscripcion=inscripcion;
		this. fecha=fecha;
		this.jugador = jugador;
	}
	
	public Jugador jugador()
	{
		return this.jugador;
	}

}
