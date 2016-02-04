package sep.pcc.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "payment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name ="amount")
	private double amount;
	
	@Column(name ="pan")
	private String PAN;
	
	@Column(name ="cardHolder")
	private String cardHolder;
	
	@Column(name ="validTo")
	private String validTo;
	
	@Column(name ="securityCode")
	private String securityCode;
	
	@Column(name = "timestamp", unique = true)
	private Date timestamp;
	
	@Column(name = "orderId", unique = true)
	private long orderId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	

}
