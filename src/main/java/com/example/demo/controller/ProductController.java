package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Status;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody CategoryDTO categoryDTO) {
		//LOGGER.info("===================inside API check ==================="); 
		productService.addProduct(categoryDTO);		
		return  new ResponseEntity<String>(HttpStatus.CREATED);
		
			
	}
	
	
//	@GetMapping("/getAllProduct")
//	public List<Product> getAllProduct(){
//		//LOGGER.info("===================inside API check ===================");
//		return productService.getAllProduct();
//	}
	@GetMapping("/getActiveProducts/{id}")  
	public List<ProductDTO> getActiveProducts(@PathVariable("id") Long id){
		return productService.getActiveProducts(id);
	}
	@GetMapping("/getProductByid/{categoryid}")
	public ProductDTO getProductByCategoryid(@PathVariable("categoryid") Long categoryid) {
		
		return productService.getProductByCategoryId(categoryid); 
	}
//	@GetMapping("/getProductByProductid/{productid}")
//	public ProductDTO getProductByProductid(@PathVariable("productid") Long productid) {
//		return productService.getProductByProductId(productid);
//	}
	@GetMapping("/getProductBytitle/{title}")
	public ProductDTO getProductBytitle(@PathVariable("title") String title) {
		return productService.getProductByProductName(title);
	}
	@PutMapping("/updateProduct")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
		productService.updateProduct(productDTO);
		return new ResponseEntity<ProductDTO>(HttpStatus.OK);
	}
	@DeleteMapping("/delete/{productid}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productid") Long productid) {
		 String delete =productService.deleteProduct(productid);
		return new ResponseEntity<String>(delete,HttpStatus.OK);
	}
	
	

}
