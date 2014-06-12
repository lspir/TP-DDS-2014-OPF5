package opf5;

public interface Estado {

	public void intentarInscribirA(Inscripcion inscripcion, Partido partido)
			throws ElPartidoYaEstaConfirmadoException;

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) throws ElPartidoYaEstaConfirmadoException;

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) throws ElPartidoYaEstaConfirmadoException;
	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo,Partido partido) throws ElPartidoYaEstaConfirmadoException,ElPartidoNoEstaCompleto;
	public void aceptarEquipos(Partido partido) throws NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException,ElPartidoNoEstaCompleto;
}
