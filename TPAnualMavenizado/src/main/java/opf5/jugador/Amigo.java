package opf5.jugador;

import org.uqbar.commons.utils.Observable;

@Observable
public class Amigo {

	private String direccion;
	
	public Amigo(String direccion)
	{
		this.direccion = direccion; 
	}
	
	public String direccion()
	{
		return this.direccion;
	}
}
