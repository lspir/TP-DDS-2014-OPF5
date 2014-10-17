package viewModels;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import opf5.algoritmosDivisionDeEquipos.DivisionPorPares;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioHandicap;
import opf5.inscripcion.Estandar;
import opf5.inscripcion.Inscripcion;
import opf5.jugador.Amigo;
import opf5.jugador.Critica;
import opf5.jugador.Infraccion;
import opf5.jugador.Jugador;
import opf5.jugador.RepositorioJugadores;
import opf5.partido.HomePartidos;
import opf5.partido.Partido;
import vistas.ViewPpal;

public class ViewModelPpalDecorador implements ViewModelPpalInterfaz {

	private ViewModelPpalInterfaz viewModel = new ViewModelPpal();
	private RepositorioJugadores repositorioJugadores = RepositorioJugadores
			.getInstance();

	public ViewModelPpalDecorador() {
		this.armarFixture();
	}

	private void armarFixture() {
		ArrayList<String> nombreJugadores = new ArrayList<String>(
			    Arrays.asList("Belén", "Leandro", "Emiliano","Alicia","Noelia","Tomasito","Patricio","Beto","Genaro","Kiara","FM9","Negrita",
			    		"Gringo","Mishimishi","Messias"));
		int maxEdad=50;
		int minEdad=1;
		for(int i=0;i<15;i++){
			this.crearJugador(nombreJugadores.get(i),this.randomEntre(minEdad, maxEdad),i);
		}
		for(int i=0;i<15;i++){
			this.amigar(RepositorioJugadores.getInstance().getJugadores().get(i),nombreJugadores.get(this.randomEntre(0, 15)));
			this.amigar(RepositorioJugadores.getInstance().getJugadores().get(i),nombreJugadores.get(this.randomEntre(0, 15)));
		}
		this.armarPartidoConfirmado(0,10);
		this.armarPartidoConfirmado(5,15);
		this.armarPartidoSinConfirmar(0,10);
				
	}
	
	private void amigar(Jugador jugador, String nombre) {
		jugador.agregarAmigo(new Amigo(nombre.concat("@gmail.com")));
		
	}
	
	private int randomEntre(int min, int max){
		return new Random().nextInt(max)+min;
	}


	private Partido armarPartidoSinConfirmar(int posInicial, int posFinal) {
		Partido partido=new Partido(LocalDate.now(),LocalTime.now(),"Campus");
		for(int i=posInicial;i<posFinal;i++){
			Jugador jugador=repositorioJugadores.getJugadores().get(i);
			Inscripcion inscripcionEstandar=new Inscripcion(jugador, new Estandar());
			partido.intentarInscribirA(inscripcionEstandar);
			jugador.agregarCritica(new Critica(this.calificacionRandom(), "Podria ser peor", partido));
			jugador.agregarCritica(new Critica(this.calificacionRandom(), "Crack", partido));
			jugador.agregarCritica(new Critica(this.calificacionRandom(), "Normal", partido));
			jugador.agregarCritica(new Critica(this.calificacionRandom(), "Podria ser peor", partido));
			if(this.randomEntre(0, 3)==1){
				partido.seDioDeBajaSinReemplazante(inscripcionEstandar);
				partido.intentarInscribirA(inscripcionEstandar);
			}
			if(this.randomEntre(0, 3)==1){
				jugador.tePenalizaron(new Infraccion("Me caes Mal", 0));
			}
		}
		HomePartidos.getInstance().create(partido);
		return partido;
	}
	
	private int calificacionRandom(){
		return randomEntre(1, 10);
	}

	private void armarPartidoConfirmado(int posInicial, int posFinal) {
		Partido partido=this.armarPartidoSinConfirmar(posInicial, posFinal);
		partido.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
		partido.aceptarEquipos(partido.getFormacionesTentativas().get(0));
}

	private void crearJugador(String nombre, int edad, int handicap) {
		repositorioJugadores.create(new Jugador(nombre, edad, handicap));
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
