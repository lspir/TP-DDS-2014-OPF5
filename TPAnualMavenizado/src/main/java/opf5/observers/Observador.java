package opf5.observers;
import opf5.jugador.*;
import opf5.mailSender.*;


public interface Observador {
	public void notificarPartidoLleno();
	public void notificarPartidoIncompleto();
	public void notificarJugadorInscripto(Jugador jugador);
	public void adaptador(AdaptadorMailSender adaptador);
}
