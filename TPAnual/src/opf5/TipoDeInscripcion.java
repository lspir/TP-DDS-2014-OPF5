package opf5;

public interface TipoDeInscripcion {
	public Boolean dejasPasar();

	public Boolean tePodesInscribirA(Partido partido);

	public Boolean prioridadMinima();
	
	public Boolean teCumple(Partido partido);

}
