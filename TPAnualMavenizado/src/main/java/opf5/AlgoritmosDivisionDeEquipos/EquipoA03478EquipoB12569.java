package opf5.AlgoritmosDivisionDeEquipos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EquipoA03478EquipoB12569 extends AlgoritmoDivisionDeEquipos{
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