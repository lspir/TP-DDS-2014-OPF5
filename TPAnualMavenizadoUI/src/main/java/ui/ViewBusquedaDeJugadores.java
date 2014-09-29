package ui;
import opf5.jugador.*;

import org.uqbar.arena.actions.*;
import org.uqbar.arena.bindings.*;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;

public class ViewBusquedaDeJugadores extends Vista<BuscadorJugadores> {

	public ViewBusquedaDeJugadores(WindowOwner parent) {
		super(parent, new BuscadorJugadores());
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
		this.armarForm(searchFormPanel, "Handicap desde", "handicapDesde");
		this.armarForm(searchFormPanel, "Handicap hasta", "handicapHasta");
		this.armarForm(searchFormPanel, "Promedio último partido desde:", "promedioDesde");
		this.armarForm(searchFormPanel, "Promedio último partido hasta", "promedioHasta");
		
		//TODO sólo los que tuvieron infracciones, sólo los que no tuvieron infracciones
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