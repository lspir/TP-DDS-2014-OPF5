package ui;

import java.awt.Color;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.widgets.Button;

import opf5.jugador.*;

public class ViewDatosJugador extends MainWindow<UnViewModelDatosJugador>{
	
	public ViewDatosJugador(Jugador jugador) {
	    super(new UnViewModelDatosJugador(jugador));
	  }

	@Override
	public void createContents(Panel mainPanel) {
		setTitle("Datos del Jugador");
	    mainPanel.setLayout(new VerticalLayout());
	    new Label(mainPanel).setText("Nombre:");
	    new Label(mainPanel).setBackground(Color.PINK).bindValueToProperty("nombre");
	    new Label(mainPanel).setText("Handicap:");
	    new Label(mainPanel).setBackground(Color.ORANGE).bindValueToProperty("handicap");
	    new Label(mainPanel).setText("Infracciones:");
	    new Table(mainPanel).bindItemsToProperty("infracciones");
	    new Button(mainPanel).setCaption("Volver").onClick(()->this.close());
	    
	}
	
//	public static void main(String[] args) {
//		Jugador jugador = new Jugador("pepe", 8);
//		Infraccion infraccion = new Infraccion("momento",2);
//		jugador.tePenalizaron(infraccion);
//	new ViewDatosJugador(jugador).startApplication();
//}
}
