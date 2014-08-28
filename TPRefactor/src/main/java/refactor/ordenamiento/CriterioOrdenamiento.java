package refactor.ordenamiento;

import refactor.Jugador;
import refactor.Partido;

import java.util.Comparator;
import java.util.List;

public interface CriterioOrdenamiento {
	
	public List<Jugador> ordenar(Partido partido);

	public Double calcularValor(Jugador jugador);
		 
}
