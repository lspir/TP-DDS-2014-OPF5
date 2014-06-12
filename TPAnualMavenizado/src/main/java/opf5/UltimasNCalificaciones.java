package opf5;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class UltimasNCalificaciones implements Criterio {

	private int n;
	
	public UltimasNCalificaciones(int n){
		this.n= n;
	}
	public int funcion(Jugador jugador) {
		List<Critica> criticas = jugador.criticas();
		List<Integer> lista = criticas.subList(criticas.size() - n, criticas.size()).stream().map(critica-> critica.nota()).collect(toList());
		int promedio = lista.sum() / lista.size();
		return promedio;
	}

}
