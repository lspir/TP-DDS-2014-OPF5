package opf5.observers;
import opf5.*;


public interface Observador {
	public void notificarPartidoLleno();
	public void notificarPartidoIncompleto();
	public void notificarJugadorInscripto(Jugador jugador);
	public void adaptador(AdaptadorMailSender adaptador);
}
