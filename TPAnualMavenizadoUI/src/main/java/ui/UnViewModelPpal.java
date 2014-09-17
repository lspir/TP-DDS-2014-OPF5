package ui;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import opf5.HomePartidos;
import opf5.Partido;
import opf5.AlgoritmosDivisionDeEquipos.AlgoritmoDivisionDeEquipos;
import opf5.AlgoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.inscripcion.Estandar;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;
import domain.UnModel;

public class UnViewModelPpal {
	
	 private UnModel unModel;
	 
	 
	 public UnViewModelPpal(){
		 Partido partido=new Partido(LocalDate.now(),LocalTime.now(),"Campus");
			for(int i=0;i<10;i++){
				Jugador jugador=RepositorioJugadores.getInstance().getData().get(i);
				Inscripcion inscripcionEstandar=new Inscripcion(jugador, new Estandar());
				partido.intentarInscribirA(inscripcionEstandar);
			}
			HomePartidos.getInstance().create(partido);
//			partido.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
//			partido.aceptarEquipos(partido.getFormacionesTentativas().get(0));
	
	 }

}
