package com.devcamp.listen.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.devcamp.listen.model.Order;
import com.devcamp.listen.model.User;
import com.devcamp.listen.repository.CustomerRepository;
import com.devcamp.listen.repository.IMyOrder;
import com.devcamp.listen.repository.IOrder;
import com.devcamp.listen.repository.IOrderTotalMoneyWeek;
import com.devcamp.listen.repository.OrderRepository;
import com.devcamp.listen.service.ExcelExporterCustomer;
import com.devcamp.listen.service.ExcelExporterOrder;
import com.devcamp.listen.service.ExcelExporterOrderTotalMoneyDay;
import com.devcamp.listen.service.ExcelExporterOrderTotalMoneyWeek;

@CrossOrigin
@RestController
@RequestMapping("/")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("order/orderdate/{orderDate}")
	public List<Order> getListOrderByOrderDate(@PathVariable("orderDate") Date orderDate) {
		try {
			// Date newDate = new Date(orderDate);
			List<Order> lOrder = orderRepository.listOrderByOrderDate(orderDate, PageRequest.of(0, 3));
			return lOrder;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/order/all")
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	@GetMapping("/order/details/{id}")
	public Order getOrderById(@PathVariable("id") Long id) {
		if (orderRepository.findById(id).isPresent())
			return orderRepository.findById(id).get();
		else
			return null;
	}

	@GetMapping("/order/bycustomerid/{id}")
	public Order findOrderByCustomerId(@PathVariable("id") Long id) {
		Order orderData = orderRepository.findOrderByCustomerId(id);
		if (orderData != null) {
			return orderData;
		} else {
			return null;
		}
	}

	@PostMapping("/order/create/{id}")
	public ResponseEntity<Object> createOrder(@Valid @PathVariable("id") Long id, @RequestBody Order order) {
		Optional<Customer> customerData = customerRepository.findById(id);
		if (customerData.isPresent()) {
			Order newOrder = new Order();

			newOrder.setOrderDate(new Date());
			newOrder.setRequiredDate(new Date());
			newOrder.setShippedDate(order.getShippedDate());
			newOrder.setStatus(order.getStatus());
			newOrder.setComments(order.getComments());

			Customer _customer = customerData.get();
			newOrder.setCustomer(_customer);
			Order saveOrder = orderRepository.save(newOrder);
			try {
				return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Create specified Order: " + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified Order:");
		}
	}

	@PutMapping("/order/update/{orderId}/{customerId}")
	public ResponseEntity<Object> updateOrder(@PathVariable("orderId") Long orderId,
			@PathVariable("customerId") Long customerId, @RequestBody Order order) {
		Optional<Order> orderData = orderRepository.findById(orderId);
		Optional<Customer> customerData = customerRepository.findById(customerId);

		if (orderData.isPresent()) {
			Order newOrder = orderData.get();
			newOrder.setOrderDate(order.getOrderDate());
			newOrder.setRequiredDate(order.getRequiredDate());
			newOrder.setShippedDate(order.getShippedDate());
			newOrder.setStatus(order.getStatus());
			newOrder.setComments(order.getComments());

			Customer _customer = customerData.get();
			newOrder.setCustomer(_customer);
			Order saveOrder = orderRepository.save(newOrder);
			return new ResponseEntity<>(saveOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/order/delete/{id}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable("id") Long id) {
		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/export/orders/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Order> order = new ArrayList<Order>();
		orderRepository.findAll().forEach(order::add);
		ExcelExporterOrder excelExporter = new ExcelExporterOrder(order);
		excelExporter.export(response);
	}

	@GetMapping("/order/datereport/{dateBegin}/{dateEnd}")
	public List<Object> getOrderByDate(@PathVariable("dateBegin") int dateBegin, @PathVariable("dateEnd") int dateEnd) {
		try {
			List<Object> listOrder = orderRepository.listOrderbydate(dateBegin, dateEnd);
//			ExcelExporterOrder excelExporter = new ExcelExporterOrder(listOrder);
			return listOrder;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/order/datereportexcell/{dateBegin}/{dateEnd}")
	public void getOrderByDateExportExcel(HttpServletResponse response, @PathVariable("dateBegin") int dateBegin,
			@PathVariable("dateEnd") int dateEnd) {
		try {
			response.setContentType("application/octet-stream");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
			response.setHeader(headerKey, headerValue);
			List<IOrder> listOrder = orderRepository.listOrderByDateExport(dateBegin, dateEnd);
			ExcelExporterOrderTotalMoneyDay excelExporter = new ExcelExporterOrderTotalMoneyDay(listOrder);
			excelExporter.export(response);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@GetMapping("/order/weekreport/{year}")
	public List<Object> getOrderByYear(@PathVariable("year") int year) {
		try {
			List<Object> listOrder = orderRepository.listOrderbyweek(year);
			return listOrder;
		} catch (Exception e) {
			return null;
		}
	}
//	
//	@GetMapping("/user/userbyphonenumber/{phoneNumber}")
//	public ResponseEntity<Object> getUserByPhoneNumber(@Valid @PathVariable("phoneNumber") String phoneNumber) {
//		User UserData = userRepository.findUserbyPhoneNumber(phoneNumber);
//		if (UserData != null) {
//			try {
//				return new ResponseEntity<>(UserData, HttpStatus.OK);
//			} catch (Exception e) {
//				return ResponseEntity.unprocessableEntity()
//						.body("Failed to Update specified UserData:" + e.getCause().getCause().getMessage());
//			}
//		} else {
//			return ResponseEntity.badRequest().body("Failed to get specified UserData");
//		}
//	}

	@GetMapping("/order/listorderbycustomerid/{customerId}")
	public List<IMyOrder> getListOrderByCustomerId(@PathVariable("customerId") long customerId) {
		try {
			List<IMyOrder> listOrder = orderRepository.listMyOrderByCustomerId(customerId);
			return listOrder;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/order/yearreportexcell/{year}")
	public void getOrderByYearExportExcel(HttpServletResponse response, @PathVariable("year") int year) {
		try {
			response.setContentType("application/octet-stream");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
			response.setHeader(headerKey, headerValue);
			List<IOrderTotalMoneyWeek> listOrder = orderRepository.listOrderbyweekExport(year);
			ExcelExporterOrderTotalMoneyWeek excelExporter = new ExcelExporterOrderTotalMoneyWeek(listOrder);
			excelExporter.export(response);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
