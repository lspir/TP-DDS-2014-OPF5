package opf5.AlgoritmosDivisionDeEquipos;

import java.util.*;

import org.uqbar.commons.utils.Observable;

@Observable
public class EquipoA03478EquipoB12569 extends AlgoritmoDivisionDeEquipos{
	public EquipoA03478EquipoB12569() {
		super("Equipo A: 1,4,5,8,9 Equipo B: 2,3,6,7,10");
	}
	private List<Integer> posicionesLista1 = new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 3,4,7,8 }));
	private List<Integer> posicionesLista2 = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2,5,6,9 }));
	
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