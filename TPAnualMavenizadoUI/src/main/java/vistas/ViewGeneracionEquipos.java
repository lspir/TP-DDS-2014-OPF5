package vistas;

import opf5.algoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.excepciones.ElPartidoNoEstaCompleto;
import opf5.excepciones.ElPartidoYaEstaConfirmadoException;
import opf5.excepciones.NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException;
import opf5.jugador.*;

import org.uqbar.arena.actions.*;
import org.uqbar.arena.bindings.*;
import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;
import org.uqbar.lacar.ui.model.*;
import org.uqbar.lacar.ui.model.bindings.*;

import viewModels.ViewModelGeneracion;
import viewModels.ViewModelPpal;

public class ViewGeneracionEquipos extends Vista<ViewModelGeneracion> {

	public ViewGeneracionEquipos(WindowOwner parent) {
		super(parent, new ViewModelGeneracion());

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Generación de Equipos");

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
		new Button(actionsPanel).setCaption("GENERAR").onClick(
				() -> this.generarEquipos());
		new Button(actionsPanel).setCaption("CONFIRMAR").onClick(() -> {this.aceptarEquipos()
			;
			
		});
		Button verDatos = new Button(actionsPanel);
		verDatos.setCaption("Ver Datos de Jugador");
		verDatos.onClick(() -> new ViewDatosJugador(this, this.getModelObject()
				.getJugadorSeleccionado()).open());
		NotNullObservable elementSelected = new NotNullObservable(
				"jugadorSeleccionado");
		verDatos.bindEnabled(elementSelected);

	}

	private void generarEquipos() {
		try{
			this.getModelObject().generacionEquipos();
		}
		//FIXME pueden evitar repetir la lógica , pueden usar pipes en el tipo de la excepción. 
		//Algo así:     catch (ElPartidoYaEstaConfirmadoException|ElPartidoNoEstaCompleto exception){
		//También pueden crear una expceción que generalice a todas las excepciones de partido
		//(es decir, 
		//que sea la excepción padre de todas estas excepciones) , y capturar sólo esa
		catch (ElPartidoYaEstaConfirmadoException exception){
			this.showError(exception.getMessage());			
		}
		catch (ElPartidoNoEstaCompleto exception){
			this.showError(exception.getMessage());
		}
		catch(NullPointerException exception){
			this.showError("Faltan completar campos para poder realizar la generacion de equipos");
		}
		
	}
	
	private void aceptarEquipos() {
		try{
			this.getModelObject().confirmarEquipos();
			new VistaConfirmacionGeneracionEquipos(this).open();
		}
		catch (ElPartidoYaEstaConfirmadoException exception){
			this.showError(exception.getMessage());			
		}
		catch (ElPartidoNoEstaCompleto exception){
			this.showError(exception.getMessage());
		}
		catch (NoSePuedeAceptarEquiposElPartidoNoEstaOrdenadoException exception){
			this.showError(exception.getMessage());
		}
		
	}

	protected void createResultsGrid(Panel mainPanel) {

		this.crearTablaEquipos(mainPanel, "Equipo 1", "equipo1",
				"jugadorSeleccionado");
		this.crearTablaEquipos(mainPanel, "Equipo 2", "equipo2",
				"jugadorSeleccionado");

	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());
	}

	protected void openDialog(Dialog<?> dialog) {
	   //FIXME no es necesario usar messageSend. Con las lambdas resuelven el mismo problema
	   //de una manera tipada estátiamente
		dialog.onAccept(new MessageSend(this.getModelObject(), "generarEquipos"));
		dialog.open();
	}

}