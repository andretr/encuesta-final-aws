package aws.mitocode.spring.service;

import aws.mitocode.spring.model.Encuesta;

public interface INotificacionSNS {

	public void enviarNotificacionSubscriptores(Encuesta encuesta);
}
