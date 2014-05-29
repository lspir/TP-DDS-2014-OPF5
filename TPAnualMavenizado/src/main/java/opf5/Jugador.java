package opf5;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private String nombre;
	private int edad;
	private List<Amigo> amigos;
	private List<Infraccion> infracciones;
	//FIXME esto no lo estan usando!
	private List<Critica> criticas;

	public Jugador(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
		amigos = new ArrayList<Amigo>();
		infracciones = new ArrayList<Infraccion>();
		criticas = new ArrayList<Critica>();
	}

	public void agregarAmigo(Amigo amigo) {
		this.amigos.add(amigo);
	}

	public List<Infraccion> infracciones() {
		return this.infracciones;
	}

	public List<Amigo> amigos() {
		return amigos;
	}

	public void tePenalizaron(Infraccion infraccion) {
		infracciones.add(infraccion);
	}

	public void critica(Jugador jugador, int nota, String texto, Partido partido) {
		if (partido.verificarSiJugaron(this, jugador)) {
			Critica critica = new Critica(nota, texto);
			jugador.agregarCritica(critica);
		} else 
			throw 
		//FIXME y si no? Está bien que deje a un jugador criticar a otro aun si no le corresponde?
		//La respuesta en realidad es filosófica, pero ustedes acá escribieron un método critica (que supongo es un 
		//criticá, el español no es bueno para codificar :P) que, entiendo, lo que hace es criticar (y para criticar, 
		//la precondición es haber jugado en ese partido). 
		//Ustedes acá están haciendo más bien un criticáSiPuede o intentáCriticar, que tienen otra semántica:
		//no tienen la precondicion de estar en el juego. 
		//En definitiva, acá tienen dos opciones: o renombran el método o hacen que falle de no cumplirse la condición.
		//Dado lo que hablamos de fail fast, siempre es más seguro fallar que seguir adelante en un posible 
		//estado de inconsistencia, asi que mi sugerencia es que vayan por lanzar una excepcion en lugar de renombrar este método
	}

	public void agregarCritica(Critica critica) {
		criticas.add(critica);
	}

	public List<Critica> criticas() {
		return criticas;
	}
}
