package opf5;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioOrdenamientoEquipos;
import opf5.criteriosDeOrdenamientoDeEquipos.MixDeCriterios;
import opf5.criteriosDeOrdenamientoDeEquipos.PromedioDeUltimoPartido;
import opf5.criteriosDeOrdenamientoDeEquipos.UltimasNCalificaciones;
import opf5.jugador.Critica;

import org.junit.Test;

public class TestOPF5_CriteriosOrdenamiento extends TestOPF5{
	@Test
	public void aplicarCriterioHandicapAUnJugadorConValor8Es8() {
		CriterioHandicap criterioHandicap = new CriterioHandicap();
		jugadorConAmigos.handicap(8);
		assertEquals(8, criterioHandicap.ponderate(jugadorConAmigos), 1);
	}

	@Test
	public void aplicarCriterioPromedioDeUltimoPartidoAJugadorConPromedioDe8Es8() {

		PromedioDeUltimoPartido criterioPromedioDeUltimoPartido = new PromedioDeUltimoPartido();
		Critica criticaDe8 = new Critica(8, "recomendable", partidoCon1Estandar);
		Critica criticaDe2 = new Critica(2, "malo", partidoCon8Estandares1Solidario1Condicional);
		jugador.agregarCritica(criticaDe2);
		for (int i = 0; i < 3; i++) {
			jugador.agregarCritica(criticaDe8);
		}
		assertEquals(8, criterioPromedioDeUltimoPartido.ponderate(jugador), 0);

	}

	@Test
	public void aplicarCriterioUltimas3CalificacionesDe7AJugadorDevuelve7() {

		Critica criticaDe7 = new Critica(7, "bueno", partidoCon1Estandar);
		UltimasNCalificaciones criterioUltimasN = new UltimasNCalificaciones(3);
		for (int i = 0; i < 5; i++) {
			jugador.agregarCritica(criticaDe7);
		}
		assertEquals(7, criterioUltimasN.ponderate(jugador), 1);

	}

	@Test
	public void aplicarCriterioMixDeCriterioAJugador() {
		Critica criticaDe7 = new Critica(7, "bueno", partidoCon1Estandar);
		Critica criticaDe5 = new Critica(5, "regular", partidoCon8Estandares1Solidario1Condicional);
		UltimasNCalificaciones criterioUltimasN = new UltimasNCalificaciones(2);
		PromedioDeUltimoPartido criterioPromedioDeUltimoPartido = new PromedioDeUltimoPartido();
		List<CriterioOrdenamientoEquipos> criterios = new ArrayList<CriterioOrdenamientoEquipos>();
		criterios.add(criterioPromedioDeUltimoPartido);
		criterios.add(criterioUltimasN);
		MixDeCriterios criterioMix = new MixDeCriterios(criterios);
		for (int i = 0; i < 6; i++) {
			jugador.agregarCritica(criticaDe7);
			jugador.agregarCritica(criticaDe5);
		}
		assertEquals(5.5, criterioMix.ponderate(jugador), 0);
	}


}
