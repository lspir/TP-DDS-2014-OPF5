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
		jugador.handicap(4);
		this.create(jugador);
		Jugador jugador2 = new Jugador("Leandro", 20);
		Jugador jugador3 = new Jugador("Emiliano", 21);
		Jugador jugador4 = new Jugador("Alicia", 17);
		Jugador jugador5 = new Jugador("Noelia", 19);
		Jugador jugador6 = new Jugador("Tomasito", 28);
		Jugador jugador7 = new Jugador("Patricio", 24);
		Jugador jugador8 = new Jugador("Beto", 35);
		Jugador jugador9 = new Jugador("Genaro", 16);
		Jugador jugador10 = new Jugador("Kiara", 15);
		jugador2.handicap(9);
		jugador3.handicap(7);
		jugador4.handicap(2);
		jugador5.handicap(5);
		jugador6.handicap(11);
		jugador7.handicap(9);
		jugador8.handicap(8);
		jugador9.handicap(10);
		jugador10.handicap(3);
		this.create(jugador2);
		this.create(jugador3);
		this.create(jugador4);
		this.create(jugador5);
		this.create(jugador6);
		this.create(jugador7);
		this.create(jugador8);
		this.create(jugador9);
		this.create(jugador10);

	}

	public void create(Jugador jugador) {
		this.data.add(jugador);
	}

	public List<Jugador> search(int numero, String nombre, int handicapDesde,
			int handicapHasta) {
		List<Jugador> resultados = new ArrayList<Jugador>();
		if (handicapHasta == 0) {
			for (Jugador jugador : this.data) {

				if (jugador.edad() > numero
						&& (jugador.getHandicap() >= handicapDesde)
						&& (jugador.nombre().startsWith(nombre))) {
					resultados.add(jugador);
				}
			}
		} else {
			if (handicapDesde == 0) {
				for (Jugador jugador : this.data) {

					if (jugador.edad() > numero
							&& (jugador.getHandicap() <= handicapHasta)
							&& (jugador.nombre().startsWith(nombre))) {
						resultados.add(jugador);
					}
				}
			}
		}
		return resultados;
	}

	public List<Jugador> getData() {
		return this.data;
	}
}
