package refactor.inscripcion;

import refactor.inscripcion.CriterioInscripcion;

public class ModoEstandar implements CriterioInscripcion {

	public String toString() {
		return "Estándar";
	}

	public boolean dejaLugarAOtro() {
		return false;
	}
}		
