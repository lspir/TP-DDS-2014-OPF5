package opf5.excepciones;
//FIXME no usen excepciones chequeadas (que hereden de Exception)
//Usen siempre excepciones no chequeadas (que hereden de RuntimeException).
//De esa forma no tienen que modiicar la firma del método cada vez que podrían lanzar una excepción
public class ElPartidoYaEstaConfirmadoException extends Exception {

}
