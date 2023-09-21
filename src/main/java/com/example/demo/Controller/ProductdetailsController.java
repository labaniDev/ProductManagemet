package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductdetailsDTO;
import com.example.demo.service.ProductdetailsService;

@RestController
@RequestMapping("/api/productdetails")
public class ProductdetailsController {
	
	@Autowired
	ProductdetailsService productdetailsService;
	
	@PostMapping("/addProductdetails")
	public ResponseEntity<ProductdetailsDTO> addProductdetails(@RequestBody ProductdetailsDTO productdetailsDTO) {
		productdetailsService.addProductdetails(productdetailsDTO);
		return new ResponseEntity<ProductdetailsDTO>(HttpStatus.CREATED);
	}

}
