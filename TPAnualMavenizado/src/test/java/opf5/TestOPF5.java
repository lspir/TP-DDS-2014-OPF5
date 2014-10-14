package opf5;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.*;
import java.util.*;

import opf5.AlgoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.estadoPartido.*;
import opf5.excepciones.*;
import opf5.inscripcion.*;
import opf5.jugador.*;
import opf5.mailSender.*;
import opf5.observers.*;

import org.junit.*;

public class TestOPF5 {
	protected Partido partidoCon5Estandares;
	protected Partido partidoCon10Solidarios;
	protected	Partido partidoLleno;
	protected Partido partidoCon8Estandares1Solidario1Condicional;
	protected Partido partidoCon1Estandar;
	protected	Estandar estandar;
	protected Solidario solidario;
	private Condicional condicional;
	private ObservadorJugadorInscripto observadorJugador;
	private ObservadorNotificarAdmin observadorAdmin;
	protected AdaptadorMailSender adaptadorMailSenderJugador;
	protected AdaptadorMailSender adaptadorMailSenderAdmin;
	protected Inscripcion inscripcionCondicional;
	protected Inscripcion inscripcionSolidario;
	protected Inscripcion inscripcionEstandar;
	protected Jugador jugador;
	protected Jugador jugadorConAmigos;
	protected Jugador jugadorCritico;
	private Amigo luciano, leandro;
	protected HomePartidos homePartidos;
	
	@Before
	public void setUp() {
		homePartidos=HomePartidos.getInstance();
		partidoCon5Estandares= new Partido(LocalDate.now(),LocalTime.now(), "Campus");
		partidoCon10Solidarios= new Partido(LocalDate.now().plusDays(10), LocalTime.now(), "Campus");
		partidoLleno = new Partido(LocalDate.of(2014, Month.AUGUST, 13),LocalTime.of(12,30), "Campus");
		partidoCon8Estandares1Solidario1Condicional= new Partido(LocalDate.of(2014, Month.APRIL, 29), LocalTime.of(21,30), "Campus");
		partidoCon1Estandar = new Partido(LocalDate.of(2014, Month.AUGUST, 29), LocalTime.of(20,00), "Mario Bravo");
		estandar = new Estandar();
		solidario = new Solidario();
		condicional= new Condicional();
		observadorJugador = new ObservadorJugadorInscripto();
		adaptadorMailSenderAdmin= mock(AdaptadorMailSender.class);
		adaptadorMailSenderJugador = mock(AdaptadorMailSender.class);
		observadorJugador.adaptador(adaptadorMailSenderJugador);
		observadorAdmin = new ObservadorNotificarAdmin("admin@admin.com.ar");
		observadorAdmin.adaptador(adaptadorMailSenderAdmin);
		partidoCon1Estandar.agregarObservador(observadorAdmin);
		partidoCon1Estandar.agregarObservador(observadorJugador);
		partidoCon10Solidarios.agregarObservador(observadorAdmin);
		partidoCon10Solidarios.agregarObservador(observadorJugador);
		partidoCon5Estandares.agregarObservador(observadorAdmin);
		partidoCon5Estandares.agregarObservador(observadorJugador);
		partidoCon8Estandares1Solidario1Condicional.agregarObservador(observadorAdmin);
		partidoCon8Estandares1Solidario1Condicional.agregarObservador(observadorJugador);
		partidoLleno.agregarObservador(observadorAdmin);
		partidoLleno.agregarObservador(observadorJugador);
		jugador = new Jugador("nombre", 20, 2);
		jugadorCritico =new Jugador("critico",12, 3);
		inscripcionEstandar = new Inscripcion(jugador, estandar);
		inscripcionSolidario = new Inscripcion(jugador, solidario);
		inscripcionCondicional = new Inscripcion(jugador, condicional);
		jugadorConAmigos = new Jugador("Cristiano Ronaldo", 28, 5);
		luciano = new Amigo("lucho@gmail.com");
		leandro = new Amigo("lean@gmail.com");
		jugadorConAmigos.agregarAmigo(luciano);
		jugadorConAmigos.agregarAmigo(leandro);		
		this.cargarPartido(partidoCon5Estandares, estandar, 5);
		this.cargarPartido(partidoLleno, estandar, 10);
		this.cargarPartido(partidoCon10Solidarios, solidario, 10);
		this.cargarPartido(partidoCon8Estandares1Solidario1Condicional, estandar, 8);		
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionSolidario);
		partidoCon8Estandares1Solidario1Condicional.intentarInscribirA(inscripcionCondicional);
		partidoCon1Estandar.intentarInscribirA(inscripcionEstandar);
}
	



private int randomEntre(int min, int max){
	return new Random().nextInt(max)+min;
}


private void cargarPartido(Partido partido,TipoDeInscripcion tipoDeinscripcion,int cantidadJugadores){
	for (int i = 0; i <cantidadJugadores; i++) {
		Inscripcion inscripcion = new Inscripcion(new Jugador("nombre", 20, 10), tipoDeinscripcion);
		partido.intentarInscribirA(inscripcion);
	}
}

protected void confirmarPartido(Partido partido){
	partido.armarEquipos(new CriterioHandicap(), new DivisionPorPares());
	partido.aceptarEquipos(partido.getFormacionesTentativas().get(0));
}


}



