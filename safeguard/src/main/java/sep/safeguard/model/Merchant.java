package sep.safeguard.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "merchant")
public class Merchant {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "identifier")
	private String identifier;
	
	@Column(name ="password")
	private String password;

	@OneToMany(mappedBy ="merchant")
	private Set<MerchantOrder> orders;

	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<MerchantOrder> getOrders() {
		if(orders == null)
			orders = new HashSet<MerchantOrder>();
		return orders;
	}

	public void setOrders(Set<MerchantOrder> orders) {
		this.orders = orders;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
}
