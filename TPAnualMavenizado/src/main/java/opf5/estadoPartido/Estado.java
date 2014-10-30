package opf5.estadoPartido;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.partido.*;

@Embeddable
public class Estado {
	@Column(name = "Estado")
	public String nombre;

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
	};
}
