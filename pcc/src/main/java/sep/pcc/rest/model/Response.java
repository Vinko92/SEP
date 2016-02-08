package sep.pcc.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class Response {

	private List<String> message;
	
	private boolean success;
	
	private long acquirerOrderId;
	
	private Date acquirerTimestamp;
	
	private long issuerOrderId;
	
	private Date issuerTimestamp;
	
	public Response(){
		message = new ArrayList<String>();
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> messages) {
		this.message = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void addMessage(String message)
	{
		this.message.add(message);
	}


	public long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Date getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}


	
	
	
}
