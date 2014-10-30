package opf5.inscripcion;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import opf5.partido.*;

public abstract class TipoDeInscripcion { 
	
	protected String nombre; 

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	protected Condicion condicion;
	
	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public abstract Boolean dejasPasar();

	public abstract Boolean tePodesInscribirA(Partido partido);

	public abstract Boolean prioridadMinima();
	
	public abstract Boolean teCumple(Partido partido);

} 
