package com.devcamp.listen.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name="order_details")
public class OrderDetail  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="price_each")
	private int priceEach;

	@Column(name="quantity_order")
	private int quantityOrder;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Product product;
	
	@OneToOne(mappedBy = "orderdetails")
	private Rate rate;
	
	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public OrderDetail() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPriceEach() {
		return this.priceEach;
	}

	public void setPriceEach(int priceEach) {
		this.priceEach = priceEach;
	}

	public int getQuantityOrder() {
		return this.quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}
	
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}