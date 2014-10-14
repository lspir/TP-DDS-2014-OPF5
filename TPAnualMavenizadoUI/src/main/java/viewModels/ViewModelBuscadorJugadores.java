package viewModels;
import java.util.*;

import opf5.jugador.*;

import org.uqbar.commons.utils.Observable;

import utilitarios.CriterioBusqueda;
import utilitarios.CriterioBusquedaDesde;
import utilitarios.CriterioBusquedaHasta;
import utilitarios.CriterioBusquedaInfractores;
import utilitarios.CriterioBusquedaInfractoresAbstracta;
import utilitarios.CriterioBusquedaNoInfractores;
import utilitarios.CriterioBusquedaTodos;
@Observable
public class ViewModelBuscadorJugadores {
	
		private String nombre;
		private double edad;
		private double handicap;
		private double promedio;
		private List<Jugador> resultados;
		private Jugador jugadorSeleccionado;
		private CriterioBusqueda criterioBusquedaHandicap,criterioPromedio;
		private List<CriterioBusqueda>criteriosBusquedaDesdeHasta= new ArrayList<CriterioBusqueda>();
		private List<CriterioBusquedaInfractoresAbstracta>criteriosInfractores=new ArrayList<CriterioBusquedaInfractoresAbstracta>();
		private CriterioBusquedaInfractoresAbstracta criterioInfractoresSeleccionado;
		
		public List<CriterioBusqueda> getCriteriosBusquedaDesdeHasta() {
			return criteriosBusquedaDesdeHasta;
		}

		public void setCriteriosBusquedaDesdeHasta(
				List<CriterioBusqueda> criteriosBusquedaDesdeHasta) {
			this.criteriosBusquedaDesdeHasta = criteriosBusquedaDesdeHasta;
		}

		public ViewModelBuscadorJugadores(){
			this.nombre="";
			criteriosBusquedaDesdeHasta.add(new CriterioBusquedaDesde());
			criteriosBusquedaDesdeHasta.add(new CriterioBusquedaHasta());
			criteriosInfractores.add(new CriterioBusquedaInfractores());
			criteriosInfractores.add(new CriterioBusquedaNoInfractores());
			criteriosInfractores.add(new CriterioBusquedaTodos());
			
		}

		public void search() {
			//FIXME no faltan criterios de búsqueda acá?
			this.resultados = RepositorioJugadores.getInstance().search(this.edad, this.nombre,this.handicap, this.criterioBusquedaHandicap,this.promedio,this.criterioPromedio,this.criterioInfractoresSeleccionado);
		}

		public double getEdad() {
			return this.edad;
		}

		public void setEdad(double numero) {
			this.edad= numero;
		}

		public double getHandicap() {
			return this.handicap;
		}

		public void setHandicap(double numero) {
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

		public CriterioBusqueda getCriterioBusquedaHandicap() {
			return criterioBusquedaHandicap;
		}

		public void setCriterioBusquedaHandicap(CriterioBusqueda criterioBusqueda) {
			this.criterioBusquedaHandicap = criterioBusqueda;
		}

		public CriterioBusqueda getCriterioPromedio() {
			return criterioPromedio;
		}

		public void setCriterioPromedio(CriterioBusqueda criterioPromedio) {
			this.criterioPromedio = criterioPromedio;
		}

		public List<CriterioBusquedaInfractoresAbstracta> getCriteriosInfractores() {
			return criteriosInfractores;
		}

		public void setCriteriosInfractores(List<CriterioBusquedaInfractoresAbstracta> criteriosInfractores) {
			this.criteriosInfractores = criteriosInfractores;
		}

		public CriterioBusquedaInfractoresAbstracta getCriterioInfractoresSeleccionado() {
			return criterioInfractoresSeleccionado;
		}

		public void setCriterioInfractoresSeleccionado(
				CriterioBusquedaInfractoresAbstracta criterioInfractoresSeleccionado) {
			this.criterioInfractoresSeleccionado = criterioInfractoresSeleccionado;
		}
		
	}
