package opf5.criteriosDeOrdenamientoDeEquipos;
import java.util.List;
import opf5.*;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class UltimasNCalificaciones implements Criterio {

	private int n;
	
	public UltimasNCalificaciones(int n){
		this.n= n;
	}
	public double funcion(Jugador jugador) {
		List<Critica> criticas = jugador.criticas();
		//FIXME esto no es un skip?
		List<Integer> lista = criticas.subList(criticas.size() - (n+1), criticas.size()-1).stream().map(critica-> critica.nota()).collect(toList());
		return lista.stream().mapToInt(i -> i).average().orElse(0);
	}

}
