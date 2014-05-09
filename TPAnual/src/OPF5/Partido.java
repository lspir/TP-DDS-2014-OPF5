package OPF5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

public class Partido {
	private String diaPartido;
	private String horaPartido;
	private String lugarPartido;
	private List<Inscripcion> inscripciones;
	private List<Inscripcion> rechazados;

	public Partido(String dia, String hora, String lugar) {
		diaPartido = dia;
		horaPartido = hora;
		lugarPartido = lugar;
		inscripciones = new ArrayList<Inscripcion>();
	}

	public List<Inscripcion> inscripciones() {
		return this.inscripciones;
	}

	public void intentarInscribirA(Inscripcion inscripcion) {
		if (this.inscripciones.size() < 10) {
			inscripciones.add(inscripcion);
		} else {
			// si está lleno de estándares
			if (this.inscripciones.stream()
					.filter(ins -> !ins.dejasPasarTuTipo())
					.collect(Collectors.toList()).size() != (10)) {
				inscripcion.inscribiteSiPodesA(this);
			}
		}
	}

	public void inscribiA(Inscripcion inscripcion) {
		Optional<Inscripcion> inscriptoAEliminar;

		if ((this.inscripciones.stream().filter(i -> i.tipoDePrioridadMinima()))
				.collect(Collectors.toList()).size() == (0)) {
			inscriptoAEliminar = this.inscripciones.stream()
					.filter(ins -> ins.dejasPasarTuTipo()).findFirst();
		} else {
			inscriptoAEliminar = ((Stream<Inscripcion>) this.inscripciones
					.stream().filter(ins -> ins.tipoDePrioridadMinima())
					.collect(Collectors.toList())).findFirst();
		}

		this.inscripciones.remove(inscriptoAEliminar);
		this.inscripciones.add(inscripcion);
	}

}
