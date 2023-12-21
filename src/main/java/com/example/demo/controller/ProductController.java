package com.example.demo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody CategoryDTO categoryDTO) { //add product by category 
		if (categoryDTO.getId() == null) {
            LOGGER.error("Category ID is required to add products.");
            return "give the categoryid"; 
        }

		productService.addProduct(categoryDTO);		
		return  "Product Added Successfully";
				
	}
	
	@GetMapping("/getAllProduct/{id}")
	public List<ProductDTO> getAllProduct(@PathVariable("id") Long id){     //To get all products by categoryId
		return productService.getAllProduct(id);
	}
	@GetMapping("/getAllProduct")
	public List<ProductDTO> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/getActiveProducts/{id}")  
	public List<ProductDTO> getActiveProducts(@PathVariable("id") Long id){  //to get the active products by categoryId
		return productService.getActiveProducts(id);
	}


//	@GetMapping("/getProductBytitle/{title}")
//	public String getProductBytitle(@PathVariable("title") String title) {  //get active product by  product title
//		return productService.getProductByProductNameToJson(title);
//	}
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
//	@GetMapping("/getProductWithCategories")
//	public List<ProductResponseDTO> getProductWithCategories(){
//		return productService.getAllProductsWithCategories();
//	}
	
	

}
