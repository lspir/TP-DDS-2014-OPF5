package opf5;

import java.util.ArrayList;
import java.util.List;

import opf5.inscripcion.Inscripcion;

public class FormacionPartido {
	private List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	private List<Inscripcion> equipoB = new ArrayList<Inscripcion>();
	
	public FormacionPartido(List<Inscripcion> equipoA, List<Inscripcion> equipoB) {
		this.equipoA=equipoA;
		this.equipoB=equipoB;
	}

	public List<Inscripcion> getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(List<Inscripcion> equipoA) {
		this.equipoA = equipoA;
	}

	public List<Inscripcion> getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(List<Inscripcion> equipoB) {
		this.equipoB = equipoB;
	}

}
