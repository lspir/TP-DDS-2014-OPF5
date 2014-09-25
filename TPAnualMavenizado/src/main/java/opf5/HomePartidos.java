package opf5;

import java.util.*;

import opf5.jugador.*;

public class HomePartidos {
	
		private List<Partido> partidos = new ArrayList<Partido>();

		//FIXME esta forma de crear singletons es engorrosa y no aporta nada. 
		//hagan directamente private static final HomePartidos instance = new HomePartios(), sin la 
		//inicilización lazy (si null inicializar)
		private static HomePartidos instance;
		public static synchronized HomePartidos getInstance() {
			if (instance == null) {
				instance = new HomePartidos();
			}
			return instance;
		}
		public void create(Partido partido) {
			this.partidos.add(partido);
		}

		public void delete(Partido partido) {
			this.partidos.remove(partido);
		}
		
		public long consultarCantidadDePartidosJugados(Jugador jugador){
			return partidos.stream().mapToInt(partido-> partido.jugo(jugador)).sum();
		}
		
		public List<Partido> getData(){
			return partidos;
		}
		

}
