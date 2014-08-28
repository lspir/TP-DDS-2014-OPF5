package opf5;
import opf5.inscripcion.*;

public interface Estado {

	public void intentarInscribirA(Inscripcion inscripcion, Partido partido);

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido);

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) ;
	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo,Partido partido);
	public void aceptarEquipos(Partido partido);
}
