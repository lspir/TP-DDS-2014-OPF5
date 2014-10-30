package db;

import static org.junit.Assert.*;
import static db.EntityManagerHelper.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaQuery;

import opf5.inscripcion.Estandar;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Infraccion;
import opf5.jugador.Jugador;
import opf5.jugador.RepositorioJugadores;
import opf5.partido.Partido;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContextTest {

	@Test
	public void contextUp() {
		EntityManagerHelper.getEntityManager();
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		EntityManagerHelper.withTransaction(() -> {});
	}
	
	@Before
	   public void begin() throws Exception {
	      beginTransaction();
	   }
		
		@After
	   public void tearDown() throws Exception {
	      rollback();
	   }
		
	@Test
	public void persistirJugador(){
		Jugador jugador = new Jugador("Pepe",15,18);
		persist(jugador);
		Jugador jugadorPersistido= EntityManagerHelper.getEntityManager().find(Jugador.class, 1l);
		assertEquals(jugador, jugadorPersistido);
		}
	
	@Test
	public void persistirJugadorAtravesDelHome(){
		Jugador jugador = new Jugador("Pepe",15,18);
		RepositorioJugadores.getInstance().create(jugador);
		jugador.tePenalizaron(new Infraccion("malo", 2));

		} 
	
	@Test
	public void persistirPartidoConUnaInscripcion(){
	Partido partido=new Partido(LocalDate.now(), LocalTime.now(), "Casa");
	partido.intentarInscribirA(new Inscripcion(new Jugador("hola", 12, 2), new Estandar()));
	persist(partido);
	Partido partidoPersistido=EntityManagerHelper.getEntityManager().find(Partido.class, 1l);
	assertEquals(partido.getInscripciones(),partidoPersistido.getInscripciones());
	assertEquals(partido.getEstado(),partidoPersistido.getEstado());
	} 
	
	
}
