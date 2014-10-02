package opf5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;

import org.junit.Test;

public class TestOPF_Inscripciones extends TestOPF {
	
	
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

}
