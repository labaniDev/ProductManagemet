package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductdetailsDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdetails;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.ProductdetailsRepo;

@Service
public class ProductdetailsService {
	
	@Autowired
	ProductdetailsRepo productdetailsRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProductRepo productRepo;
	
	public void addProductdetails(ProductdetailsDTO productdetailsDTO) {
		Optional<Product> existingProduct=productRepo.findById(productdetailsDTO.getProductid());
		if(existingProduct.isPresent()) {
			Product products=existingProduct.get();
			Productdetails productDetails=new Productdetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  productDetails.setCreated_at(dtf.format(now));
		  productDetails.setUpdated_at(dtf.format(now));
		  productDetails.setProduct(products);
		  
		  productdetailsRepo.save(productDetails);
	}

}
}
