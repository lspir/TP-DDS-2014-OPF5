package opf5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import opf5.AlgoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.estadoPartido.Ordenado;
import opf5.excepciones.ElPartidoYaEstaConfirmadoException;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;

import org.junit.Test;

public class TestOPF5_Inscripciones extends TestOPF5 {
	
	
	@Test
	public void hay8Estandares1SolidarioY1CondicionalSiSeQuiereAnotarUnEstandarLaInscripcionSeRealiza() {
		Jugador emiliano = new Jugador("Emiliano", 28, 7);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);
		assertTrue(partidoCon8Estandares1Solidario1Condicional.getInscripciones().contains(inscripcionEmi));
	}
	
	@Test
	public void Hay5EstandarSeQuiereAnotarUnSolidarioYLaInscripcionSeRealiza() {
		partidoCon5Estandares.intentarInscribirA(inscripcionSolidario);
		assertTrue(partidoCon5Estandares.getInscripciones().contains(inscripcionSolidario));
	}
	
	
	@Test
	public void Hay10EstandarSeQuiereAnotarUnEstandarMasYLaInscripcionSeRechaza() {
		partidoLleno.intentarInscribirA(inscripcionEstandar);
		assertFalse(partidoLleno.getInscripciones().contains(inscripcionEstandar));
	}
	


	@Test
	public void Hay10SolidariosSeQuiereAnotar1EstandarYLaInscripcionEsCorrecta() {
		partidoCon10Solidarios.intentarInscribirA(inscripcionEstandar);
		assertTrue(partidoCon10Solidarios.getInscripciones().contains(inscripcionEstandar));
	}
	
	@Test
	public void Hay10EstandarSeQuiereAnotarUnSolidarioYLaInscripcionSeRechaza() {
		partidoLleno.intentarInscribirA(inscripcionSolidario);
		assertFalse(partidoLleno.getInscripciones().contains(inscripcionSolidario));
	}

	@Test
	public void Hay8Estandares1CondicionalY1SolidarioCuandoSeAnotaUnEstandarSaleElCondicional() {

		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEstandar);
		assertFalse(partidoCon8Estandares1Solidario1Condicional.getInscripciones().contains(inscripcionCondicional));
	}

	
	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
	public void intentaInscribirseEnPartidoConfirmado() {
		Inscripcion inscripcionEmi = new Inscripcion(new Jugador("Emiliano", 28, 2), estandar);
		this.confirmarPartido(partidoCon8Estandares1Solidario1Condicional);
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);

	}
	
	@Test
	public void ElPartidoTieneUnSoloJugadorYEsteSeDaDeBajaSinReemplazanteElPartidoQuedaCon0Inscriptos() {
		partidoCon1Estandar.seDioDeBajaSinReemplazante(inscripcionEstandar);
		assertEquals(0, partidoCon1Estandar.getInscripciones().size());
	}
	

}
