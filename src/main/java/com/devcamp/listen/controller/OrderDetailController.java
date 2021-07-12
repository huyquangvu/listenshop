package com.devcamp.listen.controller;

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

import com.devcamp.listen.model.Order;
import com.devcamp.listen.model.OrderDetail;
import com.devcamp.listen.model.Product;
import com.devcamp.listen.repository.OrderDetailsRepository;
import com.devcamp.listen.repository.OrderRepository;
import com.devcamp.listen.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class OrderDetailController {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private ProductRepository productRepository;

	public OrderDetailController(OrderDetailsRepository orderDetailsRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
	}

	@GetMapping("/orderdetail/all")
	public List<OrderDetail> getAllOrderDetail() {
		return orderDetailsRepository.findAll();
	}

	@GetMapping("/orderdetail/details/{id}")
	public OrderDetail getOrderDetailById(@PathVariable("id") Long id) {
		if (orderDetailsRepository.findById(id).isPresent())
			return orderDetailsRepository.findById(id).get();
		else
			return null;
	}

//	@GetMapping("/orderdetail/byorderid/{id}")
//	public List<OrderDetail> getOrderDetailByOrderId(@PathVariable("id") Long id){
//		Optional<Order> orderData = orderRepository.findById(id);
//		if(orderData.isPresent()) {
//			return orderData.get().getOrderDetails();
//		}else {
//			return null;
//		}
//	}

//	@GetMapping("/orderdetail/byproductid/{id}")
//	public List<OrderDetail> getOrderDetailByProductId(@PathVariable("id") Long id){
//		Optional<Product> productData = productRepository.findById(id);
//		if(productData.isPresent()) {
//			return productData.get().getOrderDetails();
//		}else {
//			return null;
//		}
//	}

	@PostMapping("/orderdetail/create/{orderId}/{productId}")
	public ResponseEntity<Object> createOrderDetail(@Valid @PathVariable("orderId") Long orderId,
			@Valid @PathVariable("productId") Long productId, @RequestBody OrderDetail orderDetail) {
		Optional<Order> orderData = orderRepository.findById(orderId);
		Optional<Product> productData = productRepository.findById(productId);
		if (orderData.isPresent() & productData.isPresent()) {
			OrderDetail newOrderDetail = new OrderDetail();

			newOrderDetail.setQuantityOrder(orderDetail.getQuantityOrder());

			Order _order = orderData.get();
			Product _product = productData.get();
			newOrderDetail.setPriceEach(_product.getBuyPrice());
			
			newOrderDetail.setOrder(_order);
			newOrderDetail.setProduct(_product);
			OrderDetail saveOrderDetail = orderDetailsRepository.save(newOrderDetail);

			try {
				return new ResponseEntity<>(saveOrderDetail, HttpStatus.CREATED);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Create specified OrderDetail: " + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified OrderDetail:");
		}
	}

	@PutMapping("/orderdetail/update/{orderDetailId}/{orderId}/{productId}")
	public ResponseEntity<Object> updateOrderDetail(@PathVariable("orderDetailId") Long orderDetailId,
			@PathVariable("orderId") Long orderId, @PathVariable("productId") Long productId, @RequestBody OrderDetail orderDetail) {
		Optional<OrderDetail> orderDetailData = orderDetailsRepository.findById(orderDetailId);
		Optional<Order> orderData = orderRepository.findById(orderId);
		Optional<Product> productData = productRepository.findById(productId);
		if (orderDetailData.isPresent() & orderData.isPresent() & productData.isPresent()) {
			OrderDetail newOrderDetail = orderDetailData.get();
			
			
			newOrderDetail.setQuantityOrder(orderDetail.getQuantityOrder());
			
			Order _order = orderData.get();
			Product _product = productData.get();
			newOrderDetail.setOrder(_order);
			newOrderDetail.setProduct(_product);
			
			OrderDetail saveOrder = orderDetailsRepository.save(newOrderDetail);

			return new ResponseEntity<>(saveOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orderdetail/delete/{id}")
	public ResponseEntity<Object> deleteOrderDetailById(@PathVariable("id") Long id) {
		try {
			orderDetailsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
