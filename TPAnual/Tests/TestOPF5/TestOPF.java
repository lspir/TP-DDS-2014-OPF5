package TestOPF5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import opf5.Administrador;
import opf5.Condicional;
import opf5.Estandar;
import opf5.Inscripcion;
import opf5.Jugador;
import opf5.Partido;
import opf5.Solidario;

import org.junit.Before;
import org.junit.Test;

import opf5.Amigo;
import opf5.MailSender;
import opf5.StubMailSender;

public class TestOPF {

	Administrador adm = new Administrador("adm@hotmail.com");

	Partido partido = new Partido("2/5", "14:00", "Campus", adm);
	Partido partido2 = new Partido("4/5", "21:00", "Campus", adm);
	Partido partido3 = new Partido("4/5", "21:00", "Campus", adm);
	Partido partido4 = new Partido("4/5", "21:00", "Campus", adm);
	Partido partido5 = new Partido("4/5", "21:00", "Campus", adm);
	Partido partido6 = new Partido("4/5", "21:00", "Campus", adm);

	Estandar estandar = new Estandar();
	Solidario solidario = new Solidario();
	Jugador jugador1, jugador2, jugador3, jugador4, cr;
	Inscripcion inscripcion1, inscripcion2, inscripcion3, inscripcion4;
	Condicional condicional = new Condicional();
	Inscripcion inscripcionCondicional, inscripcionSolidario;
	Amigo luciano = new Amigo("lucho@gmail.com");
	Amigo leandro = new Amigo("lean@gmail.com");
	StubMailSender mailSender = new StubMailSender();

	@Before
	public void setUp() {

		jugador2 = new Jugador("nombre", 20);
		jugador3 = new Jugador("nombre", 23);
		inscripcion2 = new Inscripcion(jugador2, solidario);
		inscripcion3 = new Inscripcion(jugador3, estandar);
		cr = new Jugador("Cristiano Ronaldo", 28);
		cr.agregarAmigo(luciano);
		cr.agregarAmigo(leandro);
		inscripcion4 = new Inscripcion(cr, estandar);
	
	

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
	public void UnJugadorCon2AmigosSeInscribeYSeEnvia1MailACadaAmigo()
	{
		partido6.intentarInscribirA(inscripcion4);
		assertEquals(2, mailSender.enviados().stream().filter(mail-> mail.remitente() == inscripcion4.jugador()).collect(Collectors.toList()).size());
	}
	
	@Test
	public void UnPartidoSeLlenaYSeEnviaUnMailAlAdministrador()
	{
		assertEquals(1, mailSender.enviados().stream().filter(mail-> mail.remitente() == partido3).collect(Collectors.toList()).size());
	}
	
	@Test
	public void SiUnPartidoSeLlenaSeLeEnviaUnMailAlAdministrador()
	{
		assertEquals(1, mailSender.enviados().stream().filter(mail-> mail.remitente() == partido3).collect(Collectors.toList()).size());
	}
	
	@Test
	public void ElPartidoTieneUnSoloJugadorYEsteSeDaDeBajaSinReemplazanteElPartidoQuedaCon0Inscriptos()
	{
		partido6.seDioDeBajaSinReemplazante(inscripcion3);
		assertEquals(0, partido6.inscripciones().size());
	}
	
	@Test
	public void UnJugadorSeDaDeBajaSinReemplazanteEntoncesSeLoPenaliza()
	{
		partido6.seDioDeBajaSinReemplazante(inscripcion3);
		assertEquals(1, inscripcion3.jugador().infracciones().size());
	}
}
