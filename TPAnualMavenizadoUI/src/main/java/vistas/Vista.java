package vistas;

import java.awt.Color;

import opf5.jugador.Jugador;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.uqbar.commons.collections.Transformer;

//FIXME nombre bastante genérico, pero no tan generico como los métodos que tiene
//todas las vistas pueden crear una tabla de equipos? todas las vistas tienen una tabla de jugadores?
//no me cierra la abstracción. Suena a que derivará en un refused bequest
public abstract class Vista<ViewModel> extends SimpleWindow<ViewModel> {

	public Vista(WindowOwner parent, ViewModel model) {
		super(parent, model);
	}

	public void crearTablaEquipos(Panel mainPanel, String titulo, String items,
			String value) {
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
		this.armarColumnaPreguntandoPorHandicap(table, "Nombre", 150, "nombre");
		this.armarColumnaPreguntandoPorHandicap(table,"Handicap",100,"handicap");
		this.armarColumnaPreguntandoPorHandicap(table, "Promedio General", 150, "promedio");
	}
	
	public void botonVolver(Panel mainPanel){
		new Button(mainPanel).setCaption("Volver").onClick(()->this.close());
	}
	
	private void armarColumnaPreguntandoPorHandicap(Table<Jugador> table,String titulo,int tamanio,String contenido){
		Column<Jugador> columna = new Column<Jugador>(table);
		columna.setTitle(titulo);
		columna.setFixedSize(tamanio);
		columna.bindContentsToProperty(contenido);
		columna.bindBackground("handicap", new Transformer<Integer, Color>() {

			@Override
			public Color transform(Integer handicap) {
				return handicap>8?Color.BLUE:Color.WHITE;
			}
		});
	}
}
