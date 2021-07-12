package com.devcamp.listen.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devcamp.listen.model.Rate;
import com.devcamp.listen.model.User;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
	
	@Query(value = "SELECT * FROM rates WHERE customer_id = :customerId", nativeQuery = true)
	List<Rate> listRateByCustomerId(@Param("customerId") int customerId);
	
	@Query(value = "SELECT * FROM rates WHERE orderdetails_id = :orderdetailId", nativeQuery = true)
	Rate rateByOrderDetailId(@Param("orderdetailId") int orderdetailId);
	
	@Query(value = "SELECT * FROM rates WHERE product_id = :productId", nativeQuery = true)
	List<Rate> listRateByProductId(@Param("productId") int productId);
	

}
