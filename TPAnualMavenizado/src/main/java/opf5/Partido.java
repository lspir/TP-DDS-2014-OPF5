package opf5;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import java.util.Date;

public class Partido {
	private String dia;
	private String hora;
	private String lugar;
	//FIXME quizas aun no es tan evidente, pero no ven que de a poco el partido empieza a crecer en cosas que hace?
	//Es decir, lentamente y con cada entrega el partido se está volviendo menos cohesivo.
	//No es que tengan que hacer algo ya, pero tenganlo en cuenta en proximas entregas
	private List<Inscripcion> inscripciones;
	private List<Observador> observadores;
	private List<Inscripcion> posiblesJugadores;
	private List<Denegacion> denegaciones;

	public Partido(String dia, String hora, String lugar) {
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		inscripciones = new ArrayList<Inscripcion>();
		observadores = new ArrayList<Observador>();
		posiblesJugadores = new ArrayList<Inscripcion>();
		denegaciones = new ArrayList<Denegacion>();

	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion) {
		if (this.inscripciones.size() < 10) {
			inscripciones.add(inscripcion);
			this.revisarSiEstaLlenoEInformar();
			observadores.forEach(observador -> observador
					.notificarJugadorInscripto(inscripcion.jugador()));

		} else {
			//FIXME si necesitan aclarar con comentario en el código lo que están haciendo, 
			//es porque algo anda mal....
			// si esta lleno de estandares
			if (this.inscripciones.stream()
					.filter(ins -> !ins.tuTipoDejaPasar())
					//FIXME no es necesario hacer un toList para obtener el tamaño, 
					//pueden hacer directamente count() al stream
					.collect(toList()).size() != (10)) { 
				inscripcion.inscribiteSiPodesA(this);
			}
		}
	}

	public void inscribiA(Inscripcion inscripcion) {
		Inscripcion inscriptoAEliminar;
		List<Inscripcion> genteAEliminar;

		if ((this.inscripciones.stream().filter(i -> i.tipoDePrioridadMinima()))
				//FIXME idem comentario anterior sobre el count()
				//FIXME además tienen el isEmpty, donde hay menos lugar para manquearla que en un == 0
				.collect(toList()).size() == (0)) {
			genteAEliminar = inscripciones.stream()
					.filter(ins -> ins.tuTipoDejaPasar())
					//FIXME pavada de la tecnologia: no es necesario hacer Collectors.toList, 
					//pueden usar un import static y usar directamente toList(). Ya se los cambie, vean como quedó el código ahora
					.collect(toList());
		} else {
			genteAEliminar = (inscripciones.stream().filter(
					ins -> ins.tipoDePrioridadMinima()).collect(toList()));

		}

		inscriptoAEliminar = genteAEliminar.get(0);
		this.inscripciones.remove(inscriptoAEliminar);
		this.inscripciones.add(inscripcion);
		this.revisarCondicionales();
		this.revisarSiEstaLlenoEInformar();
		observadores.forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));

	}

	public void revisarCondicionales() {
		//FIXME las colecciones entienden el mensaje removeIf
		for (int i = 0; i < inscripciones.size(); i++) {
			Inscripcion aux = inscripciones.get(i);
			if (!aux.teCumple(this))
				inscripciones.remove(aux);
		}
	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion) {
		this.inscripciones.remove(inscripcion);
		Infraccion infraccion = new Infraccion();
		inscripcion.jugador().tePenalizaron(infraccion);
		if (this.inscripciones().size() == 9) {
			observadores.forEach(obs -> obs.notificarPartidoIncompleto());
		}
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo) {
		inscripcion.teReemplaza(jugador, tipo);
		observadores.forEach(observador -> observador
				.notificarJugadorInscripto(inscripcion.jugador()));
	}

	public void revisarSiEstaLlenoEInformar() {
		if (this.inscripciones.size() == 10) {
			observadores.forEach(obs -> obs.notificarPartidoLleno());
		}
	}

	public void tePropusieron(Jugador jugador, TipoDeInscripcion tipo) {
		Inscripcion inscripcion = new Inscripcion(jugador, tipo);
		posiblesJugadores.add(inscripcion);
	}

	public void administradorAcepto(Inscripcion inscripcion) {
		posiblesJugadores.remove(inscripcion);
		this.intentarInscribirA(inscripcion);
	}

	//FIXME por qué se llama de esta forma? Normalmente las firmas de los métodos no tienen
	//como parte de su nombre al objeto/usuario que lo van a usar, porque eso me 
	//estaría hablando de un acomplamiento implicito en el que un objeto sabe quien lo usa
	public void administradorRechazo(Inscripcion inscripcion, String motivo) {
		//FIXME no usen Date, usen LocalDate o LocalDateTime o LocalTime segun
		//su necesidad, ya que tienen interfaces mas ricas
		Date date = new Date(); 
		posiblesJugadores.remove(inscripcion);
		//FIXME sugerencia: eviten las variables locales, hacen que el codigo sea mas largo, se vea mas imperativo,
		//y sea por tanto mas complejo de seguir
		Denegacion denegacion = new Denegacion(motivo, inscripcion, date);
		denegaciones.add(denegacion);
	}

	public boolean verificarSiJugaron(Jugador jugador, Jugador otroJugador) {
		//FIXME notan que estan haciendo dos veces lo mismo?
		//No podrian separarlo en dos preguntas y eliminar asi la logica repetida?
		return (inscripciones.stream().anyMatch(
				inscripcion -> inscripcion.jugador() == jugador) && inscripciones
				.stream().anyMatch(
						inscripcion -> inscripcion.jugador() == otroJugador));

	}

	public List<Denegacion> denegaciones() {
		return denegaciones;
	}

	public List<Inscripcion> posiblesJugadores() {
		return posiblesJugadores;
	}

	public void agregarObservador(Observador observador) {
		observadores.add(observador);
	}
}
