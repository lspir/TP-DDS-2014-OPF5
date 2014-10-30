package opf5.jugador;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import opf5.partido.*;
@Entity
@Table(name="Criticas")
public class Critica extends PersistentEntity{

	private int nota;
	private String texto;
	@ManyToOne
	private Partido partido;

	public Critica(int nota, String texto, Partido partido) {
		this.nota = nota;
		this.texto = texto;
		this.partido = partido;

	}
	
	public Critica(){
		
	}

	public Partido partido() {
		return partido;
	}

	public int nota() {
		return nota;
	}

}
