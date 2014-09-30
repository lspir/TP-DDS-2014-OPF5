package vistas;

import opf5.jugador.Jugador;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import scala.collection.generic.SetFactory;
import viewModels.ViewModelPpal;

public class VistaConfirmacionGeneracionEquipos extends Vista<ViewModelPpal>{
	public VistaConfirmacionGeneracionEquipos(
			ViewGeneracionEquipos viewGeneracionEquipos) {
		super(viewGeneracionEquipos,new ViewModelPpal());
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Confirmacion Equipo");
		super.createMainTemplate(mainPanel);

	}

	@Override
	protected void addActions(Panel actionsPanel) {
		this.botonVolver(actionsPanel);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel panel = new Panel(mainPanel);
		new Label(panel).setText("¡Equipo Confirmado!").setFontSize(15);
		new Label(panel).setText("٩(-̮̮̃-̃)۶").setFontSize(35);
		}

}
