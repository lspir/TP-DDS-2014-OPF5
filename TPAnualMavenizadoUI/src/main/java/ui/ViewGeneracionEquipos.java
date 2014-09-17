//package ui;
//
//import org.uqbar.arena.actions.MessageSend;
//import org.uqbar.arena.bindings.ObservableProperty;
//import org.uqbar.arena.bindings.PropertyAdapter;
//import org.uqbar.arena.layout.ColumnLayout;
//import org.uqbar.arena.layout.VerticalLayout;
//import org.uqbar.arena.widgets.Button;
//import org.uqbar.arena.widgets.CheckBox;
//import org.uqbar.arena.widgets.Label;
//import org.uqbar.arena.widgets.Panel;
//import org.uqbar.arena.widgets.TextBox;
//import org.uqbar.arena.widgets.Selector;
//import org.uqbar.arena.widgets.tables.Column;
//import org.uqbar.arena.widgets.tables.Table;
//import org.uqbar.arena.windows.Dialog;
//import org.uqbar.arena.windows.SimpleWindow;
//import org.uqbar.arena.windows.Window;
//import org.uqbar.lacar.ui.model.ListBuilder;
//import org.uqbar.lacar.ui.model.bindings.Binding;
//
//import opf5.AlgoritmosDivisionDeEquipos.*;
//import opf5.criteriosDeOrdenamientoDeEquipos.*;
//import opf5.jugador.Jugador;
//import scala.sys.process.ProcessBuilderImpl.Simple;
//import domain.UnModel;
//
//public class ViewGeneracionEquipos extends SimpleWindow<UnViewModelGeneracion>
//
//{
//
//	public ViewGeneracionEquipos(Window owner) {
//		super(owner, new UnViewModelGeneracion());
//
//	}
//	
//	@Override
//	protected void createMainTemplate(Panel mainPanel) {
//		this.setTitle("Generación de Equipos");
//		this.setTaskDescription("Ingrese los parámetros de búsqueda");
//
//		super.createMainTemplate(mainPanel);
//
////		this.createResultsGrid(mainPanel);
////		this.createGridActions(mainPanel);
//
//	}
//	
//	@Override
//	public void createContents(Panel mainPanel) {
//
//		mainPanel.setLayout(new VerticalLayout());
//		new Label(mainPanel).setText("Criterio Selección");
//		Selector<AlgoritmoDivisionDeEquipos> selectorDivision = new Selector<AlgoritmoDivisionDeEquipos>(
//				mainPanel) //
//				.allowNull(false);
//		selectorDivision.bindValueToProperty("algoritmoDivision");
//
//		Binding<ListBuilder<AlgoritmoDivisionDeEquipos>> itemsBinding = selectorDivision
//				.bindItems( //
//				new ObservableProperty(this.getModelObject(),
//						"algoritmosDivision"));
//
//		itemsBinding.setAdapter(new PropertyAdapter(
//				AlgoritmoDivisionDeEquipos.class, "nombre"));
//
//		new Label(mainPanel).setText("Criterio Ordenamiento");
//		Selector<AlgoritmoDivisionDeEquipos> selectorOrdenamiento = new Selector<AlgoritmoDivisionDeEquipos>(
//				mainPanel) //
//				.allowNull(false);
//		selectorOrdenamiento.bindValueToProperty("criterioOrdenamiento");
//
//		Binding<ListBuilder<AlgoritmoDivisionDeEquipos>> itemsBinding2 = selectorOrdenamiento
//				.bindItems( //
//				new ObservableProperty(this.getModelObject(),
//						"criteriosOrdenamiento"));
//
//		itemsBinding2.setAdapter(new PropertyAdapter(
//				CriterioOrdenamientoEquipos.class, "nombre"));
//		new Label(mainPanel).setText("Equipo 1");
//		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
//		table.setHeigth(200);
//		table.setWidth(450);
//
//		table.bindItemsToProperty("equipo1");
//		table.bindValueToProperty("jugadorSeleccionado");
//		new Label(mainPanel).setText("Equipo 2");
//		Table<Jugador> table2 = new Table<Jugador>(mainPanel, Jugador.class);
//		table2.setHeigth(200);
//		table2.setWidth(450);
//
//		table2.bindItemsToProperty("equipo2");
//		table2.bindValueToProperty("jugadorSeleccionado");
//
//		this.describirResultados(table);
//		this.describirResultados(table2);
//
//		// new Button(mainPanel).setCaption("GENERAR").onClick(
//		// () -> new ViewGeneracionEquipos(this).generarEquipos());
//		
//		new Button(mainPanel).setCaption("CONFIRMAR").onClick(
//				() -> new ViewGeneracionEquipos(this).algo());
//
//	}
//
//	private Object algo() {
//		return null;
//	}
//
//	private void generarEquipos() {
//		new MessageSend(this.getModelObject(), "generacionEquipos");
//	}
//
//	protected void describirResultados(Table<Jugador> table) {
//		new Column<Jugador>(table).setTitle("Nombre").setFixedSize(400)
//				.bindContentsToProperty("nombre");
//
//	}
//
//	@Override
//	protected void addActions(Panel actionsPanel) {
//		new Button(actionsPanel).setCaption("GENERAR")
//		.onClick(new MessageSend(this.getModelObject(), "generacionEquipos"))
//		.setAsDefault().disableOnError();
//		
//	}
//
//	@Override
//	protected void createFormPanel(Panel mainPanel) {
//		Panel searchFormPanel = new Panel(mainPanel);
//		searchFormPanel.setLayout(new ColumnLayout(2));
//
//		new Label(searchFormPanel).setText("Edad Mínima");
//		TextBox boxEdad = new TextBox(searchFormPanel);
//		boxEdad.bindValueToProperty("edad");
//		boxEdad.setWidth(20);
//
//		new Label(searchFormPanel).setText("Nombre empieza con");
//		new TextBox(searchFormPanel).bindValueToProperty("nombre");
//		
//		new Label(searchFormPanel).setText("Handicap desde");
//		new TextBox(searchFormPanel).bindValueToProperty("handicapDesde");
//		
//		new Label(searchFormPanel).setText("Handicap hasta");
//		new TextBox(searchFormPanel).bindValueToProperty("handicapHasta");
//		
//	}
//}


package ui;
import java.awt.Color;

import opf5.AlgoritmosDivisionDeEquipos.AlgoritmoDivisionDeEquipos;
import opf5.criteriosDeOrdenamientoDeEquipos.CriterioOrdenamientoEquipos;
import opf5.jugador.*;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import java.util.List;
import java.util.ArrayList;

public class ViewGeneracionEquipos extends SimpleWindow<UnViewModelGeneracion> {

	public ViewGeneracionEquipos(WindowOwner parent) {
		super(parent, new UnViewModelGeneracion());
		//this.getModelObject().search();
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Generación de Equipos");
	//	this.setTaskDescription("Ingrese los parámetros de búsqueda");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("Criterio Selección");
		Selector<AlgoritmoDivisionDeEquipos> selectorDivision = new Selector<AlgoritmoDivisionDeEquipos>(
				mainPanel) //
				.allowNull(false);
		selectorDivision.bindValueToProperty("algoritmoDivision");

		Binding<ListBuilder<AlgoritmoDivisionDeEquipos>> itemsBinding = selectorDivision
				.bindItems( //
				new ObservableProperty(this.getModelObject(),
						"algoritmosDivision"));

		itemsBinding.setAdapter(new PropertyAdapter(
				AlgoritmoDivisionDeEquipos.class, "nombre"));
		
		new Label(mainPanel).setText("Criterio Ordenamiento");
		Selector<CriterioOrdenamientoEquipos> selectorOrdenamiento = new Selector<CriterioOrdenamientoEquipos>(
				mainPanel) //
				.allowNull(false);
		selectorOrdenamiento.bindValueToProperty("criterioOrdenamiento");

		Binding<ListBuilder<CriterioOrdenamientoEquipos>> itemsBinding2 = selectorOrdenamiento
				.bindItems( //
				new ObservableProperty(this.getModelObject(),
						"criteriosOrdenamiento"));

		itemsBinding2.setAdapter(new PropertyAdapter(
				CriterioOrdenamientoEquipos.class, "nombre"));
		


	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("GENERAR").onClick(() -> new MessageSend(this.getModelObject(), "generacionEquipos"));
		new Button(actionsPanel).setCaption("CONFIRMAR").onClick(()-> new MessageSend(this.getModelObject(), "confirmarEquipos"));
		
//		new Button(actionsPanel).setCaption("Buscar")
//				.onClick(new MessageSend(this.getModelObject(), "search"))
//				.setAsDefault().disableOnError();
//		
//
//		
//		Button verDatos = new Button(actionsPanel);
//		verDatos.setCaption("Ver Datos");
//		verDatos.onClick(()->new ViewDatosJugador(this,this.getModelObject().getJugadorSeleccionado()).open());
//		
//		
//
//		NotNullObservable elementSelected = new NotNullObservable("jugadorSeleccionado");
//		verDatos.bindEnabled(elementSelected);
		
	
	
	}
	
	void algo()
	{
		
	}

	protected void createResultsGrid(Panel mainPanel) {

		new Label(mainPanel).setText("Equipo 1");
		Table<Jugador> tablaEquipo1 = new Table<Jugador>(mainPanel, Jugador.class);
		tablaEquipo1 .setHeigth(200);
		tablaEquipo1 .setWidth(450);
		tablaEquipo1 .bindItemsToProperty("equipo1");
//		tablaEquipo1 .bindValueToProperty("jugadorSeleccionado");
		this.describeResultsGrid(tablaEquipo1);
		
		new Label(mainPanel).setText("Equipo 2");
		Table<Jugador> tablaEquipo2 = new Table<Jugador>(mainPanel, Jugador.class);
		tablaEquipo2 .setHeigth(200);
		tablaEquipo2 .setWidth(450);
		tablaEquipo2 .bindItemsToProperty("equipo2");
	//	tablaEquipo1 .bindValueToProperty("jugadorSeleccionado");
		this.describeResultsGrid(tablaEquipo2);
	}

	protected void describeResultsGrid(Table<Jugador> table) {
		new Column<Jugador>(table)
				//
				.setTitle("Nombre").setFixedSize(400)
				.bindContentsToProperty("nombre");

		
//		Column<Jugador> columnaHandicap= new Column<Jugador>(table);
//		columnaHandicap.setTitle("Handicap");
//		columnaHandicap.setFixedSize(100);
//		columnaHandicap.bindContentsToProperty("handicap");




	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());
	}


	protected void openDialog(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "search"));
		dialog.open();
	}

}