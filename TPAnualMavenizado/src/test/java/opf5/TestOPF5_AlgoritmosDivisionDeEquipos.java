package opf5;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import opf5.AlgoritmosDivisionDeEquipos.AlgoritmoDivisionDeEquipos;
import opf5.AlgoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.AlgoritmosDivisionDeEquipos.EquipoA03478EquipoB12569;
import opf5.inscripcion.Inscripcion;

import org.junit.Test;

public class TestOPF5_AlgoritmosDivisionDeEquipos extends TestOPF5 {
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
	
	@Test
	public void aplicarAlgoritmoDivisionDeEquipoA_03478_EquipoB_12569() {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		List<Inscripcion> inscripcionesValidada = new ArrayList<Inscripcion>();
		for (int i = 0; i < 10; i++) {
			if (i == 0 || i==3 || i==4||i==7||i==8) {
				inscripciones.add(inscripcionSolidario);
				inscripcionesValidada.add(inscripcionSolidario);

			} else {
				inscripciones.add(inscripcionEstandar);

			}

		}
		for (int j=0; j < 5; j++) {
			inscripcionesValidada.add(inscripcionEstandar);
			}
		AlgoritmoDivisionDeEquipos algoritmoDivision = new EquipoA03478EquipoB12569();
		assertEquals(inscripcionesValidada,
				algoritmoDivision.dameLista(inscripciones));

	}

}
