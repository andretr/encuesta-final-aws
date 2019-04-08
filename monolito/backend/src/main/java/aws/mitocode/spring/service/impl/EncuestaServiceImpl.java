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

@Service
public class EncuestaServiceImpl implements IEncuestaService {
	
	private Logger logger = Logger.getLogger(EncuestaServiceImpl.class);

	@Autowired
	private IEncuestaDao encuestaDao;
	
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
	public Encuesta obtenerEncuestaById(Integer id) {
		return encuestaDao.findById(id);
	}

	@Override
	public void guardarDatos(Encuesta encuesta) {
		encuestaDao.save(encuesta);
	}

}
