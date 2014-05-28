package opf5;

import java.util.Date;

public class Denegacion {
	private String motivo;
	private Inscripcion inscripcion;
	private Date fecha;

	public Denegacion(String motivo, Inscripcion inscripcion, Date fecha) {
		this.motivo=motivo;
		this. inscripcion=inscripcion;
		this. fecha=fecha;
	}

}
