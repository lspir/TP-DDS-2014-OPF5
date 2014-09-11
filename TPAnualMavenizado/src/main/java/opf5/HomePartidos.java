package opf5;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import opf5.jugador.Jugador;

public class HomePartidos {
	
		private static HomePartidos instance;
		private List<Partido> partidos = new ArrayList<Partido>();

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
			return partidos.stream().mapToInt(partido-> partido.jugo(jugador)).summaryStatistics().getSum();
		}

}