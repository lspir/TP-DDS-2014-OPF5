package opf5.partido;

import java.time.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import opf5.inscripcion.*;
import opf5.jugador.*;
@Entity
@Table(name="Denegaciones")
public class Denegacion extends PersistentEntity{
	private String motivo;
	@ManyToOne
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
