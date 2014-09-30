package vistas;

import java.awt.*;

import opf5.jugador.*;

import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;

import viewModels.ViewModelDatosJugador;

public class ViewDatosJugador extends Vista<ViewModelDatosJugador>{
	
	public ViewDatosJugador(WindowOwner owner, Jugador jugador) {
	    super(owner,new ViewModelDatosJugador(jugador));
	  }

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Datos del Jugador");
		super.createMainTemplate(mainPanel);

	}
	
	@Override
	public void createFormPanel(Panel mainPanel) {
		Panel panel = new Panel(mainPanel);
		panel.setLayout(new ColumnLayout(2));
		this.mostarValor(panel,"Nombre","nombre");
		this.mostarValor(panel, "Handicap:", "handicap");
		this.mostarValor(panel, "Partidos:", "cantidadPartidos");
		this.mostarValor(panel, "Promedio:", "promedio");
		this.mostarValor(panel, "Promedio Último Partido:", "ultimoPromedio");
		new Label(panel).setText("Infracciones:");
	    new Table(panel).bindItemsToProperty("infracciones");
	   }
	
	private void mostarValor(Panel panel, String titulo, String valor) {
		new Label(panel).setText(titulo);
	    new Label(panel).bindValueToProperty(valor);
		
	}


	public void  addActions(Panel mainPanel)
	{
		this.botonVolver(mainPanel);
	}
	
}