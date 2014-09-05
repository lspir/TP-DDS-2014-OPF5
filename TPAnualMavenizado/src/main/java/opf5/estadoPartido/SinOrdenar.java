package opf5.estadoPartido;
import opf5.*;
import opf5.jugador.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;


import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import opf5.excepciones.*;
import opf5.observers.*;
import opf5.inscripcion.*;

import java.util.List;

public class SinOrdenar extends NoConfirmado {

    //FIXME probablemete el problema más importante que presenta esta solución es que modifica 
 //el estado interno del partido cada vez que se solicita generar los equipos tenativos, 
 //aún sin haberlos confirmados: modifica el peso de cada inscripción.
	
 //Es decir, se está usando al partido como una memoria compartida para guardar los resultados 
 //temporales de generarEquipos. 
	
 //Si tuviera dos administradores, o tuviera un administrador que abre dos ventanas del browser
 //o dos hilos (threads) que ejecutan el generarEquipos, o incluso si simplemente quisiera
 //generar dos formaciones y tenerlas al mismo tiempo para compararlas, no podría, dado que al 
 //ser el partido el que almacena el estado conversacional de la operación de 
 //generacion de equipos tentativos,
 //termino pudiendo generar una sola formación de equios tentativa por vez. 
 //
 //Moraleja: necesitamos que cada vez que evalue el método de generación de equipos tentativos,
 //obtenga formaciones tentivas que sean independientes y no "estorben" a generaciones 
 //tentativas anteriores ni al partido. 
 //De esa forma, no solo podremos hacer cosas que antes no podiamos, sino que minimizamos las mutaciones
 //de estado innecesarias (no necesito modificar al partido para ver una potencial formación de equipos). 
	
	
	//FIXME otro problema de su solución es que no queda claro dónde está el resultado del equipo tentativo. Si yo quiero 
	//ejecutar varias veces la genreación tentativa de equipos para ver "como queda", no es evidente que están en la lista de inscripciones,
	//la que contiene a un equipo hasta la mitad y al otro equipo a partir de la segunda mitda 
	//Acá les está faltando la abstracción de equipo o de formación 
 	
	
	public void aceptarEquipos(Partido partido) {
		throw new NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException();
	}
	
}
	
