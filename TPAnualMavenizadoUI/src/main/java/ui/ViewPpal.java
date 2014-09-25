package ui;

import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;

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
