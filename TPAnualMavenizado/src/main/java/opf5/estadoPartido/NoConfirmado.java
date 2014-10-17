package opf5.estadoPartido;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.*;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.partido.*;

public abstract class NoConfirmado implements Estado {

	public void intentarInscribirA(Inscripcion inscripcion, Partido partido) {
		if (partido.getInscripciones().size() < 10) {
			partido.getInscripciones().add(inscripcion);
			partido.revisarSiEstaLlenoEInformar();
			partido.getObservadores().forEach(
					observador -> observador
							.notificarJugadorInscripto(inscripcion.jugador()));
			this.cambiarEstadoASinOrdenar(partido);

		} else {
			if (partido.noEstaLlenoDeEstandares()) {
				inscripcion.inscribiteSiPodesA(partido);
				this.cambiarEstadoASinOrdenar(partido);
			}
		}

	}

	public void armarEquipos(CriterioOrdenamientoEquipos criterio,
			AlgoritmoDivisionDeEquipos algoritmo, Partido partido) {
		partido.tenes10Jugadores();
		List<Inscripcion> inscripcionesOrdenadas = algoritmo.dameLista(partido
				.getInscripciones()
				.stream()
				.sorted(comparing(inscrip -> criterio.ponderate(inscrip
						.jugador()))).collect(toList()));
		partido.agregarFormacion(new FormacionPartido(inscripcionesOrdenadas.stream().limit(5).collect(toList()),inscripcionesOrdenadas.stream().skip(5).collect(toList())));
	partido.setEstado(new Ordenado());
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		partido.getInscripciones().remove(inscripcion);
		Infraccion infraccion = new Infraccion("Se Bajó del partido sin poner un Reemplazante",1);
		inscripcion.jugador().tePenalizaron(infraccion);
		if (partido.getInscripciones().size() == 9) {
			partido.getObservadores().forEach(
					obs -> obs.notificarPartidoIncompleto());
		}
		this.cambiarEstadoASinOrdenar(partido);
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido) {
		inscripcion.teReemplaza(jugador, tipo);
		partido.getObservadores().forEach(
				observador -> observador.notificarJugadorInscripto(inscripcion
						.jugador()));
		this.cambiarEstadoASinOrdenar(partido);
	}

	private void cambiarEstadoASinOrdenar(Partido partido) {
		partido.setEstado(new SinOrdenar());
		partido.limpiarFormaciones();
	}
	

	public int jugo(Jugador jugador) {
		return 0;
	}

}
