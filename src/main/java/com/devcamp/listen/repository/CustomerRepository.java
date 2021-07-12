package com.devcamp.listen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devcamp.listen.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * tìm customer với tên
	 * 
	 * @param fname
	 * @return 1 customer hoặc danh sách customer
	 */
	@Query(value = "SELECT * FROM customers WHERE first_name like %:fname% ORDER BY first_name DESC", nativeQuery = true)
	List<Customer> findCustomerByFirstNameDesc(@Param("fname") String fname);

	/**
	 * Tìm customer với họ
	 * 
	 * @param lname
	 * @return 1 customer hoặc danh sách customer
	 */
	@Query(value = "SELECT * FROM customers WHERE last_name like %:lname% ORDER BY last_name DESC", nativeQuery = true)
	List<Customer> findCustomerByLastNameDesc(@Param("lname") String lname);

	/**
	 * Tìm customer bằng city
	 * 
	 * @param city
	 * @param pageable
	 * @return danh sách customer
	 */
	@Query(value = "SELECT * FROM customers WHERE city like %:city% ORDER BY city DESC", nativeQuery = true)
	List<Customer> findCustomerByCityLike(@Param("city") String city, Pageable pageable);

	/**
	 * Tìm customer bằng state
	 * 
	 * @param state
	 * @param pageable
	 * @return danh sách customer
	 */
	@Query(value = "SELECT * FROM customers WHERE state like %:state% ORDER BY city DESC", nativeQuery = true)
	List<Customer> findCustomerByStateLike(@Param("state") String state, Pageable pageable);

	/**
	 * Tìm customer bằng country
	 * 
	 * @param country
	 * @param pageable
	 * @return danh sách customer có phân trang
	 */
	@Query(value = "SELECT * FROM customers WHERE country like :country ORDER BY country DESC", nativeQuery = true)
	List<Customer> findCustomerByCountryLike(@Param("country") String country, Pageable pageable);

	/**
	 * 
	 * @return danh sách Customer có country null
	 */
	@Query(value = "SELECT * FROM customers WHERE country IS NULL ORDER BY country DESC", nativeQuery = true)
	List<Customer> getCustomerCountryNullDesc();

	/**
	 * @param country
	 * @return danh sách customer không phân trang
	 */
	@Query(value = "SELECT * FROM customers WHERE country like :country ORDER BY country DESC", nativeQuery = true)
	List<Customer> ListCustomerByCountryLike(@Param("country") String country);

	@Query(value = "SELECT c.last_name, COUNT(od.product_id) AS total_product, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders as o ON o.id = od.order_id JOIN customers AS c ON o.customer_id = c.id GROUP BY o.customer_id HAVING total_money > 100000", nativeQuery = true)
	List<Object> ListCustomerByOrder();

	/**
	 * update những customer có country null
	 * 
	 * @param country
	 * @return
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE customers SET country = :countr WHERE country IS NULL", nativeQuery = true)
	int updateCountry(@Param("countr") String country);

	/**
	 * @param phone
	 * @return customer by phone number
	 */
	@Query(value = "SELECT * FROM customers WHERE phone_number = :phone", nativeQuery = true)
	List<Customer> findCustomerByPhoneNumber(@Param("phone") String phone);
	
	@Query(value="SELECT full_name, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 50000000", nativeQuery = true)
	List<Object> listCustomerPlatinum();
	
	@Query(value="SELECT full_name, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 20000000 AND total_money < 50000000", nativeQuery = true)
	List<Object> listCustomerGold();
	
	@Query(value="SELECT full_name, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 10000000 AND total_money < 20000000", nativeQuery = true)
	List<Object> listCustomerSilver();
	
	@Query(value="SELECT full_name, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 5000000 AND total_money < 10000000", nativeQuery = true)
	List<Object> listCustomerVip();
	
	@Query(value="SELECT full_name, COUNT(o.id) AS total_order FROM orders AS o JOIN customers AS c ON c.id = o.customer_id GROUP BY full_name HAVING total_order = :totalOrder", nativeQuery = true)
	List<Object> listCustomerByTotalOrder(@Param("totalOrder") int totalOrder);
	
	@Query(value="SELECT c.id, c.phone_number, c.address, c.full_name, c.birthday, c.city, c.state, c.last_name, c.first_name, c.email, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 50000000", nativeQuery = true)
	List<Customer> listCustomerPlatinumExport();
	
	@Query(value="SELECT c.id, c.phone_number, c.address, c.full_name, c.birthday, c.city, c.state, c.last_name, c.first_name, c.email, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 20000000 AND total_money < 50000000", nativeQuery = true)
	List<Customer> listCustomerGoldExport();
	
	@Query(value="SELECT c.id, c.phone_number, c.address, c.full_name, c.birthday, c.city, c.state, c.last_name, c.first_name, c.email, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 10000000 AND total_money < 20000000", nativeQuery = true)
	List<Customer> listCustomerSilverExport();
	
	@Query(value="SELECT c.id, c.phone_number, c.address, c.full_name, c.birthday, c.city, c.state, c.last_name, c.first_name, c.email, SUM(od.price_each * od.quantity_order) AS total_money FROM order_details AS od JOIN orders AS o ON o.id = od.order_id JOIN customers AS c on c.id = o.customer_id GROUP BY o.customer_id HAVING total_money > 5000000 AND total_money < 10000000", nativeQuery = true)
	List<Customer> listCustomerVipExport();
	
	@Query(value="SELECT c.id, c.phone_number, c.address, c.full_name, c.birthday, c.city, c.state, c.last_name, c.first_name, c.email, COUNT(o.id) AS total_order FROM orders AS o JOIN customers AS c ON c.id = o.customer_id GROUP BY full_name HAVING total_order = :totalOrder", nativeQuery = true)
	List<Customer> listCustomerByTotalOrderExport(@Param("totalOrder") int totalOrder);
}
