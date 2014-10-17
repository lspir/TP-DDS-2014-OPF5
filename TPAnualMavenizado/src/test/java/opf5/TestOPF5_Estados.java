package opf5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import opf5.algoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.estadoPartido.Confirmado;
import opf5.estadoPartido.Estado;
import opf5.estadoPartido.Ordenado;
import opf5.estadoPartido.SinOrdenar;
import opf5.excepciones.ElPartidoYaEstaConfirmadoException;
import opf5.excepciones.NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;
import opf5.partido.Partido;

import org.junit.Test;

public class TestOPF5_Estados extends TestOPF5{
	
	@Test
	public void partidoNuevoPoseeEstadoSinOrdenar() {
		Partido partido=new Partido(LocalDate.now(), LocalTime.now(),"Campus");
		assertTrue(partido.getEstado().getClass().equals(new SinOrdenar().getClass()));

	}
	
	@Test
	public void alArmarEquiposElPartidoSeOrdena(){
		partidoLleno.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		assertTrue(partidoLleno.getEstado().getClass().equals(new Ordenado().getClass()));
	}
	
	@Test
	public void partidoOrdenadoSeDesordenaAlBajarUnJugador(){
		partidoLleno.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		partidoLleno.seDioDeBajaSinReemplazante(partidoLleno.getInscripciones().get(0));
		assertTrue(partidoLleno.getEstado().getClass().equals(new SinOrdenar().getClass()));
	}
	
	@Test
	public void seConfirmaPartidoOrdenado(){
		this.confirmarPartido(partidoLleno);
		assertTrue(partidoLleno.getEstado().getClass().equals(new Confirmado().getClass()));
	}
	
	@Test
	public void seInscribeEnPartidoOrdenadoPasaAEstarSinOrdenar() {
		partidoCon8Estandares1Solidario1Condicional.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		Inscripcion inscripcionEmi = new Inscripcion( new Jugador("Emiliano", 28, 3), estandar);
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);
		assertTrue(partidoCon8Estandares1Solidario1Condicional.getEstado().getClass().equals(new SinOrdenar().getClass()));

	}
	
	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
	public void UnJugadorIntentaDarseDeBajaSinReemplzanteEnPartidoConfirmadoTiraError() {
		this.confirmarPartido(partidoLleno);
		partidoLleno.seDioDeBajaSinReemplazante(inscripcionEstandar);
	}

	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
	public void UnJugadorIntentaDarseDeBajaConReemplzanteEnPartidoConfirmadoTiraError() {
		this.confirmarPartido(partidoLleno);
		partidoLleno.seDioDeBajaConReemplazante(inscripcionEstandar, jugadorConAmigos, solidario);

	}


}
