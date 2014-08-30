package opf5.criteriosDeOrdenamientoDeEquipos;

import java.util.ArrayList;

import opf5.jugador.*;
import opf5.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class PromedioDeUltimoPartido implements CriterioOrdenamientoEquipos {

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


