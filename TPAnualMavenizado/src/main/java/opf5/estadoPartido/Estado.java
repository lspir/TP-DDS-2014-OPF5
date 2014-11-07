package opf5.estadoPartido;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.partido.*;

public abstract class Estado {
	
	protected String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setearNombreEstadoPartido(Partido partido){
		partido.setNombreEstado(partido.getEstado().getNombre());
	}
	
	private FormacionPartido formacionConfirmada;
	

	public void intentarInscribirA(Inscripcion inscripcion,
			Partido partido){};

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion,
			Partido partido){};

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo, Partido partido){};

	public void armarEquipos(CriterioOrdenamientoEquipos criterio,
			AlgoritmoDivisionDeEquipos algoritmo, Partido partido){};

	public void aceptarEquipos(Partido partido,
			FormacionPartido formacion){};

	public int jugo(Jugador jugador){
		return -1;
	}

	public FormacionPartido getFormacionConfirmada() {
		return formacionConfirmada;
	}

	public void setFormacionConfirmada(FormacionPartido formacion) {
		this.formacionConfirmada = formacion;
	}

	public abstract boolean confirmado();

	public abstract boolean ordenado();

}
