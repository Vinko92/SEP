package sep.safeguard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "acquirerOrder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcquirerOrder {

	
	@Id
	@GeneratedValue
	public long orderId;
	
	@Column(name = "timestamp")
	public Date timestamp;
	
	@Column(name ="pan")
	private String pan;

	@Column(name ="securityCode")
	private String securityCode;

	@Column(name ="cardHolder")
	private String cardHolder;

	@Column(name ="expirationDate")
	private Date validTo;
	
	@Column(name = "amount")
	private float amount;

	public long getId() {
		return orderId;
	}

	public void setId(long id) {
		this.orderId = orderId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
}
