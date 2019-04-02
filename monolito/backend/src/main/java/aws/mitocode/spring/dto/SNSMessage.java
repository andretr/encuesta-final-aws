package aws.mitocode.spring.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SNSMessage implements Serializable{

	private static final long serialVersionUID = -5717348123154885515L;

	@JsonProperty("Type")
	private String type;
	@JsonProperty("MessageId")
	private String messageId;
	@JsonProperty("TopicArn")
	private String topicArn;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("Timestamp")
	private String timestamp;
	@JsonProperty("SignatureVersion")
	private String signatureVersion;
	@JsonProperty("Signature")
	private String signature;
	@JsonProperty("SigningCertURL")
	private String signingCertURL;
	@JsonProperty("UnsubscribeURL")
	private String unsubscribeURL;

}
