package opf5;

public class Estandar implements TipoDeInscripcion {
	public Boolean dejasPasar() {
		return false;
	}

	public Boolean tePodesInscribirA(Partido partido) {
		return true;
	}

	public Boolean prioridadMinima() {
		return false;
	}
}
