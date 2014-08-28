package opf5;

import java.util.ArrayList;
import java.util.List;
import opf5.inscripcion.*;

//FIXME expresividad. ¿Algoritmo de división de qué? 
//Algoritmo de división "solo"me suena a algoritmo de divión numérica xD
public class AlgoritmoDivision {

	private List<Integer> posicionesLista1 = new ArrayList<Integer>();
	private List<Integer> posicionesLista2 = new ArrayList<Integer>();

	public AlgoritmoDivision(List<Integer> lista1, List<Integer> lista2) {
		this.posicionesLista1 = lista1;
		this.posicionesLista2 = lista2;
	}

	public List<Inscripcion> dameLista(List<Inscripcion> lista) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		listaAux.addAll(this.dameLista1(lista));
		listaAux.addAll(this.dameLista2(lista));
		return listaAux;
	}

	//FIXME lógica repetida entre ambos métodos
	public List<Inscripcion> dameLista1(List<Inscripcion> lista) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		posicionesLista1.forEach(posicion -> listaAux.add(lista.get(posicion)));
		return listaAux;
	}

	public List<Inscripcion> dameLista2(List<Inscripcion> lista) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		posicionesLista2.forEach(posicion -> listaAux.add(lista.get(posicion)));
		return listaAux;
	}

}
