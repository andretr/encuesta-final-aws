package aws.mitocode.spring.service;

import aws.mitocode.spring.model.Encuesta;

public interface IEmailService {
	
	public boolean sendEmail(Encuesta encuesta);
}
