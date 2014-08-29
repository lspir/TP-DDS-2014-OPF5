package opf5.estadoPartido;
import opf5.excepciones.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.inscripcion.*;
import opf5.*;

public class Confirmado implements Estado {
	public void intentarInscribirA(Inscripcion inscripcion, Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		throw new ElPartidoYaEstaConfirmadoException();
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}
	
	public void armarEquipos(CriterioOrdenamientoEquipos criterio, AlgoritmoDivision algoritmo,Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}
	public void aceptarEquipos(Partido partido){
	}
}

