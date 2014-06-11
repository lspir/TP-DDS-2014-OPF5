package opf5;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoDivision {

	private List<Integer> posicionesLista1 = new ArrayList<Integer>();
	private List<Integer> posicionesLista2 = new ArrayList<Integer>();

	public AlgoritmoDivision(List<Integer> lista1, List<Integer> lista2) {
		this.posicionesLista1 = lista1;
		this.posicionesLista2 = lista2;
	}

	public List<Integer> dameLista(List<Integer> lista) {
		List<Integer> listaAux = new ArrayList<Integer>();
		listaAux.addAll(this.dameLista1(lista));
		listaAux.addAll(this.dameLista2(lista));
		return listaAux;
	}

	public List<Integer> dameLista1(List<Integer> lista) {
		List<Integer> listaAux = new ArrayList<Integer>();
		posicionesLista1.forEach(posicion -> listaAux.add(lista.get(posicion)));
		return listaAux;
	}

	public List<Integer> dameLista2(List<Integer> lista) {
		List<Integer> listaAux = new ArrayList<Integer>();
		posicionesLista2.forEach(posicion -> listaAux.add(lista.get(posicion)));
		return listaAux;
	}

}
