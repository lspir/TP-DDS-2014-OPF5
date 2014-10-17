package refactor.ordenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import refactor.Jugador;
import refactor.Partido;

public class OrdenamientoCalificacionUltimos2Partidos extends CriterioOrdenamiento {
	
	
	public Double calcularValor(Jugador jugador) {
		List<Double> puntajes=jugador.getPuntajes();
		List<Double> misPuntajes=new ArrayList<Double>(); 
		if(!puntajes.isEmpty()){
			misPuntajes.add(jugador.getPuntajes().get(puntajes.size()-1));
		}
		if(puntajes.size()>1){
			misPuntajes.add(jugador.getPuntajes().get(puntajes.size()-2));
		}
		
		Double promedio=0d;
		for (Double puntaje : misPuntajes) {
			promedio+=puntaje;
		}
		promedio/=misPuntajes.size();
		return promedio;
	}
	
}
