package utilitarios;

import opf5.jugador.Jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public class CriterioBusquedaDesde extends CriterioBusqueda {

	public CriterioBusquedaDesde() {
		this.setNombre("Desde");
	}
	
	@Override
	public Boolean teCumple(int valor, Jugador jugador) {
		return jugador.getHandicap()>valor;
	
	}

	@Override
	public boolean teCumple(double promedio, Jugador jugador) {
		return jugador.getPromedioUltimoPartido()>promedio;
	}

}
