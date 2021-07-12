package com.devcamp.listen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devcamp.listen.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT * FROM orders WHERE customer_id like %:customerId% ORDER BY customer_id DESC", nativeQuery = true)
	List<Order> listOrderByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

	@Query(value = "SELECT * FROM orders WHERE order_date <= :orderDate% ORDER BY order_date DESC", nativeQuery = true)
	List<Order> listOrderByOrderDate(@Param("orderDate") Date orderDate, Pageable pageable);

	@Query(value = "SELECT * FROM orders WHERE customer_id = :customerId ORDER BY customer_id DESC LIMIT 1", nativeQuery = true)
	Order findOrderByCustomerId(@Param("customerId") Long customerId);

	@Query(value = "SELECT DAYNAME(`order_date`),`order_date`, COUNT(od.product_id) AS total_product, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders as o ON o.id = od.order_id GROUP BY DAY(o.order_date)HAVING DAY(order_date) BETWEEN :dateBegin AND :dateEnd", nativeQuery = true)
	List<Object> listOrderbydate(@Param("dateBegin") int dateBegin, @Param("dateEnd") int dateEnd);

	@Query(value = "SELECT WEEK(`order_date`) as eweek, YEAR(`order_date`) AS eyear, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders as o ON o.id = od.order_id GROUP BY WEEK(`order_date`) HAVING eyear = :year", nativeQuery = true)
	List<Object> listOrderbyweek(@Param("year") int year);

	@Query(value = "SELECT DAYNAME(`order_date`) as dayname,`order_date` as orderdate, COUNT(od.product_id) AS totalproduct, SUM(od.price_each * od.quantity_order) AS totalmoney FROM order_details AS od JOIN orders as o ON o.id = od.order_id GROUP BY DAY(o.order_date)HAVING DAY(order_date) BETWEEN :dateBegin AND :dateEnd", nativeQuery = true)
	List<IOrder> listOrderByDateExport(@Param("dateBegin") int dateBegin, @Param("dateEnd") int dateEnd);

	@Query(value = "SELECT WEEK(`order_date`) as eweek, YEAR(`order_date`) AS eyear, SUM(od.price_each * od.quantity_order) AS totalmoney FROM order_details AS od JOIN orders as o ON o.id = od.order_id GROUP BY WEEK(`order_date`) HAVING eyear = :year", nativeQuery = true)
	List<IOrderTotalMoneyWeek> listOrderbyweekExport(@Param("year") int year);

	@Query(value = "SELECT o.id AS orderid, od.id as orderdetailid, o.customer_id AS customerid, p.id  AS productid, p.product_name AS productname, p.product_image AS productimage, od.quantity_order AS quantityorder, od.price_each AS price FROM orders AS o JOIN order_details AS od ON o.id = od.order_id JOIN products AS p ON od.product_id = p.id HAVING customerId = :customerId", nativeQuery = true)
	List<IMyOrder> listMyOrderByCustomerId(@Param("customerId") long customerId);

}
