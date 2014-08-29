package opf5.criteriosDeOrdenamientoDeEquipos;


import opf5.*;
//FIXME expresividad: Handicap me suena a que es una clase que modela al handicp, 
//no al criterio de handicap
public class Handicap implements Criterio {

	public double funcion(Jugador jugador) {
		return jugador.handicap();
	}

}
