package opf5;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private String nombre;
	private int edad;
	private List<Amigo> amigos;
	private List<Infraccion> infracciones;
	private List<Critica> criticas;

	public Jugador(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
		amigos = new ArrayList<Amigo>();
		infracciones = new ArrayList<Infraccion>();
		criticas = new ArrayList<Critica>();
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

	public void critica(Jugador jugador, int nota, String texto, Partido partido)
			throws NoSePuedeCalificarException {
		if (partido.verificarSiJugo(this) && partido.verificarSiJugo(jugador)) {
			Critica critica = new Critica(nota, texto);
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
}
