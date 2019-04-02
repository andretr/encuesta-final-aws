package aws.mitocode.spring.service;

import java.io.IOException;

import aws.mitocode.spring.model.Encuesta;

public interface IColaSQSService {

	public void readJMS(String feedback);
	public void sendDataJMS(Encuesta encuesta) throws IOException;
}
