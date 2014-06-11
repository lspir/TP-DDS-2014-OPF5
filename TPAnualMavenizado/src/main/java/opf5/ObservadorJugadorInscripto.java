package opf5;

public class ObservadorJugadorInscripto implements Observador {

	private AdaptadorMailSender adaptadorMailSender;
	
	public void adaptador(AdaptadorMailSender adaptador)
	{
		this.adaptadorMailSender = adaptador;
	}

	public ObservadorJugadorInscripto()
	{
		adaptadorMailSender= new AdaptadorMailSender();
	}
	public void notificarPartidoLleno() {

	}

	public void notificarJugadorInscripto(Jugador jugador) {

		jugador.amigos().forEach(amigo -> adaptadorMailSender.notificar(amigo.direccion()));

	}

	public void notificarPartidoIncompleto() {

	}
}
