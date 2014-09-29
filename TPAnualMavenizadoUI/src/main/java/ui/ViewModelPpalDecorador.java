package ui;

import java.time.LocalDate;
import java.time.LocalTime;

import opf5.HomePartidos;
import opf5.Partido;
import opf5.inscripcion.Estandar;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Jugador;
import opf5.jugador.RepositorioJugadores;

public class ViewModelPpalDecorador implements ViewModelPpalInterfaz {

	private ViewModelPpalInterfaz viewModel= new ViewModelPpal();
	
	public ViewModelPpalDecorador() {
		this.armarFixture();
	}
	
	
	private void armarFixture() {
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
	@Override
	public void viewGeneracionEquipos(ViewPpal view) {
		viewModel.viewGeneracionEquipos(view);
		
	}

	@Override
	public void viewBusquedaJugadores(ViewPpal viewPpal) {
		viewModel.viewBusquedaJugadores(viewPpal);		
	}

}
