package utilitarios;

import opf5.jugador.Jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public class CriterioBusquedaInfractores extends CriterioBusquedaInfractoresAbstracta{
	
	public CriterioBusquedaInfractores(){
		this.setNombre("Con Infracciones");
	}

	@Override
	public boolean teCumple(Jugador jugador) {
		return jugador.infracciones().size()>0;
	}
}
