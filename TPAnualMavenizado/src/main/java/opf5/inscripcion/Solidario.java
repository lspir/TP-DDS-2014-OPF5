package opf5.inscripcion;
import opf5.partido.*;

public class Solidario extends TipoDeInscripcion {
	
	public Solidario(){
		this.nombre = "Solidario";
	}
	public Boolean dejasPasar() {
		return true;
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
