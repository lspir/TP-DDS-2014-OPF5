package opf5.inscripcion;
import opf5.partido.*;

public class Condicional extends  TipoDeInscripcion {

	private Condicion condicion;
	
	public Condicional(){
		this.nombre="Condicional";
	}

	public void setearCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Boolean dejasPasar() {
		return true;
	}

	public Boolean tePodesInscribirA(Partido partido) {
		return this.teCumple(partido);
	}
	
	public Boolean teCumple(Partido partido){
		return condicion.teCumple(partido);
	}

	public Boolean prioridadMinima() {
		return true;
	}
}
