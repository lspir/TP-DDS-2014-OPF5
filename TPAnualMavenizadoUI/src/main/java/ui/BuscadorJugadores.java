package ui;
import org.uqbar.commons.utils.Observable;
import java.util.List;
import opf5.jugador.*;
@Observable
public class BuscadorJugadores {
	
		private String nombre;
		private int edad;
		private int handicap;
		private List<Jugador> resultados;
		private Jugador jugadorSeleccionado;
		private int handicapDesde, handicapHasta;


		public int getHandicapDesde() {
			return handicapDesde;
		}

		public void setHandicapDesde(int handicapDesde) {
			this.handicapDesde = handicapDesde;
		}

		public int getHandicapHasta() {
			return handicapHasta;
		}

		public void setHandicapHasta(int handicapHasta) {
			this.handicapHasta = handicapHasta;
		}

		public void search() {
			this.resultados = RepositorioJugadores.getInstance().search(this.edad, this.nombre,this.handicapDesde, this.handicapHasta);
		}

		public void clear() {
			this.nombre = "";
			this.edad= 0;
		}

		public Integer getEdad() {
			return this.edad;
		}

		public void setEdad(Integer numero) {
			this.edad= numero;
		}

		public Integer getHandicap() {
			return this.handicap;
		}

		public void setHandicap(Integer numero) {
			this.handicap= numero;
		}
		
		public String getNombre() {
			return this.nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Jugador getJugadorSeleccionado() {
			return this.jugadorSeleccionado ;
		}

		public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
			this.jugadorSeleccionado = jugadorSeleccionado;
		}

		public List<Jugador> getResultados() {
			return this.resultados;
		}

		public void setResultados(List<Jugador> resultados) {
			this.resultados = resultados;
		}
	
}
