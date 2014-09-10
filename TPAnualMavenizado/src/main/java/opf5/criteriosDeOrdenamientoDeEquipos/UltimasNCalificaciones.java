package opf5.criteriosDeOrdenamientoDeEquipos;
import java.util.List;

import opf5.jugador.*;
import opf5.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class UltimasNCalificaciones extends PromedioDeUltimoPartido {
	private int n;
	
	public UltimasNCalificaciones(int n){
		super("Promedio Ultimas ".concat(String.valueOf(n)).concat(" Calificaciones"));
		this.n= n;
	}
	public double ponderate(Jugador jugador) {
		return calcularPromedioOdevolver0(calcularUltimasNcalificaciones(jugador));
	}


	public List<Integer> calcularUltimasNcalificaciones(Jugador jugador) {
		Stream<Critica> ultimasNcriticas =jugador.criticas().stream().skip(n);
		return calcularCalificacionesDeUnaListaDeCriticas(ultimasNcriticas);
	}
	
}


