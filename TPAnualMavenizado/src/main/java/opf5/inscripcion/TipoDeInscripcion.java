package opf5.inscripcion;
import opf5.partido.*;


public abstract class TipoDeInscripcion { 
	public String nombre; 
	
	public abstract Boolean dejasPasar();

	public abstract Boolean tePodesInscribirA(Partido partido);

	public abstract Boolean prioridadMinima();
	
	public abstract Boolean teCumple(Partido partido);

} 
