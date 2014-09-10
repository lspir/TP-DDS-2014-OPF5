package ui;

import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Window;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import opf5.AlgoritmosDivisionDeEquipos.*;
import opf5.criteriosDeOrdenamientoDeEquipos.*;
import opf5.jugador.Jugador;
import domain.UnModel;

public class ViewGeneracionEquipos extends Window<UnViewModelGeneracion>

{

	public ViewGeneracionEquipos(Window owner) {
		super(owner, new UnViewModelGeneracion());

	}

	public void createContents(Panel mainPanel) {

		setTitle("Generación de Equipos");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("Criterio Selección");
		Selector<AlgoritmoDivisionDeEquipos> selectorDivision = new Selector<AlgoritmoDivisionDeEquipos>(
				mainPanel) //
				.allowNull(false);
		selectorDivision.bindValueToProperty("algoritmoDivision");

		Binding<ListBuilder<AlgoritmoDivisionDeEquipos>> itemsBinding = selectorDivision
				.bindItems( //
				new ObservableProperty(this.getModelObject(),
						"algoritmosDivision"));

		 itemsBinding.setAdapter(new
		 PropertyAdapter(AlgoritmoDivisionDeEquipos.class, "nombre")); //nombre de la interfaz? 

		new Label(mainPanel).setText("Criterio Ordenamiento");
		Selector<AlgoritmoDivisionDeEquipos> selectorOrdenamiento = new Selector<AlgoritmoDivisionDeEquipos>(
				mainPanel) //
				.allowNull(false);
		selectorOrdenamiento.bindValueToProperty("criterioOrdenamiento");

		Binding<ListBuilder<AlgoritmoDivisionDeEquipos>> itemsBinding2 = selectorOrdenamiento
				.bindItems( //
				new ObservableProperty(this.getModelObject(),
						"criteriosOrdenamiento"));

		 itemsBinding2.setAdapter(new
		 PropertyAdapter(CriterioOrdenamientoEquipos.class, "nombre"));
		new Label(mainPanel).setText("Equipo 1");
		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("equipo1");
		table.bindValueToProperty("jugadorSeleccionado");
		new Label(mainPanel).setText("Equipo 2");
		Table<Jugador> table2 = new Table<Jugador>(mainPanel, Jugador.class);
		table2.setHeigth(200);
		table2.setWidth(450);

		table2.bindItemsToProperty("equipo2");
		table2.bindValueToProperty("jugadorSeleccionado");
		
		this.describirResultados(table);
		this.describirResultados(table2);

		new Button(mainPanel).setCaption("GENERAR").onClick(
				() -> new ViewGeneracionEquipos(this).algo());
		new Button(mainPanel).setCaption("CONFIRMAR").onClick(
				() -> new ViewGeneracionEquipos(this).algo());

	}

	private Object algo() {
		return null;
	}

	protected void describirResultados(Table<Jugador> table) {
		new Column<Jugador>(table)
				.setTitle("Nombre").setFixedSize(400)
				.bindContentsToProperty("nombre");

	}
}