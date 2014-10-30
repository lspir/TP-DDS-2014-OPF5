package opf5.jugador;

import java.util.*;

import org.uqbar.commons.utils.Observable;

import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.excepciones.*;
import opf5.partido.*;
import db.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Observable
@Table(name="Jugadores")
public class Jugador extends PersistentEntity{
	private String nombre;
	private int edad;
	private int handicap;
	@ManyToMany
	private List<Amigo> amigos;
	@OneToMany
	@JoinColumn(name="id")
	private List<Infraccion> infracciones;
	@OneToMany
	@JoinColumn(name="id")
	private List<Critica> criticas;
	@Transient
	private double promedio;
	@Transient
	private double promedioUltimoPartido;

	public Jugador(String nombre, int edad, int handicap) {
		this.nombre = nombre;
		this.edad = edad;
		amigos = new ArrayList<Amigo>();
		infracciones = new ArrayList<Infraccion>();
		criticas = new ArrayList<Critica>();
		this.setPromedio(0);
		this.handicap=handicap;
	}
	
	
	@PostLoad
	public void postLoad(){
		this.setearPromedioCalificaciones();
		this.setearPromedioDelUltimoPartido();
	}

	public void handicap(int handicap) {
		this.handicap = handicap;
	}

	public int getHandicap() {
		return this.handicap;
	}

	public void agregarAmigo(Amigo amigo) {
		this.amigos.add(amigo);
	}

	public List<Infraccion> infracciones() {
		return this.infracciones;
	}

	public List<Amigo> amigos() {
		return amigos;
	}

	public void tePenalizaron(Infraccion infraccion) {
		infracciones.add(infraccion);
	}

	public void criticar(Jugador jugador, int nota, String texto,
			Partido partido) {
		if (partido.verificarSiJugo(this) && partido.verificarSiJugo(jugador)) {
			Critica critica = new Critica(nota, texto, partido);
			jugador.agregarCritica(critica);
		} else
			throw new NoSePuedeCalificarException();

	}

	public void agregarCritica(Critica critica) {
		criticas.add(critica);
		this.setearPromedioCalificaciones();
		this.setearPromedioDelUltimoPartido();
	}

	public void setearPromedioCalificaciones(){
		setPromedio((new UltimasNCalificaciones(criticas.size()).ponderate(this)));
	}
	
	public void setearPromedioDelUltimoPartido(){
		setPromedioUltimoPartido(new PromedioDeUltimoPartido().ponderate(this));
	}
	public List<Critica> criticas() {
		return criticas;
	}

	public String nombre() {
		return nombre;
	}
	public int edad(){
		return edad;
	}

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = (double)Math.round(promedio*100)/100;
	}

	public double getPromedioUltimoPartido() {
		return promedioUltimoPartido;
	}

	public void setPromedioUltimoPartido(double promedioUltimoPartido) {
		this.promedioUltimoPartido = (double)Math.round(promedioUltimoPartido*100)/100;
	}
	
}
	