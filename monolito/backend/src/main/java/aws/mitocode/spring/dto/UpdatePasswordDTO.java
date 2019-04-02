package aws.mitocode.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {

	private String token;
	private String username;
	private String oldPassword;
	private String newPassword;
	private String verificationCode;

}
