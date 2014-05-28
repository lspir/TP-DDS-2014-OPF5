package opf5;

public class ObservadorJugadorInscripto implements Observador {

	private AdaptadorMailSender adaptadorMailSender;

	public ObservadorNotificarAdmin() {

		AdaptadorMailSender adaptadorMailSender = new AdaptadorMailSender();
	}

	public void notificarPartidoLleno() {

	}

	public void notificarJugadorInscripto(Jugador jugador) {

		jugador.amigos().forEach(amigo -> mailSender.notificar(amigo.mail()));

	}

	public void notificarPartidoIncompleto() {

	}
}
