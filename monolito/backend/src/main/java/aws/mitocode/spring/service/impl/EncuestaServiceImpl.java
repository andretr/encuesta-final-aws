package aws.mitocode.spring.service.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import aws.mitocode.spring.dao.IEncuestaDao;
import aws.mitocode.spring.model.Encuesta;
import aws.mitocode.spring.service.IEncuestaService;
import aws.mitocode.spring.service.INotificacionSNS;

@Service
public class EncuestaServiceImpl implements IEncuestaService {
	
	private Logger logger = Logger.getLogger(EncuestaServiceImpl.class);

	@Autowired
	private IEncuestaDao encuestaDao;
	
	@Autowired
	private INotificacionSNS notificacionSNS;
	
	@Override
	public Page<Encuesta> obtenerDatosPaginados(Pageable pageable, String usuario, Collection<GrantedAuthority> ltaRoles) {
		boolean isAdmin = false;
		if(ltaRoles != null && ltaRoles.size() > 0) {
			for(GrantedAuthority rol : ltaRoles) {
				if("ROLE_ADMIN".equalsIgnoreCase(rol.getAuthority())) {
					isAdmin = true;
					break;
				}
			}
		}
		if(isAdmin) {
			return encuestaDao.obtenerEncuestas(pageable);
		}
		return encuestaDao.obtenerEncuestaPorUsuario(pageable, usuario);
	}

	@Override
	public void guardarDatos(Encuesta encuesta) {
		encuestaDao.save(encuesta);
		try {
			notificacionSNS.enviarNotificacionSubscriptores(encuesta);
		}catch(Exception e) {
			logger.info("Error al enviar datos a la cola");
		}
	}

	@Override
	public void eliminarDatos(int id) {
		encuestaDao.delete(id);
		
	}

}
