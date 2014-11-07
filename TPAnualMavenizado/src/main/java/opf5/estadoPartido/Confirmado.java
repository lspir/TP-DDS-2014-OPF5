package opf5.estadoPartido;
import static java.util.stream.Collectors.*;

import java.util.*;

import javax.persistence.Transient;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.excepciones.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.partido.*;

public class Confirmado extends Estado {
		
	public Confirmado(FormacionPartido formacion) {
		this.setFormacionConfirmada(formacion);
		this.nombre= "Confirmado";
	}

	public Confirmado() {
	}

	public List<Inscripcion> getEquipoA() {
		return getFormacionConfirmada().getEquipoA();
	}

	public void setEquipoA(List<Inscripcion> equipoA) {
		getFormacionConfirmada().setEquipoA(equipoA);
	}

	public List<Inscripcion> getEquipoB() {
		return getFormacionConfirmada().getEquipoB();
	}

	public void setEquipoB(List<Inscripcion> equipoB) {
		getFormacionConfirmada().setEquipoB(equipoB);;
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
		if( loContiene(getFormacionConfirmada().getEquipoA(),jugador) || loContiene(getFormacionConfirmada().getEquipoB(),jugador)){
			return 1;
		}
		return 0;
	}
	private Boolean loContiene(List<Inscripcion> lista, Jugador jugador){
		return lista.stream().map(inscripcion->inscripcion.jugador()).collect(toList()).contains(jugador);
	}

	public boolean confirmado() {
		return true;
	}

	public boolean ordenado() {
		return false;
	}
}

