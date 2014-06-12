package opf5;

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
}
