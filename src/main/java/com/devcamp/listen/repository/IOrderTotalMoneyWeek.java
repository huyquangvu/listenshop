package com.devcamp.listen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderTotalMoneyWeek {
	public String getEweek();
	
	public String getEyear();
	
	public String getTotalmoney();
	
}
