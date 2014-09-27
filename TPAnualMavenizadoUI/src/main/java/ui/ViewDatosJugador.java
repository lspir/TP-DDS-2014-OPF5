package ui;

import java.awt.*;

import opf5.jugador.*;

import org.uqbar.arena.layout.*;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.*;
import org.uqbar.arena.windows.*;

/*FIXME si bien arena no tiene grandes herramientas para hacer layouts,
 * tiene el GridLayout, con el que pueden hacer que su formulario se vea un poco mejor 
 * (etiquetas a la izquierda, componentes a la derecha). 
 * 
 * Si bien siempre insistimos en que no es objetivo de la materia reaalizar un diseño visual 
 * "lindo" tal como lo haría un diseñador gráfico, con las herraientas que tienen pueden lograr
 * algo mejor sin mucho esfuerzo. 
 *
 */
public class ViewDatosJugador extends SimpleWindow<ViewModelDatosJugador>{
	
	public ViewDatosJugador(WindowOwner owner, Jugador jugador) {
	    super(owner,new ViewModelDatosJugador(jugador));
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
