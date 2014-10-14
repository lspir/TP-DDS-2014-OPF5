package utilitarios;

import opf5.jugador.Jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public class CriterioBusquedaHasta extends CriterioBusqueda {

	public CriterioBusquedaHasta() {
		this.setNombre("Hasta");
	}
	
	@Override
	public Boolean teCumple(int valor, Jugador jugador) {
		return jugador.getHandicap()<=valor;
	
	}

	@Override
	public boolean teCumple(double promedio, Jugador jugador) {
		return jugador.getPromedioUltimoPartido()<=promedio;
	}

}
