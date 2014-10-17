package opf5.inscripcion;
import opf5.partido.*;


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
	
	public Boolean teCumple(Partido partido){
		return true;
	}
}
