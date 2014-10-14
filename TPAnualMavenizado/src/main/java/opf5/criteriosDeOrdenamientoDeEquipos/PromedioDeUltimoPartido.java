package opf5.criteriosDeOrdenamientoDeEquipos;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.*;

import org.uqbar.commons.utils.Observable;

import opf5.*;
import opf5.jugador.*;
@Observable
public class PromedioDeUltimoPartido extends CriterioOrdenamientoEquipos {

  	public PromedioDeUltimoPartido() {
		super("Promedio Calificaciones Del Ultimo Partido");
		}

	public PromedioDeUltimoPartido(String string) {
		super(string);
	}

	public double ponderate(Jugador jugador) {
		return calcularPromedioOdevolver0(calcularCalificacionesDeUnaListaDeCriticas(obtenerListaDeCriticasUltimoPartido(jugador)));
	}
	
	public List<Integer> calcularCalificacionesDeUnaListaDeCriticas(Stream<Critica> calificaciones){
		return calificaciones.map(critica-> critica.nota()).collect(toList());
		
	}
	
	public double calcularPromedioOdevolver0(List<Integer> lista){
		return lista.stream().mapToDouble(i -> i).average().orElse(0);
	}
	
	private Stream<Critica> obtenerListaDeCriticasUltimoPartido(Jugador jugador){
		Partido ultimoPartidoJugado = jugador.criticas().get(jugador.criticas().size()-1).partido();
		return jugador.criticas().stream().filter(critica-> critica.partido().equals(ultimoPartidoJugado));
	}

}


