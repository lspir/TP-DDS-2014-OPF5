package opf5;

import java.util.List;
import java.util.ArrayList;

public interface MailSender {

	//FIXME les parece una interfaz razonable para un mail sender que solo le pase un string?
	//Es decir, cuando quiero enviar un mail, ¿tan solo digo "hola"?
	void notificar(String string);

	
}
