package opf5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class SinOrdenar implements Estado {

	public void intentarInscribirA(Inscripcion inscripcion, Partido partido) {
		if (partido.inscripciones().size() < 10) {
			partido.inscripciones.add(inscripcion);
			partido.revisarSiEstaLlenoEInformar();
			partido.observadores.forEach(observador -> observador
					.notificarJugadorInscripto(inscripcion.jugador()));

		} else {
			if (partido.noEstaLlenoDeEstandares()) {
				inscripcion.inscribiteSiPodesA(partido);
			}
		}
	}


	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		partido.inscripciones.remove(inscripcion);
		Infraccion infraccion = new Infraccion();
		inscripcion.jugador().tePenalizaron(infraccion);
		if (partido.inscripciones().size() == 9) {
			partido.observadores.forEach(obs -> obs.notificarPartidoIncompleto());
		}
	}
	
	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) {
		inscripcion.teReemplaza(jugador, tipo);
		partido.observadores.forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));
	}


	public void armarEquipos(Criterio criterio, AlgoritmoDivision algoritmo,Partido partido) throws ElPartidoNoEstaCompleto {
	partido.tenes10Jugadores();
	partido.inscripciones().stream().forEach(inscrip->inscrip.settearValorCriterio(criterio.funcion(inscrip.jugador())));
	List<Inscripcion> listaOrdenada = partido.inscripciones().stream().sorted(comparing(x -> x.valorDeCriterio())).collect(toList());
	partido.inscripciones= algoritmo.dameLista(listaOrdenada);
	partido.tuEstadoEs(new Ordenado());
	}
	
	public void aceptarEquipos(Partido partido) throws NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException,ElPartidoNoEstaCompleto {
		throw new NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException();
	}
	
}
	
