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

		//FIXME noten que siempre hacen new Label(...), 
		//setText, new TextBox. Acá hay lógica repetida. 
		//Fíjense de extraer algún componente que represente la idea de untexto + una etiqueta, 
		//útil para armar formularios. Este componente podría ser modelado 
		//con un método o con una clase
		new Label(searchFormPanel).setText("Edad Mínima");
		TextBox boxEdad = new TextBox(searchFormPanel);
		boxEdad.bindValueToProperty("edad");
		boxEdad.setWidth(20);

		new Label(searchFormPanel).setText("Nombre empieza con");
		new TextBox(searchFormPanel).bindValueToProperty("nombre");
		
		new Label(searchFormPanel).setText("Handicap desde");
		new TextBox(searchFormPanel).bindValueToProperty("handicapDesde");
		
		
		new Label(searchFormPanel).setText("Handicap hasta");
		new TextBox(searchFormPanel).bindValueToProperty("handicapHasta");
		
		new Label(searchFormPanel).setText("Promedio último partido desde:");
		new TextBox(searchFormPanel).bindValueToProperty("promedioDesde");
		
		new Label(searchFormPanel).setText("Promedio último partido hasta");
		new TextBox(searchFormPanel).bindValueToProperty("promedioHasta");

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
	
	void algo()
	{
		
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

}