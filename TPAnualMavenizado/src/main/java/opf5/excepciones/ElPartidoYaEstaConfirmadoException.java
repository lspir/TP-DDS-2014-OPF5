package opf5.excepciones;

public class ElPartidoYaEstaConfirmadoException extends RuntimeException {
	
	@Override
	public String getMessage(){
		return "El partido ya esta Confirmado";
	}
}
