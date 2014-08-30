package opf5;

import java.util.ArrayList;
import java.util.List;
import opf5.inscripcion.*;

public class AlgoritmoDivisionDeEquipos {

	private List<Integer> posicionesLista1 = new ArrayList<Integer>();
	private List<Integer> posicionesLista2 = new ArrayList<Integer>();

	public AlgoritmoDivisionDeEquipos(List<Integer> lista1, List<Integer> lista2) {
		this.posicionesLista1 = lista1;
		this.posicionesLista2 = lista2;
	}

	public List<Inscripcion> dameLista(List<Inscripcion> lista) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		listaAux.addAll(this.dameListaSegunPosiciones(lista,this.posicionesLista1));
		listaAux.addAll(this.dameListaSegunPosiciones(lista,this.posicionesLista2));
		return listaAux;
	}

	public List<Inscripcion> dameListaSegunPosiciones(List<Inscripcion> lista,List<Integer>posiciones) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		posiciones.forEach(posicion -> listaAux.add(lista.get(posicion)));
		return listaAux;
	}

}
