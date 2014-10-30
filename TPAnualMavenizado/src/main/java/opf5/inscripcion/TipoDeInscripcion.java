package opf5.inscripcion;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import opf5.partido.*;
@Embeddable
public abstract class TipoDeInscripcion { 
	@Column(name="TipoDeInscripcion")
	public String nombre; 

	@ManyToOne
	public Condicion condicion;
	
	public abstract Boolean dejasPasar();

	public abstract Boolean tePodesInscribirA(Partido partido);

	public abstract Boolean prioridadMinima();
	
	public abstract Boolean teCumple(Partido partido);

} 
