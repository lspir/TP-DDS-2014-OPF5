package opf5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import opf5.estadoPartido.SinOrdenar;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;

import org.junit.Test;

public class TestOPF5_Estados extends TestOPF{
	
	@Test
	public void partidoNuevoPoseeEstadoSinOrdenar() {
		Partido partido=new Partido(LocalDate.now(), LocalTime.now(),"Campus");
		assertTrue(partido.getEstado().getClass().equals(new SinOrdenar().getClass())); //FIXME

	}
	
}
