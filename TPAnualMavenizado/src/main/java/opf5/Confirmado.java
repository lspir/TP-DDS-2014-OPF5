package opf5;
import opf5.excepciones.*;

public class Confirmado implements Estado {
	public void intentarInscribirA(Inscripcion inscripcion, Partido partido)
			throws ElPartidoYaEstaConfirmadoException {
		throw new ElPartidoYaEstaConfirmadoException();
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) throws ElPartidoYaEstaConfirmadoException {
		throw new ElPartidoYaEstaConfirmadoException();
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido)
			throws ElPartidoYaEstaConfirmadoException {
		throw new ElPartidoYaEstaConfirmadoException();
	}
	
	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo,Partido partido) throws ElPartidoYaEstaConfirmadoException{
		throw new ElPartidoYaEstaConfirmadoException();
	}
	public void aceptarEquipos(Partido partido){
	}
}

