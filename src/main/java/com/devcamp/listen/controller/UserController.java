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
public class UserController {
	@Autowired
	private UserRepository userRepository;

	/**
	 * create customer
	 * @param customer
	 * @return new customer
	 */
	@PostMapping("/user/create")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody User user) {
		try {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(user.getPassword());
			newUser.setLevel(user.getLevel());
			newUser.setFullname(user.getFullname());
			newUser.setPhoneNumber(user.getPhoneNumber());
		
			User _user = userRepository.save(newUser);
			return new ResponseEntity<>(_user, HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: " + e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity()
					.body("Failed to Create specified User: " + e.getCause().getCause().getMessage());
		}
	}

	/**
	 * update customer by customerId
	 * @param id
	 * @param customer
	 * @return customer was updated
	 */
	@PutMapping("/user/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") long id, @RequestBody User customer) {
		Optional<User> customerData = userRepository.findById(id);
		if (customerData.isPresent()) {
			User newCustomer = customerData.get();


			User saveCustomer = userRepository.save(newCustomer);

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
	
	

	
	/**
	 * delete customer by customerId
	 * @param id
	 * @return
	 */
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
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
	@GetMapping("/user/details/{id}")
	public ResponseEntity<Object> getUSerById(@PathVariable("id") long id) {
		if (userRepository.findById(id).isPresent())
			//return customerRepository.findById(id).get();
			return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
		else
			return ResponseEntity.badRequest().body("Failed to get specified Customer: " + id);
	}
	
	@GetMapping("/user/userbyusername/{username}/{password}")
	public ResponseEntity<Object> getUserByUsername(@Valid @PathVariable("username") String username, @PathVariable("password") String password) {
		User UserData = userRepository.findUserbyUserName(username, password);
		if (UserData != null) {
			try {
				return new ResponseEntity<>(UserData, HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Update specified UserData:" + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified UserData");
		}
	}
	
	@GetMapping("/user/userbyphonenumber/{phoneNumber}")
	public ResponseEntity<Object> getUserByPhoneNumber(@Valid @PathVariable("phoneNumber") String phoneNumber) {
		User UserData = userRepository.findUserbyPhoneNumber(phoneNumber);
		if (UserData != null) {
			try {
				return new ResponseEntity<>(UserData, HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity()
						.body("Failed to Update specified UserData:" + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified UserData");
		}
	}



	@GetMapping("/user/all")
	public List<User> getAllCustomer() {
		return userRepository.findAll();
	}
	
	

	
	
	
}
