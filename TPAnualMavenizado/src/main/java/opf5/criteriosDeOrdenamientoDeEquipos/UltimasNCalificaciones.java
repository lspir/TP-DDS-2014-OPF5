package opf5.criteriosDeOrdenamientoDeEquipos;
import java.util.*;
import java.util.stream.*;

import org.uqbar.commons.utils.Observable;

import opf5.jugador.*;
@Observable
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
		if(n<jugador.criticas().size()){
		Stream<Critica> ultimasNcriticas =jugador.criticas().stream().skip(jugador.criticas().size()-n);
		return calcularCalificacionesDeUnaListaDeCriticas(ultimasNcriticas);}
		else{
			return calcularCalificacionesDeUnaListaDeCriticas(jugador.criticas().stream());
		}
	}
	
}


