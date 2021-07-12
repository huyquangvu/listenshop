package com.devcamp.listen.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.listen.model.Product;
import com.devcamp.listen.model.ProductLine;
import com.devcamp.listen.repository.ProductLineRepository;
import com.devcamp.listen.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductRepository productReprository;

	@Autowired
	private ProductLineRepository productLineRepository;

	@GetMapping("/product/all")
	public List<Product> getAllProduct() {
		return productReprository.findAll();
	}

	@GetMapping("/product/byprice/{price}")
	public List<Product> getListProductByPrice(@PathVariable("price") int price) {
		try {
			List<Product> lProduct = productReprository.listProductByBuyPrice(price);
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	// ========================================== product by productLineId ====================================================
	
	/**
	 * @param productLineId
	 * @param page
	 * @return list product by productLineId
	 */
	@GetMapping("/product/byproductlineid/{productLineId}/{page}")
	public List<Product> getListProductByProductLineId(@PathVariable("productLineId") int productLineId,@PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByProductLinePageable(productLineId,PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param productLineId
	 * @return length list product by productLineId
	 */
	@GetMapping("/product/lengthproductlineid/{productLineId}")
	public int getLengthListProductByProductLineId(@PathVariable("productLineId") int productLineId) {
		try {
			int lProduct = productReprository.lengthProductByProductLinePageable(productLineId).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	// ========================================== product by price 0 to price and productLineId ===============================================
	
	@GetMapping("/product/pricedownline/{productLineId}/{buyPrice}/{page}")
	public List<Product> getListProductPriceDownLine(@PathVariable("productLineId") int productLineId,
			@PathVariable("buyPrice") int buyPrice, @PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByPriceDownAndProductLine(buyPrice,productLineId,
					PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/product/lengthpricedownline/{productLineId}/{buyPrice}")
	public int getLengthListProductPriceDownLine(@PathVariable("productLineId") int productLineId,
			@PathVariable("buyPrice") int buyPrice) {
		try {
			int lProduct = productReprository.lengthProductByPriceDownAndProductLine(buyPrice,productLineId).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	// ========================================== product by price to max price and productLineId ===============================================
	
		@GetMapping("/product/priceupline/{productLineId}/{buyPrice}/{page}")
		public List<Product> getListProductPriceUpLine(@PathVariable("productLineId") int productLineId,
				@PathVariable("buyPrice") int buyPrice, @PathVariable("page") int page) {
			try {
				List<Product> lProduct = productReprository.listProductByPriceUpAndProductLine(buyPrice, productLineId,
						PageRequest.of(page, 9));
				return lProduct;
			} catch (Exception e) {
				return null;
			}
		}
		
		@GetMapping("/product/lengthpriceupline/{productLineId}/{buyPrice}")
		public int getLengthListProductPriceUpLine(@PathVariable("productLineId") int productLineId,
				@PathVariable("buyPrice") int buyPrice) {
			try {
				int lProduct = productReprository.lengthProductByPriceUpAndProductLine(buyPrice, productLineId).size();
				return lProduct;
			} catch (Exception e) {
				return 0;
			}
		}
		
		// ========================================== product by vendor and productLineId ===============================================
		
			@GetMapping("/product/vendorline/{productLineId}/{productVendor}/{page}")
			public List<Product> getListProductVendorLine(@PathVariable("productLineId") int productLineId,
					@PathVariable("productVendor") String productVendor, @PathVariable("page") int page) {
				try {
					List<Product> lProduct = productReprository.listProductByVendorAndProductLine(productVendor, productLineId,
							PageRequest.of(page, 9));
					return lProduct;
				} catch (Exception e) {
					return null;
				}
			}
			
			@GetMapping("/product/lengthvendorline/{productLineId}/{productVendor}")
			public int getLengthListProductVendorLine(@PathVariable("productLineId") int productLineId,
					@PathVariable("productVendor") String productVendor) {
				try {
					int lProduct = productReprository.lengthProductByVendorAndProductLine(productVendor, productLineId).size();
					return lProduct;
				} catch (Exception e) {
					return 0;
				}
			}

	// ========================================== product by price from 0 to price, vendor and productLineId ===============================================

	@GetMapping("/product/pricedownvendorline/{productLineId}/{productVendor}/{buyPrice}/{page}")
	public List<Product> getListProductPriceDownVendorLine(@PathVariable("productLineId") int productLineId,
			@PathVariable("productVendor") String productVendor, @PathVariable("buyPrice") int buyPrice,
			@PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByPriceDownVendorProductLine(productVendor, buyPrice,
					productLineId, PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
			
	@GetMapping("/product/lengthpricedownvendorline/{productLineId}/{productVendor}/{buyPrice}/{page}")
	public int getLengthListProductPriceDownVendorLine(@PathVariable("productLineId") int productLineId,
			@PathVariable("productVendor") String productVendor, @PathVariable("buyPrice") int buyPrice) {
		try {
			int lProduct = productReprository.lengthProductByPriceDownVendorProductLine(productVendor, buyPrice,productLineId).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}		
			
	// ========================================== product by price to max price, vendor and productLineId ===============================================

		@GetMapping("/product/priceupvendorline/{productLineId}/{productVendor}/{buyPrice}/{page}")
		public List<Product> getListProductPriceUpVendorLine(@PathVariable("productLineId") int productLineId,
				@PathVariable("productVendor") String productVendor, @PathVariable("buyPrice") int buyPrice,
				@PathVariable("page") int page) {
			try {
				List<Product> lProduct = productReprository.listProductByPriceUpVendorProductLine(productVendor, buyPrice,
						productLineId, PageRequest.of(page, 9));
				return lProduct;
			} catch (Exception e) {
				return null;
			}
		}
				
		@GetMapping("/product/lengthpriceupvendorline/{productLineId}/{productVendor}/{buyPrice}/{page}")
		public int getLengthListProductPriceUpVendorLine(@PathVariable("productLineId") int productLineId,
				@PathVariable("productVendor") String productVendor, @PathVariable("buyPrice") int buyPrice) {
			try {
				int lProduct = productReprository.lengthProductByPriceUpVendorProductLine(productVendor, buyPrice, productLineId).size();
				return lProduct;
			} catch (Exception e) {
				return 0;
			}
		}		
			
			
			
	// ========================================== Vendor =============================================================================
	/**
	 * @param productVendor
	 * @param page
	 * @return list product by vendor with paging
	 */
	@GetMapping("/product/vendor/{productVendor}/{page}")
	public List<Product> getListProductByProductVendor(@PathVariable("productVendor") String productVendor,
			@PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByProductVendor(productVendor,
					PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param productVendor
	 * @param page
	 * @return length of list product by vendor 
	 */
	@GetMapping("/product/lengthvendor/{productVendor}")
	public int getLengthListProductByProductVendor(@PathVariable("productVendor") String productVendor) {
		try {
			int lProduct = productReprository.lenghtProductByProductVendor(productVendor).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	// ========================================== price from 0 to price================================================
	/**
	 * @param buyPrice
	 * @param page
	 * @return get list product by price " from 0 to price " 
	 */
	@GetMapping("/product/pricedown/{buyPrice}/{page}")
	public List<Product> getListProductByBuyPriceDown(@PathVariable("buyPrice") int buyPrice,
			@PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByBuyPriceDown(buyPrice, PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param buyPrice
	 * @return get length list product by price " from 0 to price " 
	 */
	@GetMapping("/product/lengthpricedown/{buyPrice}")
	public int getLengthProductByBuyPriceDown(@PathVariable("buyPrice") int buyPrice) {
		try {
			int lProduct = productReprository.lengthProductByBuyPriceDown(buyPrice).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	// ========================================== price from price to max================================================
	
	/**
	 * @param buyPrice
	 * @param page
	 * @return get list product by price from price to max price
	 */
	@GetMapping("/product/priceup/{buyPrice}/{page}")
	public List<Product> getListProductByPriceUp(@PathVariable("buyPrice") int buyPrice, @PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByBuyPriceUp(buyPrice, PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param buyPrice
	 * @return get length list product by price from price to max price
	 */
	@GetMapping("/product/lengthpriceup/{buyPrice}")
	public int getLengthProductByBuyPriceUp(@PathVariable("buyPrice") int buyPrice) {
		try {
			int lProduct = productReprository.lengthProductByBuyPriceUp(buyPrice).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	// ========================================== vendor and price from price to max ================================================
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @param page
	 * @return get list product by vendor and price from price to max price
	 */
	@GetMapping("/product/vendorpriceup/{productVendor}/{buyPrice}/{page}")
	public List<Product> getListProductByVendorPriceUp(@PathVariable("productVendor") String productVendor,
			@PathVariable("buyPrice") int buyPrice, @PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByVendorPriceUp(productVendor, buyPrice, PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param productVendor
	 * @param buyPrice
	 * @return get length list product by vendor and price from price to max price
	 */
	@GetMapping("/product/lengthvendorpriceup/{productVendor}/{buyPrice}")
	public int getLengthListProductByVendorPriceUp(@PathVariable("productVendor") String productVendor,
			@PathVariable("buyPrice") int buyPrice) {
		try {
			int lProduct = productReprository.lengthProductByVendorPriceUp(productVendor, buyPrice).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	// ========================================== vendor and price from 0 to price ================================================
	@GetMapping("/product/vendorpricedown/{productVendor}/{buyPrice}/{page}")
	public List<Product> getListProductByVendorPriceDown(@PathVariable("productVendor") String productVendor,
			@PathVariable("buyPrice") int buyPrice, @PathVariable("page") int page) {
		try {
			List<Product> lProduct = productReprository.listProductByVendorPriceDown(productVendor, buyPrice, PageRequest.of(page, 9));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}

	
	@GetMapping("/product/lengthvendorpricedown/{productVendor}/{buyPrice}")
	public int getLengthListProductByVendorPriceDown(@PathVariable("productVendor") String productVendor,
			@PathVariable("buyPrice") int buyPrice) {
		try {
			int lProduct = productReprository.lengthProductByVendorPriceDown(productVendor, buyPrice).size();
			return lProduct;
		} catch (Exception e) {
			return 0;
		}
	}
	
	//===============================================================================================================================
	
	/**
	 * @param id
	 * @return get product by product Id
	 */
	@GetMapping("/product/details/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		if (productReprository.findById(id).isPresent())
			return productReprository.findById(id).get();
		else
			return null;
	}

//	@GetMapping("/product/byproductlineid/{id}")
//	public List<Product> getProductByProductLineId(@PathVariable("id") Long id){
//		Optional<ProductLine> productLineData = productLineRepository.findById(id);
//		if(productLineData.isPresent()) {
//			return productLineData.get().getProducts();
//		}else {
//			return null;
//		}
//	}

	@GetMapping("/product/limit6")
	public List<Product> getListProductLimit6() {
		try {
			List<Product> lProduct = productReprository.listProductlimit6();
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/product/pageable/{page}")
	public List<Product> listProductPageable(@PathVariable("page") int page) {
		try {
			int number = 9;
			List<Product> lProduct = productReprository.listProductPageable(PageRequest.of(page, number));
			// Page<Product> lProduct1 = productReprository.findAll(PageRequest.of(page,
			// number));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/product/allproduct")
	public List<Product> listProduct() {
		try {
			List<Product> lProduct = productReprository.listProduct();
			// Page<Product> lProduct1 = productReprository.findAll(PageRequest.of(page,
			// number));
			return lProduct;
		} catch (Exception e) {
			return null;
		}
	}
	
	

	@GetMapping("/product/length")
	public int getLengthProduct() {
		return productReprository.findAll().size();
	}

	@PostMapping("/product/create/{id}")
	public ResponseEntity<Object> createProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		Optional<ProductLine> productLineData = productLineRepository.findById(id);
		if (productLineData.isPresent()) {
			Product newProduct = new Product();
			newProduct.setProductCode(product.getProductCode());
			newProduct.setProductName(product.getProductName());
			newProduct.setProductDescription(product.getProductDescription());
			newProduct.setProductVendor(product.getProductVendor());
			newProduct.setProductImage(product.getProductImage());
			newProduct.setQuantityInStock(product.getQuantityInStock());
			newProduct.setBuyPrice(product.getBuyPrice());

			ProductLine _productLine = productLineData.get();
			newProduct.setProductL(_productLine);

			Product saveProduct = productReprository.save(newProduct);

			try {
				return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Create specified Product: " + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Product:");
		}
	}

	@PutMapping("/product/update/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = productReprository.findById(id);
		if (productData.isPresent()) {
			Product newProduct = productData.get();
			newProduct.setProductCode(product.getProductCode());
			newProduct.setProductName(product.getProductName());
			newProduct.setProductDescription(product.getProductDescription());
			newProduct.setProductImage(product.getProductImage());
			newProduct.setProductVendor(product.getProductVendor());
			newProduct.setQuantityInStock(product.getQuantityInStock());
			newProduct.setBuyPrice(product.getBuyPrice());
			newProduct.setProductL(product.getProductL());
			Product saveProduct = productReprository.save(newProduct);

			return new ResponseEntity<>(saveProduct, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified product: "+id + "  for update.");
		}
	}

	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<Object> deleteProductById(@PathVariable("id") Long id) {
		try {
			productReprository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
