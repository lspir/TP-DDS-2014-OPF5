package opf5.jugador;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import db.PersistentEntity;

@Observable
@Entity
@Table(name="Amigos")
public class Amigo extends PersistentEntity {

	private String direccion;
	
	public Amigo(){};
	public Amigo(String direccion)
	{
		this.direccion = direccion; 
	}
	
	public String direccion()
	{
		return this.direccion;
	}
}
