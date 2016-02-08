package sep.safeguard.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "antiforgeryToken")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AntiforgeryToken implements Serializable{

	public AntiforgeryToken(String value, String id)
	{
		this.value = value;
		this.merchantIdentifier = id;
	}
	@Id
	private String value;
	@Id
	private String merchantIdentifier;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMerchantIdentifier() {
		return merchantIdentifier;
	}
	public void setMerchantIdentifier(String merchantIdentifier) {
		this.merchantIdentifier = merchantIdentifier;
	}
	
	
	
}
