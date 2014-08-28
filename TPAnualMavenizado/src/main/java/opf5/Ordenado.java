package opf5;

import static java.util.stream.Collectors.toList;

//FIXME ¿les parece una buena abstracción decir que "todo estado ordenado es un estado sin ordenar"?
public class Ordenado extends SinOrdenar {

	public void intentarInscribirA(Inscripcion inscripcion, Partido partido) {
		super.intentarInscribirA(inscripcion, partido);
		partido.tuEstadoEs(new SinOrdenar());
	}

	public void aceptarEquipos(Partido partido){
		partido.tenes10Jugadores();
		partido.equipoA = partido.inscripciones.stream().limit(5)
				.collect(toList());
		partido.equipoB = partido.inscripciones.stream().skip(5)
				.collect(toList());
		partido.tuEstadoEs(new Confirmado());

	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) {
		super.seDioDeBajaConReemplazante(inscripcion, jugador, tipo, partido);
		partido.tuEstadoEs(new SinOrdenar());
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		super.seDioDeBajaSinReemplazante(inscripcion, partido);
		partido.tuEstadoEs(new SinOrdenar());
	}

}