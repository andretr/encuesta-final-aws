package aws.mitocode.spring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "encuestas")
@Getter
@Setter
public class Encuesta implements Serializable {

	private static final long serialVersionUID = -1725563866279171711L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@Column(name = "edad", nullable = false)
	private Integer edad;

	@Column(name = "lenguaje", nullable = false)
	private String lenguaje;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "id_usuario", nullable = true)
	private String idUsuario;

}
