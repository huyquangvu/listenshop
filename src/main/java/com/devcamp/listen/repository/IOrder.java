package com.devcamp.listen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrder {

	public String getDayname();
	
	public String getOrderdate();
	
	public String getTotalproduct();
	
	public String getTotalmoney();
}
