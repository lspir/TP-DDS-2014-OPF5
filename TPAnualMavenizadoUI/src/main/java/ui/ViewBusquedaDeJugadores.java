package ui;
import java.awt.Color;

import opf5.jugador.*;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

public class ViewBusquedaDeJugadores extends SimpleWindow<BuscadorJugadores> {

	public ViewBusquedaDeJugadores(WindowOwner parent) {
		super(parent, new BuscadorJugadores());
		this.getModelObject().search();
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

		new Label(searchFormPanel).setText("Edad").setForeground(Color.BLUE);
		TextBox boxEdad = new TextBox(searchFormPanel);
		boxEdad.bindValueToProperty("edad");
		boxEdad.setWidth(20);

		new Label(searchFormPanel).setText("Nombre").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("nombre");
		
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
	
	void algo()
	{
		
	}

	protected void createResultsGrid(Panel mainPanel) {
		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("jugadorSeleccionado");

		this.describeResultsGrid(table);
	}

	protected void describeResultsGrid(Table<Jugador> table) {
		new Column<Jugador>(table)
				//
				.setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");

		
		Column<Jugador> columnaHandicap= new Column<Jugador>(table);
		columnaHandicap.setTitle("Handicap");
		columnaHandicap.setFixedSize(100);
		columnaHandicap.bindContentsToProperty("handicap");

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