package opf5;

public class SinConfirmar implements Estado {

	public void intentarInscribirA(Inscripcion inscripcion, Partido partido) {
		if (partido.inscripciones().size() < 10) {
			partido.inscripciones.add(inscripcion);
			partido.revisarSiEstaLlenoEInformar();
			partido.observadores.forEach(observador -> observador
					.notificarJugadorInscripto(inscripcion.jugador()));

		} else {
			if (partido.estaLlenoDeEstandares()) {
				inscripcion.inscribiteSiPodesA(partido);
			}
		}
	}


	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		partido.inscripciones.remove(inscripcion);
		Infraccion infraccion = new Infraccion();
		inscripcion.jugador().tePenalizaron(infraccion);
		if (partido.inscripciones().size() == 9) {
			partido.observadores.forEach(obs -> obs.notificarPartidoIncompleto());
		}
	}
	
	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) {
		inscripcion.teReemplaza(jugador, tipo);
		partido.observadores.forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));
	}
}

	
