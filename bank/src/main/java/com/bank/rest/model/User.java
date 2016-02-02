package com.bank.rest.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "UMCN", unique = true, length = 13)
	private String uniqueMasterCitizenNumber;
	
	@OneToMany(mappedBy = "user")
	private Set<Card> cards;

	

	public User(){}
	
	public User(int id, String firstName, String lastName, String UMCN) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.uniqueMasterCitizenNumber = UMCN;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String surname) {
		this.lastName = surname;
	}

	public String getUniqueMasterCitizenNumber() {
		return uniqueMasterCitizenNumber;
	}

	public void setUniqueMasterCitizenNumber(String uniqueMasterCitizenNumber) {
		this.uniqueMasterCitizenNumber = uniqueMasterCitizenNumber;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}



	
}
