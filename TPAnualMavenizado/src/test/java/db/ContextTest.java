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
import opf5.jugador.HomeJugadores;
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

}
