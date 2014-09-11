package opf5.estadoPartido;
import opf5.*;
import opf5.jugador.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import opf5.excepciones.*;
import opf5.observers.*;
import opf5.inscripcion.*;

import java.util.List;

public class SinOrdenar extends NoConfirmado {

	public void aceptarEquipos(Partido partido, FormacionPartido formacion) {
		throw new NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException();
	}


}
	
