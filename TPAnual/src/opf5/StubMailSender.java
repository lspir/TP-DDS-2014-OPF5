package opf5;

import java.util.List;
import java.util.ArrayList;

public class StubMailSender {

	private static List<Mail> enviados = new ArrayList<Mail>();

	public static void notificar(String direccion, Object remitente) {
		Mail mail = new Mail(remitente);
		enviados.add(mail);

	}
	
	public  List<Mail> enviados()
	{
		return enviados;
	}
}
