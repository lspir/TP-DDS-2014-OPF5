package opf5.partido;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import opf5.inscripcion.*;
@Entity
@Table(name="Formaciones")
public class FormacionPartido extends PersistentEntity{
	@ManyToMany
	private List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	@ManyToMany
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
