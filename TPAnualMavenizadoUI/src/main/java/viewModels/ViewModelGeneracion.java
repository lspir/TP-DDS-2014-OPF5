package viewModels;

import static java.util.stream.Collectors.*;

import java.util.*;

import opf5.*;
import opf5.AlgoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.jugador.*;

import org.uqbar.commons.utils.Observable;
@Observable

public class ViewModelGeneracion {

	private AlgoritmoDivisionDeEquipos algoritmoDivision;
	private CriterioOrdenamientoEquipos criterioOrdenamiento;
	private List<AlgoritmoDivisionDeEquipos> algoritmosDivision = new ArrayList<AlgoritmoDivisionDeEquipos>();
	private List<CriterioOrdenamientoEquipos> criteriosOrdenamiento = new ArrayList<CriterioOrdenamientoEquipos>();
	private Partido partido;
	private Jugador jugadorSeleccionado;
	private List<Jugador> equipo1 = new ArrayList<Jugador>();
	private List <Jugador> equipo2=new ArrayList<Jugador>();
	
	public ViewModelGeneracion(){
		partido=HomePartidos.getInstance().getPartidos().get(HomePartidos.getInstance().getPartidos().size()-1);
	}
	
	public Jugador getJugadorSeleccionado() {
		return jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
		this.jugadorSeleccionado = jugadorSeleccionado;
	}

	public List<Jugador> getEquipo1() {
		return equipo1;
}

	public void setEquipo1(List<Jugador> equipo1) {
		this.equipo1 = equipo1;
	}

	public List<Jugador> getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(List<Jugador> equipo2) {
		this.equipo2 = equipo2;
	}

	public ArrayList<CriterioOrdenamientoEquipos> getCriteriosOrdenamiento() {
		ArrayList<CriterioOrdenamientoEquipos> asd = new ArrayList<CriterioOrdenamientoEquipos>();
		asd.add(new PromedioDeUltimoPartido());
		asd.add(new UltimasNCalificaciones(3));
		asd.add(new CriterioHandicap());
		return asd;
	}

	public ArrayList<AlgoritmoDivisionDeEquipos> getAlgoritmosDivision() {
		ArrayList<AlgoritmoDivisionDeEquipos> asd = new ArrayList<AlgoritmoDivisionDeEquipos>();
		asd.add(new DivisionPorPares());
		asd.add(new EquipoA03478EquipoB12569());
		return asd;
	}

	public AlgoritmoDivisionDeEquipos getAlgoritmoDivision() {
		return algoritmoDivision;
	}

	public void setAlgoritmoDivision(AlgoritmoDivisionDeEquipos nom) {
		this.algoritmoDivision = nom;
	}

	public void setAlgoritmosDivision(
			ArrayList<AlgoritmoDivisionDeEquipos> algoritmos) {
		this.algoritmosDivision = algoritmos;
	}
	
	public CriterioOrdenamientoEquipos getCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}

	public void setCriterioOrdenamiento (CriterioOrdenamientoEquipos nom) {
		this.criterioOrdenamiento= nom;
	}

	public void setCriteriosOrdenamiento(
			ArrayList<CriterioOrdenamientoEquipos> criterios) {
		this.criteriosOrdenamiento= criterios;
	}
	public void generacionEquipos(){
		
		partido.armarEquipos(criterioOrdenamiento,algoritmoDivision);
		equipo1 = this.formacion().getEquipoA().stream().map(inscrip -> inscrip.jugador()).collect(toList());
		equipo2= this.formacion().getEquipoB().stream().map(inscrip -> inscrip.jugador()).collect(toList());
		
	}
	public void confirmarEquipos(){
		partido.aceptarEquipos(partido.getFormacionesTentativas().get(0));
	}

	private FormacionPartido formacion() {
		return partido.getFormacionesTentativas().get(partido.getFormacionesTentativas().size()-1);
		}
}
