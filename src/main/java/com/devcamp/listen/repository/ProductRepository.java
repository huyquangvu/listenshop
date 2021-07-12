package com.devcamp.listen.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devcamp.listen.model.Customer;
import com.devcamp.listen.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByProductCode(String productCode);

	@Query(value = "SELECT * FROM products WHERE product_name like %:productName% ORDER BY product_name DESC", nativeQuery = true)
	List<Product> listProductByProductName(@Param("productName") String productName);

	@Query(value = "SELECT * FROM products WHERE buy_price like %:buyPrice% ORDER BY buy_price DESC", nativeQuery = true)
	List<Product> listProductByBuyPrice(@Param("buyPrice") int buyPrice);

	@Query(value = "SELECT * FROM products ORDER BY product_name DESC LIMIT 6", nativeQuery = true)
	List<Product> listProductlimit6();
	
	
	// ========================================== all product ================================================
	/**
	 * @param pageable
	 * @return list all product with Paging
	 */
	@Query(value = "SELECT * FROM products", nativeQuery = true)
	List<Product> listProductPageable(Pageable pageable);
	
	@Query(value = "SELECT * FROM products", nativeQuery = true)
	List<Product> listProduct();
	
	// ========================================== product by product line ================================================
	/**
	 * @param productLineId
	 * @param pageable
	 * @return List product by product line id
	 */
	@Query(value = "SELECT * FROM products WHERE product_line_id = :productLineId", nativeQuery = true)
	List<Product> listProductByProductLinePageable(@Param("productLineId") int productLineId, Pageable pageable);
	
	/**
	 * @param productLineId
	 * @return length List product by product line id by controller
	 */
	@Query(value = "SELECT * FROM products WHERE product_line_id = :productLineId", nativeQuery = true)
	List<Product> lengthProductByProductLinePageable(@Param("productLineId") int productLineId);
	
	
	
	// ========================================== product by vendor and product line ================================================
	
	/**
	 * @param productVendor
	 * @param productLineId
	 * @param pageable
	 * @return get list product by vendor and productLineId
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> listProductByVendorAndProductLine(@Param("productVendor") String productVendor, @Param("productLineId") int productLineId, Pageable pageable);
	
	
	/**
	 * @param productVendor
	 * @param productLineId
	 * @param pageable
	 * @return get length product by vendor and productLineId by controller
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> lengthProductByVendorAndProductLine(@Param("productVendor") String productVendor, @Param("productLineId") int productLineId);
	
	
	// ========================================== product by price from 0 to price and product line ================================================
	
	/**
	 * @param buyPrice
	 * @param productLineId
	 * @param pageable
	 * @return get list product by price from 0 to price and productLineId
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price <= :buyPrice AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> listProductByPriceDownAndProductLine(@Param("buyPrice") int buyPrice, @Param("productLineId") int productLineId, Pageable pageable);
	
	/**
	 * @param buyPrice
	 * @param productLineId
	 * @return get length product by price from 0 to price and productLineId ( controller )
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price <= :buyPrice AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> lengthProductByPriceDownAndProductLine(@Param("buyPrice") int buyPrice, @Param("productLineId") int productLineId);
	
	
	// ========================================== product by price to max price and product line ================================================
	
	/**
	 * @param buyPrice
	 * @param productLineId
	 * @param pageable
	 * @return get list product by price to max price and productLineId
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price >= :buyPrice AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> listProductByPriceUpAndProductLine(@Param("buyPrice") int buyPrice,
			@Param("productLineId") int productLineId, Pageable pageable);

	/**
	 * @param buyPrice
	 * @param productLineId
	 * @return get length product by price to max price and productLineId ( controller )
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price >= :buyPrice AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> lengthProductByPriceUpAndProductLine(@Param("buyPrice") int buyPrice,
			@Param("productLineId") int productLineId);
	
	
	
	// ========================================== product by price to max price, vendor and product line ================================================
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param productLineId
	 * @param pageable
	 * @return get list product by price - max price, vendor and productLineId 
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price >= :buyPrice AND product_vendor = :productVendor AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> listProductByPriceUpVendorProductLine(@Param("productVendor") String productVendor, @Param("buyPrice") int buyPrice,
			@Param("productLineId") int productLineId, Pageable pageable);
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param productLineId
	 * @return  get length list product by price - max price, vendor and productLineId (controller)
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price >= :buyPrice AND product_vendor = :productVendor AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> lengthProductByPriceUpVendorProductLine(@Param("productVendor") String productVendor, @Param("buyPrice") int buyPrice,
			@Param("productLineId") int productLineId);
	
	
	// ========================================== product by price from 0 to price, vendor and product line ================================================
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param productLineId
	 * @param pageable
	 * @return get list product by price from 0 to price, vendor and productLineId 
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price <= :buyPrice AND product_vendor = :productVendor AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> listProductByPriceDownVendorProductLine(@Param("productVendor") String productVendor, @Param("buyPrice") int buyPrice,
			@Param("productLineId") int productLineId, Pageable pageable);
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param productLineId
	 * @return  get length list product by price from 0 to price, vendor and productLineId (controller)
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price <= :buyPrice AND product_vendor = :productVendor AND product_line_id = :productLineId", nativeQuery = true)
	List<Product> lengthProductByPriceDownVendorProductLine(@Param("productVendor") String productVendor, @Param("buyPrice") int buyPrice,
			@Param("productLineId") int productLineId);
		
	// ========================================== product by vendor ============================================================================================
	/**
	 * @param productVendor
	 * @param pageable
	 * @return get list product by vendor
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor", nativeQuery = true)
	List<Product> listProductByProductVendor(@Param("productVendor") String productVendor, Pageable pageable);

	/**
	 * @param productVendor
	 * @return get length of list product by vendor by controller
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor", nativeQuery = true)
	List<Product> lenghtProductByProductVendor(@Param("productVendor") String productVendor);

	
	// ========================================== price from 0 to "buyPrice"==================================================================================
	/**
	 * @param buyPrice
	 * @param pageable
	 * @return get list product by price from 0 to "buyPrice"
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price <= :buyPrice", nativeQuery = true)
	List<Product> listProductByBuyPriceDown(@Param("buyPrice") int buyPrice, Pageable pageable);

	/**
	 * @param buyPrice
	 * @return get length of list product by price from 0 to "buyPrice" by controller
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price <= :buyPrice", nativeQuery = true)
	List<Product> lengthProductByBuyPriceDown(@Param("buyPrice") int buyPrice);
	
	
	
	// ========================================== price from price to max price ================================================
	/**
	 * @param buyPrice
	 * @param pageable
	 * @return get list product by price from from price to max price
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price >= :buyPrice", nativeQuery = true)
	List<Product> listProductByBuyPriceUp(@Param("buyPrice") int buyPrice, Pageable pageable);

	/**
	 * @param buyPrice
	 * @return get length of list product by price from from price to max price by controller
	 */
	@Query(value = "SELECT * FROM products WHERE buy_price >= :buyPrice", nativeQuery = true)
	List<Product> lengthProductByBuyPriceUp(@Param("buyPrice") int buyPrice);

	
	// ==========================================vendor and price from price to max price ================================================
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param pageable
	 * @return get list product by vendor and by price from price to max price
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor AND buy_price >= :buyPrice", nativeQuery = true)
	List<Product> listProductByVendorPriceUp(@Param("productVendor") String productVendor,
			@Param("buyPrice") int buyPrice, Pageable pageable);
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @return get length of list product by vendor and by price from price to max price by controller
	 */ 
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor AND buy_price >= :buyPrice", nativeQuery = true)
	List<Product> lengthProductByVendorPriceUp(@Param("productVendor") String productVendor,
			@Param("buyPrice") int buyPrice);
	

	// ==========================================vendor and price from 0 to price ================================================
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param pageable
	 * @return get list product by vendor and by price from 0 to price 
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor AND buy_price <= :buyPrice", nativeQuery = true)
	List<Product> listProductByVendorPriceDown(@Param("productVendor") String productVendor,
			@Param("buyPrice") int buyPrice, Pageable pageable);
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @return get length of list product by vendor and by price from 0 to price by controller
	 */
	@Query(value = "SELECT * FROM products WHERE product_vendor = :productVendor AND buy_price <= :buyPrice", nativeQuery = true)
	List<Product> lengthProductByVendorPriceDown(@Param("productVendor") String productVendor,
			@Param("buyPrice") int buyPrice);

}
