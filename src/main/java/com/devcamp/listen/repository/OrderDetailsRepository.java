package com.devcamp.listen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devcamp.listen.model.OrderDetail;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long>{
	@Query(value="SELECT * FROM order_details WHERE order_id like %:orderId% ORDER BY order_id DESC", nativeQuery = true)
	List<OrderDetail> listOrderDetailByOrderId(@Param("orderId") Long orderId);
	
	@Query(value="SELECT * FROM order_details WHERE product_id like %:productId% ORDER BY product_id DESC", nativeQuery = true)
	List<OrderDetail> listOrderDetailProductId(@Param("productId") Long productId);
}
