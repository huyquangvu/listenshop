package com.devcamp.listen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.listen.model.ProductLine;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long>{
	ProductLine findByProductLine(String productLine);
}
