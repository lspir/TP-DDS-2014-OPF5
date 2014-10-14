package opf5;

import static org.junit.Assert.assertEquals;
import opf5.excepciones.NoSePuedeCalificarException;

import org.junit.Test;

public class TestOPF5_Calificaciones extends TestOPF5 {
	@Test
	public void UnJugadorSeDaDeBajaSinReemplazanteEntoncesSeLoPenaliza() {
		partidoCon1Estandar.seDioDeBajaSinReemplazante(inscripcionEstandar);
		assertEquals(1, inscripcionEstandar.jugador().infracciones().size());
	}
	@Test(expected = NoSePuedeCalificarException.class)
	public void unJugadorQueNoParticipoIntentaCalificarParaEsePartidoEntoncesEsaCalificacionNoEsTenidaEnCuenta() {
		jugadorCritico.criticar(jugador, 8, "Se atajó todo", partidoCon1Estandar);
	}

	@Test
	public void unJugadorQueParticipoIntentaCalificarParaEsePartidoEntoncesEsaCalificacionSeGuardaEnLaListaDelJugadorCalificado() {
		partidoCon1Estandar.intentarInscribirA(inscripcionSolidario);
		jugador.criticar(jugador, 8, "Se atajó todo", partidoCon1Estandar);
		assertEquals(1, jugador.criticas().size());
	}

}
