package opf5;

public class Administrador {

	private String direccion;

	public Administrador(String direccion) {
		this.direccion = direccion;
	}
	
	public String direccion()
	{
		return direccion;
	}

	public void penaliza(Jugador jugador) {
		Infraccion infraccion = new Infraccion();
		jugador.tePenalizaron(infraccion);
		
	}

}
