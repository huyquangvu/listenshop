package com.devcamp.listen.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="orders")
public class Order  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="comments")
	private String comments;

	@NotNull(message = "Phải có orderDate")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date orderDate;

	@NotNull(message = "Phải có requiredDate")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="required_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date requiredDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="shipped_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date shippedDate;
	
	@Column(name="status")
	private String status;

	@ManyToOne
	private Customer customer;

	public Order() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return this.requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return this.shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}