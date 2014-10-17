package opf5.inscripcion;
import opf5.partido.*;


public interface TipoDeInscripcion {
	public Boolean dejasPasar();

	public Boolean tePodesInscribirA(Partido partido);

	public Boolean prioridadMinima();
	
	public Boolean teCumple(Partido partido);

}
