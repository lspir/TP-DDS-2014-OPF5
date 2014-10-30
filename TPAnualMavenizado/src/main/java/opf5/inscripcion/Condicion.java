package opf5.inscripcion;
import javax.persistence.Entity;
import javax.persistence.Table;

import db.PersistentEntity;
import opf5.partido.*;
@Entity
@Table(name="Condiciones")
public abstract class Condicion extends PersistentEntity {
	String condicion;
	
	
	public String getCondicion() {
		return condicion;
	}


	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}


	public abstract boolean teCumple(Partido unPartido);
}
