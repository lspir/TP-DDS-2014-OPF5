package opf5.estadoPartido;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;

import opf5.AlgoritmoDivisionDeEquipos;
import opf5.Partido;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioOrdenamientoEquipos;
import opf5.inscripcion.Inscripcion;
import opf5.inscripcion.TipoDeInscripcion;
import opf5.jugador.Infraccion;
import opf5.jugador.Jugador;

public abstract class NoConfirmado implements Estado {
	
	public void intentarInscribirA(Inscripcion inscripcion, Partido partido) {
		if (partido.getInscripciones().size() < 10) {
			partido.getInscripciones().add(inscripcion);
			partido.revisarSiEstaLlenoEInformar();
			partido.getObservadores().forEach(observador -> observador
					.notificarJugadorInscripto(inscripcion.jugador()));
			partido.setEstado(new SinOrdenar());
			
		} else {
			if (partido.noEstaLlenoDeEstandares()) {
				inscripcion.inscribiteSiPodesA(partido);
				partido.setEstado(new SinOrdenar());
			}
		}
		
	}
	

	public void armarEquipos(CriterioOrdenamientoEquipos criterio, AlgoritmoDivisionDeEquipos algoritmo,Partido partido) {
		partido.tenes10Jugadores();
		List<Inscripcion> listaOrdenada = partido.getInscripciones().stream().sorted(comparing(inscrip -> criterio.ponderate(inscrip.jugador()))).collect(toList());
		partido.setInscripciones(algoritmo.dameLista(listaOrdenada));
		partido.setEstado(new Ordenado());
		}
	
	
	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		partido.getInscripciones().remove(inscripcion);
		Infraccion infraccion = new Infraccion();
		inscripcion.jugador().tePenalizaron(infraccion);
		if (partido.getInscripciones().size() == 9) {
			partido.getObservadores().forEach(obs -> obs.notificarPartidoIncompleto());
		}
		partido.setEstado(new SinOrdenar());
	}
	
	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) {
		inscripcion.teReemplaza(jugador, tipo);
		partido.getObservadores().forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));
		partido.setEstado(new SinOrdenar());
	}
}
