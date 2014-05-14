package opf5;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private String nombreJ;
	private int edadJ;
	private List<Amigo> amigos;

	public Jugador(String nombre, int edad) {
		nombreJ = nombre;
		edadJ = edad;
		amigos = new ArrayList<Amigo>();
	}

	public void agregarAmigo(Amigo amigo) {
		this.amigos.add(amigo);
	}

	public void avisarATusAmigos() {

		amigos.forEach(amigo -> MailSender.notificar(amigo.direccion()));

	}

	public List<Amigo> amigos() {
		return amigos;
	}
}
