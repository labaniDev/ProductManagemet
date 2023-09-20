package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
		productService.addProduct(productDTO);
		return  new ResponseEntity<ProductDTO>(HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllProduct")
	public List<ProductDTO> getAllProduct(){
		return productService.getAllProduct();
	}
	@GetMapping("/getProductByid/{productid}")
	public ResponseEntity<ProductDTO> getProductByid(@PathVariable("productid") Long productid) {
		ProductDTO productdto=productService.getProductByid(productid);
		return new ResponseEntity<ProductDTO>(productdto,HttpStatus.OK);
	}
	@PutMapping("/updateProduct")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
		productService.updateProduct(productDTO);
		return new ResponseEntity<ProductDTO>(HttpStatus.OK);
	}
	@DeleteMapping("/delete/{productid}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("productid") Long productid) {
		productService.deleteProduct(productid);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}
	
	

}
