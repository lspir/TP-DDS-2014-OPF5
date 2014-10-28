package opf5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import opf5.inscripcion.Inscripcion;

import org.junit.Test;

public class TestOPF5_PropuestaJugadores extends TestOPF5 {
	@Test
	public void unJugadorPropuestoEsAceptadoYPuedeInscribirseEnElPartidoEntoncesApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador, estandar);
		partidoCon1Estandar.posiblesJugadores().add(inscripcionPropuesta);
		partidoCon1Estandar.administradorAcepto(inscripcionPropuesta);
		assertTrue(partidoCon1Estandar.getInscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador().equals(jugador)));
	}

	@Test
	public void unJugadorPropuestoEsRechazadoNoApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugadorCritico, estandar);
		partidoCon1Estandar.posiblesJugadores().add(inscripcionPropuesta);
		partidoCon1Estandar.seRechazoInscripcion(inscripcionPropuesta, "Me cae mal");
		assertFalse(partidoCon1Estandar.getInscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador().equals(jugadorCritico)));
	}

	@Test
	public void unJugadorPropuestoEsRechazadoEntoncesSeAgregaUnaDenegacion() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador, estandar);
		partidoCon1Estandar.posiblesJugadores().add(inscripcionPropuesta);
		partidoCon1Estandar.seRechazoInscripcion(inscripcionPropuesta, "Me cae mal");
		assertTrue(partidoCon1Estandar
				.denegaciones()
				.stream()
				.anyMatch(
						denegacion -> denegacion.jugador().equals(inscripcionPropuesta
								.jugador())));
	}

	@Test
	public void unJugadorPropuestoEsAceptadoPeroNoPuedeInscribirseAlPartidoEntoncesNoApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador, solidario);
		partidoLleno.posiblesJugadores().add(inscripcionPropuesta);
		partidoLleno.administradorAcepto(inscripcionPropuesta);
		assertFalse(partidoLleno.getInscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador().equals(jugador)));
	}
}
