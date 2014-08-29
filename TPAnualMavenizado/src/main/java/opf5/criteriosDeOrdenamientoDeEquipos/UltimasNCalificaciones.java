package opf5.criteriosDeOrdenamientoDeEquipos;
import java.util.List;

import opf5.jugador.*;
import opf5.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class UltimasNCalificaciones implements CriterioOrdenamientoEquipos {

	private int n;
	
	public UltimasNCalificaciones(int n){
		this.n= n;
	}
	public double ponderate(Jugador jugador) {
		return calcularPromedioOdevolver0(calcularUltimasNcalificaciones(jugador));
	}


	private List<Integer> calcularUltimasNcalificaciones(Jugador jugador) {
		return jugador.criticas().stream().skip(n).map(critica-> critica.nota()).collect(toList());
	}
	
	private double calcularPromedioOdevolver0(List<Integer> lista){
		return lista.stream().mapToInt(i -> i).average().orElse(0);
	}
}


