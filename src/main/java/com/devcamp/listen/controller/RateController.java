package com.devcamp.listen.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

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

import com.devcamp.listen.model.Customer;
import com.devcamp.listen.model.OrderDetail;
import com.devcamp.listen.model.Product;
import com.devcamp.listen.model.Rate;

import com.devcamp.listen.repository.CustomerRepository;
import com.devcamp.listen.repository.OrderDetailsRepository;
import com.devcamp.listen.repository.ProductRepository;
import com.devcamp.listen.repository.RateRepository;


@CrossOrigin
@RestController
@RequestMapping("/")
public class RateController {
	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@GetMapping("/rate/listratebycustomerid/{customerId}")
	public List<Rate> getListRateByCustomerId(@PathVariable("customerId") int customerId) {
		try {
			List<Rate> lRate = rateRepository.listRateByCustomerId(customerId);
			return lRate;
		} catch (Exception e) {
			return null;
		}
	}
	

	@GetMapping("/rate/listratebyproductid/{productId}")
	public List<Rate> getListRateByProductId(@PathVariable("productId") int productId) {
		try {
			List<Rate> lRate = rateRepository.listRateByProductId(productId);
			return lRate;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/rate/ratebyorderdetailId/{orderdetailId}")
	public Rate getRateByOrderDetailId(@PathVariable("orderdetailId") int orderdetailId) {
		try {
			Rate lRate = rateRepository.rateByOrderDetailId(orderdetailId);
			return lRate;
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/rate/create/{orderDetailId}/{productId}/{customerId}")
	public ResponseEntity<Object> createRate(@Valid @RequestBody Rate rate, @PathVariable("orderDetailId") Long orderDetailId, 
			@PathVariable("productId") Long productId, @PathVariable("customerId") Long customerId) {
		
		Optional<OrderDetail> orderDetailData = orderDetailsRepository.findById(orderDetailId);
		Optional<Product> productData = productRepository.findById(productId);
		Optional<Customer> customerData = customerRepository.findById(customerId);
		
		if(orderDetailData.isPresent() & productData.isPresent() & customerData.isPresent()) {
			
			try {
				Rate newRate = new Rate();
				newRate.setRateScore(rate.getRateScore());
				newRate.setComments(rate.getComments());
				newRate.setCreateDate(new Date());
				newRate.setUpdateDate(new Date());
				
				OrderDetail _orderDetail = orderDetailData.get();
				Product _product = productData.get();
				Customer _customer = customerData.get();
				
				newRate.setOrderdetails(_orderDetail);
				newRate.setProduct(_product);
				newRate.setCustomer(_customer);
				
				Rate _rate = rateRepository.save(newRate);
				return new ResponseEntity<>(_rate, HttpStatus.CREATED);

			} catch (Exception e) {
				System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Create specified Rate: " + e.getCause().getCause().getMessage());
			}
		}
		else {
			return ResponseEntity.badRequest().body("Failed to create Rate");
		}
		
	}


	@PutMapping("/rate/update/{id}")
	public ResponseEntity<Object> updateRate(@PathVariable("id") long id, @RequestBody Rate rate) {
		Optional<Rate> rateData = rateRepository.findById(id);
		if (rateData.isPresent()) {
			Rate newRate = rateData.get();
			
			newRate.setRateScore(rate.getRateScore());
			newRate.setComments(rate.getComments());
			newRate.setUpdateDate(new Date());
			
			
			Rate saveRate = rateRepository.save(newRate);
			try {
				return new ResponseEntity<>(saveRate, HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Update specified Rate:" + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified Rate: " + id + "  for update.");
		}
	}
	
	

	

	@DeleteMapping("/rate/delete/{id}")
	public ResponseEntity<Object> deleteRateById(@PathVariable("id") Long id) {
		try {
			rateRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@GetMapping("/rate/details/{id}")
	public ResponseEntity<Object> getRateById(@PathVariable("id") long id) {
		if (rateRepository.findById(id).isPresent())
			//return customerRepository.findById(id).get();
			return new ResponseEntity<>(rateRepository.findById(id).get(), HttpStatus.OK);
		else
			return ResponseEntity.badRequest().body("Failed to get specified Customer: " + id);
	}
	
	


	@GetMapping("/rate/all")
	public List<Rate> getAllRate() {
		return rateRepository.findAll();
	}
	
	
	
	
	
	
}
