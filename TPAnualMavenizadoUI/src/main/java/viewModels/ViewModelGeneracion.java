package viewModels;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

import java.util.*;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.excepciones.NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.*;
import opf5.partido.*;

import org.uqbar.commons.utils.Observable;

import db.EntityManagerHelper;
@Observable

public class ViewModelGeneracion {

	private AlgoritmoDivisionDeEquipos algoritmoDivision;
	private CriterioOrdenamientoEquipos criterioOrdenamiento;
	private ArrayList<AlgoritmoDivisionDeEquipos> algoritmosDivision = new ArrayList<AlgoritmoDivisionDeEquipos>();
	private ArrayList<CriterioOrdenamientoEquipos> criteriosOrdenamiento = new ArrayList<CriterioOrdenamientoEquipos>();
	private Partido partido;
	private Jugador jugadorSeleccionado;
	private List<Jugador> equipo1 = new ArrayList<Jugador>();
	private List <Jugador> equipo2=new ArrayList<Jugador>();
	
	public ViewModelGeneracion(){
		partido=HomePartidos.getInstance().getPartidosOrdenados().get(HomePartidos.getInstance().getPartidos().size()-1);
		if (partido.estaConfirmado()){
		this.equipo1=partido.getFormacionConfirmada().getEquipoA().stream().map(inscripcion->inscripcion.jugador()).collect(toList());
		this.equipo2=partido.getFormacionConfirmada().getEquipoB().stream().map(inscripcion->inscripcion.jugador()).collect(toList());
		}
		if (partido.estaOrdenado()){
			this.equipo1=this.formacion().getEquipoA().stream().map(inscripcion->inscripcion.jugador()).collect(toList());
			this.equipo2=this.formacion().getEquipoB().stream().map(inscripcion->inscripcion.jugador()).collect(toList());
		}
		this.agregarCriteriosOrdenamiento();
		this.agregarAlgoritmosDivision();
		}
	
	private void agregarAlgoritmosDivision() {
		algoritmosDivision.add(new DivisionPorPares());
		algoritmosDivision.add(new EquipoA03478EquipoB12569());
	}

	private void agregarCriteriosOrdenamiento() {
		criteriosOrdenamiento.add(new PromedioDeUltimoPartido());
		criteriosOrdenamiento.add(new UltimasNCalificaciones(3));
		criteriosOrdenamiento.add(new CriterioHandicap());
		
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
		return criteriosOrdenamiento;
	}

	public ArrayList<AlgoritmoDivisionDeEquipos> getAlgoritmosDivision() {
		return algoritmosDivision;
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
		EntityManagerHelper.beginTransaction();
		try{
			partido.armarEquipos(criterioOrdenamiento,algoritmoDivision);
		}
		catch(Error e){
			EntityManagerHelper.rollback();
			throw e;
		}
		EntityManagerHelper.commit();
		equipo1 = this.formacion().getEquipoA().stream().map(inscrip -> inscrip.jugador()).collect(toList());
		equipo2= this.formacion().getEquipoB().stream().map(inscrip -> inscrip.jugador()).collect(toList());
		}
	
	public void confirmarEquipos(){
		EntityManagerHelper.beginTransaction();
		try{
			partido.aceptarEquipos(formacion());
		}
		catch (IndexOutOfBoundsException exception){
			EntityManagerHelper.rollback();
			throw new NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException();
		}catch(Error e){
			EntityManagerHelper.rollback();
			throw e;
		}
		EntityManagerHelper.commit();
		}

	private FormacionPartido formacion() {
		return partido.getFormacionesTentativas().get(partido.getFormacionesTentativas().size()-1);
		}
}
