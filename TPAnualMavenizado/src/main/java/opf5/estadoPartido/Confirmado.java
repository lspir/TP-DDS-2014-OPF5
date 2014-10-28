package opf5.estadoPartido;
import static java.util.stream.Collectors.*;

import java.util.*;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.excepciones.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.partido.*;

public class Confirmado implements Estado {
	private FormacionPartido formacion;
	
	public Confirmado(FormacionPartido formacion) {
		this.formacion=formacion;
	}

	public Confirmado() {
	}

	public List<Inscripcion> getEquipoA() {
		return formacion.getEquipoA();
	}

	public void setEquipoA(List<Inscripcion> equipoA) {
		formacion.setEquipoA(equipoA);
	}

	public List<Inscripcion> getEquipoB() {
		return formacion.getEquipoB();
	}

	public void setEquipoB(List<Inscripcion> equipoB) {
		formacion.setEquipoB(equipoB);;
	}
	public void intentarInscribirA(Inscripcion inscripcion, Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido) {
		throw new ElPartidoYaEstaConfirmadoException();
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}
	
	public void armarEquipos(CriterioOrdenamientoEquipos criterio, AlgoritmoDivisionDeEquipos algoritmo,Partido partido){
		throw new ElPartidoYaEstaConfirmadoException();
	}
	public void aceptarEquipos(Partido partido,FormacionPartido formacion){
		throw new ElPartidoYaEstaConfirmadoException();
	}

	
	public int jugo(Jugador jugador) {
		if( loContiene(formacion.getEquipoA(),jugador) || loContiene(formacion.getEquipoB(),jugador)){
			return 1;
		}
		return 0;
	}
	private Boolean loContiene(List<Inscripcion> lista, Jugador jugador){
		return lista.stream().map(inscripcion->inscripcion.jugador()).collect(toList()).contains(jugador);
	}
}

