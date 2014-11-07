package opf5.partido;

import java.util.*;

import db.EntityManagerHelper;
import static db.EntityManagerHelper.*;
import opf5.jugador.*;

public class HomePartidos {
	
		private static final HomePartidos instance=new HomePartidos();

		public static HomePartidos getInstance() {
			return instance;
		}
		public void create(Partido partido) {
			persist(partido);
		}

		public void delete(Partido partido) {
			entityManager().remove(partido);
		}
		
		public long consultarCantidadDePartidosJugados(Jugador jugador){
			return this.getPartidos().stream().mapToInt(partido-> partido.jugo(jugador)).sum();
		}
		
		public List<Partido> getPartidos(){
			return entityManager().unwrap(org.hibernate.Session.class).createCriteria(Partido.class).list();
		}
		
		public List<Partido> getPartidosOrdenados(){
			List<Partido> partidosAOrdenar=this.getPartidos();
			Collections.sort(partidosAOrdenar,new Comparator<Partido>() {
				public int compare(Partido p1, Partido p2) {
				    return Long.compare(p1.getId(), p2.getId());
				}
				});
			return partidosAOrdenar;
		}
		

}
