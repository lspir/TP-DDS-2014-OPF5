package opf5.observers;
import opf5.*;

public class ObservadorNotificarAdmin implements Observador {

	private String mailAdmin;
	private AdaptadorMailSender adaptadorMailSender;

	public ObservadorNotificarAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
		adaptadorMailSender = new AdaptadorMailSender();
	}

	public void notificarPartidoLleno() {
		adaptadorMailSender.notificar(mailAdmin);

	}

	public void notificarJugadorInscripto(Jugador jugador) {

	}

	public void notificarPartidoIncompleto() {

		adaptadorMailSender.notificar(mailAdmin);
	}

	public void adaptador(AdaptadorMailSender adaptador) {
		this.adaptadorMailSender = adaptador;
	}

}
