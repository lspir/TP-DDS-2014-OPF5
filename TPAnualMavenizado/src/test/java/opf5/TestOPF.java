package opf5;

import static org.junit.Assert.*;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import opf5.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestOPF {

	// les parece cohesivo esta clase de tests? Miren el nombre: básicamente esta diciendo 
	//"soy un tests que prueba el sistema completo"
	//Por otro lado, capaz seria interesane complemetnar este tests de integracion
	//con tests mas unitarios
	
	Partido partido = new Partido("2/5", "14:00", "Campus");
	Partido partido2 = new Partido("4/5", "21:00", "Campus");
	Partido partido3 = new Partido("4/5", "21:00", "Campus");
	Partido partido4 = new Partido("4/5", "21:00", "Campus");
	Partido partido5 = new Partido("4/5", "21:00", "Campus");
	Partido partido6 = new Partido("4/5", "21:00", "Campus");

	Estandar estandar = new Estandar();
	Solidario solidario = new Solidario();
	Jugador jugador1, jugador2, jugador3, jugador4, cristianoRonaldo, messi;
	Inscripcion inscripcion1, inscripcion2, inscripcion3, inscripcion4;
	Condicional condicional = new Condicional();
	Inscripcion inscripcionCondicional, inscripcionSolidario;
	Amigo luciano = new Amigo("lucho@gmail.com");
	Amigo leandro = new Amigo("lean@gmail.com");

	AdaptadorMailSender adaptadorMailSender = mock(AdaptadorMailSender.class);
	ObservadorJugadorInscripto observadorJugador;
	ObservadorNotificarAdmin observadorAdmin;
	

	@Before
	public void setUp() {

		//mailSender = mock(MailSender.class);
		observadorJugador = new ObservadorJugadorInscripto();
		observadorAdmin = new ObservadorNotificarAdmin("admin@admin.com.ar");
		jugador2 = new Jugador("nombre", 20);
		jugador3 = new Jugador("nombre", 23);
		inscripcion2 = new Inscripcion(jugador2, solidario);
		inscripcion3 = new Inscripcion(jugador3, estandar);
		cristianoRonaldo = new Jugador("Cristiano Ronaldo", 28);
		cristianoRonaldo.agregarAmigo(luciano);
		cristianoRonaldo.agregarAmigo(leandro);
		inscripcion4 = new Inscripcion(cristianoRonaldo, estandar);
		messi = new Jugador("Messi", 29);
		messi.agregarAmigo(luciano);

		for (int i = 0; i < 5; i++) {
			jugador1 = new Jugador("Ronaldo", 28);
			Inscripcion inscripcion1 = new Inscripcion(jugador1, estandar);
			partido.intentarInscribirA(inscripcion1);
		}

		for (int i = 0; i < 10; i++) {
			jugador1 = new Jugador("Ronaldo", 28);
			Inscripcion inscripcion1 = new Inscripcion(jugador1, estandar);
			partido2.intentarInscribirA(inscripcion1);
		}

		for (int i = 0; i < 10; i++) {
			jugador2 = new Jugador("nombre", 20);
			inscripcion2 = new Inscripcion(jugador2, solidario);
			partido3.intentarInscribirA(inscripcion2);
		}

		for (int i = 0; i < 10; i++) {
			jugador1 = new Jugador("Ronaldo", 28);
			Inscripcion inscripcion1 = new Inscripcion(jugador1, estandar);
			partido4.intentarInscribirA(inscripcion1);
		}

		for (int i = 0; i < 8; i++) {
			jugador1 = new Jugador("Ronaldo", 28);
			Inscripcion inscripcion1 = new Inscripcion(jugador1, estandar);
			partido5.intentarInscribirA(inscripcion1);
		}

		inscripcionSolidario = new Inscripcion(jugador2, solidario);

		partido5.intentarInscribirA(inscripcionSolidario);

		inscripcionCondicional = new Inscripcion(jugador2, condicional);

		partido5.intentarInscribirA(inscripcionCondicional);

		partido6.intentarInscribirA(inscripcion3);

	}

	@Test
	public void hay8Estandares1SolidarioY1CondicionalSiSeQuiereAnotarUnEstandarLaInscripcionSeRealiza() {
		Jugador emiliano = new Jugador("Emiliano", 28);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partido5.intentarInscribirA(inscripcionEmi);
		assertTrue(partido5.inscripciones().contains(inscripcionEmi));
	}

	@Test
	public void Hay5EstandarSeQuiereAnotarUnSolidarioYLaInscripcionSeRealiza() {
		partido.intentarInscribirA(inscripcion2);
		assertTrue(partido.inscripciones().contains(inscripcion2));
	}

	@Test
	public void Hay10EstandarSeQuiereAnotarUnEstandarMasYLaInscripcionSeRechaza() {
		partido2.intentarInscribirA(inscripcion3);
		assertFalse(partido2.inscripciones().contains(inscripcion3));
	}

	@Test
	public void Hay10SolidariosSeQuiereAnotar1EstandarYLaInscripcionEsCorrecta() {
		partido3.intentarInscribirA(inscripcion3);
		assertTrue(partido3.inscripciones().contains(inscripcion3));
	}

	@Test
	public void Hay10EstandarSeQuiereAnotarUnSolidarioYLaInscripcionSeRechaza() {
		partido4.intentarInscribirA(inscripcion2);
		assertFalse(partido4.inscripciones().contains(inscripcion2));
	}

	@Test
	public void Hay8Estandares1CondicionalY1SolidarioCuandoSeAnotaUnEstandarSaleElCondicional() {

		partido5.intentarInscribirA(inscripcion3);
		assertFalse(partido5.inscripciones().contains(inscripcionCondicional));
	}

	@Test
	public void UnJugadorCon2AmigosSeInscribeYSeEnvia1MailACadaAmigo() {
		observadorJugador.adaptador(adaptadorMailSender);
		partido6.agregarObservador(observadorJugador);
		partido6.agregarObservador(observadorAdmin);
		partido6.intentarInscribirA(inscripcion4);
		verify(adaptadorMailSender, times(2)).notificar(any(String.class));
	}


	/*@Test
	public void UnPartidoSeLlenaYSeEnviaUnMailAlAdministrador() {
		partido3.agregarObservador(observadorAdmin);
		partido3.agregarObservador(observadorJugador);
		verify(mailSender,times(1)).notificar("admin@admin.com.ar");
	}*/
	
	@Test
	public void ElPartidoTieneUnSoloJugadorYEsteSeDaDeBajaSinReemplazanteElPartidoQuedaCon0Inscriptos() {
		partido6.seDioDeBajaSinReemplazante(inscripcion3);
		assertEquals(0, partido6.inscripciones().size());
	}

	@Test
	public void UnJugadorSeDaDeBajaSinReemplazanteEntoncesSeLoPenaliza() {
		partido6.seDioDeBajaSinReemplazante(inscripcion3);
		assertEquals(1, inscripcion3.jugador().infracciones().size());
	}

	/*@Test
	public void UnJugadorSeBajaConReemplazanteEntoncesALosAmigosDelReemplazanteLesLlegaUnMail() {
		partido6.seDioDeBajaConReemplazante(inscripcion3, messi);
		assertEquals(
				1,
				mailSender.enviados().stream()
						.filter(mail -> mail.remitente() == messi)
						.collect(Collectors.toList()).size());
	}*/
	
	
	@Test(expected=NoSePuedeCalificarException.class)
	public void unJugadorQueNoParticipoIntentaCalificarParaEsePartidoEntoncesEsaCalificacionNoEsTenidaEnCuenta()throws NoSePuedeCalificarException  {
		jugador2.critica(jugador3, 8, "Se atajó todo", partido6);
	}

	@Test
	public void unJugadorQueParticipoIntentaCalificarParaEsePartidoEntoncesEsaCalificacionSeGuardaEnLaListaDelJugadorCalificado() throws NoSePuedeCalificarException {
		partido6.intentarInscribirA(inscripcion2);
		jugador2.critica(jugador3, 8, "Se atajó todo", partido6);
		assertEquals(1, jugador3.criticas().size());
	}

	@Test
	public void unJugadorPropuestoEsAceptadoYPuedeInscribirseEnElPartidoEntoncesApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador2, estandar);
		partido6.posiblesJugadores().add(inscripcionPropuesta);
		partido6.administradorAcepto(inscripcionPropuesta);
		assertTrue(partido6.inscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador() == jugador2));
	}

	@Test
	public void unJugadorPropuestoEsRechazadoNoApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador2, estandar);
		partido6.posiblesJugadores().add(inscripcionPropuesta);
		partido6.seRechazoInscripcion(inscripcionPropuesta, "Me cae mal");
		assertFalse(partido6.inscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador() == jugador2));
	}

	@Test
	public void unJugadorPropuestoEsRechazadoEntoncesSeAgregaUnaDenegacion() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador2, estandar);
		partido6.posiblesJugadores().add(inscripcionPropuesta);
		partido6.seRechazoInscripcion(inscripcionPropuesta, "Me cae mal");
		assertTrue(partido6.denegaciones().stream().anyMatch(denegacion->denegacion.jugador()==inscripcionPropuesta.jugador()));
	}

	@Test
	public void unJugadorPropuestoEsAceptadoPeroNoPuedeInscribirseAlPartidoEntoncesNoApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador2, solidario);
		partido2.posiblesJugadores().add(inscripcionPropuesta);
		partido2.administradorAcepto(inscripcionPropuesta);
		assertFalse(partido2.inscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador() == jugador2));

	}

}
