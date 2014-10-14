package utilitarios;

import opf5.jugador.Jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public class CriterioBusquedaTodos extends CriterioBusquedaInfractoresAbstracta {

	public CriterioBusquedaTodos(){
		this.setNombre("Todos");
	}

	@Override
	public boolean teCumple(Jugador jugador) {
		return true;
	}
}
