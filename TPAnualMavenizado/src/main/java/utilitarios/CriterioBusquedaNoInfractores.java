package utilitarios;

import opf5.jugador.Jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public class CriterioBusquedaNoInfractores extends CriterioBusquedaInfractoresAbstracta{

	public  CriterioBusquedaNoInfractores(){
		this.setNombre("Sin Infracciones");
	}

	@Override
	public boolean teCumple(Jugador jugador) {
		return jugador.infracciones().size()==0;
	}
}
