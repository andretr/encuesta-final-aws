package aws.mitocode.spring.controller.api;

import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.model.Encuesta;
import aws.mitocode.spring.service.IEncuestaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/encuesta")
public class ApiEncuestaController {

    private static final Logger logger = LoggerFactory.getLogger(ApiEncuestaController.class);

    @Autowired
    private IEncuestaService encuestaService;

    @GetMapping(value="listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtenerTodos(Pageable pageable){
        try {
            User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new ResponseEntity<>(
                    encuestaService.obtenerDatosPaginados(pageable, usuario.getUsername(), usuario.getAuthorities()), HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error: ",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="registrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> registrarEncuesta(
            @RequestBody Encuesta encuesta){
        try {
            User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            encuesta.setIdUsuario(usuario.getUsername());
            encuestaService.guardarDatos(encuesta);
            return new ResponseEntity<>(new RespuestaApi("OK", ""), HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error: ",e);
            return new ResponseEntity<>((RespuestaApi) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="editar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> editarEncuesta(@PathVariable int id, @RequestBody Encuesta encuesta){
        try {
            User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            encuesta.setIdUsuario(usuario.getUsername());
            encuesta.setId(id);
            encuestaService.guardarDatos(encuesta);
            return new ResponseEntity<>(new RespuestaApi("OK", ""), HttpStatus.OK);
        }catch(Exception e) {
            logger.error("Error: ",e);
            return new ResponseEntity<>((RespuestaApi) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
