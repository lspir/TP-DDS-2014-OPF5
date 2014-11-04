package opf5.jugador;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import db.PersistentEntity;
@Entity
@Table(name="Infracciones")
public class Infraccion extends PersistentEntity {

	private String momento;
	private int numero;


	public Infraccion(){}
	
	public Infraccion(String momento, int numero){
		this.momento = momento;
		this.numero  = numero;
	}
	
	public String getMomento()
	{
		return this.momento;
	}
	
	public int getNumero()
	{
		return this.numero;
	}
	
}
