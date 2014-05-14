package opf5;

public class Condicional implements TipoDeInscripcion {

	private Condicion condicion;

	public void setearCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Boolean dejasPasar() {
		return true;
	}

	public Boolean tePodesInscribirA(Partido partido) {
		return condicion.teCumple(partido);
	}

	public Boolean prioridadMinima() {
		return true;
	}
}
