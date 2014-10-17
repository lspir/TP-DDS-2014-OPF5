package opf5.estadoPartido;
import opf5.excepciones.*;
import opf5.partido.*;

public class SinOrdenar extends NoConfirmado {

	public void aceptarEquipos(Partido partido, FormacionPartido formacion) {
		throw new NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException();
	}


}
	
