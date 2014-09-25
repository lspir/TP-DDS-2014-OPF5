package ui;
import java.time.*;

import opf5.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import domain.*;

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
