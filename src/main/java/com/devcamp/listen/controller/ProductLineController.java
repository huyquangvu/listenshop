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

import com.devcamp.listen.model.ProductLine;
import com.devcamp.listen.repository.ProductLineRepository;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ProductLineController {
	@Autowired
	private ProductLineRepository productLineRepository;
	
	@PostMapping("/productline/create")
	public ResponseEntity<Object> createProductLine(@Valid @RequestBody ProductLine productLine){
		try {
			ProductLine _productLine = productLineRepository.save(productLine);
			
			return new ResponseEntity<>(_productLine, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("+++++++++++++++++++++::::: "+e.getCause().getCause().getMessage());
			return ResponseEntity.unprocessableEntity().body("Failed to Create specified ProductLine: "+e.getCause().getCause().getMessage());
		}
	}
	
	@PutMapping("/productline/update/{id}")
	public ResponseEntity<Object> updateProductLine(@PathVariable("id") Long id, @RequestBody ProductLine productLine){
		Optional<ProductLine> productLineData = productLineRepository.findById(id);
		if (productLineData.isPresent()) {
			ProductLine newProducLine = productLineData.get();
			newProducLine.setProductLine(productLine.getProductLine());		
			newProducLine.setDescription(productLine.getDescription());	
			
			ProductLine saveProductLine = productLineRepository.save(newProducLine);
			
			try {
				return new ResponseEntity<>(saveProductLine, HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().body("Failed to Update specified ProductLine:"+e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to get specified ProductLine: "+id + "  for update.");
		}
	}
	
	@DeleteMapping("/productline/delete/{id}")
	public ResponseEntity<Object> deleteProductLineById(@PathVariable("id") Long id){
		try {
			productLineRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/productline/details/{id}")
	public ProductLine getProductLineById(@PathVariable("id") Long id) {
		if (productLineRepository.findById(id).isPresent())
			return productLineRepository.findById(id).get();
		else
			return null;
	}
	
	
	@GetMapping("/productline/all")
	public List<ProductLine> getAllProductLine(){
		return productLineRepository.findAll();
	}
}
