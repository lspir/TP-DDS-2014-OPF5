package opf5;
import opf5.excepciones.*;

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
	
	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo,Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}
	public void aceptarEquipos(Partido partido){
	}
}

