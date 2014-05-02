package TestOPF5;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import OPF5.Estandar;
import OPF5.Inscripcion;
import OPF5.Jugador;
import OPF5.Partido;
import OPF5.Solidario;

public class TestOPF {
	
	Partido partido = new Partido("2/5", "14:00", "Campus");
	Partido partido2 = new Partido("4/5", "21:00", "Campus");
	Partido partido3 = new Partido("4/5", "21:00", "Campus");
	Partido partido4 = new Partido("4/5", "21:00", "Campus");
	Estandar estandar= new Estandar();
	Solidario solidario = new Solidario();
	Jugador jugador1, jugador2,jugador3, jugador4;
	Inscripcion inscripcion1, inscripcion2, inscripcion3;
	
	
	@Before 
	public void setUp()
	{
		
		jugador2 = new Jugador("nombre", 20);
		jugador3= new Jugador("nombre", 23);
		inscripcion2= new Inscripcion (jugador2, solidario);
		inscripcion3= new Inscripcion (jugador3, estandar);
				
		for (int i= 0; i< 6; i++) 
			{
			jugador1 = new Jugador("Ronaldo",  28);
			Inscripcion inscripcion1 = new Inscripcion (jugador1, estandar);
			inscripcion1. nuevaInscripcionA(partido);
			}
		

		for (int i= 0; i< 10; i++) 
			{
			jugador1 = new Jugador("Ronaldo",  28);
			Inscripcion inscripcion1 = new Inscripcion (jugador1, estandar);
			inscripcion1. nuevaInscripcionA(partido2);
			}
		
		
		for (int i= 0; i< 11; i++) 
		{
		jugador2 = new Jugador("nombre", 20);
		inscripcion2= new Inscripcion (jugador2, solidario);
		inscripcion2. nuevaInscripcionA(partido3);
		}
		
		for (int i= 0; i< 11; i++) 
		{
		jugador1 = new Jugador("Ronaldo",  28);
		Inscripcion inscripcion1 = new Inscripcion (jugador1, estandar);
		inscripcion1. nuevaInscripcionA(partido4);
		}
		
			
	}
	
	@Test
	public void Hay5EstandarSeQuiereAnotarUnSolidarioYLaInscripcionSeRealiza()
	{
		assertEquals("Inscripción realizada", inscripcion2.nuevaInscripcionA(partido));
	}

	@Test
	public void Hay10EstandarSeQuiereAnotarUnEstandarMasYLaInscripcionSeRechaza()
	{
		assertEquals("Cupos Llenos", inscripcion3.nuevaInscripcionA(partido2));
	}
	
	@Test
	public void Hay10SolidariosSeQuiereAnotar1EstandarYLaInscripcionEsCorrecta()
	{
		assertEquals("inscripcion correcta", inscripcion3.nuevaInscripcionA(partido3));
	}
	
	@Test
	public void Hay10EstandarSeQuiereAnotarUnSolidarioYLaInscripcionSeRechaza()
	{
		assertEquals("Cupos Llenos", inscripcion2.nuevaInscripcionA(partido4));
	}
	
	
}
