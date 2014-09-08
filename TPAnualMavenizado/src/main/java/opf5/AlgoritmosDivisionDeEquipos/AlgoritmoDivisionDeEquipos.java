package opf5.AlgoritmosDivisionDeEquipos;

import java.util.ArrayList;
import java.util.List;

import opf5.inscripcion.*;

public abstract class AlgoritmoDivisionDeEquipos {


	public List<Inscripcion> dameLista(List<Inscripcion> lista) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		listaAux.addAll(this.dameListaSegunPosiciones(lista,this.getPosicionesLista1()));
		listaAux.addAll(this.dameListaSegunPosiciones(lista,this.getPosicionesLista2()));
		return listaAux;
	}

	public List<Inscripcion> dameListaSegunPosiciones(List<Inscripcion> lista,List<Integer>posiciones) {
		List<Inscripcion> listaAux = new ArrayList<Inscripcion>();
		posiciones.forEach(posicion -> listaAux.add(lista.get(posicion)));
		return listaAux;
	}
	
	abstract  List<Integer> getPosicionesLista1();
	abstract List<Integer> getPosicionesLista2();

}
