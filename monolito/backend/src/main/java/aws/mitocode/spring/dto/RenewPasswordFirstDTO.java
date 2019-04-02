package aws.mitocode.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenewPasswordFirstDTO {

	private String username;
	private String password;
	private String session;

}
