package opf5;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import opf5.inscripcion.Inscripcion;

import org.junit.Test;

public class TestOPF5_MailSender extends TestOPF5{
	
	@Test
	public void UnJugadorCon2AmigosSeInscribeYSeEnvia1MailACadaAmigo() {
		partidoCon1Estandar.intentarInscribirA(new Inscripcion(jugadorConAmigos, estandar));
		verify(adaptadorMailSenderJugador, times(2)).notificar(any(String.class));
	}

	@Test
	public void TresPartidosSeLlenaronYSeEnvianTresMailsAlAdministrador() {
		verify(adaptadorMailSenderAdmin, times(3)).notificar("admin@admin.com.ar");

	}

	@Test
	public void UnJugadorSeBajaConReemplazanteEntoncesALosAmigosDelReemplazanteLesLlegaUnMail() {
		partidoCon1Estandar.seDioDeBajaConReemplazante(inscripcionEstandar, jugadorConAmigos, estandar);
		verify(adaptadorMailSenderJugador, times(jugadorConAmigos.amigos().size())).notificar(
				any(String.class));
	}

}
