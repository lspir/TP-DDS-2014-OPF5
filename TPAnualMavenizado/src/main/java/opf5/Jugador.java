package opf5;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private String nombre;
	private int edad;
	private List<Amigo> amigos;
	private List<Infraccion> infracciones;

	public Jugador(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
		amigos = new ArrayList<Amigo>();
		infracciones = new ArrayList<Infraccion>();
	}

	public void agregarAmigo(Amigo amigo) {
		this.amigos.add(amigo);
	}

	public List<Infraccion> infracciones() {
		return this.infracciones;
	}

	public void avisarATusAmigos() {

		amigos.forEach(amigo -> StubMailSender.notificar(amigo.direccion(),
				this));

	}

	public List<Amigo> amigos() {
		return amigos;
	}

	public void tePenalizaron(Infraccion infraccion) {
		infracciones.add(infraccion);
	}
}
