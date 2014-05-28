package opf5;

import java.util.List;
import java.util.ArrayList;

public interface MailSender {

	public void notificar(String direccion, Object remitente);
}
