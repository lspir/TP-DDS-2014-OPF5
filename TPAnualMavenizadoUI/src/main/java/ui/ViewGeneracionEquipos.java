//FIXME no comenten código!
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
import opf5.AlgoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.jugador.*;

import org.uqbar.arena.actions.*;
import org.uqbar.arena.bindings.*;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;
import org.uqbar.lacar.ui.model.*;
import org.uqbar.lacar.ui.model.bindings.*;


/*
FIXME me genera este error cuando presiono el botón generar equipos, con división por pares
y ordenando por calificaiciones. Además, no genera nada y muestra un diálogo de error. 


 java.lang.ArrayIndexOutOfBoundsException: -1
	at java.util.ArrayList.elementData(ArrayList.java:403)
	at java.util.ArrayList.get(ArrayList.java:416)
	at opf5.criteriosDeOrdenamientoDeEquipos.PromedioDeUltimoPartido.obtenerListaDeCriticasUltimoPartido(PromedioDeUltimoPartido.java:35)
	at opf5.criteriosDeOrdenamientoDeEquipos.PromedioDeUltimoPartido.ponderate(PromedioDeUltimoPartido.java:22)
	at opf5.estadoPartido.NoConfirmado.lambda$1(NoConfirmado.java:40)
	at opf5.estadoPartido.NoConfirmado$$Lambda$11/1505964934.apply(Unknown Source)
	at java.util.Comparator.lambda$comparing$77a9974f$1(Comparator.java:469)
	at java.util.Comparator$$Lambda$12/1906128360.compare(Unknown Source)
	at java.util.TimSort.countRunAndMakeAscending(TimSort.java:351)
	at java.util.TimSort.sort(TimSort.java:216)
	at java.util.Arrays.sort(Arrays.java:1507)
	at java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:302)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:502)
	at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
	at opf5.estadoPartido.NoConfirmado.armarEquipos(NoConfirmado.java:41)
	at opf5.Partido.armarEquipos(Partido.java:149)
	at ui.UnViewModelGeneracion.generacionEquipos(UnViewModelGeneracion.java:107)
	at ui.ViewGeneracionEquipos.lambda$0(ViewGeneracionEquipos.java:223)
	at ui.ViewGeneracionEquipos$$Lambda$8/1217741734.execute(Unknown Source)
	at org.uqbar.lacar.ui.impl.jface.actions.JFaceActionAdapter.widgetSelected(JFaceActionAdapter.java:42)
	at org.eclipse.swt.widgets.TypedListener.handleEvent(TypedListener.java:228)
	at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:84)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1176)
	at org.eclipse.swt.widgets.Display.runDeferredEvents(Display.java:3493)
	at org.eclipse.swt.widgets.Display.readAndDispatch(Display.java:3112)
	at org.eclipse.jface.window.Window.runEventLoop(Window.java:825)
	at org.eclipse.jface.window.Window.open(Window.java:801)
	at org.uqbar.lacar.ui.impl.jface.windows.JFaceWindowBuilder.open(JFaceWindowBuilder.java:110)
	at org.uqbar.arena.windows.Window.open(Window.java:147)
	at ui.ViewPpal.lambda$0(ViewPpal.java:19)
	at ui.ViewPpal$$Lambda$6/404648734.execute(Unknown Source)
	at org.uqbar.lacar.ui.impl.jface.actions.JFaceActionAdapter.widgetSelected(JFaceActionAdapter.java:42)
	at org.eclipse.swt.widgets.TypedListener.handleEvent(TypedListener.java:228)
	at org.eclipse.swt.widgets.EventTable.sendEvent(EventTable.java:84)
	at org.eclipse.swt.widgets.Widget.sendEvent(Widget.java:1176)
	at org.eclipse.swt.widgets.Display.runDeferredEvents(Display.java:3493)
	at org.eclipse.swt.widgets.Display.readAndDispatch(Display.java:3112)
	at org.eclipse.jface.window.Window.runEventLoop(Window.java:825)
	at org.eclipse.jface.window.Window.open(Window.java:801)
	at org.uqbar.lacar.ui.impl.jface.windows.JFaceWindowBuilder.open(JFaceWindowBuilder.java:110)
	at org.uqbar.arena.windows.Window.open(Window.java:147)
	at org.uqbar.arena.Application.run(Application.java:74)
	at org.eclipse.core.databinding.observable.Realm.runWithDefault(Realm.java:288)
	at org.uqbar.lacar.ui.impl.jface.JFaceApplicationBuilder.run(JFaceApplicationBuilder.java:25)
	at org.uqbar.arena.Application.start(Application.java:55)
	at org.uqbar.arena.windows.MainWindow.startApplication(MainWindow.java:30)
	at ui.ViewPpal.main(ViewPpal.java:25)
  
  
  
 */
public class ViewGeneracionEquipos extends SimpleWindow<UnViewModelGeneracion> {

	public ViewGeneracionEquipos(WindowOwner parent) {
		super(parent, new UnViewModelGeneracion());
		
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
		new Button(actionsPanel).setCaption("GENERAR").onClick(() -> this.getModelObject().generacionEquipos());
		//FIXME esto parece estar funcionando, pero 
		//1. No hay ningún feedback visual de que la acción se haya ejecutado. Le doy click y no sé si funcionó o no
		//2. Si confirmo dos veces un partido debería fallar e informar que ya está cofirmado, o al menos, indicar visualizar de alguna forma
		//que ya lo está, así el usuario es consciente de que va a confirmar un partido ya confirmado. 
		new Button(actionsPanel).setCaption("CONFIRMAR").onClick(()-> this.getModelObject().confirmarEquipos());
		
		Button verDatos = new Button(actionsPanel);
		verDatos.setCaption("Ver Datos de Jugador");
		verDatos.onClick(()->new ViewDatosJugador(this,this.getModelObject().getJugadorSeleccionado()).open());
		NotNullObservable elementSelected = new NotNullObservable("jugadorSeleccionado");
		verDatos.bindEnabled(elementSelected);
//FIXME no comenten código!
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
	
	//FIXME WTF?
	void algo()
	{
		
	}

	protected void createResultsGrid(Panel mainPanel) {

		this.crearTablaEquipos("Equipo 1", "equipo1", mainPanel);
		this.crearTablaEquipos("Equipo 2", "equipo2", mainPanel);
		
	}
	private void crearTablaEquipos(String titulo,String equipo, Panel mainPanel){
		new Label(mainPanel).setText(titulo);
		Table<Jugador> tablaEquipo = new Table<Jugador>(mainPanel, Jugador.class);
		tablaEquipo .setHeigth(200);
		tablaEquipo .setWidth(450);
		tablaEquipo .bindItemsToProperty(equipo);
		tablaEquipo .bindValueToProperty("jugadorSeleccionado");
		this.describeResultsGrid(tablaEquipo);
	}
	protected void describeResultsGrid(Table<Jugador> table) {
		new Column<Jugador>(table)
				//
				.setTitle("Nombre").setFixedSize(400)
				.bindContentsToProperty("nombre");

//FIXME no comenten código		
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
		dialog.onAccept(new MessageSend(this.getModelObject(), "generarEquipos"));
		dialog.open();
	}

}