package com.devcamp.listen.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="rates")
public class Rate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="rate_score")
	private int rateScore;
	
	@Column(name="comments")
	private String comments;
			
	@ManyToOne
    private Customer customer;
	
	@OneToOne(optional = false)
    @JoinColumn(name = "orderdetails_id")
    private OrderDetail orderdetails;
	
	@ManyToOne
	@JsonIgnore
	private Product product;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = true)
    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updateDate;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Rate() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRateScore() {
		return rateScore;
	}

	public void setRateScore(int rateScore) {
		this.rateScore = rateScore;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@JsonIgnore
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderDetail getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(OrderDetail orderdetails) {
		this.orderdetails = orderdetails;
	}
	
	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

}