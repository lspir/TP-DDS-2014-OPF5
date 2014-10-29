package db;

import static org.junit.Assert.*;
import static db.EntityManagerHelper.*;

import javax.persistence.criteria.CriteriaQuery;

import opf5.jugador.Infraccion;
import opf5.jugador.Jugador;
import opf5.jugador.RepositorioJugadores;

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
		Jugador jugadorr= EntityManagerHelper.getEntityManager().find(Jugador.class, 1l);
		}
	
	@Test
	public void persistirJugadorAtravesDelHome(){
		Jugador jugador = new Jugador("Pepe",15,18);
		RepositorioJugadores.getInstance().create(jugador);
		jugador.tePenalizaron(new Infraccion("bobo", 2));

		}
	
}
