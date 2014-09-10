package ui;

import java.awt.Color;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.List;

import domain.UnModel;

//IMPORTANTE: correr con -Djava.system.class.loader=com.uqbar.apo.APOClassLoader
public class ViewPpal extends MainWindow<UnViewModelPpal>{


	public ViewPpal() {
		super(new UnViewModelPpal());
	}

	public void createContents(Panel mainPanel) {
		setTitle("OPF 5 ");
		mainPanel.setLayout(new VerticalLayout());
		new Button(mainPanel).setCaption("Generación de Equipos").onClick(
				() -> new ViewGeneracionEquipos(this).open());
		new Button(mainPanel).setCaption("Búsqueda de Jugadores").onClick(
				() -> new ViewBusquedaDeJugadores(this).open());
	}

	public static void main(String[] args) {
		new ViewPpal().startApplication();
	}
}
