package opf5;

public class Condicional implements TipoDeInscripcion {
	public Boolean dejasPasar() {
		return true;
	}

	public Boolean tePodesInscribirA(Partido partido) {
		return false;
	}

	public Boolean prioridadMinima() {
		return true;
	}
}
