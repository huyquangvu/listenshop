package com.devcamp.listen.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.LastModifiedDate;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="email")
	private String email;
	
	//@NotNull(message = "Phải có firstName")
	@Column(name="first_name")
	private String firstName;

	//@NotNull(message = "Phải có lastName")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message = "Phải có fullName")
	@Column(name="full_name")
	private String fullName;

	@NotNull(message = "Phải có phoneNumber")
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="state")
	private String state;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birthday")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date birthday;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
	
	@OneToMany(mappedBy="customer", targetEntity = Rate.class)
	private List<Rate> rates;
	
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Customer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}