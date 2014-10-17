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
	private List<Inscripcion> equipoA = new ArrayList<Inscripcion>();
	private List<Inscripcion> equipoB = new ArrayList<Inscripcion>();
	
	public Confirmado(List<Inscripcion> equipoA, List<Inscripcion> equipoB) {
		this.equipoA=equipoA;
		this.equipoB=equipoB;
	}

	public Confirmado() {
	}

	public List<Inscripcion> getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(List<Inscripcion> equipoA) {
		this.equipoA = equipoA;
	}

	public List<Inscripcion> getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(List<Inscripcion> equipoB) {
		this.equipoB = equipoB;
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
		if( loContiene(this.equipoA,jugador) || loContiene(this.equipoB,jugador)){
			return 1;
		}
		return 0;
	}
	private Boolean loContiene(List<Inscripcion> lista, Jugador jugador){
		return lista.stream().map(inscripcion->inscripcion.jugador()).collect(toList()).contains(jugador);
	}
}

