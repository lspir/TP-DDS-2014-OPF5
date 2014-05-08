package OPF5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import java.util.*;

public class Prueba {

	public static void main(String[] args) {
		Partido partido = new Partido("2/5", "14:00", "Campus");
		Estandar estandar = new Estandar();
		Solidario solidario = new Solidario();
		for (int i = 0; i < 11; i++) {
			Jugador jugador1 = new Jugador("Ronaldo", 28);

			Inscripcion inscripcion1 = new Inscripcion(jugador1, estandar);
			System.out.println(inscripcion1.nuevaInscripcionA(partido));

		}

		Jugador jugador2 = new Jugador("Bely", 20);
		Inscripcion inscripcion2 = new Inscripcion(jugador2, estandar);
		System.out.println(inscripcion2.nuevaInscripcionA(partido));

		/*
		 * List<Integer> lista = new ArrayList<Integer>(); for (int i=0; i<10;
		 * i++) { lista.add(i); } System.out.println(lista);
		 * System.out.println(lista.size()); //List<Integer> listaFiltrada = new
		 * ArrayList<Integer>();
		 * 
		 * List<Integer> listaFiltrada = lista.stream().filter(num -> (num % 2
		 * )== 0).collect(Collectors.toList());
		 * System.out.println(listaFiltrada);
		 * System.out.println(listaFiltrada.size());
		 */

	}

}
