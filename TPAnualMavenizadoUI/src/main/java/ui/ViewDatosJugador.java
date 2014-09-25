package ui;

import java.awt.*;

import opf5.jugador.*;

import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;


public class ViewDatosJugador extends SimpleWindow<UnViewModelDatosJugador>{
	
	public ViewDatosJugador(WindowOwner owner, Jugador jugador) {
	    super(owner,new UnViewModelDatosJugador(jugador));
	  }

	@Override
	public void createFormPanel(Panel mainPanel) {
		setTitle("Datos del Jugador");
	    mainPanel.setLayout(new VerticalLayout());
	    new Label(mainPanel).setText("Nombre:");
	    new Label(mainPanel).setBackground(Color.WHITE).bindValueToProperty("nombre");
	    new Label(mainPanel).setText("Handicap:");
	    new Label(mainPanel).setBackground(Color.WHITE).bindValueToProperty("handicap");
	    new Label(mainPanel).setText("Infracciones:");
	    new Table(mainPanel).bindItemsToProperty("infracciones");
	    new Label(mainPanel).setText("Partidos:");
	    new Label(mainPanel).setBackground(Color.WHITE).bindValueToProperty("cantidadPartidos");
	    new Label(mainPanel).setText("Promedio:");
	    new Label(mainPanel).setBackground(Color.WHITE).bindValueToProperty("promedio");
	    new Label(mainPanel).setText("Último Promedio:");
	    new Label(mainPanel).setBackground(Color.WHITE).bindValueToProperty("ultimoPromedio");
	    
	   
	    
	}
	
	public void  addActions(Panel mainPanel)
	{
		new Button(mainPanel).setCaption("Volver").onClick(()->this.close());
	}

}
