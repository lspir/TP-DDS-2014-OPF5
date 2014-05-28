package opf5;

public class ObservadorJugadorInscripto implements Observador {

	public void notificarPartidoLleno() {

	}

	public void notificarJugadorInscripto(Jugador jugador) {
		jugador.amigos().forEach(amigo -> mailSender.notificar(amigo.dfdd));
	}

	public void notificarPartidoIncompleto() {

	}
}
