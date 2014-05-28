package opf5;

public interface Observador {
	public void notificarPartidoLleno();
	public void notificarPartidoIncompleto();
	public void notificarJugadorInscripto(Jugador jugador);
}
