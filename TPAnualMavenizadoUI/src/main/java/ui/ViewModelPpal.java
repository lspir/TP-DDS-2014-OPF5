package ui;
import java.time.*;

import org.uqbar.commons.utils.Observable;

import opf5.*;
import opf5.inscripcion.*;
import opf5.jugador.*;

@Observable
public class ViewModelPpal implements ViewModelPpalInterfaz {
	 

	 
	 public void viewGeneracionEquipos(ViewPpal view){
		 new ViewGeneracionEquipos(view).open();
	 }

	public void viewBusquedaJugadores(ViewPpal viewPpal) {
		new ViewBusquedaDeJugadores(viewPpal).open();
	}


}
