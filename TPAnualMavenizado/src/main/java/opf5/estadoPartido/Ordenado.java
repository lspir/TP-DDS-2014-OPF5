package opf5.estadoPartido;
import opf5.*;
import opf5.jugador.*;


import static java.util.stream.Collectors.toList;
import opf5.inscripcion.*;

public class Ordenado extends NoConfirmado {

	public void aceptarEquipos(Partido partido){
		partido.tenes10Jugadores();
		partido.setEquipoA(partido.getInscripciones().stream().limit(5)
				.collect(toList()));
		partido.setEquipoB(partido.getInscripciones().stream().skip(5)
				.collect(toList()));
		partido.setEstado(new Confirmado());

	}

}