package vistas;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioOrdenamientoEquipos;
import opf5.jugador.*;

import org.uqbar.arena.actions.*;
import org.uqbar.arena.bindings.*;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;
import org.uqbar.lacar.ui.model.ItemsBindingBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import utilitarios.CriterioBusqueda;
import utilitarios.CriterioBusquedaInfractores;
import viewModels.ViewModelBuscadorJugadores;

public class ViewBusquedaDeJugadores extends Vista<ViewModelBuscadorJugadores> {

	public ViewBusquedaDeJugadores(WindowOwner parent) {
		super(parent, new ViewModelBuscadorJugadores());
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Buscador de Jugadores");
		this.setTaskDescription("Ingrese los parámetros de búsqueda");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
		this.armarForm(searchFormPanel, "Edad Mínima", "edad");
		this.armarForm(searchFormPanel, "Nombre empieza con", "nombre").setWidth(100);
		this.armarForm(searchFormPanel, "Valor Promedio último partido:", "promedio");
		this.armarSelectorDesdeHasta(searchFormPanel, "Criterio Busqueda Promedio", "criterioPromedio");
		this.armarForm(searchFormPanel,"Valor de Handicap", "handicap");
		this.armarSelectorDesdeHasta(searchFormPanel,"Criterio Busqueda Handicap","criterioBusquedaHandicap");
		
		new Label(searchFormPanel).setText("Criterio Infracciones");
		RadioSelector<CriterioBusquedaInfractores> box= new RadioSelector<>(searchFormPanel, "criteriosInfractores");
		box.bindValueToProperty("criterioInfractoresSeleccionado");
		box.allowNull(true);
		Binding<ListBuilder<CriterioBusquedaInfractores>> itemsBinding = box
				.bindItems( 
				new ObservableProperty(this.getModelObject(),
						"criteriosInfractores"));
		
		itemsBinding.setAdapter(new PropertyAdapter(
				CriterioBusquedaInfractores.class, "nombre"));
	}

	private void armarSelectorDesdeHasta(Panel searchFormPanel,String titulo,String bindValue) {
		
		new Label(searchFormPanel).setText(titulo);
		Selector<CriterioBusqueda> selectorOrdenamiento = new Selector<CriterioBusqueda>(
				searchFormPanel)
				.allowNull(true);
		
		selectorOrdenamiento.bindValueToProperty(bindValue);

		Binding<ListBuilder<CriterioBusqueda>> itemsBinding2 = selectorOrdenamiento
				.bindItems( 
				new ObservableProperty(this.getModelObject(),
						"criteriosBusquedaDesdeHasta"));

		itemsBinding2.setAdapter(new PropertyAdapter(
				CriterioBusqueda.class, "nombre"));

	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Buscar")
				.onClick(new MessageSend(this.getModelObject(), "search"))
				.setAsDefault().disableOnError();
				
		Button verDatos = new Button(actionsPanel);
		verDatos.setCaption("Ver Datos");
		verDatos.onClick(()->new ViewDatosJugador(this,this.getModelObject().getJugadorSeleccionado()).open());
		
		
		NotNullObservable elementSelected = new NotNullObservable("jugadorSeleccionado");
		verDatos.bindEnabled(elementSelected);
			
	}
	

	protected void createResultsGrid(Panel mainPanel) {
		this.crearTablaEquipos(mainPanel, "Resultados Busqueda", "resultados","jugadorSeleccionado");
	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());
	}


	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "search"));
		dialog.open();
	}
	
	private TextBox armarForm(Panel panel, String titulo, String valor){
		new Label(panel).setText(titulo);
		TextBox box= new TextBox(panel);
		box.bindValueToProperty(valor);
		box.setWidth(30);
		return box;
	}

}