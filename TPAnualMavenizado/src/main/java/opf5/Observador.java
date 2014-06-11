package opf5;

//FIXME a esta interfaz la llamaron Observador, pero piensen que esto es valido solo porque en su sistema 
//hay un unico componente observable. Capaz podrian pensar un mejor nombre que refleje a
//que componente observe,
//para evitar ambiguedades futuras, pero, mucho mas importante, para hacer al diseño más claro 
//(expresivo) hoy. 
public interface Observador {
	public void notificarPartidoLleno();
	public void notificarPartidoIncompleto();
	public void notificarJugadorInscripto(Jugador jugador);
	public void adaptador(AdaptadorMailSender adaptador);
}
