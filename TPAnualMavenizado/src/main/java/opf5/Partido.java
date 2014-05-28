package opf5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

public class Partido {
	private String dia;
	private String hora;
	private String lugar;
	private List<Inscripcion> inscripciones;
	private List<Observador> observadores;

	public Partido(String dia, String hora, String lugar) {
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		inscripciones = new ArrayList<Inscripcion>();

	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion) {
		if (this.inscripciones.size() < 10) {
			inscripciones.add(inscripcion);
			this.revisarSiEstaLlenoEInformar();
			inscripcion.avisarATusAmigos();
			
		} else {
			// si esta lleno de estandares
			if (this.inscripciones.stream()
					.filter(ins -> !ins.tuTipoDejaPasar())
					.collect(Collectors.toList()).size() != (10)) {
				inscripcion.inscribiteSiPodesA(this);
			}
		}
	}

	public void inscribiA(Inscripcion inscripcion) {
		Inscripcion inscriptoAEliminar;
		List<Inscripcion> genteAEliminar;

		if ((this.inscripciones.stream().filter(i -> i.tipoDePrioridadMinima()))
				.collect(Collectors.toList()).size() == (0)) {
			genteAEliminar = inscripciones.stream()
					.filter(ins -> ins.tuTipoDejaPasar())
					.collect(Collectors.toList());
		} else {
			genteAEliminar = (inscripciones.stream().filter(
					ins -> ins.tipoDePrioridadMinima()).collect(Collectors
					.toList()));

		}

		inscriptoAEliminar = genteAEliminar.get(0);
		this.inscripciones.remove(inscriptoAEliminar);
		this.inscripciones.add(inscripcion);
		this.revisarCondicionales();
		this.revisarSiEstaLlenoEInformar();
		inscripcion.avisarATusAmigos();

	}

	public void revisarCondicionales() {

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
			this.avisarAAdministrador();
		}
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador, TipoDeInscripcion tipo) {
		inscripcion.teReemplaza(jugador, tipo);
		inscripcion.avisarATusAmigos();
	}

	public void revisarSiEstaLlenoEInformar() {
		if (this.inscripciones.size() == 10) {
			observadores.forEach(obs-> obs.notificarPartidoLleno());
		}
	}

	public void avisarAAdministrador() {
		MailSender.notificar(administrador.direccion(), this);
	}
}
