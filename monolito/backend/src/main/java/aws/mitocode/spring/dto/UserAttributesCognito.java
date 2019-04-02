package aws.mitocode.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAttributesCognito {

	private String email_verified;
	private String email;

}
