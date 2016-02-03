package com.acquirer.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "antiForgery")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AntiForgery {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "isActive")
	private boolean isActive;
	

	public AntiForgery(String token, boolean isActive) {
		super();
		this.token = token;
		this.isActive = isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public void setStatus(boolean isActive)
	{
		this.isActive = isActive;
	}
	
	
}
