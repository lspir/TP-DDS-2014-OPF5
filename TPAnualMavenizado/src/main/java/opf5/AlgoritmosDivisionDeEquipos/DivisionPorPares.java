package opf5.AlgoritmosDivisionDeEquipos;

import java.util.*;

import org.uqbar.commons.utils.Observable;

@Observable
public class DivisionPorPares extends AlgoritmoDivisionDeEquipos{
	public DivisionPorPares() {
		super("Division Por Pares");
		
	}
	private List<Integer> posicionesLista1 = new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 2,4,6,8 }));
	private List<Integer> posicionesLista2 = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 3,5,7,9 }));
	
	public List<Integer> getPosicionesLista1() {
		return posicionesLista1;
	}
	public void setPosicionesLista1(List<Integer> posicionesLista1) {
		this.posicionesLista1 = posicionesLista1;
	}
	public List<Integer> getPosicionesLista2() {
		return posicionesLista2;
	}
	public void setPosicionesLista2(List<Integer> posicionesLista2) {
		this.posicionesLista2 = posicionesLista2;
	}
	
}