package opf5;

import java.util.List;
import java.util.ArrayList;

public class StubMailSender extends MailSender {

	private static List<Mail> enviados = new ArrayList<Mail>();

	public static void notificar(String direccion) {
		Mail mail = new Mail();
		enviados.add(mail);

	}
	
	public  List<Mail> enviados()
	{
		return enviados;
	}
}
