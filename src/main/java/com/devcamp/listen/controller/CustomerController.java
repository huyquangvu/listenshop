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
import com.devcamp.listen.model.User;
import com.devcamp.listen.repository.CustomerRepository;
import com.devcamp.listen.repository.UserRepository;
import com.devcamp.listen.service.ExcelExporterCustomer;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * create customer
	 * @param customer
	 * @return new customer
	 */
	@PostMapping("/customer/create/{userid}")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer, @PathVariable("userid") Long userid) {
		Optional<User> userData = userRepository.findById(userid);
		if(userData.isPresent() ) {
			Customer newCustomer = new Customer();
			newCustomer.setLastName(customer.getLastName());
			newCustomer.setFirstName(customer.getFirstName());
			newCustomer.setFullName(customer.getFullName());
			newCustomer.setPhoneNumber(customer.getPhoneNumber());
			newCustomer.setAddress(customer.getAddress());
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setCity(customer.getCity());
			newCustomer.setState(customer.getState());
			newCustomer.setBirthday(customer.getBirthday());
			
			User _user = userData.get();
			newCustomer.setUser(_user);
			
			Customer _customer = customerRepository.save(newCustomer);
			try {
				return new ResponseEntity<>(_customer, HttpStatus.CREATED);

			} catch (Exception e) {
				System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Create specified Customer: " + e.getCause().getCause().getMessage());
			}
		}
		else {
			return ResponseEntity.badRequest().body("Failed to create Customer");
		}
		
	}

	/**
	 * update customer by customerId
	 * @param id
	 * @param customer
	 * @return customer was updated
	 */
	@PutMapping("/customer/update/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		Optional<Customer> customerData = customerRepository.findById(id);
		if (customerData.isPresent()) {
			Customer newCustomer = customerData.get();
			newCustomer.setLastName(customer.getLastName());
			newCustomer.setFirstName(customer.getLastName());
			newCustomer.setFullName(customer.getFullName());
			newCustomer.setPhoneNumber(customer.getPhoneNumber());
			newCustomer.setAddress(customer.getAddress());
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setCity(customer.getCity());
			newCustomer.setState(customer.getState());
			newCustomer.setBirthday(customer.getBirthday());

			Customer saveCustomer = customerRepository.save(newCustomer);

			try {
				return new ResponseEntity<>(saveCustomer, HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Update specified Customer:" + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified Customer: " + id + "  for update.");
		}
	}
	
	@GetMapping("/customer/customerbyphone/{phoneNumber}")
	public List<Customer> getCustomerByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		try {
			List<Customer> lCustomer = customerRepository.findCustomerByPhoneNumber(phoneNumber);
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}

	
	/**
	 * delete customer by customerId
	 * @param id
	 * @return
	 */
	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") long id) {
		try {
			customerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * get customer by id
	 * @param id
	 * @return
	 */
	@GetMapping("/customer/details/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable("id") long id) {
		if (customerRepository.findById(id).isPresent())
			//return customerRepository.findById(id).get();
			return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK);
		else
			return ResponseEntity.badRequest().body("Failed to get specified Customer: " + id);
	}
	
	

	/**
	 * get customer by first name
	 * @param firstName
	 * @return list of customer 
	 */
	@GetMapping("/customer/byfname/{fname}")
	public List<Customer> getCustomerByFirstName(@PathVariable("fname") String firstName) {
		try {
			List<Customer> lCustomer = customerRepository.findCustomerByFirstNameDesc(firstName);
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	

	@GetMapping("/customer/bylname/{lname}")
	public List<Customer> getCustomerByLastName(@PathVariable("lname") String lastName) {
		try {
			List<Customer> lCustomer = customerRepository.findCustomerByLastNameDesc(lastName);
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/customer/bycountryName/{countryName}")
	public List<Integer> getCustomerByCountryName(@PathVariable("countryName") List<String> countryName) {
		try {
			int index ;
			List<Integer> numberCus = new ArrayList<Integer>();
			List<Customer> lCustomer = new ArrayList<Customer>();
			for(index = 0; index < countryName.size(); index++) {
				lCustomer = customerRepository.ListCustomerByCountryLike(countryName.get(index));
				numberCus.add(lCustomer.size());
			}

			return numberCus;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/customer/bycity/{city}")
	public List<Customer> getCustomerByCity(@PathVariable("city") String city) {
		try {
			List<Customer> lCustomer = customerRepository.findCustomerByCityLike(city, PageRequest.of(0, 3));
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/customer/bystate/{state}")
	public List<Customer> getCustomerByState(@PathVariable("state") String state) {
		try {
			List<Customer> lCustomer = customerRepository.findCustomerByStateLike(state, PageRequest.of(0, 3));
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/customer/bycountry/{country}")
	public List<Customer> getCustomerByCountry(@PathVariable("country") String country) {
		try {
			List<Customer> lCustomer = customerRepository.findCustomerByCountryLike(country, PageRequest.of(0, 3));
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	

	@GetMapping("/customer/countrynull")
	public List<Customer> getCustomerCountryNull() {
		try {
			List<Customer> lCustomer = customerRepository.getCustomerCountryNullDesc();
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping("/customer/updatecountry/{country}")
	public ResponseEntity<Object> updateCustomerCountryNull(@PathVariable("country") String country) {
		try {
			int lCustomer = customerRepository.updateCountry(country);
			return ResponseEntity.ok().body("Update thành công trên : " + lCustomer + "  bả ghi.");
			//return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@GetMapping("/customer/all")
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
	
	

	
	@GetMapping("/export/customers/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Customer> customer = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customer::add);
		ExcelExporterCustomer excelExporter = new ExcelExporterCustomer(customer);
		excelExporter.export(response);
	}
	
	
	
	@GetMapping("/customer/customerplatinum")
	public List<Object> getCustomerPlatinum() {
		try {
			List<Object> lCustomer = customerRepository.listCustomerPlatinum();
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/customer/customergold")
	public List<Object> getCustomerGold() {
		try {
			List<Object> lCustomer = customerRepository.listCustomerGold();
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/customer/customersilver")
	public List<Object> getCustomerSilver() {
		try {
			List<Object> lCustomer = customerRepository.listCustomerSilver();
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/customer/customervip")
	public List<Object> getCustomerVip() {
		try {
			List<Object> lCustomer = customerRepository.listCustomerVip();
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/customer/customertotalorder/{totalOrder}")
	public List<Object> getCustomerByTotalOrder(@PathVariable("totalOrder") int totalOrder) {
		try {
			List<Object> lCustomer = customerRepository.listCustomerByTotalOrder(totalOrder);
			return lCustomer;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/export/customertotalorder/excel/{totalOrder}")
	public void exportToExcelByTotalOrder(HttpServletResponse response, @PathVariable("totalOrder") int totalOrder) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Customer> customer = customerRepository.listCustomerByTotalOrderExport(totalOrder);
		ExcelExporterCustomer excelExporter = new ExcelExporterCustomer(customer);
		excelExporter.export(response);
	}
	
	@GetMapping("/export/customerplatinum/excel")
	public void exportToExcelPlatinum(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Customer> customer = customerRepository.listCustomerPlatinumExport();
		ExcelExporterCustomer excelExporter = new ExcelExporterCustomer(customer);
		excelExporter.export(response);
	}
	
	@GetMapping("/export/customergold/excel")
	public void exportToExcelGold(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Customer> customer = customerRepository.listCustomerGoldExport();
		ExcelExporterCustomer excelExporter = new ExcelExporterCustomer(customer);
		excelExporter.export(response);
	}
	
	@GetMapping("/export/customersilver/excel")
	public void exportToExcelSilver(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Customer> customer = customerRepository.listCustomerSilverExport();
		ExcelExporterCustomer excelExporter = new ExcelExporterCustomer(customer);
		excelExporter.export(response);
	}
	
	
	@GetMapping("/export/customervip/excel")
	public void exportToExcelVip(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Customer> customer = customerRepository.listCustomerVipExport();
		ExcelExporterCustomer excelExporter = new ExcelExporterCustomer(customer);
		excelExporter.export(response);
	}
	
}
