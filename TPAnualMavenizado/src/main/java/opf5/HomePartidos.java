package opf5;

import java.util.*;

import opf5.jugador.*;

public class HomePartidos {
	
		private List<Partido> partidos = new ArrayList<Partido>();
		private static final HomePartidos instance=new HomePartidos();

		public static synchronized HomePartidos getInstance() {
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
