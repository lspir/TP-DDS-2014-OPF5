package opf5.excepciones;

public class NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException extends
RuntimeException {
	
	@Override
	public String getMessage(){
		return "No se puede confirmar equipos, el partido no esta ordenado";
	}
}
