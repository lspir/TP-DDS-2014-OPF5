package viewModels;
import java.util.*;

import opf5.jugador.*;

import org.uqbar.commons.utils.Observable;
@Observable
public class ViewModelBuscadorJugadores {
	
		private String nombre;
		private int edad;
		private int handicap;
		private double promedio;
		private List<Jugador> resultados;
		private Jugador jugadorSeleccionado;
		private int handicapDesde, handicapHasta, promedioDesde, promedioHasta;
		
		public ViewModelBuscadorJugadores(){
			this.nombre="";			
		}

		public int getPromedioDesde() {
			return promedioDesde;
		}

		public void setPromedioDesde(int promedioDesde) {
			this.promedioDesde = promedioDesde;
		}

		public int getPromedioHasta() {
			return promedioHasta;
		}

		public void setPromedioHasta(int promedioHasta) {
			this.promedioHasta = promedioHasta;
		}

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
			//FIXME no faltan criterios de búsqueda acá?
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

		public double getPromedio() {
			return promedio;
		}

		public void setPromedio(double promedio) {
			this.promedio = promedio;
		}
		
	}
