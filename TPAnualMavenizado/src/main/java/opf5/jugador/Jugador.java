package opf5.jugador;

import java.util.ArrayList;
import opf5.*;

import java.util.List;
import opf5.excepciones.*;

public class Jugador {
	private String nombre;
	private int edad;
	private List<Amigo> amigos;
	private List<Infraccion> infracciones;
	private List<Critica> criticas;
	private int handicap;

	public Jugador(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
		amigos = new ArrayList<Amigo>();
		infracciones = new ArrayList<Infraccion>();
		criticas = new ArrayList<Critica>();
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
}
