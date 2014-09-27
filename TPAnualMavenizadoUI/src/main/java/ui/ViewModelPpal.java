package ui;
import java.time.*;

import opf5.*;
import opf5.inscripcion.*;
import opf5.jugador.*;

public class ViewModelPpal {
	 
	 
	 public ViewModelPpal(){
		 //FIXME el problema de poner este código de inicialización acá
		 //es que no podés instanciar esta ventana sin cargar datos de prueba. 
		 //¿Qué podrían hacer para resolver esto? ¿Dónde podrían generar el fixture, sin que estorbe
		 //al código productivo?
		 Partido partido=new Partido(LocalDate.now(),LocalTime.now(),"Campus");
			for(int i=0;i<10;i++){
				Jugador jugador=RepositorioJugadores.getInstance().getJugadores().get(i);
				Inscripcion inscripcionEstandar=new Inscripcion(jugador, new Estandar());
				partido.intentarInscribirA(inscripcionEstandar);
			}
			HomePartidos.getInstance().create(partido);
//			partido.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
//			partido.aceptarEquipos(partido.getFormacionesTentativas().get(0));
	
	 }

}
