package opf5.partido;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import opf5.inscripcion.*;
@Entity
@Table(name="Formaciones")
public class FormacionPartido extends PersistentEntity{
	@Column(name = "index_formacion")
	Integer index;


	@ManyToMany
	@JoinTable(name="Formaciones_equipoA",
	joinColumns={@JoinColumn(name="id_Formacion",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="id_inscripcion",referencedColumnName="id")})
	private List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	@ManyToMany
	@JoinTable(name="Formaciones_equipoB",
	joinColumns={@JoinColumn(name="id_Formacion",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="id_inscripcion",referencedColumnName="id")})
	private List<Inscripcion> equipoB = new ArrayList<Inscripcion>();
	
	public FormacionPartido(List<Inscripcion> equipoA, List<Inscripcion> equipoB) {
		this.equipoA=equipoA;
		this.equipoB=equipoB;
	}

	public FormacionPartido(){
		
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
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
