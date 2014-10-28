package opf5.estadoPartido;
import opf5.partido.*;

public class Ordenado extends NoConfirmado {

	public void aceptarEquipos(Partido partido, FormacionPartido formacion){
		partido.tenes10Jugadores();
		partido.setEstado(new Confirmado(formacion));

	}


}