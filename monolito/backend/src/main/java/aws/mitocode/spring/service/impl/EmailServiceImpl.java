package aws.mitocode.spring.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import aws.mitocode.spring.model.Encuesta;
import aws.mitocode.spring.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService{
	
	private Logger logger = Logger.getLogger(EmailServiceImpl.class);

	private static final String CODIFICACION_UTF_8 = "UTF-8";
	
	//Este correo debe existir en AWS SES y debe estar verificado, caso contrario, aws nos devolverá un error
	static final String FROM = "andre.ticona.r@gmail.com";
	
	//Este correo debe existir en AWS SES y debe estar verificado, caso contrario, aws nos devolverá un error
	static final String TO = "aticona@mc4.com.bo";
	
	static final String SUBJECT = "Notificación Encuesta";
	  
	static final String HTMLBODY = "<h1>Nueva Respuesta</h1><p>%s | %s</p><br><h4>Recibido de: %s</h4>";

	static final String TEXTBODY = "Nuevo Respuesta: %s | %s. Recibido de %s";
    
    @Autowired
    private AmazonSimpleEmailService emailAwsService;
    
    

    public boolean sendEmail(Encuesta encuesta) {
    	try {
    		//TODO review email
    		String mensajeCorreoHTML = String.format(HTMLBODY, 
//    				encuesta.getProblema().getDescripcion(),
//    				encuesta.getMensaje(),
    				encuesta.getIdUsuario());
    		String mensajeCorreoTEXT = String.format(TEXTBODY,
//    				encuesta.getProblema().getDescripcion(),
//    				encuesta.getMensaje(),
    				encuesta.getIdUsuario());
    		SendEmailRequest request = new SendEmailRequest()
    		          .withDestination(
    		              new Destination().withToAddresses(TO))
    		          .withMessage(new Message()
    		              .withBody(new Body()
    		                  .withHtml(new Content()
    		                      .withCharset(CODIFICACION_UTF_8).withData(mensajeCorreoHTML))
    		                  .withText(new Content()
    		                      .withCharset(CODIFICACION_UTF_8).withData(mensajeCorreoTEXT)))
    		              .withSubject(new Content()
    		                  .withCharset(CODIFICACION_UTF_8).withData(SUBJECT)))
    		          .withSource(FROM);
    		emailAwsService.sendEmail(request);
    		return true;
    	}catch(Exception e) {
    		logger.error("Error al enviar email", e);
    	}
    	return false;
    }
    
}
