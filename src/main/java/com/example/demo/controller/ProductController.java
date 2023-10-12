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
import com.example.demo.service.ProductService;


@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody CategoryDTO categoryDTO) {   //add product by category 
		productService.addProduct(categoryDTO);		
		return  new ResponseEntity<String>(HttpStatus.CREATED);
				
	}
	
	@GetMapping("/getAllProduct/{id}")
	public List<ProductDTO> getAllProduct(@PathVariable("id") Long id){     //To get all products by categoryId
		return productService.getAllProduct(id);
	}
	
	@GetMapping("/getActiveProducts/{id}")  
	public List<ProductDTO> getActiveProducts(@PathVariable("id") Long id){  //to get the active products by categoryId
		return productService.getActiveProducts(id);
	}


	@GetMapping("/getProductBytitle/{title}")
	public ProductDTO getProductBytitle(@PathVariable("title") String title) {  //get active product by  product title
		return productService.getProductByProductName(title);
	}
	@PutMapping("/updateProductByCategory")
	public ResponseEntity<String> updateProductByCategory(@RequestBody CategoryDTO categoryDTO) {
		productService.updateProductInCategories(categoryDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@DeleteMapping("/inactiveProductById/{id}")
	public String inactiveProductById(@PathVariable("id") Long id) {  //to inactive product by productId
		 productService.inactiveProductById(id);
		return "Product Successfully Marked As Inactive";
	}
	
	

}
