package opf5;

public class Solidario implements TipoDeInscripcion {
	public Boolean dejasPasar() {
		return true;
	}

	public Boolean tePodesInscribirA(Partido partido) {
		return true;
	}

	public Boolean prioridadMinima() {
		return false;
	}

}
