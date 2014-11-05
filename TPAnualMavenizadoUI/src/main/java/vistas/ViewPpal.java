package vistas;

import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.*;
import org.uqbar.arena.windows.*;

import db.EntityManagerHelper;
import viewModels.ViewModelPpalDecorador;
import viewModels.ViewModelPpalInterfaz;

//IMPORTANTE: correr con -Djava.system.class.loader=com.uqbar.apo.APOClassLoader
public class ViewPpal extends MainWindow<ViewModelPpalInterfaz>{


	public ViewPpal() {
		super(new ViewModelPpalDecorador());
	}

	public void createContents(Panel mainPanel) {
		setTitle("OPF 5 ");
		mainPanel.setLayout(new VerticalLayout());
		new Button(mainPanel).setCaption("Generación de Equipos").onClick(()->this.getModelObject().viewGeneracionEquipos(this));
		new Button(mainPanel).setCaption("Búsqueda de Jugadores").onClick(()->this.getModelObject().viewBusquedaJugadores(this));
	}

	public static void main(String[] args) {
		EntityManagerHelper.beginTransaction();
		new ViewPpal().startApplication();
	}
}
