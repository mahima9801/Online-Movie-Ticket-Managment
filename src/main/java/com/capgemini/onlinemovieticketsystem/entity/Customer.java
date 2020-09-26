package com.capgemini.onlinemovieticketsystem.entity;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Customer")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customerId")
	private String customerId;
	
	@Column(name="customerName")
	private String customerName;
	
	@Column(name="customerPassword")
	private String customerPassword;
	
	@Column(name="customerContact")
	private String customerContact;
	
	
	@ElementCollection
	private List<Ticket> myTickets;
	
	@Column(name="dateOfBirth")
	private LocalDate dateOfBirth;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public List<Ticket> getMyTickets() {
		return myTickets;
	}

	public void setMyTickets(List<Ticket> myTickets) {
		this.myTickets = myTickets;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Customer() {
		
	}

	public Customer(String customerId, String customerName, String customerPassword, String customerContact,
			List<Ticket> myTickets, LocalDate dateOfBirth) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerContact = customerContact;
		this.myTickets = myTickets;
		this.dateOfBirth = dateOfBirth;
	}

		
}
