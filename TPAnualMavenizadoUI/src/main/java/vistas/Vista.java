package vistas;

import opf5.jugador.Jugador;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

public abstract class Vista<ViewModel> extends SimpleWindow<ViewModel> {

	public Vista(WindowOwner parent, ViewModel model) {
		super(parent, model);
	}

	public void crearTablaEquipos(Panel mainPanel, String titulo, String items,
			String value) {
		// FIXME además, en ambos casos, no veo implementada en ninguna de las
		// dos ventanas
		// la funcionalidad de cambiar el color de la fila según el handicap
		new Label(mainPanel).setText(titulo);
		Table<Jugador> tablaEquipo = new Table<Jugador>(mainPanel,
				Jugador.class);
		tablaEquipo.setHeigth(200);
		tablaEquipo.setWidth(450);
		tablaEquipo.bindItemsToProperty(items);
		tablaEquipo.bindValueToProperty(value);
		this.describeResultsGridJugadores(tablaEquipo);
	}

	public void describeResultsGridJugadores(Table<Jugador> table) {

		new Column<Jugador>(table).setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");

		Column<Jugador> columnaHandicap = new Column<Jugador>(table);
		columnaHandicap.setTitle("Handicap");
		columnaHandicap.setFixedSize(100);
		columnaHandicap.bindContentsToProperty("handicap");

		Column<Jugador> columnaPromedio = new Column<Jugador>(table);
		columnaPromedio.setTitle("Promedio General");
		columnaPromedio.setFixedSize(150);
		columnaPromedio.bindContentsToProperty("promedio");

	}
	public void botonVolver(Panel mainPanel){
		new Button(mainPanel).setCaption("Volver").onClick(()->this.close());
	}
}
