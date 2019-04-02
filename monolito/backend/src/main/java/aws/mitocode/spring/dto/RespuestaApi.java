package aws.mitocode.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaApi{

	private String status;
	private String accessToken;
	private String idToken;
	private String refreshToken;
	private String sessionId;
	
	private Object body;
	
	public RespuestaApi() {}
	
	public RespuestaApi(String status, Object body) {
		this.status = status;
		this.body = body;
	}
}
