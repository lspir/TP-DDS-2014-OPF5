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
	private Administrador administrador;

	public Partido(String dia, String hora, String lugar,
			Administrador administrador) {
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		inscripciones = new ArrayList<Inscripcion>();
		this.administrador = administrador;
	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion) {
		if (this.inscripciones.size() < 10) {
			inscripciones.add(inscripcion);
			this.revisarSiEstaLlenoEInformar();
			// acá se le avisaría a los amigos del jugador que se inscribio
		} else {
			// si está lleno de estándares
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
		this.revisarSiEstaLlenoEInformar();
		// aca se le avisa a por mail a los amigos del jugador que se inscribio

	}

	public void seDioDeBajaSinReemplazante(Inscripcion inscripcion) {
		this.inscripciones.remove(inscripcion);
		this.administrador.penaliza(inscripcion.jugador());
		if (this.inscripciones().size() == 9) {
			// informar por mail que se vacio
		}
	}

	public void seDioDeBajaConReemplazante(Inscripcion inscripcion,
			Jugador jugador) {
		inscripcion.teReemplaza(jugador);
		// mail
	}

	public void revisarSiEstaLlenoEInformar() {
		if (this.inscripciones.size() == 10) {
			// avisar al administrador por mail que esta lleno
		}
	}

}
