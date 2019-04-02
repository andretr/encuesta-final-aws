package aws.mitocode.spring.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import aws.mitocode.spring.model.Encuesta;
import aws.mitocode.spring.service.INotificacionSNS;

@Service
public class NotificacionSNSServiceImpl implements INotificacionSNS {
	
	private Logger logger = Logger.getLogger(NotificacionSNSServiceImpl.class);

	//private static final String ARN_TOPICO_PROCESA_ENCUESTA = "arn:aws:sns:us-east-1:123456789012:procesarFeedBack";

	@Value("${topico.aws.sns}")
	private String ARN_TOPICO_PROCESA_ENCUESTA;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private AmazonSNSClient servicioSNS;

	public void enviarNotificacionSubscriptores(Encuesta encuesta) {
		try {
			PublishRequest publishRequest = new PublishRequest(ARN_TOPICO_PROCESA_ENCUESTA,
					mapper.writeValueAsString(encuesta));
			
			System.out.println("ARN_TOPICO_PROCESA_ENCUESTA: " + ARN_TOPICO_PROCESA_ENCUESTA);
			PublishResult publishResult = servicioSNS.publish(publishRequest);
			
			logger.info("MessageId - " + publishResult.getMessageId());
		} catch (Exception e) {
			logger.error("Error al enviar mensaje a SNS");
		}
	}
}
