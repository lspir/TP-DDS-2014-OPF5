package opf5.jugador;
import opf5.partido.*;

public class Critica {

	private int nota;
	private String texto;
	private Partido partido;

	public Critica(int nota, String texto, Partido partido) {
		this.nota = nota;
		this.texto = texto;
		this.partido = partido;

	}

	public Partido partido() {
		return partido;
	}

	public int nota() {
		return nota;
	}

}
