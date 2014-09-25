package opf5.estadoPartido;
import opf5.*;
import opf5.excepciones.*;

public class SinOrdenar extends NoConfirmado {

	public void aceptarEquipos(Partido partido, FormacionPartido formacion) {
		throw new NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException();
	}


}
	
