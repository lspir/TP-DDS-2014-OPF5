package opf5;

import java.util.Date;
import opf5.jugador.*;
import opf5.inscripcion.*;

public class Denegacion {
	private String motivo;
	private Inscripcion inscripcion;
	private Date fecha;
	private Jugador jugador;

	public Denegacion(String motivo, Inscripcion inscripcion, Date fecha, Jugador jugador) {
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
