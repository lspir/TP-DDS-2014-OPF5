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
import opf5.AlgoritmosDivisionDeEquipos.*;
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
//FIXME no medieron bola. EL comentario de arriba es del 4 de Julio!!!
public class TestOPF {
	private Partido partidoCon5Estandares;
	private Partido partidoCon10Solidarios;
	private	Partido partidoLleno;
	private Partido partidoCon8Estandares1Solidario1Condicional;
	private Partido partidoCon1Estandar;
	private	Estandar estandar;
	private Solidario solidario;
	private Condicional condicional;
	private ObservadorJugadorInscripto observadorJugador;
	private ObservadorNotificarAdmin observadorAdmin;
	private AdaptadorMailSender adaptadorMailSenderJugador;
	private AdaptadorMailSender adaptadorMailSenderAdmin;
	private Inscripcion inscripcionCondicional, inscripcionSolidario,inscripcionEstandar;
	private Jugador jugador, jugadorConAmigos,jugadorCritico;
	private Amigo luciano, leandro;
	private HomePartidos homePartidos;
	
	@Before
	public void setUp() {
		homePartidos=HomePartidos.getInstance();
		partidoCon5Estandares= new Partido(LocalDate.now(),LocalTime.now(), "Campus");
		partidoCon10Solidarios= new Partido(LocalDate.now().plusDays(10), LocalTime.now(), "Campus");
		partidoLleno = new Partido(LocalDate.of(2014, Month.AUGUST, 13),LocalTime.of(12,30), "Campus");
		partidoCon8Estandares1Solidario1Condicional= new Partido(LocalDate.of(2014, Month.APRIL, 29), LocalTime.of(21,30), "Campus");
		partidoCon1Estandar = new Partido(LocalDate.of(2014, Month.AUGUST, 29), LocalTime.of(20,00), "Mario Bravo");
		estandar = new Estandar();
		solidario = new Solidario();
		condicional= new Condicional();
		observadorJugador = new ObservadorJugadorInscripto();
		adaptadorMailSenderAdmin= mock(AdaptadorMailSender.class);
		adaptadorMailSenderJugador = mock(AdaptadorMailSender.class);
		observadorJugador.adaptador(adaptadorMailSenderJugador);
		observadorAdmin = new ObservadorNotificarAdmin("admin@admin.com.ar");
		observadorAdmin.adaptador(adaptadorMailSenderAdmin);
		partidoCon1Estandar.agregarObservador(observadorAdmin);
		partidoCon1Estandar.agregarObservador(observadorJugador);
		partidoCon10Solidarios.agregarObservador(observadorAdmin);
		partidoCon10Solidarios.agregarObservador(observadorJugador);
		partidoCon5Estandares.agregarObservador(observadorAdmin);
		partidoCon5Estandares.agregarObservador(observadorJugador);
		partidoCon8Estandares1Solidario1Condicional.agregarObservador(observadorAdmin);
		partidoCon8Estandares1Solidario1Condicional.agregarObservador(observadorJugador);
		partidoLleno.agregarObservador(observadorAdmin);
		partidoLleno.agregarObservador(observadorJugador);
		jugador = new Jugador("nombre", 20);
		jugadorCritico =new Jugador("critico",12);
		inscripcionEstandar = new Inscripcion(jugador, estandar);
		inscripcionSolidario = new Inscripcion(jugador, solidario);
		inscripcionCondicional = new Inscripcion(jugador, condicional);
		jugadorConAmigos = new Jugador("Cristiano Ronaldo", 28);
		luciano = new Amigo("lucho@gmail.com");
		leandro = new Amigo("lean@gmail.com");
		jugadorConAmigos.agregarAmigo(luciano);
		jugadorConAmigos.agregarAmigo(leandro);		
		this.cargarPartido(partidoCon5Estandares, estandar, 5);
		this.cargarPartido(partidoLleno, estandar, 10);
		this.cargarPartido(partidoCon10Solidarios, solidario, 10);
		this.cargarPartido(partidoCon8Estandares1Solidario1Condicional, estandar, 8);		
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionSolidario);
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionCondicional);
		partidoCon1Estandar.intentarInscribirA(inscripcionEstandar);
	}

	@Test
	public void hay8Estandares1SolidarioY1CondicionalSiSeQuiereAnotarUnEstandarLaInscripcionSeRealiza() {
		Jugador emiliano = new Jugador("Emiliano", 28);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);
		assertTrue(partidoCon8Estandares1Solidario1Condicional.getInscripciones().contains(inscripcionEmi));
	}

//	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
//	public void intentaInscribirseEnPartidoConfirmado() {
//		Jugador emiliano = new Jugador("Emiliano", 28);
//		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
//		partidoCon8Estandares1Solidario1Condicional.setEstado(new Confirmado());
//		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);
//
//	}

	@Test
	public void seInscribirseEnPartidoOrdenado() {
		Jugador emiliano = new Jugador("Emiliano", 28);
		Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
		partidoCon8Estandares1Solidario1Condicional.setEstado(new Ordenado());
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);

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

	@Test
	public void UnJugadorCon2AmigosSeInscribeYSeEnvia1MailACadaAmigo() {
		partidoCon1Estandar.intentarInscribirA(new Inscripcion(jugadorConAmigos, estandar));
		verify(adaptadorMailSenderJugador, times(2)).notificar(any(String.class));
	}

	@Test
	public void TresPartidosSeLlenaronYSeEnvianTresMailsAlAdministrador() {
		verify(adaptadorMailSenderAdmin, times(3)).notificar("admin@admin.com.ar");

	}

	@Test
	public void ElPartidoTieneUnSoloJugadorYEsteSeDaDeBajaSinReemplazanteElPartidoQuedaCon0Inscriptos() {
		partidoCon1Estandar.seDioDeBajaSinReemplazante(inscripcionEstandar);
		assertEquals(0, partidoCon1Estandar.getInscripciones().size());
	}

	@Test
	public void UnJugadorSeDaDeBajaSinReemplazanteEntoncesSeLoPenaliza() {
		partidoCon1Estandar.seDioDeBajaSinReemplazante(inscripcionEstandar);
		assertEquals(1, inscripcionEstandar.jugador().infracciones().size());
	}
//FIXME no comenten código
//	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
//	public void UnJugadorIntentaDarseDeBajaSinReemplzanteEnPartidoConfirmadoTiraError() {
//		// FIXME que piensan de que esté seteando el estado desde afuera?
//		// Eso respeta la idea del patrón state?
//		partidoCon1Estandar.setEstado(new Confirmado());
//		partidoCon1Estandar.seDioDeBajaSinReemplazante(inscripcionEstandar);
//
//	}
//
//	@Test(expected = ElPartidoYaEstaConfirmadoException.class)
//	public void UnJugadorIntentaDarseDeBajaConReemplzanteEnPartidoConfirmadoTiraError() {
//		partidoCon1Estandar.setEstado(new Confirmado());
//		partidoCon1Estandar.seDioDeBajaConReemplazante(inscripcionEstandar, jugadorConAmigos, solidario);
//
//	}

	@Test
	public void UnJugadorSeBajaConReemplazanteEntoncesALosAmigosDelReemplazanteLesLlegaUnMail() {
		partidoCon1Estandar.seDioDeBajaConReemplazante(inscripcionEstandar, jugadorConAmigos, estandar);
		verify(adaptadorMailSenderJugador, times(jugadorConAmigos.amigos().size())).notificar(
				any(String.class));
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

	@Test
	public void aplicarAlgoritmoDivisionDeParesAListaDel1Al10() {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		List<Inscripcion> inscripcionesValidada = new ArrayList<Inscripcion>();
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				inscripciones.add(inscripcionSolidario);
				inscripcionesValidada.add(inscripcionSolidario);

			} else {
				inscripciones.add(inscripcionEstandar);

			}

		}
		for (int j=0; j < 5; j++) {
			inscripcionesValidada.add(inscripcionEstandar);
			}
		AlgoritmoDivisionDeEquipos divisionDePares = new DivisionPorPares();
		assertEquals(inscripcionesValidada,
				divisionDePares.dameLista(inscripciones));

	}


@Test(expected = ElPartidoYaEstaConfirmadoException.class)
public void testAuxiliar() {
	List<Integer> pares = new ArrayList<Integer>();
	List<Integer> impares = new ArrayList<Integer>();
	for (int i = 0; i < 10; i++) {
		if (i % 2 == 0) {
			pares.add(i);
			
		} else {
			impares.add(i);
		}

	}
	AlgoritmoDivisionDeEquipos divisionDePares = new DivisionPorPares();
	Jugador emiliano = new Jugador("Emiliano", 28);
	CriterioHandicap criterioHandicap = new CriterioHandicap();
	Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
	partidoCon8Estandares1Solidario1Condicional.armarEquipos(criterioHandicap, divisionDePares);
	partidoCon8Estandares1Solidario1Condicional.aceptarEquipos(partidoCon8Estandares1Solidario1Condicional.getFormacionesTentativas().get(0));
	partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionEmi);
}

@Test
public void jugadorJuegaUnPartidoSeLeConsultaALaHomePorCantidadPartidosJugados() {
	AlgoritmoDivisionDeEquipos divisionDePares = new DivisionPorPares();
	Jugador emiliano = new Jugador("Emiliano", 28);
	CriterioHandicap criterioHandicap = new CriterioHandicap();
	Inscripcion inscripcionEmi = new Inscripcion(emiliano, estandar);
	partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
	partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
	partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
	partidoCon5Estandares.intentarInscribirA(inscripcionEstandar);
	partidoCon5Estandares.intentarInscribirA(inscripcionEmi);
	partidoCon5Estandares.armarEquipos(criterioHandicap, divisionDePares);
	partidoCon5Estandares.aceptarEquipos(partidoCon5Estandares.getFormacionesTentativas().get(0));
	
	assertEquals(0,
			homePartidos.consultarCantidadDePartidosJugados(emiliano));

}


private void cargarPartido(Partido partido,TipoDeInscripcion tipoDeinscripcion,int cantidadJugadores){
	for (int i = 0; i <cantidadJugadores; i++) {
		Inscripcion inscripcion = new Inscripcion(new Jugador("nombre", 20), tipoDeinscripcion);
		partido.intentarInscribirA(inscripcion);
	}
}


}



