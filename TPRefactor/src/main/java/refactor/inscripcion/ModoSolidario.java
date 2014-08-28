package refactor.inscripcion;

import refactor.inscripcion.CriterioInscripcion;

public class ModoSolidario implements CriterioInscripcion {

	public String toString() {
		return "Solidario";
	}

	public boolean dejaLugarAOtro() {
		return true;
	}
	
}
