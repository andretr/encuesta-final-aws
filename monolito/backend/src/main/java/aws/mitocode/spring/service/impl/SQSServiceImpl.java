package aws.mitocode.spring.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import aws.mitocode.spring.dto.SNSMessage;
import aws.mitocode.spring.model.Encuesta;
import aws.mitocode.spring.service.IColaSQSService;
import aws.mitocode.spring.service.IEmailService;

@Service
public class SQSServiceImpl implements IColaSQSService {

	private Logger logger = Logger.getLogger(SQSServiceImpl.class);
	
	// @Autowired
    // private JmsTemplate jmsTemplate;
	
	@Autowired
    private ObjectMapper mapper;
	
	@Autowired
	private IEmailService emailService;
	
	//Nombre de la cola SQS configurado en AWS
	@JmsListener(destination = "correo-curso-jaws")
    public void readJMS(String json) {
    	try {
    		logger.info("JSON SQS: "+json);
    		
    		SNSMessage mensajeSNS = mapper.readValue(json, SNSMessage.class);
    		
    		Encuesta encuesta = mapper.readValue(mensajeSNS.getMessage(), Encuesta.class);
    		
    		logger.info("Received " + encuesta.getIdUsuario() + " | "+ encuesta.getLenguaje());
    		
    		if(emailService.sendEmail(encuesta)) {
    			logger.info("Correo enviado");
    		}else {
    			logger.error("No se pudo enviar el correo");
    		}
    	}catch(Exception e) {
    		logger.info("Error al leer json", e);
    	}
    }
    
    public void sendDataJMS(Encuesta encuesta) throws IOException {
    	//jmsTemplate.convertAndSend("colaEnvioFeedBack", mapper.writeValueAsString(feedBack));
    }
}
