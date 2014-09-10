package opf5.jugador;

public class Infraccion {

	private Object motivo;
	private String momento;
	private int numero;
	
	public Infraccion(String momento, int numero){
		this.momento = momento;
		this.numero  = numero;
	}
	
	public String getMomento()
	{
		return this.momento;
	}
	
	public int getNumero()
	{
		return this.numero;
	}
	
	
}
