package sep.pcc.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name = "bank")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bank {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "pan")
	private String pan;
	
	@Column(name = "serviceUrl")
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getServiceUri() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
		
}
