package opf5.partido;

import java.time.*;

import opf5.inscripcion.*;
import opf5.jugador.*;

public class Denegacion {
	private String motivo;
	private Inscripcion inscripcion;
	private LocalDate fecha;
	

	public Denegacion(String motivo, Inscripcion inscripcion, LocalDate fecha) {
		this.motivo=motivo;
		this. inscripcion=inscripcion;
		this. fecha=fecha;
		}
	
	public Jugador jugador()
	{
		return this.inscripcion.jugador();
	}

}
