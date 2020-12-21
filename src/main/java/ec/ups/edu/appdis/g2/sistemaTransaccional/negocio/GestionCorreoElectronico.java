
package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class GestionCorreoElectronico {

	String origen = "alexcristopher96@gmail.com";
	String password = "ced0105315113";

	public void enviarCOrreo(String correo, String asunto, String mensaje) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			Session session = Session.getDefaultInstance(props);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(origen));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
			message.setSubject(asunto);
			message.setText(mensaje );
			Transport trans = session.getTransport("smtp");
			trans.connect(origen, password);
			trans.sendMessage(message, message.getAllRecipients());
			trans.close();
		} catch (Exception e) {
			System.out.println("Error de mesaje evio mail " + e.getMessage());
			e.getStackTrace();
		}
	}

	public void envioMailIngresoValido(Usuario u) {
		String mensaje= "Estimado/a: " +u.getPersona().getNombres() + " "+ u.getPersona().getApellidos()
				+"\nSe realizó un ingreso satisfactorio al Sistema Transaccional UPS"
				+ "\nFecha: "+ new Date();
		enviarCOrreo(u.getPersona().getCorreo(), "Ingreso Válido", mensaje);
	}

	public void envioMailIngresoInValido(Usuario u) {
		String mensaje= "Estimado/a: " +u.getPersona().getNombres() + " "+ u.getPersona().getApellidos()
				+"\nSe realizó un ingreso inválido al Sistema Transaccional UPS"
				+"\nNumero de intentos activos: " +u.getIntentosLogin() + ", se bloqueará la cuenta al culminar los intentos."
				+ "\nFecha: "+ new Date();
		enviarCOrreo(u.getPersona().getCorreo(), "Ingreso Inválido", mensaje);
	}
	
}
