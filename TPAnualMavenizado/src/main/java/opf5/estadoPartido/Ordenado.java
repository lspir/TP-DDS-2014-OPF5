package opf5.estadoPartido;
import opf5.partido.*;

public class Ordenado extends NoConfirmado {

	public Ordenado()
	{
		this.nombre= "Ordenado";
	}
	public void aceptarEquipos(Partido partido, FormacionPartido formacion){
		partido.tenes10Jugadores();
		partido.setEstado(new Confirmado(formacion));
		this.setearNombreEstadoPartido(partido);
		partido.setFormacionConfirmada(formacion);
	}


}