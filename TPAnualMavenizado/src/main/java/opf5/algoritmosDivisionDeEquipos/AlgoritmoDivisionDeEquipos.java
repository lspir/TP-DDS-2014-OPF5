package opf5.algoritmosDivisionDeEquipos;

import java.util.*;

import org.uqbar.commons.utils.Observable;

//import org.uqbar.commons.utils.Observable;
import opf5.inscripcion.*;
@Observable
public abstract class AlgoritmoDivisionDeEquipos {
	private String nombre;
	public AlgoritmoDivisionDeEquipos(String nombre){
		this.setNombre(nombre);
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
