package opf5.excepciones;

public class ElPartidoNoEstaCompleto extends RuntimeException {

	@Override
	public String getMessage(){
		return "El partido no esta Completo";
	}
}
