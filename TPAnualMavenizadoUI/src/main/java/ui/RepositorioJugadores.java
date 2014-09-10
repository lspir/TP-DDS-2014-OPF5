package ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import opf5.jugador.*;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioJugadores implements Serializable {

	private static RepositorioJugadores instance;
	private List<Jugador> data = new ArrayList<Jugador>();

	public static synchronized RepositorioJugadores getInstance() {
		if (instance == null) {
			instance = new RepositorioJugadores();
		}
		return instance;
	}

	private RepositorioJugadores() {
		Jugador jugador = new Jugador("Belén", 21);
		jugador.handicap(4); // para ver si me lo muestra bien
		this.create(jugador);
		this.create(new Jugador("Leandro", 20));
		this.create(new Jugador("Emiliano", 21));
		this.create(new Jugador("asd", 17));
		this.create(new Jugador("fsa", 19));
		this.create(new Jugador("afs", 28));
		this.create(new Jugador("aet", 24));
		this.create(new Jugador("awtgf", 35));
		this.create(new Jugador("qetfas", 16));
		this.create(new Jugador("astwaf", 15));

	}

	public void create(Jugador jugador) {
		this.data.add(jugador);
	}

	public List<Jugador> search(int numero) {
		return this.search(numero, null);
	}

	public List<Jugador> search(int numero, String nombre) {
		List<Jugador> resultados = new ArrayList<Jugador>();

		for (Jugador jugador : this.data) {
			if (jugador.edad() > numero) {
				resultados.add(jugador); // BUSQUEDA ANIDADA *mentira, fallé en
											// el intento :( *
			}
		}

		return resultados;
	}

	public List<Jugador> getData() {
		return this.data;
	}
}
