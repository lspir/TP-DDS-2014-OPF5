package refactor.ordenamiento;

import refactor.Jugador;
import refactor.Partido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class CriterioOrdenamiento {
	
	public List<Jugador> ordenar(Partido partido){
				Collections.sort(partido.getInscriptos(), new Comparator<Jugador>() {
				@Override
				public int compare(Jugador jugador1, Jugador jugador2) {
					return calcularValor(jugador1).compareTo(calcularValor(jugador2));
				}
			});

			Collections.reverse(partido.getInscriptos());
			
			List<Jugador> jugadores=new ArrayList<Jugador>();
			jugadores.addAll(partido.getInscriptos());
			return jugadores;
		}
	

	public abstract Double calcularValor(Jugador jugador);
		 
}
