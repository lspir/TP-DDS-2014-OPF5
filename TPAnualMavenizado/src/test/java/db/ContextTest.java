package db;

import static org.junit.Assert.*;
import static db.EntityManagerHelper.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaQuery;

import opf5.TestOPF5;
import opf5.algoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.estadoPartido.Confirmado;
import opf5.estadoPartido.Ordenado;
import opf5.inscripcion.Condicional;
import opf5.inscripcion.Estandar;
import opf5.inscripcion.Inscripcion;
import opf5.inscripcion.Solidario;
import opf5.inscripcion.TipoDeInscripcion;
import opf5.jugador.Amigo;
import opf5.jugador.Critica;
import opf5.jugador.Infraccion;
import opf5.jugador.Jugador;
import opf5.jugador.HomeJugadores;
import opf5.partido.HomePartidos;
import opf5.partido.Partido;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uqbar.commons.exceptions.NestedException.SystemOutObserver;

public class ContextTest {

	@Test
	public void contextUp() {
		EntityManagerHelper.getEntityManager();
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		EntityManagerHelper.withTransaction(() -> {
		});
	}

	@Before
	public void begin() throws Exception {
		beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		commit();
	}

	@Test
	public void persistirJugador() {
		Jugador jugador = this.crearJugadorPersistido("Pepe", 15, 8);
		Jugador persistido = entityManager().find(jugador.getClass(),
				jugador.getId());
		assertEquals(jugador, persistido);
	}

	@Test
	public void persistirAmigo() {
		Jugador jugador = this.crearJugadorPersistido("Jose", 13, 1);
		Amigo amigo = new Amigo("amigo@gmail.com");
		jugador.agregarAmigo(amigo);
		persist(amigo);
		Amigo amigoPersistido = entityManager().find(amigo.getClass(),
				amigo.getId());
		assertEquals(amigo, amigoPersistido);
	}

	@Test
	public void persistirInfraccion() {
		Jugador jugador = this.crearJugadorPersistido("Messias", 27, 100);
		Infraccion infraccion = new Infraccion("Falto", 0);
		persist(infraccion);
		jugador.tePenalizaron(infraccion);
	}

	@Test
	public void persistirInscripcionEstandar() {
		Jugador jugador = this.crearJugadorPersistido("Jugador", 15, 5);
		Inscripcion inscripcion = this.crearInscripcionPersistida(jugador,
				new Estandar());
		Inscripcion inscripcionPersistida = entityManager().find(
				inscripcion.getClass(), inscripcion.getId());
		assertEquals(inscripcion, inscripcionPersistida);
	}

	@Test
	public void persistirInscripcionSolidaria() {
		Jugador jugador = this.crearJugadorPersistido("Fm9", 20, 0);
		Inscripcion inscripcion = this.crearInscripcionPersistida(jugador,
				new Solidario());
		Inscripcion inscripcionPersistida = entityManager().find(
				inscripcion.getClass(), inscripcion.getId());
		assertEquals(inscripcion, inscripcionPersistida);
	}

	@Test
	public void persistirInscripcionCondicional() {
		Jugador jugador = this.crearJugadorPersistido("Fm10", 20, 0);
		Inscripcion inscripcion = this.crearInscripcionPersistida(jugador,
				new Condicional());
		Inscripcion inscripcionPersistida = entityManager().find(
				inscripcion.getClass(), inscripcion.getId());
		assertEquals(inscripcion, inscripcionPersistida);
	}

	@Test
	public void persistirCritica() {
		Jugador jugador = this.crearJugadorPersistido("Mishi", 22, 2);
		Critica critica = this.crearCriticaPersistida(jugador, 2);
		Critica criticaPersistida = entityManager().find(critica.getClass(),
				critica.getId());
		assertEquals(critica, criticaPersistida);
		Jugador jugadorPersistido = entityManager().find(jugador.getClass(),
				jugador.getId());
		assertEquals(jugador.getPromedio(), jugadorPersistido.getPromedio(),
				0.0);
		assertEquals(jugador.getPromedioUltimoPartido(),
				jugadorPersistido.getPromedioUltimoPartido(), 0.0);
	}

	@Test
	public void calculoPromedioDeJugadorPersistido() {
		Jugador jugador = this.crearJugadorPersistido("Mishi", 22, 2);
		this.crearCriticaPersistida(jugador, 2);
		this.crearCriticaPersistida(jugador, 10);
		Jugador jugadorPersistido = entityManager().find(jugador.getClass(),
				jugador.getId());
		assertEquals(jugador.getPromedio(), jugadorPersistido.getPromedio(),
				0.0);
		assertEquals(6.0, jugadorPersistido.getPromedio(), 0.0);
		assertEquals(10.0, jugadorPersistido.getPromedioUltimoPartido(), 0.0);
	}

	@Test
	public void persistirPartido() {
		Partido partido = new Partido(LocalDate.now(), LocalTime.now(),
				"Mario Bravo");
		persist(partido);
	}

	@Test
	public void persistirPartidoConInscripciones() {
		Partido partido = new Partido(LocalDate.now(), LocalTime.now(),
				"Medrano");
		persist(partido);
		Jugador jugador = new Jugador("Mishimishi", 20, 12);
		persist(jugador);
		Inscripcion inscripcionEstandar = this.crearInscripcionPersistida(
				jugador, new Estandar());
		Inscripcion inscripcionSolidaria = this.crearInscripcionPersistida(
				jugador, new Solidario());
		partido.intentarInscribirA(inscripcionSolidaria);
		partido.intentarInscribirA(inscripcionEstandar);
	}

	@Test
	public void persistirPartidoConPosiblesJugadores() {
		Partido partido = new Partido(LocalDate.now(), LocalTime.now(),
				"Olivos");
		persist(partido);
		Jugador jugador = this.crearJugadorPersistido("Lean", 20, 3);
		partido.tePropusieron(jugador, new Estandar());
	}

	@Test
	public void persistirPartidoConDenegacion() {
		Partido partido = new Partido(LocalDate.now(), LocalTime.now(),
				"Olivos");
		persist(partido);
		Jugador jugador = this.crearJugadorPersistido("Andres", 20, 4);
		partido.tePropusieron(jugador, new Estandar());
		partido.seRechazoInscripcion(partido.posiblesJugadores().get(0),
				"No se juega");
	}

	@Test
	public void persistirPartidoConPosibleFormacionEstadoFinalOrdenado() {
		Partido partidoLleno=this.partidoLlenoPersistido();
		partidoLleno.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		Partido partidoLlenoPersistido=entityManager().find(partidoLleno.getClass(), partidoLleno.getId());
		assertEquals(Ordenado.class,partidoLlenoPersistido.getEstado().getClass());
		}
	
	@Test
	public void persistirPartidoConfirmadoEstadoFinalConfirmado() {
		Partido partidoLleno=this.partidoLlenoPersistido();
		partidoLleno.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		partidoLleno.aceptarEquipos(partidoLleno.getFormacionesTentativas().get(0));
		Partido partidoLlenoPersistido=entityManager().find(partidoLleno.getClass(), partidoLleno.getId());
		assertEquals(Confirmado.class,partidoLlenoPersistido.getEstado().getClass());
		assertEquals(partidoLleno.getFormacionConfirmada(), partidoLlenoPersistido.getFormacionConfirmada());
	//	assertEquals(1,HomePartidos.getInstance().consultarCantidadDePartidosJugados(entityManager().find(Jugador.class, 21l)));
		}
	
//	@Test
//	public void consultarCantPartidos(){
//		Jugador jugador= this.crearJugadorPersistido("El jugador", 2, 1);
//		Partido partido=new Partido(LocalDate.now(),LocalTime.now(), "Casa");
//		Inscripcion inscripcion = this.crearInscripcionPersistida(jugador, new Estandar());
//		Integer i;
//		for (i = 0; i < 9; i++) {
//			partido.intentarInscribirA(this.crearInscripcionPersistida(this
//					.crearJugadorPersistido("Jugador".concat(i.toString()), i,
//							i + 5), new Estandar()));
//		}
//		partido.intentarInscribirA(inscripcion);
//		persist(partido);
//		assertEquals(1,HomePartidos.getInstance().getPartidos().size());
//		partido.armarEquipos(new CriterioHandicap(),new DivisionPorPares());
//		assertEquals(0,partido.jugo(jugador));
//		partido.aceptarEquipos(partido.getFormacionesTentativas().get(0));
//		assertEquals(1,partido.jugo(jugador));
//		assertEquals(1,HomePartidos.getInstance().getPartidos().get(0).jugo(jugador));
//		assertEquals(1,HomePartidos.getInstance().consultarCantidadDePartidosJugados(jugador));
//		Partido partidoPersistido=entityManager().find(Partido.class, partido.getId());
//		assertEquals(1,partidoPersistido.jugo(jugador));
//	
//	}
	
	@Test
	public void consultarCantidadDePartidosAlHome(){
		Jugador jugador= this.crearJugadorPersistido("Coco", 20,20);
		assertEquals(0,HomePartidos.getInstance().consultarCantidadDePartidosJugados(jugador));
	}
	
	private Inscripcion crearInscripcionPersistida(Jugador jugador,
			TipoDeInscripcion tipoDeInscripcion) {
		Inscripcion inscripcion = new Inscripcion(jugador, tipoDeInscripcion);
		persist(inscripcion);
		return inscripcion;
	}

	private Jugador crearJugadorPersistido(String nombre, int edad, int handicap) {
		Jugador jugador = new Jugador(nombre, edad, handicap);
		persist(jugador);
		return jugador;
	}

	private Critica crearCriticaPersistida(Jugador jugador, int valorCritica) {
		Partido partido = new Partido(LocalDate.now(), LocalTime.now(),
				"Campus");
		Critica critica = new Critica(valorCritica, "Malo", partido);
		jugador.agregarCritica(critica);
		persist(critica);
		persist(partido);
		return critica;
	}

	private Partido partidoLlenoPersistido() {
		Partido partido = new Partido(LocalDate.now(), LocalTime.now(),
				"Tablada");
		Integer i;
		for (i = 0; i < 10; i++) {
			partido.intentarInscribirA(this.crearInscripcionPersistida(this
					.crearJugadorPersistido("Jugador".concat(i.toString()), i,
							i + 5), new Estandar()));
		}
		persist(partido);
		return partido;
	}
}
