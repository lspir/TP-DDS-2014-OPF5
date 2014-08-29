package opf5;

import static org.junit.Assert.*;
import opf5.jugador.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import opf5.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.estadoPartido.*;
import opf5.inscripcion.*;
import opf5.excepciones.*;
import opf5.observers.*;
import opf5.mailSender.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

//FIXME es un buen momento para separar los tests en varias clases 
public class TestOPF {

	Partido partido = new Partido(LocalDate.now(),LocalTime.now(), "Campus");
	Partido partido2 = new Partido(LocalDate.of(2014, Month.JANUARY, 1), LocalTime.now(), "Campus");
	Partido partido3 = new Partido(LocalDate.now().plusDays(10), LocalTime.now(), "Campus");
	Partido partido4 = new Partido(LocalDate.of(2014, Month.AUGUST, 13),LocalTime.of(12,30), "Campus");
	Partido partido5 = new Partido(LocalDate.of(2014, Month.APRIL, 29), LocalTime.of(21,30), "Campus");
	Partido partido6 = new Partido(LocalDate.of(2014, Month.AUGUST, 29), LocalTime.of(20,00), "Mario Bravo");

	Estandar estandar = new Estandar();
	Solidario solidario = new Solidario();
	Jugador jugador1, jugador2, jugador3, jugador4, cristianoRonaldo, messi;
	Inscripcion inscripcion1, inscripcion2, inscripcion3, inscripcion4;
	Condicional condicional = new Condicional();
	Inscripcion inscripcionCondicional, inscripcionSolidario;
	Amigo luciano = new Amigo("lucho@gmail.com");
	Amigo leandro = new Amigo("lean@gmail.com");

	AdaptadorMailSender adaptadorMailSender = mock(AdaptadorMailSender.class);
	AdaptadorMailSender adaptadorMailSender2 = mock(AdaptadorMailSender.class);
	AdaptadorMailSender adaptadorMailSender3 = mock(AdaptadorMailSender.class);
	ObservadorJugadorInscripto observadorJugador1, observadorJugador2;
	ObservadorNotificarAdmin observadorAdmin;

	@Before
	public void setUp() {

		observadorJugador1 = new ObservadorJugadorInscripto();
		observadorJugador2 = new ObservadorJugadorInscripto();
		observadorJugador2.adaptador(adaptadorMailSender3);
		partido6.agregarObservador(observadorJugador2);
		observadorAdmin = new ObservadorNotificarAdmin("admin@admin.com.ar");
		observadorAdmin.adaptador(adaptadorMailSender2);
		jugador2 = new Jugador("nombre", 20);
		jugador3 = new Jugador("nombre", 23);
		jugador4 = new Jugador("hola", 4);
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
		partido3.agregarObservador(observadorAdmin);

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

	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
	public void intentaInscribirseEnPartidoConfirmado() {
		Jugador emiliano = new Jugador("Emiliano", 28);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partido5.tuEstadoEs(new Confirmado());
		partido5.intentarInscribirA(inscripcionEmi);

	}

	@Test
	public void seInscribirseEnPartidoOrdenado() {
		Jugador emiliano = new Jugador("Emiliano", 28);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partido5.tuEstadoEs(new Ordenado());
		partido5.intentarInscribirA(inscripcionEmi);

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
		observadorJugador1.adaptador(adaptadorMailSender);
		partido6.agregarObservador(observadorJugador1);
		partido6.agregarObservador(observadorAdmin);
		partido6.intentarInscribirA(inscripcion4);
		verify(adaptadorMailSender, times(2)).notificar(any(String.class));
	}

	@Test
	public void UnPartidoSeLlenaYSeEnviaUnMailAlAdministrador() {
		verify(adaptadorMailSender2, times(1)).notificar("admin@admin.com.ar");

	}

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

	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
	public void UnJugadorIntentaDarseDeBajaSinReemplzanteEnPartidoConfirmadoTiraError() {
		// FIXME que piensan de que esté seteando el estado desde afuera?
		// Eso respeta la idea del patrón state?
		partido6.tuEstadoEs(new Confirmado());
		partido6.seDioDeBajaSinReemplazante(inscripcion3);

	}

	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
	public void UnJugadorIntentaDarseDeBajaConReemplzanteEnPartidoConfirmadoTiraError() {
		partido6.tuEstadoEs(new Confirmado());
		partido6.seDioDeBajaConReemplazante(inscripcion3, jugador1, solidario);

	}

	@Test
	public void UnJugadorSeBajaConReemplazanteEntoncesALosAmigosDelReemplazanteLesLlegaUnMail() {
		partido6.seDioDeBajaConReemplazante(inscripcion3, messi, estandar);
		verify(adaptadorMailSender3, times(messi.amigos().size())).notificar(
				any(String.class));
	}

	@Test(expected = NoSePuedeCalificarException.class)
	public void unJugadorQueNoParticipoIntentaCalificarParaEsePartidoEntoncesEsaCalificacionNoEsTenidaEnCuenta() {
		jugador2.criticar(jugador3, 8, "Se atajó todo", partido6);
	}

	@Test
	public void unJugadorQueParticipoIntentaCalificarParaEsePartidoEntoncesEsaCalificacionSeGuardaEnLaListaDelJugadorCalificado() {
		partido6.intentarInscribirA(inscripcion2);
		jugador2.criticar(jugador3, 8, "Se atajó todo", partido6);
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
		assertTrue(partido6
				.denegaciones()
				.stream()
				.anyMatch(
						denegacion -> denegacion.jugador() == inscripcionPropuesta
								.jugador()));
	}

	@Test
	public void unJugadorPropuestoEsAceptadoPeroNoPuedeInscribirseAlPartidoEntoncesNoApareceEnLaListaDeJugadores() {
		Inscripcion inscripcionPropuesta = new Inscripcion(jugador2, solidario);
		partido2.posiblesJugadores().add(inscripcionPropuesta);
		partido2.administradorAcepto(inscripcionPropuesta);
		assertFalse(partido2.inscripciones().stream()
				.anyMatch(inscripcion -> inscripcion.jugador() == jugador2));

	}

	@Test
	public void aplicarCriterioHandicapAUnJugadorConValor8Es8() {
		CriterioHandicap criterioHandicap = new CriterioHandicap();
		messi.handicap(8);
		assertEquals(8, criterioHandicap.ponderate(messi), 1);
	}

	@Test
	public void aplicarCriterioPromedioDeUltimoPartidoAJugadorConPromedioDe8Es8() {

		PromedioDeUltimoPartido criterioPromedioDeUltimoPartido = new PromedioDeUltimoPartido();
		Critica criticaDe8 = new Critica(8, "recomendable", partido6);
		Critica criticaDe2 = new Critica(2, "malo", partido5);
		jugador1.agregarCritica(criticaDe2);
		for (int i = 0; i < 3; i++) {
			jugador1.agregarCritica(criticaDe8);
		}
		assertEquals(8, criterioPromedioDeUltimoPartido.ponderate(jugador1), 0);

	}

	@Test
	public void aplicarCriterioUltimas3CalificacionesDe7AJugadorDevuelve7() {

		Critica criticaDe7 = new Critica(7, "bueno", partido6);
		UltimasNCalificaciones criterioUltimasN = new UltimasNCalificaciones(3);
		for (int i = 0; i < 5; i++) {
			jugador4.agregarCritica(criticaDe7);
		}
		assertEquals(7, criterioUltimasN.ponderate(jugador4), 1);

	}

	@Test
	public void aplicarCriterioMixDeCriterioAJugador() {
		Critica criticaDe7 = new Critica(7, "bueno", partido6);
		Critica criticaDe5 = new Critica(5, "regular", partido5);
		UltimasNCalificaciones criterioUltimasN = new UltimasNCalificaciones(2);
		PromedioDeUltimoPartido criterioPromedioDeUltimoPartido = new PromedioDeUltimoPartido();
		MixDeCriterios criterioMix = new MixDeCriterios();
		for (int i = 0; i < 6; i++) {
			jugador4.agregarCritica(criticaDe7);
			jugador4.agregarCritica(criticaDe5);
		}
		criterioMix.agregarCriterio(criterioPromedioDeUltimoPartido);
		criterioMix.agregarCriterio(criterioUltimasN);
		assertEquals(5.5, criterioMix.ponderate(jugador4), 0);
	}

	@Test
	public void aplicarAlgoritmoDivisionDeParesAListaDel1Al10() {
		// FIXME esto (el criterio de división por pares) es parte de los
		// requerimientos,
		// por lo que esta lógica no debería ser parte de un tests sino estar
		// implementada
		// en el dominio
		List<Integer> pares = new ArrayList<Integer>();
		List<Integer> impares = new ArrayList<Integer>();
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		List<Inscripcion> inscripcionesValidada = new ArrayList<Inscripcion>();
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				pares.add(i);
				inscripciones.add(inscripcion2);
				inscripcionesValidada.add(inscripcion2);

			} else {
				impares.add(i);
				inscripciones.add(inscripcion3);

			}

		}
		for (int j = 1; j < 11; j++) {
			if (j % 2 == 1) {
				inscripcionesValidada.add(inscripcion3);
			}
		}
		AlgoritmoDivisionDeEquipos divisionDePares = new AlgoritmoDivisionDeEquipos(pares,
				impares);
		assertEquals(inscripcionesValidada,
				divisionDePares.dameLista(inscripciones));

	}

}
