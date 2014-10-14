package opf5;

import static org.junit.Assert.assertEquals;
import opf5.AlgoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;
import opf5.jugador.RepositorioJugadores;

import org.junit.Test;

public class TestOPF5_HomePartidos extends TestOPF5{
	@Test
	public void jugadorJuegaUnPartidoSeLeConsultaALaHomePorCantidadPartidosJugados() {
		Jugador emiliano = new Jugador("Emiliano", 28, 2);
		RepositorioJugadores.getInstance().create(emiliano);	
		HomePartidos.getInstance().create(partidoCon5Estandares);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
		partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
		partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
		partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
		partidoCon5Estandares.intentarInscribirA(inscripcionEmi);
		partidoCon5Estandares.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		partidoCon5Estandares.aceptarEquipos(partidoCon5Estandares.getFormacionesTentativas().get(0));
		assertEquals(1,
				homePartidos.consultarCantidadDePartidosJugados(emiliano));

	}

}
